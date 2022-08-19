package com.example.operation.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.operation.controller.OperationController;
import com.example.operation.model.DTO.OperationDTO;


/**
 * <pre>
 * Class : OperationMenu
 * Comment : 수술 일정 메인 화면
 * </pre>
 * @author 이규철
 * */
public class OperationMenu {
	 private final Scanner sc = new Scanner(System.in);
	 private OperationController oc = new OperationController();

	    public void main() {       // 수술 일정 메인 화면
	        while (true) {
	            System.out.print("*** 수술 일정 ***\n1. 수술 일정 열람\n2. 수술 일정 작성\n3. 수술 일정 수정\n4. 수술 일정 삭제" +
	                    "\n0. 뒤로가기\n메뉴 번호 선택 : ");
	            int menu = sc.nextInt();
	            switch (menu) {
	                case 1:
	                    searchOp();   // 수술 일정 열람 메뉴 호출
	                    break;
	                case 2:
	                	writeOp();   // 수술 일정 작성 메뉴 호출
	                    break;
	                case 3:
	                	updateOp();  // 수술 일정 수정 메뉴 호출
	                    break;
	                case 4:
	                	deleteOp();  // 수술 삭제 메뉴 호출
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

	    /** @author 이규철 **/
		private void deleteOp() {   // 삭제 컨트롤 호출
			System.out.print("환자의 이름을 입력하세요 : "); 
			sc.nextLine();
			String patientName = sc.nextLine();
			System.out.print("환자의 주민 번호를 입력하세요 : ");
			String patientNo = sc.nextLine();
			System.out.print("삭제할 날짜를 입력하세요 : ex(1997-05-29 01:30:22)");
			String date = sc.nextLine();
			int result = oc.deletOp(patientName,patientNo,date);   // 환자 이름, 삭제 날짜 입력 받는다.
			if(result>0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
		}

		/** @author 이규철 **/
		private void updateOp() {    // 수술 일정 업데이트 컨트롤 호출
			System.out.print("환자의 이름을 입력하세요: ");
			sc.nextLine();
			String patientName = sc.nextLine();
			System.out.print("환자의 주민 번호를 입력하세요 : ");
			String patientNo = sc.nextLine();
			System.out.print("수정할 날짜를 입력하세요 : ex(1997-05-29 01:30:22)");
			String date = sc.nextLine();
			System.out.print("바뀐 수술 날짜를 입력하세요 : ex(1997-05-29 01:30:22)");
			String newDate = sc.nextLine();
			int result = oc.updateOp(patientName,patientNo,date,newDate);  // 환자 이름, 주민번호, 수술 날짜, 바뀌고 싶은 날짜
			if(result>0) {
				System.out.println("업데이트 성공");
			}else {
				System.out.println("업데이트 실패");
			}
		}

		/** @author 이규철 **/
		private void writeOp() {   // 수술 일정 작성 컨트롤 호출
			sc.nextLine();
			System.out.print("날짜를 입력하세요 : ex(1997-05-29 01:30:22) ");
			String date = sc.nextLine();
			System.out.print("특이사항 입력하세요 : ");
			String uniqueness = sc.nextLine();
			System.out.print("수술 명을 입력하세요 : ");
			String op_name = sc.nextLine();
			System.out.print("의사의 이름을 입력하세요 : ");
			String doctorName = sc.nextLine();
			System.out.print("환자의 이름을 입력하세요 : ");
			String patientName = sc.nextLine();
			int result = oc.writeOp(date,uniqueness,op_name,doctorName,patientName); //날짜,특이사항,수술 명, 의사 이름, 환자 이름을 받습니다.
			if(result>0) {
				System.out.println("작성 완료");
			}else {
				System.out.println("작성 실패");
			}
		}

		/** @author 이규철 **/
		private void searchOp() {   // 수술 일정 열람 컨트롤 호출 
			System.out.print("환자 이름을 입력하세요 : ");
			sc.nextLine();
			String patientName = sc.nextLine();
			System.out.print("주민 번호를 입력하세요 : ");
			String patientNo = sc.nextLine();
			List<OperationDTO> orr = oc.searchOp(patientName,patientNo);  // 환자 이름, 환자 주민 번호를 입력 받습니다.
			if(orr != null) {
			for(int i = 0;i<orr.size();i++) 
			System.out.println(orr.get(i).toString());
		}}
	}