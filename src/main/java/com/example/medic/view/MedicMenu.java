
package com.example.medic.view;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.example.medic.controller.PharController;
import com.example.medic.model.dto.Medic;
import com.example.medic.model.dto.PharmacyData;

/**
 * @author 최영준
 *
 */
public class MedicMenu {

	public static Object main;
	PharController Pharcontroller = new PharController();
	private final Scanner sc = new Scanner(System.in);
	private final PharController pc = new PharController();

	// MEMO 약사 메인메뉴
	public void main() {
		while (true) {
			System.out.print("*** 약사 메뉴선택 ***\n1. 재고관리\n2. 제조확인서 출력하기 " + "\n0. 메인으로 돌아가기\n메뉴 번호 선택 : ");
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				callStock();
				break;
			case 2:
				selectScription();
				break;
			case 0:
				System.out.println("약사 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("올바른 메뉴번호를 선택하세요.");
				break;
			}
		}

	}

	// MEMO 처방전 출력 스크립트
	public void selectScription() {
		System.out.println("=========조제하기=========");
		System.out.println("▶처방전 번호를 입력하세요");
		System.out.println("▶");
		int patientNumber = sc.nextInt();
		// 환자번호를 입력받고 환자번호를 찾아서 스크립트 실행

		// 디스크립션 실행 함수..
		Pharcontroller.PrintScription(patientNumber);

	}

	// MEMO 재고관리 메인메뉴
	private void callStock() {
		while (true) {
			System.out.print("*** 재고관리 기능을 선택하세요***\n1. 총재고 확인하기\n2. 기존재고 주문 넣기\n3. 신제품 주문하기\n4. 제품 삭제하기"
					+ "\n0. 메인으로 돌아가기\n메뉴 번호 선택 : ");
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				checkStock();
				break;
			case 2:
				updateStock();
				break;
			case 3:
				insertNewPha();
				break;

			case 4:
				deleteStock();
				break;

			case 0:
				System.out.println("직업을 선택하러 갑니다!");
				return;
			default:
				System.out.println("올바른 메뉴번호를 선택하세요.");
				break;
			}
		}
	}

	// MEMO 재고관리 - 해당 품명을 전부 날리기
	private void deleteStock() {

		System.out.println("===== 삭제할 품목을 입력하세요 =====");

		System.out.println("▶ 제품 이름 입력 ");
		String phaName = sc.next();
		System.out.println("▶");

		Pharcontroller.deletePha(phaName);
	}

	// MEMO 재고관리 - 총재고확인하기
	private void checkStock() {
		Pharcontroller.selectAll();

	}

	// MEMO 재고관리 - 총재고확인하기 출력
	public void displayMemberList(List<Medic> list) {

		System.out.println("\n조회된 전체 회원정보는 다음과 같습니다.");
		System.out.println("  상품번호   상품명        상품종류    상품가격         재고수량");

//		System.out.println("│상품번호 \t 상품명 \t 상품종류 \t 상품가격 \t 재고수량 ");
		System.out.println("│------------------------------------------------------│");

		for (Medic m : list) { 
			System.out.printf(" %3s  \t %5s \t %4s \t %6s \t %5s \t \n" , m.getPhaNo(),m.getPhaName(),m.getPhaType(), m.getPhaPrice() , m.getPhaStock() );
			
			System.out.println("│------------------------------------------------------│");
		}

	}

	// MEMO 재고관리 - 기존재고 주문 넣기
	private void updateStock() {
		System.out.println("===== 주문등록 =====");

		System.out.println("▶ 제품 이름 입력");
		String phaName = sc.next();
		System.out.println("▶");

		System.out.print("추가할 수량을 입력 : ");
		int phaStock = sc.nextInt();
		System.out.println("▶");

		Medic medic = new Medic(phaName, phaStock);

//       Medic medic = new Medic(12, "두통약", "etc", 3000, 10);
		Pharcontroller.updatePha(medic);

	}

	// MEMO - 재고관리 - 기존재고 주문 넣기 - 총재고출력
	public void displayUpdatestock(Medic medic2) {

		System.out.println("==============================");
		System.out.println("재고입고완료");
		System.out.println("입력하신 상품명 " + medic2.getPhaName() + "의 총재고는 " + medic2.getPhaStock() + "개 입니다.");
		System.out.println();
		System.out.println();
		System.out.println("==================================");
	}

	// MEMO 재고관리 - 신제품 등록하기
	public void insertNewPha() {
		System.out.println("===== 주문등록 =====");

		System.out.println("제품 등록번호를 입력하세요 (ex 132)");
		int phaNo = sc.nextInt();
		System.out.println("▶");

		System.out.print("제품이름 입력 (ex 타이레놀) : ");
		String phaName = sc.next();
		System.out.println("▶");

		System.out.print("제품타입 입력 (pill / etc) : ");
		String phaType = sc.next();
		System.out.println("▶");

		System.out.print("제품가격 입력 (ex 3000) : ");
		int phaPrice = sc.nextInt();
		System.out.println("▶");

		System.out.print("입고 수량 입력 (ex 40) : ");
		int phaStock = sc.nextInt();
		System.out.println("▶");

		Medic medic = new Medic(phaNo, phaName, phaType, phaPrice, phaStock);
		Pharcontroller.insertNewPhar(medic);

	}

	// MEMO 2. 조제확인서 출력문
	// 서비스에서 처방전정보(printPD)를 받아옴
	public void displayScription(PharmacyData printPD) {
		// 처방전번호
		int patientNo = printPD.getPreNo();
		// 환자이름
		String patientName = printPD.getPatientName();
		// 환자주민등록번호
		String patientNumber = printPD.getPatientNo();
		// 의사이름
		String doctorName = printPD.getDoctorName();
		// 처방날짜
		Date preDate = printPD.getPreDate();
		// 약이름
		String phaName = printPD.getPhaName();
		// 약종류
		String phaType = printPD.getPhaType();
		// 처방기간
		int phaPeriod = printPD.getPhaPeriod();
		// 복용량
		int phaDayDosage = printPD.getPhaDayDosage();
		// 가격
		int phaPrice = printPD.getPhaPrice();
		// 재고량
		int phaStock = printPD.getPhaStock();

		System.out.println("=========== 조제내역서를 출력합니다 ===========");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("===============    조  제  내  역  서     ===============");
		System.out.println("=======================================================");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("┃ 환 ┃   이름     :             " + patientName + "                ┃");
		System.out.println("┃ 자 ┃ 주민등록번호 :        " + patientNumber + "            ┃      ");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("┃ 처방날짜  : " + preDate + "                            ┃");
		System.out.println("┃ 의사이름  : " + doctorName + "                                     ┃");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("┃   상품명     하루복용량    처방일수   1알가격     총약값  ┃ ");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("┃   " + phaName + " :      " + phaDayDosage + "          " + phaPeriod + "      " + phaPrice
				+ "      " + (phaPrice * phaDayDosage * phaPeriod) + " ┃");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("");
		System.out.println("");

	}

	// MEMO 잘못된 입력값에 대한 실패출력문
	public void displayError(String message) {

		System.out.println("서비스 요청 실패 : " + message);
	}

	public void displayInfo(String message) {

		System.out.println(message);
	}

}
