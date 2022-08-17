package main.java.com.example.cashier.view;


import java.util.Scanner;

import main.java.com.example.cashier.controller.CashierController;
import main.java.com.example.cashier.model.dto.Price;


public class CashierMenu {
	private final Scanner sc = new Scanner(System.in);
	private final CashierController cc = new CashierController();

	public void main() {
		while (true) {
			System.out.println("*** 수납원 페이지 ***\n"
					+ "1. 환자 예약 등록\n"
					+ "2. 진료비 변경 안내\n"
					+ "3. 수납 비용 청구\n"
					+ "4. 수납원 해고\n" 
					+ "0. 뒤로가기\n"
					+ "메뉴 번호 선택 : ");
			
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				registerReservation();
				break;
			case 2:
				priceUpdate();
				break;
			case 3:
				clinicCost();
				break;
			case 4:
				cashierFire();
				break;
			case 0:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("올바른 메뉴번호를 선택하세요.");
				break;
				
			}
			
		}

	}

	private void cashierFire() {
		System.out.println("수납원의 급여를 입력하세요 : -");
		int salary = sc.nextInt();
		int result = cc.salaryselect(salary);
		if (result > 0) {
			System.out.println("나가주세요");
		}else System.out.println("함께 갑니다.");
		
	}

	private void priceUpdate() {
		System.out.println("진료비를 바꿀 병명을 입력하세요 : ");
		sc.nextLine();
		String disName = sc.nextLine();
		int result = cc.priceUpdate(disName);
		if(result > 0) {
			System.out.println("진료비가 변경되었습니다.");
		}else {
			System.out.println("진료비가 유지됩니다.");
		}
		
	}

	private void clinicCost() {
		System.out.print("주민번호를 입력하세요 : ");
		sc.nextLine();
		String resNo = sc.nextLine();
		Price ca = cc.price(resNo);
		System.out.println(ca.getDisease_name() + ca.getMr_date() + ca.getPatient_id() + ca.getPatient_name() + ca.getPatient_no() +ca.getPrice());
		
	}

	private void registerReservation() {  //예약등록 메서드 생성
		System.out.print("주민번호를 입력하세요 : ");
		sc.nextLine();
		String resNo = sc.nextLine();
		int result = cc.registerReservation(resNo);
		if(result > 0) {
			System.out.println("예약이 완료되었습니다.");
		}else {
			System.out.println("예약이 실패되었습니다.");
		}
		
	}
	
	

}
