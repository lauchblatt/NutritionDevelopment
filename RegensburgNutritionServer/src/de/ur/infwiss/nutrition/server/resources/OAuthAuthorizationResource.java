package de.ur.infwiss.nutrition.server.resources;



 

public class OAuthAuthorizationResource {
	
	/*
	 * 
	 * Authorization Service 1  (called via Consumer-API or by servlet1)
	 * Parameter Request Token, Callback-Uri
	 * Uses ExtendedOAuthProviderInterface to look up Request Token, provides
	 * user information about permissions

	 * AuthorizationService 2 (called via servlet2 OR by client API)
	 * Recieves checks user credentials and, if OK, returns verifier token and
	 * redirect to callback uri
	 * 
	 * SESSION TRACKING NEEDED TO KEEP CONTEXT BETWEEN CALL TO SERVICE 1 AND 2
	 * Maybe we should again use RequestToken (not critical, as extra security 
	 * by token secret)
	 * 
	 * 
	 Servlet1: Calls Service 1 and generates HTML-Form
	 Servlet2: receives via SUBMIT-Button posted, calls service 2 and passes
	 through the returned redirect
	 */
 
}

