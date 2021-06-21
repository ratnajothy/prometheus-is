package com.wso2.service;

import com.wso2.model.AuthenticationJson;
import com.wso2.model.ServletContext;
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
        System.out.println(authenticationJson.event.payloadData.tenantDomain);
        System.out.println(authenticationJson.event.payloadData.serviceProvider);
        System.out.println(authenticationJson.event.payloadData.authStepSuccess);

        ServletContext.gauge.labels(authenticationJson.event.payloadData.tenantDomain,
                authenticationJson.event.payloadData.serviceProvider,
                String.valueOf(authenticationJson.event.payloadData.authenticationSuccess)).inc();
    }
}
