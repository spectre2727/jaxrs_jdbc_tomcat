package pckg.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pckg.connection.JdbcConnection;
import pckg.entity.Item;

public class ItemRepository {	
	
	private JdbcConnection jdbcConnection = new JdbcConnection();

	public List<Item> selectAllItems() {
		try (Connection connection = jdbcConnection.get()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from items order by value");
			List<Item> items = new ArrayList<Item>();
			while (resultSet.next()) {
				Item item = new Item();
				item.setId(resultSet.getString(1));
				item.setValue(resultSet.getString(2));
				items.add(item);
			}
			return items;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int insertItem(Item item) {
		try (Connection connection = jdbcConnection.get()) {
			PreparedStatement preparedStatement = 
					connection.prepareStatement("insert into items values (?, ?)");
			preparedStatement.setString(1, UUID.randomUUID().toString().replace("-", "").substring(0, 16));
			preparedStatement.setString(2, item.getValue());
			preparedStatement.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int updateItem(String id, Item item) {
		try (Connection connection = jdbcConnection.get()) {
			PreparedStatement preparedStatement = 
					connection.prepareStatement("update items set value = ? where id = ?");
			preparedStatement.setString(1, item.getValue());
			preparedStatement.setString(2, id);
			preparedStatement.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int deleteItem(String id) {
		try (Connection connection = jdbcConnection.get()) {
			PreparedStatement preparedStatement = 
					connection.prepareStatement("delete from items where id = ?");
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
			connection.close();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
