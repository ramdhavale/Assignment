package com.dms.controller;

import java.sql.SQLException;
import java.util.Scanner;



import com.dms.beans.Item;
import com.dms.dao.CategoryManagerDAO;
import com.dms.dao.ItemManagerDAO;
import com.dms.dao.StatisticsDataDAO;


public class ItemCategoryController {

	public static void main(String[] args) {

		try {

			ItemManagerDAO itemManagerDAO = new ItemManagerDAO();

			while (true) {
				System.out.println("Item Category");
				System.out.println("1. Add a new category");
				System.out.println("2. List all categories");
				System.out.println("3. Add a new item and associate it with a category");
				System.out.println("4. Perform statistics calculations and show the output");
				System.out.println("5. Exit");
				Scanner scanner = new Scanner(System.in);
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					CategoryManagerDAO categoryManagerDAO = new CategoryManagerDAO();
					Item item = new Item();
					item.setName("Dress Material");
					item.setDescription("Dress Material");
					item.setCategoryId(categoryManagerDAO.maxCategories());
					itemManagerDAO.addItems(item);
					categoryManagerDAO.closeConnection();
					break;
				case 2:
					CategoryManagerDAO categoryManager = new CategoryManagerDAO();
					categoryManager.listCategories();
					categoryManager.closeConnection();
					break;
				case 3:
					CategoryManagerDAO categoryManagr = new CategoryManagerDAO();
					categoryManagr.addCategory("New Category");
					categoryManagr.closeConnection();
					break;

				case 4:
					StatisticsDataDAO statisticsDataDAO = new StatisticsDataDAO();
					statisticsDataDAO.getStatisticsData();
					statisticsDataDAO.closeConnection();
					break;
				case 5:
					System.out.println("Exiting the program.");
					System.exit(0);
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
