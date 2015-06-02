package de.ur.infwiss.nutrition.clientapi;

import java.util.HashMap;
import java.io.StringReader;

import javax.ws.rs.FormParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.glassfish.jersey.client.ClientConfig;

import de.ur.infwiss.nutrition.basicfunc.NutValue;
import de.ur.infwiss.nutrition.server.resources.NutConstants;


/*
 * 
 * Simple client, persistent storage of access tokens is left to the user
 * Can be done by a wrapper class in the future
 */
public class NutritionClient {
	
	public final static String TOKEN_KEY="token";
	public final static String RESULT_KEY="result";
	private String serviceBaseUri=null;
	
	public NutritionClient(String serviceUri) {
		serviceBaseUri=serviceUri;
	}


	public NutValue getNutritionValue(String token,String ingredient,String substance,String language,int intakeQuantity,String intakeUnit,String outputUnit) throws Exception {
		
		ClientConfig clientConfig = new ClientConfig();   
		Client client = ClientBuilder.newClient(clientConfig); 
		WebTarget webTarget = client.target(serviceBaseUri+"basicservice/getNutValue");		   
		Form form = new Form();
		form.param("ingr", ingredient);
        form.param("subst",substance);
        form.param("lang", language);
        form.param("inqua", String.valueOf(intakeQuantity));
        form.param("inuni", intakeUnit);
        form.param("outuni", outputUnit);
        form.param("token", token);
		Invocation.Builder invocationBuilder =webTarget.request(MediaType.APPLICATION_XML);   
		Response response = invocationBuilder.post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		if (response.getStatus()!=200) {throw new Exception(response.readEntity(String.class));}
		JAXBContext c = JAXBContext.newInstance(NutValue.class);
		Unmarshaller u =c.createUnmarshaller();
		NutValue result = (NutValue) u.unmarshal(new StringReader(response.readEntity(String.class)));
		return result;
	}
	
	public HashMap<String,Object> getNutritionValue(String ingredient,String substance,String language,int intakeQuantity,String intakeUnit,String outputUnit) throws Exception {
        String token=authenticate(NutConstants.PERMISSION_ACCESS_BASICFUNC);	
		NutValue val= this.getNutritionValue(token,ingredient,substance,language,intakeQuantity,intakeUnit,outputUnit);
		HashMap<String,Object> m=new HashMap<String,Object>();
		m.put(NutritionClient.RESULT_KEY, val);
		m.put(NutritionClient.TOKEN_KEY, token);
		return m;
	}
	
	public String authenticate(String rights) throws Exception {
		ClientConfig clientConfig = new ClientConfig();   
		Client client = ClientBuilder.newClient(clientConfig); 
		WebTarget webTarget = client.target(serviceBaseUri+"oauth1");		   
		Form form = new Form();
		form.param("rights", rights);
		Invocation.Builder invocationBuilder =webTarget.request(MediaType.APPLICATION_XML);   
		Response response = invocationBuilder.post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		if (response.getStatus()!=200) {throw new Exception(response.readEntity(String.class));}
		//call redirection url, TODO What happens if rederict
		
		//send login data
		
		//?? redirect done automatically: if not perform redirect
		return response.readEntity(String.class);
	}
}
