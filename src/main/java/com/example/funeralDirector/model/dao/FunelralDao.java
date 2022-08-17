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
		String sql ="SELECT A.FUNELRAL_ID, A.DATE_DEATH, A.DEATH_REASON, A.PATIENT_ID,B.PATIENT_NAME FROM FUNELRAL A LEFT JOIN PATIENT B ON A.PATIENT_ID = B.PATIENT_ID WHERE B.PATIENT_NAME= ? AND B.PATIENT_NO=?";
	
		
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)){
			pstmt.setString(1, name);
			pstmt.setString(2, no);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				funelralDto = new FunelralDto(rs.getInt(1), rs.getDate(2),rs.getString(3),rs.getInt(4),rs.getString(5));
			
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
		String sql = "SELECT * FROM FUNELRAL A LEFT JOIN PATIENT B ON A.PATIENT_ID = B.PATIENT_ID";
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
				fd.setPatientName(rset.getString("PATIENT_NAME"));
				fd.setDateDeath(rset.getDate("DATE_DEATH"));
				fd.setDeathReason(rset.getString("DEATH_REASON"));
				
				list.add(fd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("여기가 문제니?");
		}
		return list;
	}
	
	
}	
	
