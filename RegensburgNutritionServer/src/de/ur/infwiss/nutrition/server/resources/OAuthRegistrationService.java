package de.ur.infwiss.nutrition.server.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.server.oauth1.*;


@Path("/oauth1/registration")
public class OAuthRegistrationService {

	@Context
	ServletContext sContext;
	
	@Context
	Application app;
	
	@Path("/register")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces({ MediaType.TEXT_PLAIN })
	public String doRegistration(@FormParam("owner") String owner) throws Exception {
		Map<String,Object> prop = app.getProperties();
		ExtendedOauthProviderInterface prov = (ExtendedOauthProviderInterface) prop.get("oauth1Provider");
		OAuth1Consumer consumer = prov.registerConsumer(owner,new MultivaluedHashMap<String,String>());
		return "ok";
	}
}
