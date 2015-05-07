package de.ur.infwiss.nutrition.server.resources;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.oauth1.DefaultOAuth1Provider;

/*
 * Adds implements declaration
 * Processes additional injected parameters indicating the type of permissions being requested
 */
@Provider
public class ExtendedDefaultOauth1Provider extends DefaultOAuth1Provider implements
		ExtendedOauthProviderInterface {
    
	
	public ExtendedDefaultOauth1Provider() {
		super();
	}
	
	public DefaultOAuth1Provider.Consumer registerConsumer(String owner, MultivaluedMap<String,String> attributes) {
		return super.registerConsumer(owner, attributes);
	}
	
}
