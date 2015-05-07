package de.ur.infwiss.nutrition.server.resources;

//import javax.annotation.Resource;
//import javax.servlet.ServletContext;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
//import javax.xml.ws.WebServiceContext;
//import javax.xml.ws.handler.MessageContext;










import de.ur.infwiss.nutrition.basicfunc.BasicFuncImplFactory;
import de.ur.infwiss.nutrition.basicfunc.BasicFuncInterface;
import de.ur.infwiss.nutrition.basicfunc.NutValue;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.oauth1.*;
/*
 * Checks token and then delegates all calls to an shared instance of
 * BasicApiInterface
 * No concurrency issues, as only reading access is offered
 * TODO: Define parameter in web.xml to specify the implementation
 * TODO: Access context via injection
 */

@Path("/basicapi")
public class BasicNutrionApiRessource {

	@Context
	ServletContext sContext;
	
	@Context
	Application app;
    
	@Path("/getNutritionValue")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces({ MediaType.APPLICATION_XML })
	
	/*Token parameter should be turned into HeaderParam to avoid redundancy!*/
	public NutValue getNutValue(@FormParam("token") String token,@FormParam("ingr") String ingredient,@FormParam("subst") String substance, @FormParam("lang") String language,@FormParam("inqua") int intakeQuantity,@FormParam("inuni") String intakeUnit,@FormParam("outuni") String outputUnit) throws Exception {
		try {
			Map<String,Object> prop = app.getProperties();
			ExtendedOauthProviderInterface prov = (ExtendedOauthProviderInterface) prop.get("oauth1Provider");
		    /*
		     * TODO CHECK TOKEN AND THROW EXCEPTION IN CASE OF INVALIDITY	
		     */
		    BasicFuncInterface func = BasicFuncImplFactory.instance.getBasicFuncImpl(sContext);
		    int result=func.getNutritionValue(ingredient,substance,language,intakeQuantity,intakeUnit,outputUnit);
		    return new NutValue(ingredient,substance,language,intakeQuantity,intakeUnit,result,outputUnit);   
	
	    } catch (Exception e)
	    {
	    	throw e;
	       /*TODO error handling*/	
	    }
	}
	
	@GET
	@Path("/test")
	@Produces({ MediaType.TEXT_PLAIN })
	public String test() {
		return "Test";
	}
	
}
