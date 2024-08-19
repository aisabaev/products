package org.example.config;

import org.example.dao.CustomerDao;
import org.example.utils.DbConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "org.example.*")
public class ProjectConfig {


    @Bean
    Connection connection(){
        try {
           return DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/products2?user=postgres&password=1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
