package jaxrs_tomcat.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jaxrs_tomcat.entity.Item;
import jaxrs_tomcat.repository.ItemRepository;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
public class ItemController {
	
	private ItemRepository itemRepository = new ItemRepository();
	
	@GET
	public List<Item> selectAllItems() {
		return itemRepository.selectAllItems();
	}

}
