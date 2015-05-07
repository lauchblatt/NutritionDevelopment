package de.ur.infwiss.nutrition.defaultconsumer;

import java.util.HashMap;
import java.util.Map;

import org.glassfish.jersey.client.oauth1.OAuth1AuthorizationFlow;
import org.glassfish.jersey.server.ResourceConfig;

/*
 * 
 * TODO Check if Hashmap threadsafe
 */

public class DefaultConsumerApp extends ResourceConfig {
	
	public DefaultConsumerApp() {
		super();
		String[] p=new String[1];
		p[0] = "de.ur.infwiss.nutrition.defaultconsumer";
		this.packages(p[0]);
		this.property("flowMap", new  HashMap<String,OAuth1AuthorizationFlow>());
	}

}
