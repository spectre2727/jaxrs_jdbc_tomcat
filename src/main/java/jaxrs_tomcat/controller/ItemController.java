package jaxrs_tomcat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jaxrs_tomcat.entity.Item;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
public class ItemController {
	
	private List<Item> items = new ArrayList<Item>();
	
	{
		Item item1 = new Item();
		item1.setId("1");
		item1.setValue("value 1");
		items.add(item1);
		Item item2 = new Item();
		item2.setId("2");
		item2.setValue("value 2");
		items.add(item2);
	}
	
	@GET
	public List<Item> selectAllItems() {
		return items;
	}

}
