package com.example.funeralDirector.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.funeralDirector.model.controller.FunelralController;
import com.example.funeralDirector.model.controller.SubsidyController;
import com.example.funeralDirector.model.dao.SubsidyDao;
import com.example.funeralDirector.model.dto.FunelralDto;
import com.example.funeralDirector.model.dto.SubsidyDto;

/** @author fla90*/
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

	//서브메뉴. 사망자 명단 메뉴
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

	

	//서브메뉴.  부조금 관리 메뉴
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
			System.out.println("5.사망자별 부조금 조회");
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
			case 5:
				selectBypatient();
				break;
			case 0:
				return;
			default:
				System.out.println("메뉴 번호를 잘못 입력하셨습니다");
				return;

			}
		}
	}

	
	//사망자 명단 1. 사용자 아이디로 명단 하나 조회
		private void inputDeathinfo() {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("사망자의 이름 입력:");
			String patientName = s.next();	
			System.out.print("사망자의 주민번호 입력:");
			String patientNo = s.next();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println();
			System.out.println();
			System.out.println();
			
			FunelralDto funelralDto = fc.selectOne(patientName, patientNo);
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("사망자 아이디 : "+funelralDto.getFunelralId());
			System.out.println("사망 날짜 : "+funelralDto.getDateDeath());
			System.out.println("사망 사유 : "+funelralDto.getDeathReason());
			System.out.println("환자 아이디 : "+funelralDto.getPatientId());
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		}
	
	
	//사망자 명단 2. 사망자 추가 
	private void inputDeathAdd() {
		System.out.print("추가할 사망자 아이디을 입력하세요");
		int funelralId2 = s.nextInt();
		System.out.print("추가할 사망자 사망사유을 입력하세요");
		String deathReason = s.next();
		System.out.print("추가할 사망자 환자아이디을 입력하세요");
		int patientId = s.nextInt();
		System.out.println();
		System.out.println();
		System.out.println();

		fc.registerDeath(funelralId2,deathReason, patientId);
		
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("사망자 아이디 : "+ funelralId2);
		//System.out.println("사망 날짜 : "+ deathReason);sysdate()
		System.out.println("사망 사유 : "+ deathReason);
		System.out.println("환자 아이디 : "+ patientId);
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}

	
	
	//사망자 명단 3.사망자 명단 총 조회()
		public void DeathList(List<FunelralDto> list) {
			System.out.println("사망자 명단 전체 정보조회");
			for (FunelralDto m : list) {
				System.out.println(list.indexOf(m)+1);
				System.out.println("");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println();
				System.out.println("사망자 정보 : " +"사망자이름 : "+ m.getPatientName()+" | "+"사망자 ID : "+m.getFunelralId()+" | "+"사망날짜 : "+m.getDateDeath()+" | "+"사망사유 : "+m.getDeathReason()+" | "+"환자 ID : "+m.getPatientId()+" | ");
				System.out.println();
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				
			}
		}
	
		
		

	// 에러뜰 때
	public void DeathError(String message) {
		System.out.println("너 사망자 부분 요청 실패함" + message);
	}

	
	// 보조금 부분 에러뜰 때
	public void SubsidyError(String message) {
		System.out.println("너 부조금 부분 실패함" + message);
	}

	
	//부조금 관리  1.부조금 총 조회
	public void SubsidyList() {
		ArrayList<SubsidyDto> list;
		list = sdc.selectSubsidy();
		System.out.println("");

		for(int i = 0;i<list.size();i++) {
			System.out.println("부조금 총 조회 "+(i+1));
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("부조한 사람 : "+list.get(i).getSubsidyName());
			System.out.println("부조한 금액 : "+list.get(i).getSubsidy());
			System.out.println("계좌 번호 : "+list.get(i).getAccount_number());
			System.out.println("계좌 은행 : "+list.get(i).getAccount_bank());
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println();

			//System.out.println(list.get(i).getClass());
		}
	}
	
	

	//부조금 관리 2.부조금 수정(update)
	public void InsertSubsidyList() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("부조한 사람의 이름을 입력하세요");
		String insertyname = s.next();
		System.out.println("부조한 금액을 입력하세요");
		int insertprice = s.nextInt();
		System.out.println("입금 된 계좌 정보를 입력하세요");
		String insertAccountInfo = s.next();
		System.out.println("입금 된 은행를 입력하세요");
		String insertAccountBank = s.next();
		System.out.println("환자 아이디를  입력하세요");
		int insertPatient = s.nextInt();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		SubsidyDto subsidyDto = new SubsidyDto(insertyname, insertprice, insertAccountInfo, insertAccountBank,insertPatient);
		System.out.println("부조금 정보가 추가 되었습니다");
		

		sdc.registerSubsidy(subsidyDto);
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(insertyname);
		System.out.println(insertPatient);
		System.out.println(insertprice);
		System.out.println(insertAccountInfo);
		System.out.println(insertAccountBank);
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		

	}
	
	//부조금 관리 3.부조금 수정(update)
	public void updateDeath() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("부조금 수정할 이름을 입력하세요 : ");
		String name =s.next();
		System.out.print("부조금 수정할 금액을 입력하세요 : ");
		int price =s.nextInt();
		System.out.print("부조금 수정할 계좌번호을 입력하세요 : ");
		String info =s.next();
		System.out.print("부조금 수정할 은행을 입력하세요 : ");
		String bank =s.next();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();
		
		int result = sdc.updateDeath(price, info, bank, name);
		if(result>0) {
			System.out.println("부조금 정보가 수정 완료 되었습니다");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("부조한 사람 : "+name);
			System.out.println("부조한 금액 : "+price);
			System.out.println("계좌 번호 : "+info);
			System.out.println("계좌 은행 : "+bank);
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		}else {
			System.out.println("실패");
		}
	}
	
	
	//부조금 관리 4.부조금 삭제(delete)
	public void deleteDeath() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();
		System.out.println("부조금 삭제할 이름을 입력하세요");
		String name =s.next();
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		
		
		int result = sdc.deleteDeath(name);
		if(result > 0) {
			System.out.println("부조금 정보가 삭제 완료 되었습니다");
		}else {
			System.out.println("실패");
		}
	}
	
	//부조금 관리 5.환자 이름별 부조금 총 조회
			public void selectBypatient() {
				System.out.println("부조금이 궁금한 환자 이름를 입력하세요");
				String name = s.next();
				
				ArrayList<SubsidyDto> list;
				list = sdc.selectBypatient(name);
				System.out.println("");
				
				for(int i = 0;i<list.size();i++) {
					
					System.out.println(i);
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.println(list.get(i).getSubsidyName());
					System.out.println(list.get(i).getSubsidy());
					System.out.println(list.get(i).getAccount_number());
					System.out.println(list.get(i).getAccount_bank());
					System.out.println(list.get(i).getFunelral_id());
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					//System.out.println(list.get(i).getClass());
				}
		}
}

