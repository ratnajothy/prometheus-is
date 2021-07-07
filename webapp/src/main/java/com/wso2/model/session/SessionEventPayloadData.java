package com.wso2.model.session;

import com.wso2.model.common.AbstractPayloadData;

public class SessionEventPayloadData extends AbstractPayloadData {

    public String sessionId;
    public float startTimestamp;
    public float renewTimestamp;
    public float terminationTimestamp;
    public float action;
    public String userstoreDomain;
    public String tenantDomain;
    public String identityProviders;
    public boolean rememberMeFlag;
    public String userAgent;

}