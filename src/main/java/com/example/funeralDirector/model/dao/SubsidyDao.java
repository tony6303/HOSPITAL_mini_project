package com.example.funeralDirector.model.dao;
import static com.example.config.JdbcTemplate.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.funeralDirector.model.dto.SubsidyDto;

/** @author fla90*/
public class SubsidyDao {
	
		
		//부조금 전체 정보 조회 
		public ArrayList<SubsidyDto> selectAll(){
			
			
			String sql = "SELECT * FROM SUBSIDY";
			
			
			ArrayList<SubsidyDto> list = new ArrayList<>();
			Connection connection;
			
			try {
				connection = getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rset = stmt.executeQuery(sql);
				
				while(rset.next()) {
					SubsidyDto sd = new SubsidyDto();
					sd.setSubsidyName(rset.getString(1));
					sd.setSubsidy(rset.getInt(2));
					sd.setAccount_number(rset.getString(3));
					sd.setAccount_bank(rset.getString(4));
					
					list.add(sd);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		
		
		
		
		//부조금 추가 
		public int insert(SubsidyDto subsidyDto) {
			
			
			String sql = "INSERT INTO SUBSIDY(SUBSIDY_NAME, SUBSIDY, ACCOUNT_INFO, ACCOUNT_BANK, FUNELRAL_ID) VALUES(?,?,?,?,?)";
			
			try {
				Connection connection = getConnection();
				PreparedStatement pstmt = getConnection().prepareStatement(sql);
				pstmt.setString(1,subsidyDto.getSubsidyName());
				pstmt.setInt(2,subsidyDto.getSubsidy());
				pstmt.setString(3,subsidyDto.getAccount_number());
				pstmt.setString(4,subsidyDto.getAccount_bank());
				pstmt.setInt(5,subsidyDto.getFunelral_id());
				
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
		}

		
		//부조금 수정 update
		public int updateDeath(int subsidy, String account_info, String account_bank,String subsidy_name) {
			
			String sql ="UPDATE SUBSIDY SET SUBSIDY = ? ,ACCOUNT_INFO = ? ,ACCOUNT_BANK=? WHERE SUBSIDY_NAME =?";
			
			try (PreparedStatement pstmt = getConnection().prepareStatement(sql)){
				pstmt.setInt(1, subsidy);
				pstmt.setString(2, account_info);
				pstmt.setString(3, account_bank);
				pstmt.setString(4, subsidy_name);
				
				
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		//부조금 delete
		public int deleteDeath(String subsidy_name) {
		
		String sql = "DELETE FROM SUBSIDY WHERE SUBSIDY_NAME = ? " ;
		
		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)){
			pstmt.setString(1, subsidy_name);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
		
		
		//환자이름 별 부조금 전체 정보 조회 
		public ArrayList<SubsidyDto> selectBypatient(String patient_name){
			
			
			String sql = "SELECT SUBSIDY_NAME, SUBSIDY, ACCOUNT_INFO, ACCOUNT_BANK ,A.FUNELRAL_ID FROM SUBSIDY A LEFT JOIN FUNELRAL B ON A.FUNELRAL_ID = B.FUNELRAL_ID LEFT JOIN PATIENT C ON B.FUNELRAL_ID = C.PATIENT_ID WHERE C.PATIENT_NAME = ? ";
			
			
			ArrayList<SubsidyDto> list = new ArrayList<>();
			
			try (PreparedStatement pstmt = getConnection().prepareStatement(sql)){
				pstmt.setString(1,patient_name);
				
				ResultSet rset = pstmt.executeQuery();
				while(rset.next()) {
					SubsidyDto sd = new SubsidyDto();
					sd.setSubsidyName(rset.getString(1));
					sd.setSubsidy(rset.getInt(2));
					sd.setAccount_number(rset.getString(3));
					sd.setAccount_bank(rset.getString(4));
					sd.setFunelral_id(rset.getInt(5));
					
					list.add(sd);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
}


