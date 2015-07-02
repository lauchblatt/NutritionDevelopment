package de.ur.infwiss.nutrition.defaultconsumer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.oauth1.*;
import org.glassfish.jersey.oauth1.signature.OAuth1Parameters;

import de.ur.infwiss.nutrition.basicfunc.NutValue;


/*
 *  doAuth / doCallback are  designed to be used by a client api, which can also store access tokens and will decide dynamically what to do
 * browser clients get a link to a servlet, which in turn calls doAuth (if no access token is submitted via cookie)
 * in that case a callback uri to another servlet is provided (otherwise, standard uri of doCallback is callback uri); after authorization either 
 * this callback servlet is called back and and in turn calls doCallback
 * OR doCallBack is called back directly by the client
 * 
 * 
 */

@Path("consumer")
public class DefaultConsumerResource {
	
	protected static class TestFilter implements ClientResponseFilter {

	
		@Override
		public void filter(ClientRequestContext arg0, ClientResponseContext arg1)
				throws IOException {

			Iterator<String> i = arg1.getHeaders().keySet().iterator();
		    PrintWriter wr=new PrintWriter(new FileOutputStream(new File("/home/sistbien/temp/filterlog"),false));
		    while (i.hasNext()) {
		    	wr.println(i.next());
		    }
			wr.close();
		}
		
	}

	@Context
	Application app;
	
	@Context
	ServletContext sContext;
	
	@Path("/oauth1")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces({ MediaType.TEXT_PLAIN })
	public String doAuth(@FormParam("callback") String callBack,@FormParam("rights") String rights) throws Exception {
		//get URLs
		String accUrl = sContext.getInitParameter("accessTokenUrl");
		String requUrl = sContext.getInitParameter("requestTokenUrl");
		String authUrl =sContext.getInitParameter("authUrl");
		String callbackUrl=null;
		//servlet will send callback url pointing to another servlet calling do Callback method, client api won't do that
		if (callBack==null)
		{	
				callbackUrl=sContext.getInitParameter("callbackUrl");
		}
		else
		{
			callbackUrl=callBack;
		}
		String ckey=sContext.getInitParameter("consumerKey");
		String csecret=sContext.getInitParameter("consumerSecret");
		//prepare filter for passing additional parameter containing rights
		ExtendHeaderRequestFilter filt = new ExtendHeaderRequestFilter(requUrl,"de.ur.infwiss.defaultconsumer.RIGHTS");
		filt.setHeaderValue(rights);
		//initiate oauth flow
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(filt);
		Client client = ClientBuilder.newClient(clientConfig);
		ConsumerCredentials consumerCredentials = new ConsumerCredentials(ckey,csecret);
		
     
         OAuth1Builder.FlowBuilder b =   OAuth1ClientSupport.builder(consumerCredentials)
                 .authorizationFlow(
                         requUrl,
                         accUrl,
                         authUrl);
         b.client(client);
        
       OAuth1AuthorizationFlow authFlow =b.callbackUri(callbackUrl).build();
             
       String redirUrl=authFlow.start();
       @SuppressWarnings("unchecked")
	   Map<String,OAuth1AuthorizationFlow> m = ( Map<String,OAuth1AuthorizationFlow>) app.getProperties().get("flowMap");
       String requToken=Utility.extractParam(OAuth1Parameters.TOKEN,redirUrl);  //TODO : Extract from redirectUrl
       m.put(requToken, authFlow); 
	   return redirUrl; //
	}
	
	@Path("/oauth1Callback")
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	public String doCallback(@QueryParam(OAuth1Parameters.VERIFIER) String verifier,@QueryParam(OAuth1Parameters.TOKEN) String requToken) throws Exception {
		
		//retrieve flow instance AND REMOVE IT FROM THE MAP IN ORDER TO ALLOW DESTRUCTION BY GARBAGE COLLECTION
		Map<String,OAuth1AuthorizationFlow> m = ( Map<String,OAuth1AuthorizationFlow>) app.getProperties().get("flowMap");
		OAuth1AuthorizationFlow flow=m.remove(requToken);
		flow.finish(verifier);
		
		return ""; //Access Key
	}
	
	@Path("/basicservice/getNutValue")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces({ MediaType.APPLICATION_XML })
	public NutValue getNutritionValue(@FormParam("token") String token,@FormParam("ingr") String ingredient,@FormParam("subst") String substance, @FormParam("lang") String language,@FormParam("inqua") int intakeQuantity,@FormParam("inuni") String intakeUnit,@FormParam("outuni") String outputUnit) throws Exception {
		
		//get consumer credentials and token secret
		
		//make authenticated call as in example 16.9 in the user guide
		return null;
	}
	
	/*public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
		  //clientConfig.register(MyClientResponseFilter.class);
		  clientConfig.register(new TestFilter());
		   
		  Client client = ClientBuilder.newClient(clientConfig);
		  //client.register(ThirdClientFilter.class);
		   
		  //WebTarget webTarget = client.target("http://132.199.143.90:8080/RegensburgNutritionServer/rest/basicapi/getNutritionValue");
		  WebTarget webTarget = client.target("http://localhost:8080/RegensburgNutritionServer/rest/basicapi/getNutritionValue");

		   
		  Form form = new Form();
		  form.param("ingr", "Schweinshaxn");
          form.param("subst","Fett");
          form.param("lang", "bayerisch");
          form.param("inqua", "2");
          form.param("inuni", "kg");
          form.param("outuni", "g");
		  
		  
		  Invocation.Builder invocationBuilder =webTarget.request(MediaType.APPLICATION_XML);
		   
		  Response response = invocationBuilder.post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		  System.out.println(response.getStatus());
		  System.out.println(response.readEntity(String.class));
		  
		  /*WebTarget webTarget2 = client.target("http://localhost:8080/RegensburgNutritionServer/rest/oauth1/registration/register");
		  
		   
		  Form form2= new Form();
		  form2.param("owner", "XYZ");
        
		  
		  Invocation.Builder invocationBuilder2 =webTarget2.request(MediaType.TEXT_PLAIN);
		   
		  Response response2 = invocationBuilder2.post(Entity.entity(form2,MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		  System.out.println(response2.getStatus());
		  System.out.println(response2.readEntity(String.class));
	} */
}
