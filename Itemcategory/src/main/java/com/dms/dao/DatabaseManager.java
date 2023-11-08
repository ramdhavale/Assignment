package com.dms.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "admin";

	public static Connection getDataBaseConnection()
    {
        Connection connection=null;
        try{
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            if(connection!=null)
                System.out.println("Connection OK");
            else
                System.out.println("Connection Failed");
 
        }
        catch(Exception e)
            {
                System.out.println(e);
            }
        return connection;
    }
	
	

}
