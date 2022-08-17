package main.java.com.example.funeralDirector.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.com.example.funeralDirector.model.controller.FunelralController;
import main.java.com.example.funeralDirector.model.controller.SubsidyController;
import main.java.com.example.funeralDirector.model.dao.SubsidyDao;
import main.java.com.example.funeralDirector.model.dto.FunelralDto;
import main.java.com.example.funeralDirector.model.dto.SubsidyDto;

public class FuneralDirectorMenu {
	public Scanner s = new Scanner(System.in);
	private final FunelralController fc = new FunelralController();
	private final SubsidyController sdc = new SubsidyController();
	SubsidyDto sd = new SubsidyDto();
	
	// 메인 메뉴
	public void main() {
		while (true) {
			System.out.println("=====장례 관리자=====");
			System.out.println("1.사망자 명단");
			System.out.println("2.부조금 관리");
			System.out.println("0.이전 페이지");
			System.out.println("메뉴 번호를 선택하세요");

			int menunum = s.nextInt();

			// 메뉴 선택
			switch (menunum) {
			case 1:
				사망자명단();
				break;
			case 2:
				부조금관리();
				break;
			case 0:
				return;
			default:
				System.out.println("올바른 메뉴번호를 선택하세요");
				break;
			}
		}
	}

	// 사망자 명단 메뉴
	public void 사망자명단() {
		while (true) {
			System.out.println("=====사망자 명단=====");
			System.out.println("1.사망자 조회");
			System.out.println();
			System.out.println("2.사망자 추가");
			System.out.println();
			System.out.println("3.사망자 총 조회");
			System.out.println();
			System.out.println("메뉴 번호를 선택하세요");
			
			int numu2num = s.nextInt();
			
			

			switch (numu2num) {
			case 1:
				inputDeathinfo() ;
				break;
			case 2:
				inputDeathAdd();
				break;
			case 3:
				fc.selectAll();
			case 0:
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

	

	// 부조금 관리 메뉴
	public void 부조금관리() {
		while (true) {
			System.out.println("=====부조금 관리=====");
			System.out.println("1.부조금 총 조회");
			System.out.println();
			System.out.println("2.부조금 추가");
			System.out.println();
			System.out.println("3.부조금 수정");
			System.out.println();
			System.out.println("4.부조금 삭제");
			System.out.println();
			System.out.println("메뉴 번호를 선택하세요");
			int numu3num = s.nextInt();
			switch (numu3num) {
			case 1:
				SubsidyList();
				break;
			case 2:
				InsertSubsidyList();
				break;
			case 3:
				updateDeath();
				break;
			case 4:
				deleteDeath();
				break;
			case 0:
				return;
			default:
				System.out.println("메뉴 번호를 잘못 입력하셨습니다");
				return;

			}
		}
	}
	// 이전페이지 메뉴

	// 사망자 명단 전체 정보 조회
	public void DeathList(List<FunelralDto> list) {
		System.out.println("사망자 명단 전체 정보조회");
		for (FunelralDto m : list) {
			System.out.println("사망자 정보" + m);
		}
	}
	//사망자 추가 
	private void inputDeathAdd() {
		System.out.print("추가할 사망자 아이디을 입력하세요");
		int funelralId2 = s.nextInt();
		System.out.print("추가할 사망자 사망사유을 입력하세요");
		String deathReason = s.next();
		System.out.print("추가할 사망자 환자아이디을 입력하세요");
		int patientId = s.nextInt();

		fc.registerDeath(funelralId2,deathReason, patientId);
	}

	// 사용자 아이디로 명단 하나 조회
	private void inputDeathinfo() {
		System.out.print("사망자의 이름 입력:");
		String patientName = s.next();	
		System.out.print("사망자의 주민번호 입력:");
		String patientNo = s.next();
		
		FunelralDto funelralDto = fc.selectOne(patientName, patientNo);
		System.out.println(funelralDto.getFunelralId());
		System.out.println(funelralDto.getDateDeath());
		System.out.println(funelralDto.getDeathReason());
		System.out.println(funelralDto.getPatientId());
	}
	// public void DeathOne

	// 에러뜰 때
	public void DeathError(String message) {
		System.out.println("너 사망자 부분 요청 실패함" + message);
	}

	// 보조금 부분 에러뜰 때
	public void SubsidyError(String message) {
		System.out.println("너 부조금 부분 실패함" + message);
	}

	// 부조금
	// 1.부조금 총 조회
	public void SubsidyList() {
		ArrayList<SubsidyDto> list;
		list = sdc.selectSubsidy();
		System.out.println("");
//		for (SubsidyDto sd : list) {
//			System.out.println("=======부조금 총 조회======");
//			System.out.println(sd);
//		}
		
		for(int i = 0;i<list.size();i++) {
			System.out.println(list.get(i).getSubsidyName());
			System.out.println(list.get(i).getSubsidy());
			System.out.println(list.get(i).getAccount_number());
			System.out.println(list.get(i).getAccount_bank());

			//System.out.println(list.get(i).getClass());
		}
	}

	// 2.부조금 삽입
	public void InsertSubsidyList() {
		System.out.println("부조한 사람의 이름을 입력하세요");
		String insertyname = s.next();
		System.out.println("부조한 금액을 입력하세요");
		int insertprice = s.nextInt();
		System.out.println("입금 된 계좌 정보를 입력하세요");
		String insertAccountInfo = s.next();
		System.out.println("입금 된 은행를 입력하세요");
		String insertAccountBank = s.next();

		SubsidyDto subsidyDto = new SubsidyDto(insertyname, insertprice, insertAccountInfo, insertAccountBank);
		System.out.println("부조금 정보가 추가 되었습니다");
		

		sdc.registerSubsidy(subsidyDto);
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("┃"+insertyname);
		System.out.println(insertprice);
		System.out.println(insertAccountInfo);
		System.out.println(insertAccountBank);
		

	}
	
	//update
	public void updateDeath() {
		System.out.println("부조금 수정할 이름을 입력하세요");
		String name =s.next();
		
		System.out.println("부조금 수정할 금액을 입력하세요");
		int price =s.nextInt();
		System.out.println("부조금 수정할 계좌번호을 입력하세요");
		String info =s.next();
		System.out.println("부조금 수정할 은행을 입력하세요");
		String bank =s.next();
		
		int result = sdc.updateDeath(price, info, bank, name);
		if(result>0) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
		//update insert delete는 결과값으로 성공하면 1 실패하면 0을 반환한다 
	}
	
	
	//delete
	public void deleteDeath() {
		System.out.println("부조금 삭제할 이름을 입력하세요");
		String name =s.next();
		
		
		
		int result = sdc.deleteDeath(name);
		if(result > 0) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}

}
//사망자 명단 조회 메뉴
//if(numu2num == 1){
//  System.out.print("사망자 명단 조회 할 아이디를 입력하세요:");
//  String funelralId = s.next();
//  System.out.print("=====사망자 조회=====");
//
//}else if(numu2num == 2){
//	System.out.print("추가할 사망자 아이디을 입력하세요");
//	int funelralId = s.nextInt();
////	System.out.print("추가할 사망자 사망날짜을 입력하세요");
////	String dateStr = s.next(); //string으로 받아서 
////	SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy/mm/dd");//형식설정
////	java.util.Date dateDeath = null; //dateDeath에 데이트 초기화만 해줌
////	java.util.Date utilDate = null;
////	
////	try {
////		dateDeath = simpledateformat.parse(dateStr);
////		utilDate = new java.util.Date(dateDeath.getTime());
////		//dateDeath가 dateStr 분석해라 simpledateformat("yyyy/mm/dd")형식으로 포맷해라
////	} catch (ParseException e) {
////		// TODO Auto-generated catch block
////		e.printStackTrace();
////	}
//	System.out.print("추가할 사망자 사망사유을 입력하세요");
//	String deathReason = s.next();
//	System.out.print("추가할 사망자 환자아이디을 입력하세요");
//	int patientId = s.nextInt();
//	
//  // dateDeath 두번쨰 
//  FunelralDto funelraldto = new FunelralDto (funelralId,null,deathReason,patientId);
//  System.out.println("사망자가 추가 되었습니다");
//  
//  fc.registerDeath(funelraldto);
//}else if(numu2num == 3){
//	fc.selectAll();
//}
//else {
//  System.out.println("메뉴 번호를 잘못 입력하셨습니다");
//}
//}
//
//부조금 관리 메뉴
//public void 부조금관리(){
//System.out.println("=====부조금 관리=====");
//System.out.println("1.부조금 총 조회");
//System.out.println();
//System.out.println("2.부조금 추가");
//System.out.println();
//System.out.println("메뉴 번호를 선택하세요");
//int numu2num = s.nextInt();
//
//
//
//if(numu2num == 1){
//	SubsidyList();
////  System.out.println("부조금 조회할 부조금 이름숫자로 입력");
////  int subsidyName = s.nextInt();
////  System.out.println("주민번호를 입력하세요");
////  int accountNumber = s.nextInt();
//}else if(numu2num == 2){
//	InsertSubsidyList();
//}else {
//  System.out.println("메뉴 번호를 잘못 입력하셨습니다");
//  return;
//}
//}
