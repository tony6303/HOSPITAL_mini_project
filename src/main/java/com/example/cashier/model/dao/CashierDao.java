package main.java.com.example.cashier.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.com.example.cashier.model.dto.Cashier;
import main.java.com.example.cashier.model.dto.Price;
import main.java.com.example.patient.model.dto.Patient;

public class CashierDao {

	private Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "hospital";
		String password = "1234";

		return DriverManager.getConnection(url, username, password);
	}

	public int getColumnR() {
		try (Statement statement = getConnection().createStatement()) {
			ResultSet rs = statement.executeQuery("select count(1) from RESERVATION");
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int reservationinsert(String resNo) {
		String sql = "INSERT INTO RESERVATION VALUES (? ,SYSDATE, (SELECT PATIENT_ID FROM PATIENT WHERE PATIENT_NO = ?))";

		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, (getColumnR() + 1));
			pstmt.setString(2, resNo);

			getConnection().commit();
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public Price priceselect(String resNo) {
		String sql = "select A.PATIENT_ID ,A.PATIENT_NAME ,A.PATIENT_NO ,B.MR_DATE ,C.DISEASE_NAME ,C.PRICE FROM PATIENT A LEFT JOIN MEDICAL_RECORDS B ON A.PATIENT_ID = B.PATIENT_ID LEFT JOIN COST C ON B.COST_ID = C.COST_ID WHERE PATIENT_NO = ? AND to_char(MR_DATE , 'YY/MM/DD' ) = to_char( sysdate, 'YY/MM/DD')";
		Price orr = null;
		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setString(1, resNo);
			ResultSet rset = pstmt.executeQuery();

			if (rset.next()) {
				orr = new Price(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getDate(4),
						rset.getString(5), rset.getInt(6));
			}
			rset.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return orr;
	}

	public int priceUpdate(String disName) {
		String sql = "update cost set price = price*1.1 where disease_name = ?";
		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setString(1, disName);
			

			getConnection().commit();
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public int salaryselect(int salary) {
		String sql = "delete from cashier_info where salary >= ?";
		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, salary);
			

			getConnection().commit();
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
