package com.example.funeralDirector.model.dao;

/** @author fla90*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.example.funeralDirector.model.dto.FunelralDto;

import static com.example.config.JdbcTemplate.getConnection;


public class FunelralDao {
	
	public FunelralDao() {
		
	}
	
	//사망자 이름,주민번호별 조회
	public FunelralDto findByFunelralId(String name, String no) {
		FunelralDto funelralDto = null;
		
	
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
		
		
		String sql = "SELECT * FROM FUNELRAL A LEFT JOIN PATIENT B ON A.PATIENT_ID = B.PATIENT_ID";
		
		ArrayList<FunelralDto> list = new ArrayList<>();
		Connection connection; 
		
		
		
		
		try {
			connection = getConnection();
			
			Statement stmt = connection.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				FunelralDto fd = new FunelralDto();
				fd.setPatientName(rset.getString("PATIENT_NAME"));
				fd.setDateDeath(rset.getDate("DATE_DEATH"));
				fd.setDeathReason(rset.getString("DEATH_REASON"));
				fd.setPatientId(rset.getInt("PATIENT_ID"));
				fd.setFunelralId(rset.getInt("FUNELRAL_ID"));
				
				
				list.add(fd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("여기가 문제니?");
		}
		return list;
		
		
		
	}
	
}	
	
