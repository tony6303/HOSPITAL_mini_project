package com.example.doctor.view;

import java.util.List;
import java.util.Scanner;

import com.example.doctor.controller.DoctorController;
import com.example.doctor.model.DTO.MedicalRecordsDTO;
import com.example.operation.view.OperationMenu;


public class DoctorMenu {
	 private final Scanner sc = new Scanner(System.in);
	    private final DoctorController pc = new DoctorController();
	    private final OperationMenu om = new OperationMenu();
	    public void main() {
	        while (true) {
	            System.out.print("*** 환자 페이지 ***\n1. 진료 기록 열람\n2. 진료 기록 작성\n3. 처방전 작성\n4. 수술 일정" +
	                    "\n0. 뒤로가기\n메뉴 번호 선택 : ");
	            int menu = sc.nextInt();
	            switch (menu) {
	                case 1:
	                    searchMR();
	                    break;
	                case 2:
	                	writeMR();
	                    break;
	                case 3:
	                	writePha();
	                    break;
	                case 4:
	                	operationMain();
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
	    
	    private void operationMain() {
			om.main();
		}

		private void writePha() {
			// TODO Auto-generated method stub
			System.out.println("===== 처방전 작성 =====");
			System.out.print("환자 이름을 입력하세요 : ");
			sc.nextLine();
			String str = sc.nextLine();
			System.out.print("약 이름을 입력하세요 : ");
			String pha = sc.nextLine();
			System.out.print("하루 복용량을 입력하세요 : ");
			int  day= sc.nextInt();
			System.out.print("복용 주기를 입력하세요 : ");
			int period = sc.nextInt();
			sc.nextLine();
			System.out.print("의사 이름를 입력하세요 : ");
			String doctorName = sc.nextLine();
			int result = pc.writePha(str, pha, period, day,doctorName);
			if(result>0) {
				System.out.println("작성 성공");
			}else {
				System.out.println("작성 실패");
			}
		}

		private void writeMR() {
			// TODO Auto-generated method stub
			System.out.println("===== 진료 기록 작성 =====");
			System.out.print("환자에 이름을 입력하세요: ");
			sc.nextLine();
			String str = sc.nextLine();
			System.out.print("환자의 병명을 입력하세요: ");
			String diseaseName = sc.nextLine();
			int result = pc.writeRecords(str,diseaseName);
			if(result>0) {
				System.out.println("작성 성공");
			}else {
				System.out.println("작성 실패");
			}
		}

		private void searchMR() {
	    	System.out.println("===== 진료 기록 열람=====");
	    	System.out.print("환자에 이름을 입력하세요:");
	    	sc.nextLine();
	    	String resName = sc.nextLine();
	    	List<MedicalRecordsDTO> mrr = pc.searchRecords(resName);
	    	for(int i = 0; i<mrr.size();i++) {
				System.out.print(mrr.get(i).toString());
			}
	    }
	}