package com.demo;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.oauth1.DefaultOAuth1Provider;
import org.glassfish.jersey.server.oauth1.OAuth1ServerFeature;



public class MainApplication extends ResourceConfig {
	
	public MainApplication(){
		packages("com.demo");

		DefaultOAuth1Provider provider = new DefaultOAuth1Provider();
		MultivaluedMap<String, String> emptyMap = new MultivaluedHashMap<String, String>();
		provider.registerConsumer("testOwner", "testKey", "testSecret", emptyMap);
		this.property("provider", provider);
		System.out.println(provider.getConsumer("testKey").getKey());
		OAuth1ServerFeature feature = new OAuth1ServerFeature(provider, "oauth/request", "oauth/access");
		register(feature);
	}

}