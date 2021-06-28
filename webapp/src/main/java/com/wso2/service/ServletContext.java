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

    private static final CollectorRegistry pushRegistry = new CollectorRegistry();
    public static Gauge loginGauge = Gauge.build().name("login")
            .labelNames("tenantDomain", "serviceProvider", "authenticationSuccess")
            .help("No. of logins").register(pushRegistry);
    public static HTTPServer server;
    public static PushGateway pushGateway;

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            setProperties("webapps/webapp/WEB-INF/IS.properties");
            server = new HTTPServer(new InetSocketAddress(host, port), pushRegistry);
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
