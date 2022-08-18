package com.example.product.model.dao;

import static com.example.config.JdbcTemplate.getConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.product.model.dto.Product;

/**
 * @author tony6(이대엽)
 *
 */
//메뉴(View) ↔ 컨트롤러 ↔ 서비스 ↔ *DAO* ↔ DB
// 일을 마치면 결과를 서비스(나를 호출한곳)로 반환(return)한다.
// DAO : Data Access Object , 데이터에 접근하는 객체
// sql, DB와 관련된 코드들만 작성한다. DB에 직접 접근하는 객체이다.
public class ProductDao {
	
	public List<Product> findAll() {

		ResultSet rs = null; // SELECT 후 결과값 받아올 객체

		String sql = "SELECT * FROM PRO_MANAGE ORDER BY P_ID";
		// pstmt = 쿼리를 실행하는 객체
		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			List<Product> list = new ArrayList<>();

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Product(rs.getInt("p_id"), rs.getString("p_name"), rs.getInt("amount"),
						rs.getDate("receive_date")));
			}
			rs.close();
			return list;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	} // findAll 끝

	public int insert(Product product) {
		String sql = "INSERT INTO PRO_MANAGE VALUES (pro_manage_seq.NEXTVAL, ?, ?, SYSDATE)";
		List<Product> list = findAll(); // 전체 조회의 결과값을 list 변수에 대입한다
		
		try {
			// pstmt = 쿼리를 실행하는 객체
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
		
			for(int i = 0; i < list.size(); i++) {  // list 변수의 사이즈 만큼 반복한다
				// list의 pname과 insert를 수행하면서 입력받은 pname이 같은지 반복문을 사용하여 비교한다
				if(list.get(i).getpName().equals(product.getpName())) {  
					System.out.println("이미 존재하는 제품입니다.");
					return 0; // 실패의 리턴 값인 0 을 반환 해 준다.
				}
			}
			
			pstmt.setString(1, product.getpName());			
			pstmt.setInt(2, product.getAmount());

			return pstmt.executeUpdate(); // insert 된 행의 개수 반환 (성공시 1개 실패시 0개)

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	} // insert 끝

	public int update(int pId, int amount) {
		String sql = "UPDATE PRO_MANAGE SET AMOUNT = ? WHERE P_ID = ?";

		try {
			// pstmt = 쿼리를 실행하는 객체
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, pId);

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	} // update 끝

	public int delete(int pId) {
		String sql = "DELETE FROM PRO_MANAGE WHERE P_ID = ?";

		try {
			// pstmt = 쿼리를 실행하는 객체
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setInt(1, pId);

			return pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	} // delete 끝

}
