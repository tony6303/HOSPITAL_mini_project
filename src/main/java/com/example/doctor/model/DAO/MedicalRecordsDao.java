package com.example.doctor.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.config.JdbcTemplate;
import com.example.doctor.model.DTO.MedicalRecordsDTO;

/**
 * -----
 * <pre>
 * Class : MedicalRecordsDao
 * Comment : 진료 테이블에 쿼리 전송 및 결과값 반환.
 * </pre>
 * @author 이규철
 * */
public class MedicalRecordsDao {
	 
	 /** @author 이규철 **/
	 public int getColumnMR() {   // 진료 테이블 키 값 중 가장 큰 값을 반환
	        try (Statement statement = JdbcTemplate.getConnection().createStatement()) {
	            ResultSet rs = statement.executeQuery("select MAX(MR_NO) from MEDICAL_RECORDS");
	            rs.next();
	            return rs.getInt(1);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 /** @author 이규철 **/
	 public int getColumnPre() {  // 처방전 키 값 중 가장 큰 값을 반환
	        try (Statement statement = JdbcTemplate.getConnection().createStatement()) {
	            ResultSet rs = statement.executeQuery("select MAX(PRE_NO) from PRESCRIPTION");
	            rs.next();
	            return rs.getInt(1);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 
	 
	 /** @author 이규철 **/
	public List<MedicalRecordsDTO> checkRecords(String resName,String resNo) {  // 진료 기록 열람 
		String sql = "SELECT MR_NO,MR_DATE,A.PATIENT_ID,A.COST_ID,B.DISEASE_NAME FROM MEDICAL_RECORDS A LEFT JOIN COST B ON A.COST_ID=B.COST_ID LEFT JOIN PATIENT C ON A.PATIENT_ID = C.PATIENT_ID WHERE C.PATIENT_NAME = ? AND C.PATIENT_NO = ?";
		List<MedicalRecordsDTO> mr = new ArrayList<>();
		try (PreparedStatement pstmt = JdbcTemplate.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, resName);
            pstmt.setString(2, resNo);  
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()) {
            	rs.close();
            	rs = pstmt.executeQuery();
            while (rs.next()) {  
                mr.add(
                        new  MedicalRecordsDTO(rs.getInt(1),rs.getDate(2),rs.getInt(3),rs.getInt(4),rs.getString(5))
                );
            }}else {
            	rs.close();
            	System.out.println("에러러러러");
            	return null;
            }
            rs.close();
            return mr;
        } catch (SQLException e) {
//              throw new RuntimeException(e);
        	return null;
        }
	}
	
	/** @author 이규철 **/
	public int writeRecords(String str,String resNo,String diseaseName) {   // 진료 기록 작성 
		String sql = "INSERT INTO MEDICAL_RECORDS VALUES (?,SYSDATE,(SELECT PATIENT_ID FROM PATIENT WHERE PATIENT_NAME = ? AND PATIENT_NO = ?),(SELECT COST_ID FROM COST WHERE DISEASE_NAME = ?))";
		int result = 0;
		try (PreparedStatement pstmt = JdbcTemplate.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, (getColumnMR()+1));
			pstmt.setString(2, str);
			pstmt.setString(3, resNo);
            pstmt.setString(4, diseaseName);
            System.out.println("log : insert execute 실행");
            JdbcTemplate.getConnection().commit();
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return -1;
        }
       return result;

	}

	/** @author 이규철 **/
	public int writePrescribtion(String str, String pha, int period, int day,String doctorName) {  // 처방전 작성
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
//            throw new RuntimeException(e);
        	return -1;
        }
		
	}
	 
}
