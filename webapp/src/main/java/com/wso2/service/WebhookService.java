package com.wso2.service;

import com.wso2.model.AuthenticationJson;
import com.wso2.model.SessionJson;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("webhook")
public class WebhookService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public void loginEvent(AuthenticationJson authenticationJson) {
        System.out.printf("%s %s %s%n",
                authenticationJson.event.payloadData.tenantDomain,
                authenticationJson.event.payloadData.serviceProvider,
                authenticationJson.event.payloadData.authenticationSuccess);
        if (authenticationJson.event.payloadData.authenticationSuccess) {
            ServletContext.loginGauge.labels(authenticationJson.event.payloadData.tenantDomain,
                    authenticationJson.event.payloadData.serviceProvider,
                    "yes").inc();
        } else if (!authenticationJson.event.payloadData.authStepSuccess) {
            ServletContext.loginGauge.labels(authenticationJson.event.payloadData.tenantDomain,
                    authenticationJson.event.payloadData.serviceProvider,
                    "no").inc();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("session")
    public void sessionEvent(SessionJson sessionJson) {
        System.out.printf("%s %s %f %f %f%n",
                sessionJson.event.payloadData.tenantDomain,
                sessionJson.event.payloadData.serviceProvider,
                sessionJson.event.payloadData.startTimestamp,
                sessionJson.event.payloadData.renewTimestamp,
                sessionJson.event.payloadData.terminationTimestamp,
                sessionJson.event.payloadData._timestamp);
        if (sessionJson.event.payloadData.terminationTimestamp == sessionJson.event.payloadData._timestamp) {
            ServletContext.loginGauge.labels(sessionJson.event.payloadData.tenantDomain,
                    sessionJson.event.payloadData.serviceProvider,
                    "yes").dec();
        }
    }
}
