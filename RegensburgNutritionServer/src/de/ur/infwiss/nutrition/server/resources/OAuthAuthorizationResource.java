package de.ur.infwiss.nutrition.server.resources;



 

public class OAuthAuthorizationResource {
	
	/*
	 * 
	 * Authorization Service 1  (called via redirect)
	 * Parameter Request Token, Callback-Uri
	 * Uses ExtendedOAuthProviderInterface to look up Request Token, provides
	 * user with HTML-Page providing information about permissions to be granted
	 * and form to enter credentials 
	 * AND PASS THEM TO
	 * AuthorizationService 2 (called via SUBMIT OR by client API)
	 * R
	 * Checks credentials and, if OK, eturns verifier token and
	 * redirects to callback uri
	 * 
	 * SESSION TRACKING NEEDED TO KEEP CONTEXT BETWEEN CALL TO SERVICE 1 AND 2
	 */
 
}

