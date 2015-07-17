package de.ur.infwiss.nutrition.server.resources;

import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.server.oauth1.OAuth1Consumer;
import org.glassfish.jersey.server.oauth1.OAuth1Provider;

/* Includes all used methods of the standard implementation*/

public interface ExtendedOauthProviderInterface extends OAuth1Provider {

	  OAuth1Consumer registerConsumer(String owner,MultivaluedMap<String,String> attr);
	  
	  String getVerifier(String requToken);
}
