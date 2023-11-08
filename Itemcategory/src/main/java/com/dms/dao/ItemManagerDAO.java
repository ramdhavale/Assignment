package com.dms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dms.beans.Item;
/**
 * This class is useful to add, update and delete Items
 * @author Ram
 *
 */
public class ItemManagerDAO {

	// Define your database connection properties

	// JDBC connection
	private Connection connection;

	public ItemManagerDAO() throws SQLException {
		// Initialize the database connection
		connection = DatabaseManager.getDataBaseConnection();
	}

	/**
	 * maxItems
	 * 
	 * @return
	 */
	public int maxItems() {
		int id = 0;
		String selectCategoriesSQL = "SELECT MAX(id) AS id FROM items";

		try (PreparedStatement statement = connection.prepareStatement(selectCategoriesSQL);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id + 1;
	}

	/**
	 * addItems
	 * 
	 * @param item
	 */
	public void addItems(Item item) {
		String insertCategorySQL = "INSERT INTO items (id, name, description, category_id) VALUES (?,?,?,?)";

		try {

			PreparedStatement statement = connection.prepareStatement(insertCategorySQL);
			statement.setInt(1, maxItems());
			statement.setString(2, item.getName());
			statement.setString(3, item.getDescription());
			statement.setInt(4, item.getCategoryId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * listItems
	 * 
	 * @return
	 */
	public List<String> listItems() {
		List<String> categories = new ArrayList<>();
		String selectCategoriesSQL = "SELECT i.id, i.name, description, c.name as categories_name FROM categories c, items i\r\n"
				+ " WHERE c.id = i.category_id";

		try (PreparedStatement statement = connection.prepareStatement(selectCategoriesSQL);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String itemName = resultSet.getString(2);
				String description = resultSet.getString(3);
				String categoriesName = resultSet.getString(4);

				categories.add(id + "  " + itemName + " " + description + " " + categoriesName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}

	/**
	 * updateItem
	 * 
	 * @param item
	 */
	public void updateItem(Item item) {
		try {
			String updateQuery = "UPDATE items SET name = ?, description=?, category_id = ? WHERE id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
				preparedStatement.setString(1, item.getName());
				preparedStatement.setString(2, item.getDescription());
				preparedStatement.setInt(3, item.getCategoryId());
				preparedStatement.setInt(4, item.getId());
				int updatedRows = preparedStatement.executeUpdate();
				if (updatedRows == 0) {
					System.out.println("Error while update");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle or log the exception as needed
		}
	}

	/**
	 * deleteItem
	 * 
	 * @param item
	 */
	public void deleteItem(Item item) {
		try {
			String deleteQuery = "DELETE FROM items WHERE id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
				preparedStatement.setInt(1, item.getId());
				int updatedRows = preparedStatement.executeUpdate();
				if (updatedRows == 0) {
					System.out.println("Error while update");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle or log the exception as needed
		}
	}
	
	
	


	/**
	 * closeConnection
	 */
	public void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
