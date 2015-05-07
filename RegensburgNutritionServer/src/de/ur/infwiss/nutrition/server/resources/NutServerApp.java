package de.ur.infwiss.nutrition.server.resources;


import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.oauth1.OAuth1ServerFeature;


public class NutServerApp extends ResourceConfig {
	
	
	public NutServerApp() {
		super();
		String[] p=new String[1];
		p[0] = "de.ur.infwiss.nutrition.server.resources";
		this.packages(p[0]);
		/*
		 * Will need an implementation that stores tokens and secrets persistently in the future
		 */
		ExtendedDefaultOauth1Provider prov = new ExtendedDefaultOauth1Provider();
		this.property("oauth1Provider", prov);
		
		/*
		According to Jersey 2.17 user guide this solves everything: Authenticated requests are verified, authorization is performed via the specified endpoints 
        */
		this.register(new OAuth1ServerFeature(prov,"/oauth1/request","/oauth1/access"));
	}

}
