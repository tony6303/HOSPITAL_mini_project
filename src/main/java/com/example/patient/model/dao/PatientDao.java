package com.example.patient.model.dao;

import com.example.patient.model.dto.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDao {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "ehek01";
        String password = "ehek12";

        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 총 컬럼 갯수 가져오기
     * Auto_increment 대신, 총 컬럼 갯수에 +1 해서 pk값 설정.
     */
    public int getColumnCount() {
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery("select count(1) from patient");
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 모든 환자 정보 가져오기
     */
    public List<Patient> findAll() {
        String sql = "select * from patient";

        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            List<Patient> patients = new ArrayList<>();
            ResultSet rs = pstmt.executeQuery("select * from patient");

            while (rs.next()) {
                patients.add(
                        new Patient(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4))
                );
            }

            rs.close();
            return patients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 주민번호로 환자 조회
     */
    public Patient findByPatientNo(String patientNo) {
        Patient patient = null;
        String sql = "select * from patient where patient_no = ?";

        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, patientNo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                patient = new Patient(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            System.out.println("log : 환자 정보 조회 쿼리 실행");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patient;
    }

    /**
     * 환자 등록
     */
    public int insert(Patient patient) {
        String sql = "INSERT INTO patient (patient_id, patient_no, patient_name, phone) VALUES (?,?,?,?)";

        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setLong(1, (getColumnCount()+1));
            pstmt.setString(2, patient.getPatientNo());
            pstmt.setString(3, patient.getPatientName());
            pstmt.setString(4, patient.getPhone());

            System.out.println("log : insert execute 실행");
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}