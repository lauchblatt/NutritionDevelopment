package com.demo;


import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.oauth1.signature.OAuth1Parameters;
import org.glassfish.jersey.server.oauth1.OAuth1Provider;

import de.ur.infwiss.nutrition.server.resources.ExtendedOauthProviderInterface;

@Path("oauth/redirection/")
public class RedirectResource {
	
	@Context
	Application app;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String doAuthentication(@QueryParam(OAuth1Parameters.TOKEN) String token){
		Map<String,Object> prop = app.getProperties();
		ExtendedOauthProviderInterface pr = (ExtendedOauthProviderInterface) prop.get("provider");
		
		return pr.getVerifier(token);  //Returns verifier
	}
	
}
