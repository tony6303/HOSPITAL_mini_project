package com.example.cashier.view;


import java.util.List;
import java.util.Scanner;

import com.example.cashier.controller.CashierController;
import com.example.cashier.model.dto.Price;
import com.example.patient.model.dto.Patient;


public class CashierMenu {
	private final Scanner sc = new Scanner(System.in);  // 스캐너 객체 sc로 입력받음 접근제한자는 private이며 final로인해 한번 초기화된 변수는 변경할수없는 상수값이 됨.
	private final CashierController cc = new CashierController(); // 컨트롤러에 있는 클래스명을 이용한 객체 cc 생성

	public void main() {  // true가 될때까지반복   // 정수형으로 입력받아 해당하는 번호를 실행하고 빠져나옴
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
			System.out.println(result);
		}else {
			System.out.println(result);
			System.out.println("함께 갑니다.");
		}
		
	}

	private void priceUpdate() {
		System.out.println("진료비를 바꿀 병명을 입력하세요 : ");
		sc.nextLine();
		String disName = sc.nextLine();
		int result = cc.priceUpdate(disName);
		if(result > 0) {
			System.out.println("진료비가 변경되었습니다.");
		}else {
			System.out.println("질병명을 제대로 입력해주세요.");
		}
		
	}

	private void clinicCost() {
		System.out.print("주민번호를 입력하세요 : ");
		sc.nextLine();
		String resNo = sc.nextLine();
		Price ca = cc.price(resNo);
		//System.out.println("질병 이름 : " + ca.getDisease_name());
		System.out.println("진료 날짜 : " + ca.getMr_date());
		System.out.println("환자 번호 : " + ca.getPatient_id());
		System.out.println("환자 이름 : " + ca.getPatient_no() + "님");
		System.out.println("환자 주민번호 : " + ca.getPatient_name());
		System.out.println("질병 이름 : " + ca.getDisease_name());
		System.out.println("진료 비용 : " + ca.getPrice() + "원 입니다.");


	}

	private void registerReservation() {  //예약등록 메서드 생성
		System.out.print("주민번호를 입력하세요 : ");
		sc.nextLine();
		String resNo = sc.nextLine();
		Patient resNoCheck = cc.checkResNo(resNo);
		
		
		if(resNoCheck == null) {
			System.out.println("조회되지 않는 환자입니다");
		}else {
			int result = cc.registerReservation(resNo);
			if(result > 0) {
				System.out.println("예약이 완료되었습니다.");
			}else {
				System.out.println("예약이 실패되었습니다.");
			}
		}
		
		
	}
	
	

}
