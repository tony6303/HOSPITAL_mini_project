package com.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTemplate {
    private JdbcTemplate() {
        System.out.println("생성해서 쓰지마세요.");
    }

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:oracle:thin:@oracle.douzone-jo3.kro.kr:1521:XE";
        String username = "JO3";
        String password = "JO3";

        return DriverManager.getConnection(url, username, password);
    }
}
