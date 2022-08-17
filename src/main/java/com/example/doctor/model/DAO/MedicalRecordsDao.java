package com.example.doctor.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.config.JdbcTemplate;
import com.example.doctor.model.DTO.MedicalRecordsDTO;

public class MedicalRecordsDao {
	 
	 public int getColumnMR() {
	        try (Statement statement = JdbcTemplate.getConnection().createStatement()) {
	            ResultSet rs = statement.executeQuery("select count(1) from MEDICAL_RECORDS");
	            rs.next();
	            return rs.getInt(1);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 public int getColumnPre() {
	        try (Statement statement = JdbcTemplate.getConnection().createStatement()) {
	            ResultSet rs = statement.executeQuery("select count(1) from PRESCRIPTION");
	            rs.next();
	            return rs.getInt(1);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 
	 
	 
	public List<MedicalRecordsDTO> checkRecords(String resName) {
		String sql = "SELECT MR_NO,MR_DATE,A.PATIENT_ID,A.COST_ID,B.DISEASE_NAME FROM MEDICAL_RECORDS A LEFT JOIN COST B ON A.COST_ID=B.COST_ID LEFT JOIN PATIENT C ON A.PATIENT_ID = C.PATIENT_ID WHERE C.PATIENT_NAME = ?";
		try (PreparedStatement pstmt = JdbcTemplate.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, resName);
			List<MedicalRecordsDTO> mr = new ArrayList<>();
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                mr.add(
                        new  MedicalRecordsDTO(rs.getInt(1),rs.getDate(2),rs.getInt(3),rs.getInt(4),rs.getString(5))
                );
            }
            rs.close();
            return mr;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	public int writeRecords(String str,String diseaseName) {
		String sql = "INSERT INTO MEDICAL_RECORDS VALUES (?,SYSDATE,(SELECT PATIENT_ID FROM PATIENT WHERE PATIENT_NAME = ?),(SELECT COST_ID FROM COST WHERE DISEASE_NAME = ?))";
		try (PreparedStatement pstmt = JdbcTemplate.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, (getColumnMR()+1));
			pstmt.setString(2, str);
            pstmt.setString(3, diseaseName);
            System.out.println("log : insert execute 실행");
            JdbcTemplate.getConnection().commit();
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	public int writePrescribtion(String str, String pha, int period, int day,String doctorName) {
		String sql = "INSERT INTO PRESCRIPTION VALUES(?,(SELECT PHA_NO FROM PHA_STOCK WHERE PHA_NAME = ?),SYSDATE,?,?,(SELECT PATIENT_ID FROM PATIENT WHERE PATIENT_NAME = ?),(SELECT DOCTOR_ID FROM DOCTOR WHERE DOCTOR_NAME = ?))";
		try (PreparedStatement pstmt = JdbcTemplate.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, (getColumnPre()+1));
            pstmt.setString(2, pha);
            pstmt.setInt(3, day);
            pstmt.setInt(4, period);
            pstmt.setString(5, str);
            pstmt.setString(6, doctorName);

            System.out.println("log : insert execute 실행");
            JdbcTemplate.getConnection().commit();
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
	}
	 
}
