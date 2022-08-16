package com.example.medic.model.dao;

import com.example.medic.model.dto.Medic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicDao {
    public void insert(Medic medic) {
        String sql = "INSERT INTO PHA_STOCK (pha_no, pha_name, pha_type, pha_price, pha_stock) VALUES (?,?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)
        ) {
            pstmt.setLong(1, medic.getPhaNo());
            pstmt.setString(2, medic.getPhaName());
            pstmt.setString(3, medic.getPhaType());
            pstmt.setLong(4, medic.getPhaPrice());
            pstmt.setLong(5, medic.getPhaStock());

            System.out.println("log : insert execute 실행");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "ehek01";
        String password = "ehek12";

        return DriverManager.getConnection(url, username, password);
    }


}
