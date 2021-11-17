package com.example.inventory.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NewDao {
    String dbURL = "jdbc:mysql://remotemysql.com/varDt01HvK";
    String username = "varDt01HvK";
    String password = "gQa4TK4SvA";
    public Connection conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            try {
                Connection conn = DriverManager.getConnection(dbURL, username, password);
                return conn;
            } catch (SQLException e) {
                System.out.println("Error in initializing a connection to MYSQL DB");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("JAVA: error");
            e.printStackTrace();
            return null;
        }
    }
}


