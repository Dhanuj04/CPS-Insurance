package com.insurance.connections;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {


    public static Connection getMySqlConnection()
    {
        String userName = "root";
        String password = "Dhanujr04";
        String url = "jdbc:mysql://localhost:3306/projectdb";
        String driver = "com.mysql.cj.jdbc.Driver";
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, password);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(e);
        }
        return connection;

    }


}