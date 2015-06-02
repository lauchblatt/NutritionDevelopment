package com.demo;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

@Path("helloWorld/")
public class HelloWorldResource {
	
	@GET
	@Path("{name1}/{name2}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(@PathParam("name1") String name1, @PathParam("name2") String name2){
		return "Hello,  " + name1 + " and " + name2;
	}
	
	@GET
	@Path("/queryParam")
	public String getUser(@QueryParam("name")String name){
		System.out.println("Name: " + name);
		return name;
	}
	
	@GET
	@Path("/getUserAgent")
	public String getUserDevice(@HeaderParam("user-agent") String userAgent,
			@HeaderParam("Content-Type") MediaType contentType){
				return "User Agent: " + userAgent + ", Content-Type: " + contentType;
	}
	
	@POST
	public String addUser(MultivaluedMap<String, String> formData){
		System.out.println("Form Data: " + formData);
		return "User added successfully";

	}
	
	@DELETE
	@Path("{name}")
	public void delete (@PathParam("name")String name){
		System.out.println("DELETE: " + name);
	}

}
