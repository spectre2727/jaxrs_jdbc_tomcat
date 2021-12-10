package jaxrs_tomcat.controller;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jaxrs_tomcat.Main;

public class ItemControllerTest {
	
	@BeforeClass
	public static void main() throws Exception {
		Main.main(new String[1]);
	}
	
	@AfterClass
	public static void stop() throws Exception {
		Main.stop();
	}

	@Test
	public void selectAllItemsTest() {
		Client client = ClientBuilder.newBuilder().build();
		Response response = client.target("http://localhost:8080/tom")
			.path("/items")
			.request()
			.get();
		assertEquals(200, response.getStatus());
	}

}
