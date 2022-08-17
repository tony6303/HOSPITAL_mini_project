package com.example;

import java.util.Scanner;

import com.example.cashier.view.CashierMenu;
import com.example.patient.view.PatientMenu;


public class IndexMenu {
    private final Scanner sc = new Scanner(System.in);
    private final PatientMenu pm = new PatientMenu();
    private final CashierMenu cm = new CashierMenu();


    public void mainMenu() {
        while (true) {
            System.out.print("*** 병원 관리 프로그램 ***\n1. 약사\n2. 의사\n3. 수납원" +
                    "\n4. 재고관리\n5. 환자\n6. 장의사\n0. 끝내기\n메뉴 번호 선택 : ");
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                	cm.main();
                    break;
                case 4:
                    break;
                case 5:
                    pm.main();
                    break;
                case 6:
                    break;
                case 0:
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("올바른 메뉴번호를 선택하세요.");
                    break;
            }
        }
    } // mainMenu 끝  
    
}
