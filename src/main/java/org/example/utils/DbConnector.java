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
            throw new RuntimeException(e);
        }
    }
    public DbConnector(){
        if (connection == null){
            try {
                connection = DriverManager
<<<<<<< HEAD
                        .getConnection("jdbc:postgresql://localhost:5432/products?user=postgres&password=admin");
=======
                        .getConnection("jdbc:postgresql://localhost:5432/products2?user=postgres&password=1234");
>>>>>>> e27538420e813c70f861778181fa56158f362057
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
