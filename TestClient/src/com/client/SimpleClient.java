package com.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class SimpleClient {

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/TestServer/services/helloWorld/test");
		Response response = target.request().get();
		String responseData = response.readEntity(String.class);
		System.out.println(responseData);

	}

}
