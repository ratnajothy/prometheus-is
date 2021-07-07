package com.wso2.model.authentication;

import com.wso2.model.common.AbstractPayloadData;

public class AuthenticationEventPayloadData extends AbstractPayloadData {

    public String contextId;
    public String eventId;
    public String eventType;
    public boolean authenticationSuccess;
    public String localUserName;
    public String userStoreDomain;
    public String tenantDomain;
    public String inboundAuthType;
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

}
