package jaxrs_tomcat.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import jaxrs_tomcat.configuration.JdbcConnection;
import jaxrs_tomcat.entity.Item;

@RunWith(MockitoJUnitRunner.class)
public class ItemRepositoryTest {

	private static Connection connection;

	@Mock
	private JdbcConnection jdbcConnection;

	@InjectMocks
	private ItemRepository itemRepository;

	@BeforeClass
	public static void initConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:standard", "root", "root");
		connection.createStatement().executeQuery("create table items (id varchar(16), value varchar(50))");
		connection.createStatement().executeQuery("insert into items values (\'1\', \'value1\')");
	}
	
	@Before
	public void initRepository() throws SQLException {
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:standard", "root", "root");
		Mockito.when(jdbcConnection.get()).thenReturn(connection);
	}

	@Test
	public void selectAllItemsTest() throws SQLException {
		assertNotNull(itemRepository.selectAllItems().get(0));
	}

	@Test
	public void dontSelectAllItemsTest() {
		BDDMockito.given(jdbcConnection.get()).willAnswer(i -> {
			throw new SQLException();
		});
		assertNull(itemRepository.selectAllItems());
	}
	
	@Test
	public void insertItemTest() throws SQLException {
		Item item = new Item();
		item.setValue("itemValue");
		assertEquals(1, itemRepository.insertItem(item));
	}
	
	@Test
	public void dontInsertItem() {
		BDDMockito.given(jdbcConnection.get()).willAnswer(i -> {
			throw new SQLException();
		});
		assertEquals(0, itemRepository.insertItem(new Item()));
	}

}
