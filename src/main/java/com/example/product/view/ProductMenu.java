package com.example.product.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.example.product.controller.ProductController;
import com.example.product.model.dto.Product;

/**
 * @author tony6(이대엽)
 *
 */
// *메뉴(View)* ↔ 컨트롤러 ↔ 서비스 ↔ DAO ↔ DB 
//메뉴는 메뉴의 작업만 해야한다. ( 값 출력 )
//메뉴(View) 는 컨트롤러와만 통신한다.
public class ProductMenu {
	Scanner sc = new Scanner(System.in);
	private final ProductController proControll = new ProductController();

	public void main() {
		while (true) {
			System.out.println("=====의료도구 재고관리=====");
			System.out.println("1. 재고 조회");
			System.out.println("2. 재고 추가");
			System.out.println("3. 재고 수정");
			System.out.println("4. 재고 삭제");
			System.out.println("9. 뒤로가기");
			System.out.print("메뉴 선택 : ");
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				selectAllProduct(); // 사용자의 요청
				break;
			case 2:
				addProduct();
				break;
			case 3:
				modifyProduct();
				break;
			case 4:
				deleteProduct();
				break;
			case 9:

				return;
			default:
				System.out.println("올바른 메뉴번호를 선택하세요.");
				break;
			}
		}
	}// mainMenu 끝

	private void deleteProduct() {
		selectAllProduct();
		System.out.println("===== 재고 삭제 =====");
		System.out.println("삭제할 제품의 아이디를 입력하세요");
		int deleteId = sc.nextInt();

		int result = proControll.deleteProduct(deleteId); // 컨트롤러 떄리기
		if (result > 0) {
			System.out.println("삭제가 완료 되었습니다.");
		} else {
			System.out.println("삭제에 실패 했습니다.");
		}
	} // deleteProduct 끝

	private void modifyProduct() {
		selectAllProduct();
		int modifyAmount = 0;
		
		System.out.println("===== 재고 수정 =====");
		System.out.println("수정할 제품의 아이디를 입력하세요");
		int modifyId = sc.nextInt();
		
		while(true) {
			try {
				System.out.println("수정할 수량을 입력하세요");
				modifyAmount = sc.nextInt();	
				break;
			} catch (InputMismatchException e) {
				System.out.println("수량은 양의 정수만 입력해주세요");
				sc = new Scanner(System.in);
			}
		}

		int result = proControll.modifyProduct(modifyId, modifyAmount); // 컨트롤러 떄리기
		if (result > 0) {
			System.out.println("수정이 완료되었습니다.");
		} else {
			System.out.println("수정에 실패 했습니다.");
		}

	} // modifyProduct 끝

	private void selectAllProduct() {
		List<Product> list = proControll.selectAllProduct(); // 컨트롤러 떄리기
		if (list.isEmpty()) {
			System.out.println("조회된 재고가 없습니다");
		} else {
			displayProductList(list); // 서비스에 출력문이 있어서 Menu로 옮겼음.
		}
	} // selectAllProduct 끝

	private void addProduct() {
		System.out.println("===== 재고 추가 =====");
		System.out.print("제품이름 입력 : ");
		String name = sc.next();
		int amount = 0;
		
		while(true) { // 계속 입력받는다
			try {
				System.out.print("수량 입력 : ");
				amount = sc.nextInt();	
				break;
			} catch (InputMismatchException e) {  // 정수만 입력받아야 하는데 문자를 입력받을 시 예외처리
				System.out.println("수량은 양의 정수만 입력해주세요");
				sc = new Scanner(System.in); // 새로 Scanner 를 만든다
			}
		}
		
		

		Product product = new Product(name, amount); // pId 와 receiveDate 는 자동으로 생성하게 만들어놔서 2개값만 받도록 함

		// request
		int result = proControll.addProduct(product); // 컨트롤러 떄리기

		// response
		if (result == 1) {
			System.out.println("재고 추가가 완료되었습니다.");
		} else if (result == 0) {
			System.out.println("재고 추가를 실패했습니다.");
		}
	} // addProduct 끝
	
	public void displayProductList(List<Product> list) {
		System.out.println("\n조회된 전체 회원정보는 다음과 같습니다.");
		System.out.println("\n재품아이디\t제품이름\t현재수량\t입고날짜");
		System.out.println("---------------------------------");

		for (Product pro : list) {
			System.out.println(pro.toString());
		}
	} // displayProductList 끝

}
