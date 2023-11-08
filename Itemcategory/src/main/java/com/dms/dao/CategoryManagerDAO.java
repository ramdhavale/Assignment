package com.dms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * This Class is useful to add category and select the list of category
 * @author Ram
 *
 */
public class CategoryManagerDAO {
	// Define your database connection properties

	// JDBC connection
	private Connection connection;

	public CategoryManagerDAO() throws SQLException {
		// Initialize the database connection
		connection = DatabaseManager.getDataBaseConnection();
	}

	public int maxCategories() {
		int id = 0;
		String selectCategoriesSQL = "SELECT MAX(id) AS id FROM categories";

		try (PreparedStatement statement = connection.prepareStatement(selectCategoriesSQL);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id+1;
	}

	
	
	public void addCategory(String categoryName) {
		String insertCategorySQL = "INSERT INTO categories (id, name) VALUES (?,?)";

		try {
    
				PreparedStatement statement = connection.prepareStatement(insertCategorySQL); 
			statement.setInt(1, maxCategories());
			
			statement.setString(2, categoryName);
			statement.executeUpdate();
			System.out.println("categories added successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> listCategories() {
		List<String> categories = new ArrayList<>();
		String selectCategoriesSQL = "SELECT name FROM categories";

		try (PreparedStatement statement = connection.prepareStatement(selectCategoriesSQL);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				String categoryName = resultSet.getString("name");
				categories.add(categoryName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (String category : categories)
			System.out.println(category);

		return categories;
	}

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
