package jaxrs_tomcat.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import jaxrs_tomcat.entity.Item;

public class ItemControllerTest extends JerseyTest {
	
	@Override
    protected Application configure() {
        return new ResourceConfig(ItemController.class);
    }
	
	@Test
	public void selectAllItemsTest() {
		Response response = target("/items").request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertNotNull(response.readEntity(new GenericType<List<Item>>() {}));
	}

}
