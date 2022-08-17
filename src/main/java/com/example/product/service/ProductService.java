package com.example.product.service;

import java.util.List;

import com.example.product.model.dao.ProductDao;
import com.example.product.model.dto.Product;
import com.example.product.view.ProductMenu;

/**
 * @author tony6(이대엽)
 *
 */
//메뉴(View) ↔ 컨트롤러 ↔ *서비스* ↔ DAO ↔ DB
// 서비스는 Dao와만 통신한다. 일을 마치면 결과를 컨트롤러(나를 호출한곳)로 반환(return)한다.

public class ProductService {
	ProductDao productDao = new ProductDao();

	public List<Product> findAllProduct() {
		List<Product> list = productDao.findAll();// select 의 결과를 List 형으로 반환
		return list;
	} // findAllProduct 끝

	public int addProduct(Product product) {
		int result = productDao.insert(product);  // 성공시 성공한 행의개수(1이상) 실패시 0 반환
		return result;
	} // addProduct 끝

	public int modifyProduct(int pId, int amount) {
		int result = productDao.update(pId, amount); // 성공시 성공한 행의개수(1이상) 실패시 0 반환
		return result;
	} // modifyProduct 끝

	public int deleteProduct(int pId) {
		return productDao.delete(pId); // 성공시 성공한 행의개수(1이상) 실패시 0 반환
	} // deleteProduct 끝

}
