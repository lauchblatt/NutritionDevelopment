package com.demo;

import org.glassfish.jersey.server.ResourceConfig;

public class MainApplication extends ResourceConfig {
	
	public MainApplication(){
		packages("com.demo");
	}

}
