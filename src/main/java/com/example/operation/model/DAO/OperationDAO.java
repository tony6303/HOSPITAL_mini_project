package com.example.operation.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.operation.model.DTO.OperationDTO;
import com.example.config.*;

/**
 * <pre>
 * Class : OperationDAO
 * Comment : 수술 테이블에 쿼리 전송 및 결과값 반환.
 * </pre>
 * @author 이규철
 * */
public class OperationDAO {

	/** @author 이규철 **/
	public int getColumnOP() {  // 수술 테이블 키 값의 최대값
	        try (Statement statement = JdbcTemplate.getConnection().createStatement()) {
	            ResultSet rs = statement.executeQuery("select MAX(OP_NO) from OPERATION");
	            rs.next();
	            return rs.getInt(1);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	/** @author 이규철 **/
	public int insertOp(String day, String uniqueness, String op_name, String doctorName, String patientName) { // 수술 일정 작성
		String sql = "INSERT INTO OPERATION VALUES(?,TO_TIMESTAMP(?),?,?,(SELECT DOCTOR_ID FROM DOCTOR WHERE DOCTOR_NAME = ?),(SELECT PATIENT_ID FROM PATIENT WHERE PATIENT_NAME = ?))";
		try (PreparedStatement pstmt = JdbcTemplate.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, (getColumnOP()+1));
			pstmt.setString(2, day);
            pstmt.setString(3, uniqueness);
            pstmt.setString(4, op_name);
            pstmt.setString(5, doctorName);
            pstmt.setString(6, patientName);
            
            JdbcTemplate.getConnection().commit();
            return pstmt.executeUpdate();
        } catch (SQLException e) {
//            throw new RuntimeException(e);
        	return -1;
        }
	}

	/** @author 이규철 **/
	public List<OperationDTO> searchOp(String patientName,String patientNo) { // 수술 일정 열람
		String sql = "SELECT OP_NO,OP_DATE,UNIQUENESS,OP_NAME,DOCTOR_ID,A.PATIENT_ID FROM OPERATION A LEFT JOIN PATIENT B ON A.PATIENT_ID = B.PATIENT_ID WHERE B.PATIENT_NAME = ? AND B.PATIENT_NO = ?"; 
		
		try (PreparedStatement pstmt = JdbcTemplate.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, patientName);
            pstmt.setString(2, patientNo);
            ResultSet rset = pstmt.executeQuery();
            List<OperationDTO> orr = new ArrayList<>();    
           
            if(rset.next()) {
            	rset.close();
            	rset = pstmt.executeQuery();
			while (rset.next()) {
				orr.add(new OperationDTO(rset.getInt(1), rset.getTimestamp(2), rset.getString(3), rset.getString(4), rset.getInt(5),rset.getInt(6)));
			}
			}else {
				rset.close();
				System.out.println("제대로 입력합시다");
				return null;
			}
            
            rset.close();
            
            return orr;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	/** @author 이규철 **/
	public int updateOp(String patientName,String patientNo, String date,String newDate) {  // 수술 일정 업데이트
		String sql = "UPDATE OPERATION SET OP_DATE = TO_TIMESTAMP(?) WHERE OP_DATE = ? AND PATIENT_ID = (SELECT PATIENT_ID FROM PATIENT WHERE PATIENT_NAME = ? AND PATIENT_NO = ?) ";
		try (PreparedStatement pstmt = JdbcTemplate.getConnection().prepareStatement(sql)) {
			pstmt.setString(1,newDate);
            pstmt.setString(2, date);
			pstmt.setString(3, patientName);
			pstmt.setString(4, patientNo);
            
			JdbcTemplate.getConnection().commit();
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	/** @author 이규철 **/
	public int deleteOp(String patientName,String patientNo, String date) {  // 수술 일정 삭제
		String sql = "DELETE FROM OPERATION WHERE PATIENT_ID = (SELECT PATIENT_ID FROM PATIENT WHERE PATIENT_NAME = ? AND PATIENT_NO = ?) AND OP_DATE = TO_TIMESTAMP(?)";
		try (PreparedStatement pstmt = JdbcTemplate.getConnection().prepareStatement(sql)) {
			pstmt.setString(1,patientName);
			pstmt.setString(2, patientNo);
            pstmt.setString(3, date);
            
            JdbcTemplate.getConnection().commit();
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
}
