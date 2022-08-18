package com.example.medic.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.medic.model.dto.Medic;
import com.example.medic.model.dto.PharmacyData;
import static com.example.config.JdbcTemplate.getConnection;

/**
 * @author 최영준
 *
 */
public class MedicDao {

	// MEMO 모든 제품명을 SELECT
	public ArrayList<Medic> selectAll() {
		String sql = "SELECT * FROM PHA_STOCK";
		// 받은 결과값을 객체에 저장할 저장 공간을 만들기
		ArrayList<Medic> list = new ArrayList<Medic>();
		Connection connection;
		try {
			connection = getConnection();
			// 연결이 성공하면 커넥션값이 넘어온다.
			// 쿼리문을 실행할 statement 객체를 만듬
			Statement stmt = connection.createStatement();

			// 쿼리문을 전송하고, 실행할 결과를 resultset으로 받기
			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				Medic m = new Medic();
				m.setPhaNo(rset.getInt("PHA_NO"));
				m.setPhaName(rset.getString("PHA_NAME"));
				m.setPhaType(rset.getString("PHA_TYPE"));
				m.setPhaPrice(rset.getInt("PHA_PRICE"));
				m.setPhaStock(rset.getInt("PHA_STOCK"));
				list.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return list;

	}

//===================INSERT를 위한 구문 =======================
	// MEMO insert로 입력받은 약번호가 테이블에 존재하는지 알아보는 메소드
	public int setlectPhaNo(Medic medic) {

		// 전달받은 약번호가 테이블에 존재하면 전달받은 번호 그대로 반환하고 없으면 0을 반환함
		int data = 0;

		String sql2 = "SELECT PHA_NO FROM PHA_STOCK WHERE PHA_NO =?";
		try (Connection connection = getConnection(); PreparedStatement pstmt2 = connection.prepareStatement(sql2)) {
			pstmt2.setInt(1, medic.getPhaNo());
			ResultSet exist = pstmt2.executeQuery();
			while (exist.next()) {
				data = exist.getInt("PHA_NO");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return data;
	}

	// MEMO - 5가지의 신제품 정보를 받아서 데이터 INSERT
	public void insert(Medic medic) {

		String sql = "INSERT INTO PHA_STOCK (pha_no, pha_name, pha_type, pha_price, pha_stock) VALUES (?,?,?,?,?)";

		try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, medic.getPhaNo());
			pstmt.setString(2, medic.getPhaName());
			pstmt.setString(3, medic.getPhaType());
			pstmt.setInt(4, medic.getPhaPrice());
			pstmt.setInt(5, medic.getPhaStock());
			System.out.println("log : 신제품 주문 완료 ");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	// MEMO 상품명과 , 갯수를 받아서 재고의 수를 증가시킴
	public Medic update(Medic medic) {
		String sql = "UPDATE PHA_STOCK SET PHA_STOCK = (PHA_STOCK + ?) WHERE PHA_NAME = ? ";

		try {
			Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, medic.getPhaStock());
			pstmt.setString(2, medic.getPhaName());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}

	// MEMO 재고추가 후에 다시 한번 총 재고수를 조회
		String sql2 = "SELECT * FROM PHA_STOCK WHERE PHA_NAME = ?";
		Medic medic2 = null;
		try {

			Connection connection = getConnection();
//			System.out.println("두번째 연결완료");
			PreparedStatement pstmt2 = connection.prepareStatement(sql2);
			pstmt2.setString(1, medic.getPhaName());
			ResultSet rset2 = pstmt2.executeQuery();
			while (rset2.next()) {
				String title1 = rset2.getString("PHA_NAME");
				int totalCount = rset2.getInt("PHA_STOCK");
				medic2 = new Medic(title1, totalCount);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return medic2;
	}

	// MEMO 상품명을 받아서 해당 재고 전체를 삭제
	public int delete(String phaName) {

		String sql = "DELETE FROM PHA_STOCK WHERE PHA_NAME = ?";

		try {
			Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, phaName);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	// MEMO 처방전 데이터 전부를 받아서 출력하기
	public PharmacyData printPD(int patientNumber) {
		String sql = "SELECT A.PRE_NO, C.PATIENT_NAME, C.PATIENT_NO, B.DOCTOR_NAME, A.PRE_DATE, D.PHA_NAME, D.PHA_TYPE, A.PHA_PERIOD,A.PHA_DAY_DOSAGE,D.PHA_PRICE,D.PHA_STOCK "
				+ " FROM PRESCRIPTION A " + " LEFT JOIN DOCTOR B ON A.DOCTOR_ID = B.DOCTOR_ID "
				+ " LEFT JOIN PATIENT C ON A.PATIENT_ID = C.PATIENT_ID "
				+ " LEFT JOIN PHA_STOCK D ON A.PHA_NO = D.PHA_NO " + "WHERE A.PRE_NO = ?";

		ResultSet rset = null;
		PharmacyData pharmacyData = null;

		try (Connection connection = getConnection(); 
			PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, patientNumber);
			rset = pstmt.executeQuery();

			while (rset.next()) {

				// 바로 변수에 값을 넣기
				int preNo = rset.getInt("PRE_NO");
				String patientName = rset.getString("PATIENT_NAME");
				String patientNo = rset.getString("PATIENT_NO");
				String doctorName = rset.getString("DOCTOR_NAME");
				Date preDate = rset.getDate("PRE_DATE");
				String phaName = rset.getString("PHA_NAME");
				String phaType = rset.getString("PHA_TYPE");
				int phaPeriod = rset.getInt("PHA_PERIOD");
				int phaDayDosage = rset.getInt("PHA_DAY_DOSAGE");
				int phaPrice = rset.getInt("PHA_PRICE");
				int phaStock = rset.getInt("PHA_STOCK");

				pharmacyData = new PharmacyData(preNo, patientName, patientNo, doctorName, preDate, phaName, phaType,
						phaPeriod, phaDayDosage, phaPrice, phaStock);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return pharmacyData;

	}

	// 처방전 출력후 업데이트 하는것,  조회하는 것 2개를 구현해야함
	
	public Medic updateScr(int minusStock, String phaName) {
		String sql = "UPDATE PHA_STOCK SET PHA_STOCK = (PHA_STOCK - ?) WHERE PHA_NAME = ? ";

		try {
			Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, minusStock);
			pstmt.setString(2, phaName);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}
		
		
		// MEMO 처방전 이후 재고 내리고 다시 총재고 값을 반환
		String sql2 = "SELECT * FROM PHA_STOCK WHERE PHA_NAME = ?";
		Medic medic2 = null;
		try {

			Connection connection = getConnection();
			PreparedStatement pstmt2 = connection.prepareStatement(sql2);
			pstmt2.setString(1, phaName);
			ResultSet rset2 = pstmt2.executeQuery();
			while (rset2.next()) {
				String title1 = rset2.getString("PHA_NAME");
				int totalCount = rset2.getInt("PHA_STOCK");
				medic2 = new Medic(title1, totalCount);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return medic2;
	}

	}
	
	
	

//	// MEMO LOCAL DB 연결 데이터 
//	private Connection getConnection() throws SQLException {
//		String url = "jdbc:oracle:thin:@localhost:1521:XE";
//		String username = "JO3";
//		String password = "JO3";
//
//		return DriverManager.getConnection(url, username, password);
//	}


