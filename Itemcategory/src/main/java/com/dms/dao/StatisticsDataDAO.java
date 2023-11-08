package com.dms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dms.beans.Item;

import com.dms.beans.StatisticData;

/**
 * This class is useful to create statistics Data of category
 * @author Ram
 *
 */
public class StatisticsDataDAO {

	// JDBC connection
		private Connection connection;

		public StatisticsDataDAO() throws SQLException {
			// Initialize the database connection
			connection = DatabaseManager.getDataBaseConnection();
		}

	
		/**
		 * listItems
		 * 
		 * @return
		 */
		public List<String> getStatisticsData() {
			List<String> statisticsData = new ArrayList<>();
			String selectStatisticsDataCategoriesSQL = "WITH ItemCounts AS ( SELECT category_id, COUNT(*) AS category_item_count\r\n"
					+ "    FROM items   GROUP BY category_id\r\n"
					+ " ), TotalItemCount AS (SELECT COUNT(*) AS total_items  FROM items)\r\n"
					+ " SELECT c.name, ic.category_item_count, ic.category_item_count * 100.0 / tc.total_items AS category_percentage\r\n"
					+ " FROM categories c LEFT JOIN ItemCounts ic ON c.id = ic.category_id\r\n"
					+ " CROSS JOIN TotalItemCount tc";

			try (PreparedStatement statement = connection.prepareStatement(selectStatisticsDataCategoriesSQL);
					ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					String name = resultSet.getString(1);
					int count = resultSet.getInt(2);
					float percentage = resultSet.getFloat(3);
					
					statisticsData.add(name + "  " + count + " " + percentage);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return statisticsData;
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

