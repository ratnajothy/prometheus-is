package com.wso2.model.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationJson {

    public AuthenticationEventJson event;

}