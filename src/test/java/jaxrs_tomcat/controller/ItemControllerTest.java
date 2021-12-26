package jaxrs_tomcat.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
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
		Item item = new Item();
		item.setId("1");
		item.setValue("testValue");
		
		List<Item> items = new ArrayList<Item>(Arrays.asList(item));
		
		Mockito.when(itemRepository.selectAllItems()).thenReturn(items);
		
		Response response = target("/items").request().get();
		List<Item> responseItems = response.readEntity(new GenericType<List<Item>>() {});
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertEquals(1, responseItems.size());
		assertEquals("testValue", responseItems.get(0).getValue());
	}

}
