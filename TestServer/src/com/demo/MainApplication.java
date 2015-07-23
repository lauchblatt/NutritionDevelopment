package com.demo;

import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.oauth1.DefaultOAuth1Provider;
import org.glassfish.jersey.server.oauth1.OAuth1ServerFeature;

public class MainApplication extends ResourceConfig {
	
	public MainApplication(){
		packages("com.demo");

		DefaultOAuth1Provider provider = new DefaultOAuth1Provider();
		MultivaluedMap<String, String> attributes = new MultivaluedStringMap();
		provider.registerConsumer("id", "testKey", "testSecret", attributes);
		OAuth1ServerFeature feature = new OAuth1ServerFeature(provider, "oauth/request", "oauth/access");
		register(feature);
	}

}
