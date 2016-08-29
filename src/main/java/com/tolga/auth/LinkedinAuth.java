package com.tolga.auth;

import com.github.scribejava.apis.LinkedInApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;

public class LinkedinAuth {
	
	    final String clientId = "client-id";
        final String clientSecret = "client-secret";
        final OAuth20Service service = new ServiceBuilder()
                .apiKey(clientId).apiSecret(clientSecret)
                .scope("r_basicprofile r_emailaddress") 
                .callback("http://localhost:8080/auth/linkedin/callback")
                .state("some_params")
                .build(LinkedInApi20.instance());
        
        public String obtainAuthUrl() {       	
        	return service.getAuthorizationUrl();
        }
       
        public OAuth20Service getService() {
        	return service;
        }
}
