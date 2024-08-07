package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private Connection connection = null;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public DbConnector(){
        if (connection == null){
            try {
                connection = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/products2?user=postgres&password=1234");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
