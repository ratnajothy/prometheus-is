package com.wso2.service;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.exporter.PushGateway;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContext implements ServletContextListener {

    private static String host;
    private static int port;
    private static CollectorRegistry pushRegistry = new CollectorRegistry();

    public static HTTPServer server;
    public static Gauge loginGauge;
    public static PushGateway pushGateway;

    @Override
    public void contextInitialized(ServletContextEvent arg0) {

        setProperties(getClass().getClassLoader().getResource("prometheus.properties").getPath());
        try {
            server = new HTTPServer(new InetSocketAddress(host, port), pushRegistry);
            loginGauge = Gauge.build().name("login")
                    .labelNames("tenantDomain", "serviceProvider", "authenticationSuccess")
                    .help("No. of logins").register(pushRegistry);
            pushGateway = new PushGateway(host + ":" + port);
            pushGateway.pushAdd(pushRegistry, "wso2is");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setProperties(String fileName){

        try {
            Properties property=new Properties();
            property.load(new FileReader(fileName));
            host = property.getProperty("HOST");
            port = Integer.valueOf(property.getProperty("PORT"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

        server.stop();

    }

}
