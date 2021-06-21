package com.wso2.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthenticationJson {
    public Event event;
    public class Event {
        public MetaData metaData;
        public PayloadData payloadData;
        public class PayloadData {
            public String contextId;
            public String eventId;
            public String eventType;
            public boolean authenticationSuccess;
            public String username;
            public String localUserName;
            public String userStoreDomain;
            public String tenantDomain;
            public String remoteIp;
            public String region;
            public String inboundAuthType;
            public String serviceProvider;
            public boolean rememberMeEnabled;
            public boolean forceAuthEnabled;
            public boolean passiveAuthEnabled;
            public String rolesCommaSeparated;
            public String authenticationStep;
            public String identityProvider;
            public boolean authStepSuccess;
            public String stepAuthenticator;
            public boolean isFirstLogin;
            public String identityProviderType = null;
            public float _timestamp;
        }
        public class MetaData {
            public float tenantId;
        }
    }
}