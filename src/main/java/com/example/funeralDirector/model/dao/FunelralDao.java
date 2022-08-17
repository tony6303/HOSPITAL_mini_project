package com.example.funeralDirector.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.example.funeralDirector.model.dto.FunelralDto;


public class FunelralDao {
	//하나 조회하는 밑에꺼 걍 오류떠서 걍 추가한거다
	public FunelralDao() {
		// TODO Auto-generated constructor stub
	}
	//이거 계정 연결하는거다 
	private Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "HOS";
		String password = "HOS";
		System.out.println("연결완료");
		return DriverManager.getConnection(url, username, password);

	}
	
	//사망자 하나만 조회
	public FunelralDto findByFunelralId(String name, String no) {
		FunelralDto funelralDto = null;
		//SQL 조회 문
		String sql ="SELECT A.FUNELRAL_ID, A.DATE_DEATH, A.DEATH_REASON, A.PATIENT_ID FROM FUNELRAL A LEFT JOIN PATIENT B ON A.PATIENT_ID = B.PATIENT_ID WHERE B.PATIENT_NAME= ? AND B.PATIENT_NO=?";
	
		
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)){
			pstmt.setString(1, name);
			pstmt.setString(2, no);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				funelralDto = new FunelralDto(rs.getInt(1), rs.getDate(2),rs.getString(3),rs.getInt(4));
			
			}
			System.out.println("log: 사망자 정보 조회 쿼리 실행");
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return funelralDto;
	}
	
	
	
	
	//사망자 추가 
		public int insert(int funelralId2,String deathReason,int patientId){
			//SQL 삽입 문
			String sql = "INSERT INTO FUNELRAL (FUNELRAL_ID, DATE_DEATH, DEATH_REASON, PATIENT_ID) VALUES (?,sysdate,?,?)";
			
			try (PreparedStatement pstmt = getConnection().prepareStatement(sql)){
				pstmt.setInt(1, funelralId2);
//				pstmt.setDate(2, (Date) funelraldto.getDateDeath());
				pstmt.setString(2, deathReason);
				pstmt.setInt(3, patientId);
				
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}		
			
		}
		
	
	//사망자 명단전체내용 조회 
	public ArrayList<FunelralDto> selectAll(){
		//실행할 sql문 적어라 
		String sql = "select * from funelral";
		//전체 조회할꺼니깐 dto형태의 arraylist를 선언해야것지!
		ArrayList<FunelralDto> list = new ArrayList<>();
		Connection connection; 
		
		try {
			//연결하고 연결한 값을 넘겨 -> 커리문은 실행할 Statemet객체를 만들고 쿼리문전송
			//실행한 결과를 rset으로 받아둬
			connection = getConnection();
			
			Statement stmt = connection.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			//System.out.println(rset);
			
			while(rset.next()) {
				FunelralDto fd = new FunelralDto();
				fd.setFunelralId(rset.getInt(1));
				fd.setDateDeath(rset.getDate(2));
				fd.setDeathReason(rset.getString(3));
				fd.setPatientId(rset.getInt(4));
				
				list.add(fd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("여기가 문제니?");
		}
		return list;
	}
	
	
//	//사망자 수정 update
//	public int updateDeath(int subsidy, String account_info, String account_bank,String subsidy_name) {
//		
//		String sql ="UPDATE SUBSIDY SET SUBSIDY = ? ,ACCOUNT_INFO = ? ,ACCOUNT_BANK=? WHERE SUBSIDY_NAME =?";
//		
//		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)){
//			pstmt.setInt(1, subsidy);
//			pstmt.setString(2, account_info);
//			pstmt.setString(3, account_bank);
//			pstmt.setString(4, subsidy_name);
//			
//			return pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
	
	
	
	
	
	

	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public ArrayList<FunelralDto> selectAll() {
//		String sql = "SELECT * FROM PHA_STOCK";
//		// 받은 결과값을 객체에 저장할 저장 공간을 만들기
//		ArrayList<FunelralDto> list = new ArrayList<FunelralDto>();
//		Connection connection;
//		try {
//			connection = getConnection();
//			// 연결이 성공하면 커넥션값이 넘어온다.
//
//			System.out.println("conn : " + connection);
//			System.out.println("연결되면 대박");
//			// 쿼리문을 실행할 statement 객체를 만듬
//			Statement stmt = connection.createStatement();
//			
//
//			// 쿼리문을 전송하고, 실행할 결과를 resultset으로 받기
//			ResultSet rset = stmt.executeQuery(sql);
//
//			//
////		         private int funelralId;
////		 	    private Date dateDeath;
////		 	    private String deathReason;
////		 	    private int patientId;
////		 	    
//			while (rset.next()) {
//				FunelralDto m = new FunelralDto();
////		            m.setPhaNo(rset.getInt("PHA_NO"));
//
//				m.getFunelralId();
//
//				list.add(m);
//
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//
//		return list;
//
//	}


