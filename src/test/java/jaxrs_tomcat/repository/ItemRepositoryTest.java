package jaxrs_tomcat.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import pckg.connection.JdbcConnection;
import pckg.entity.Item;
import pckg.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemRepositoryTest {

	private static Connection connection;

	@Mock
	private JdbcConnection jdbcConnection;

	@InjectMocks
	private ItemRepository itemRepository;

	@BeforeAll
	public static void beforeTests() throws SQLException {
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:standard", "root", "root");
		connection.createStatement().executeQuery("create table items (id varchar(16), value varchar(50))");
		connection.createStatement().executeQuery("insert into items values (\'toSelect\', \'value\')");
		connection.createStatement().executeQuery("insert into items values (\'toUpdate\', \'value\')");
		connection.createStatement().executeQuery("insert into items values (\'toDelete\', \'value\')");
		
		System.setErr(new PrintStream(new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				
			}
		}));
	}
	
	@AfterAll
	public static void afterTests() {
		System.setErr(System.err);
	}

	@Test
	public void selectAllItemsTest() throws SQLException {
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:standard", "root", "root");
		Mockito.when(jdbcConnection.get()).thenReturn(connection);
		
		List<Item> items = itemRepository.selectAllItems();
		
		assertNotNull(items);
		assertTrue(items.size() > 0);
	}

	@Test
	public void selectAllItemsExceptionTest() {
		BDDMockito.given(jdbcConnection.get()).willAnswer(answer -> {
			throw new SQLException();
		});
			
		List<Item> items = itemRepository.selectAllItems();
		
		assertNull(items);
	}
	
	@Test
	public void insertItemTest() throws SQLException {
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:standard", "root", "root");
		Mockito.when(jdbcConnection.get()).thenReturn(connection);
		
		int insertResult = itemRepository.insertItem(new Item());
		
		assertEquals(1, insertResult);
	}
	
	@Test
	public void insertItemExceptionTest() {
		BDDMockito.given(jdbcConnection.get()).willAnswer(answer -> {
			throw new SQLException();
		});
		
		int insertResult = itemRepository.insertItem(new Item());
		
		assertEquals(0, insertResult);
	}
	
	@Test
	public void updateItemTest() throws SQLException {
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:standard", "root", "root");
		Mockito.when(jdbcConnection.get()).thenReturn(connection);
		
		int updateResult = itemRepository.updateItem("toUpdate", new Item());
		
		assertEquals(1, updateResult);
	}
	
	@Test
	public void updateItemExceptionTest() {
		BDDMockito.given(jdbcConnection.get()).willAnswer(answer -> {
			throw new SQLException();
		});
		
		int updateResult = itemRepository.updateItem("toUpdate", new Item());
		
		assertEquals(0, updateResult);
	}
	
	@Test
	public void deleteItemTest() throws SQLException {
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:standard", "root", "root");
		Mockito.when(jdbcConnection.get()).thenReturn(connection);
		
		int deleteResult = itemRepository.deleteItem("toDelete");
		
		assertEquals(1, deleteResult);
	}
	
	@Test
	public void deleteItemExceptionTest() {
		BDDMockito.given(jdbcConnection.get()).willAnswer(answer -> {
			throw new SQLException();
		});
		
		int deleteResult = itemRepository.deleteItem("toDelete");
		
		assertEquals(0, deleteResult);
	}

}
