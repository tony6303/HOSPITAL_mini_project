package com.example.cashier.model.dao;
import static com.example.config.JdbcTemplate.getConnection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.example.cashier.model.dto.Price;
import com.example.patient.model.dto.Patient;


/**
 * @author cpzhr(박경민)
 *
 */
public class CashierDao {

	// 예약테이블 모두 조회
	public int getColumnR() { 
		try (Statement statement = getConnection().createStatement()) {
			 //SELECT는 ResultSet을 사용하여 executeQuery를 통해 쿼리를 실행하면 ResultSet타입으로 반환하여 결과값 저장
			ResultSet rs = statement.executeQuery("SELECT MAX(RESERVATION_NO) "
												 +"FROM RESERVATION");  //rs에 쿼리 결과값 넣음
			rs.next(); // 공백 이전까지의 문자열을 입력받음.
			return rs.getInt(1);  // rs 결과 반환
		} catch (SQLException e) { 
			throw new RuntimeException(e);
		}
	}

	//RESERVATION테이블에 예약번호 -> 자동으로 1증가
	//					예약날짜 -> 현재날짜
	//					환자번호 -> 주민번호 넣어서 조회
	public int reservationinsert(String resNo) {
		
		
		String sql = "INSERT INTO RESERVATION "
				+	 "VALUES (? ,SYSDATE, (SELECT PATIENT_ID FROM PATIENT WHERE PATIENT_NO = ?))";

		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, (getColumnR() + 1)); // 예약번호 자동으로 1증가
			pstmt.setString(2, resNo);

			getConnection().commit();
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	//물음표에 resNo를 넣고 입력한 주민번호와 AND  진료 날짜 = 현재 날짜인 환자의 각 컬럼을 출력
	public Price priceselect(String resNo) {
		String sql = "select "
				+		"A.PATIENT_ID "
				+		",A.PATIENT_NAME "
				+		",A.PATIENT_NO "
				+		",B.MR_DATE "
				+		",C.DISEASE_NAME "
				+		",C.PRICE "
				+	 "FROM PATIENT A "
				+		"LEFT JOIN MEDICAL_RECORDS B ON A.PATIENT_ID = B.PATIENT_ID "
				+		"LEFT JOIN COST C ON B.COST_ID = C.COST_ID "
				+	 "WHERE PATIENT_NO = ? AND TO_CHAR(MR_DATE , 'YY/MM/DD' ) = TO_CHAR( SYSDATE, 'YY/MM/DD')";
		Price orr = null;
		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setString(1, resNo);
			ResultSet rset = pstmt.executeQuery();

			
			if (rset.next()) {
				orr = new Price(rset.getInt(1),
								rset.getString(2),
								rset.getString(3),
								rset.getDate(4),
								rset.getString(5),
								rset.getInt(6));
			}
			rset.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return orr;
	}

	//질병을 입력해 cost테이블의 price를 price*1.1배로 수정
	public int priceUpdate(String disName) {
		String sql = "UPDATE COST "
				+	 "SET PRICE = PRICE*1.1 "
				+	 "WHERE DISEASE_NAME = ?";
		
		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setString(1, disName);
			

			getConnection().commit();
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	//?에 salary값을 입력해 입력값보다 직원의 급여보다 높으면 해당직원 삭제
	public int salaryselect(int salary) {
		String sql = "DELETE FROM CASHIER_INFO "
				+	 "WHERE SALARY >= ?";
		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, salary);
			

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	//주민번호로 환자조회
	public Patient selectByResNo(String resNo) {
		Patient patient = null;
		
		String sql = "SELECT * "
				+	 "FROM PATIENT "
				+	 "WHERE PATIENT_NO = ?";
		
		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setString(1, resNo);
			ResultSet rset = pstmt.executeQuery();
			if (rset.next()) {
                patient = new Patient(rset.getLong(1), 
                					  rset.getString(2), 
                					  rset.getString(3), 
                					  rset.getString(4));
            }
			
			rset.close();
			
			return patient;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//질병명으로 가격조회
	public int selectPriceByDiseaseName(String disName) {
		String sql = "select price "
				+ 	 "from cost "
				+ 	 "where disease_name = ?";
		
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, disName);
			ResultSet rset = pstmt.executeQuery();
			int price= 0;
			if (rset.next()) {
				price = rset.getInt("Price");
			}
			
			return price;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
}
