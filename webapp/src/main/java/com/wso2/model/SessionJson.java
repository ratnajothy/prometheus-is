package com.wso2.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SessionJson {
    public Event event;
    public class Event {
        public MetaData metaData;
        public PayloadData payloadData;
        public class PayloadData {
            public String sessionId;
            public float startTimestamp;
            public float renewTimestamp;
            public float terminationTimestamp;
            public float action;
            public String username;
            public String userstoreDomain;
            public String remoteIp;
            public String region;
            public String tenantDomain;
            public String serviceProvider;
            public String identityProviders;
            public boolean rememberMeFlag;
            public String userAgent;
            public float _timestamp;
        }
        public class MetaData {
            public float tenantId;
        }
    }
}