package jaxrs_tomcat.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import pckg.controller.ItemController;
import pckg.entity.Item;
import pckg.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest extends JerseyTest {
		
	@InjectMocks
	private ItemController itemController;
	
	@Mock
	private ItemRepository itemRepository;
		
	@Override
    protected Application configure() {
		itemController = new ItemController();
		return new ResourceConfig().register(itemController);
    }
	
	@Test
	public void selectAllItemsTest() {
		List<Item> items = new ArrayList<Item>(Arrays.asList(new Item()));
		
		Mockito.when(itemRepository.selectAllItems()).thenReturn(items);
		
		Response response = target("/items").request().get();
		
		List<Item> responseItems = response.readEntity(new GenericType<List<Item>>() {});
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertEquals(1, responseItems.size());
	}
	
	@Test
	public void insertItemTest() {
		Mockito.when(itemRepository.insertItem(Mockito.any())).thenReturn(1);
		
		Response response = target("/items").request()
				.post(Entity.entity(new Item(), MediaType.APPLICATION_JSON));
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertEquals(1, response.readEntity(Integer.class));
	}
	
	@Test
	public void updateItemTest() {
		Mockito.when(itemRepository.updateItem(Mockito.anyString(), Mockito.any())).thenReturn(1);
		
		Response response = target("/items" + "/" + new Random().nextInt(10) + 1).request()
				.put(Entity.entity(new Item(), MediaType.APPLICATION_JSON));
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertEquals(1, response.readEntity(Integer.class));
	}
	
	@Test
	public void deleteItemTest() {
		Mockito.when(itemRepository.deleteItem(Mockito.anyString())).thenReturn(1);
		
		Response response = target("/items" + "/" + new Random().nextInt(10) + 1).request()
				.delete();
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertEquals(1, response.readEntity(Integer.class));
	}

}
