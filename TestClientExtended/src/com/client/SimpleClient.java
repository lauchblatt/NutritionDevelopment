package com.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.oauth1.AccessToken;
import org.glassfish.jersey.client.oauth1.ConsumerCredentials;
import org.glassfish.jersey.client.oauth1.OAuth1AuthorizationFlow;
import org.glassfish.jersey.client.oauth1.OAuth1Builder;
import org.glassfish.jersey.client.oauth1.OAuth1ClientSupport;

public class SimpleClient {

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		ConsumerCredentials consumerCredentials = new ConsumerCredentials("testKey", "testSecret");
		OAuth1AuthorizationFlow authFlow = OAuth1ClientSupport.builder(consumerCredentials)
			    .authorizationFlow(
			        "http://localhost:8080/TestServer/services/oauth/request",
			        "http://localhost:8080/TestServer/services/oauth/access",
			        "http://localhost:8080/TestServer/services/oauth/redirection")
			    .build();
		
		String authorizationUri = authFlow.start();
		System.out.println("TEST");
	}

}