package main.java.com.example.funeralDirector.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.java.com.example.funeralDirector.model.dto.FunelralDto;
import main.java.com.example.funeralDirector.model.dto.SubsidyDto;

public class SubsidyDao {
	
	
	//이거 계정 연결하는거다 
		private Connection getConnection() throws SQLException {
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String username = "HOS";
			String password = "HOS";
			System.out.println("연결완료");
			return DriverManager.getConnection(url, username, password);
		}
		
		//부조금 전체 정보 조회 
		public ArrayList<SubsidyDto> selectAll(){
			String sql = "SELECT * FROM SUBSIDY";
			//전체 조회할 꺼니깐 dto형태의 arrayList를 선언해야겠지!
			ArrayList<SubsidyDto> list = new ArrayList<>();
			Connection connection;
			
			try {
				connection = getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rset = stmt.executeQuery(sql);
				//System.out.println(rset);
				
				while(rset.next()) {
					SubsidyDto sd = new SubsidyDto();
					sd.setSubsidyName(rset.getString(1));
					sd.setSubsidy(rset.getInt(2));
					sd.setAccount_number(rset.getString(3));
					sd.setAccount_bank(rset.getString(4));
					
					list.add(sd);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		//부조금 전체 정보 조회 끝
		
		
		//부조금 추가 
		public int insert(SubsidyDto subsidyDto) {
			
			//sql문 삽입해야지?
			
			String sql = "INSERT INTO SUBSIDY(SUBSIDY_NAME, SUBSIDY, ACCOUNT_INFO, ACCOUNT_BANK) VALUES(?,?,?,?)";
			
			try {
				Connection connection = getConnection();
				PreparedStatement pstmt = getConnection().prepareStatement(sql);
				pstmt.setString(1,subsidyDto.getSubsidyName());
				pstmt.setInt(2,subsidyDto.getSubsidy());
				pstmt.setString(3,subsidyDto.getAccount_number());
				pstmt.setString(4,subsidyDto.getAccount_bank());
				
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
		}
		//부조금 추가 끝
		
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
//			pstmt.setString(2, account_info);
//			pstmt.setString(3, account_bank);
//			pstmt.setString(4, subsidy_name);
			
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}


