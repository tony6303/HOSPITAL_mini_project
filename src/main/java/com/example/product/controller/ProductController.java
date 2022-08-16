package com.example.product.controller;

import java.util.List;

import com.example.product.model.dto.Product;
import com.example.product.service.ProductService;

/**
 * @author tony6(이대엽)
 *
 */
//메뉴(View) ↔ *컨트롤러* ↔ 서비스 ↔ DAO ↔ DB
// 컨트롤러는 서비스랑만 통신한다. 일을 마치면 결과를 메뉴(나를 호출한곳)로 반환(return)한다.
// 서비스 통신 이외의 다른 작업을 수행하는 코드를 작성하면 안된다.
public class ProductController { // 딱 서비스만 호출하고 끝
	private final ProductService productService = new ProductService();

	public List<Product> selectAllProduct() {
		return productService.findAllProduct();

	} // selectAllProduct 끝

	public int addProduct(Product product) {

		return productService.addProduct(product); // 성공시 1 실패시 0 반환
	} // insertProduct 끝

	public int modifyProduct(int pId, int amount) {
		return productService.modifyProduct(pId, amount);
	} // modifyProduct 끝

	public int deleteProduct(int pId) {
		return productService.deleteProduct(pId);
	} // deleteProduct 끝
}
