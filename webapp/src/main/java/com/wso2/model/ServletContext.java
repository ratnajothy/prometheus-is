package com.wso2.model;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.exporter.PushGateway;
import java.io.IOException;
import java.net.InetSocketAddress;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContext implements ServletContextListener {
    private static final CollectorRegistry pushRegistry = new CollectorRegistry();
    public static HTTPServer server;
    public static PushGateway pushGateway;
    public static Gauge gauge = Gauge.build().name("login").labelNames("tenantDomain", "serviceProvider", "authenticationSuccess").help("No. of logins").register(pushRegistry);

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            server = new HTTPServer(new InetSocketAddress("127.0.0.1", 9091), pushRegistry);
            pushGateway = new PushGateway("127.0.0.1:9091");
            pushGateway.pushAdd(pushRegistry, "wso2is");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        server.stop();
    }
}
