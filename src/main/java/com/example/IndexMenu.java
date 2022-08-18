package com.example;

import java.util.Scanner;

import com.example.doctor.view.DoctorMenu;
import com.example.funeralDirector.view.FuneralDirectorMenu;
import com.example.patient.view.PatientMenu;
import com.example.medic.view.MedicMenu;
import com.example.product.view.ProductMenu;
import com.example.utils.AsciiArtUtils;
import com.example.cashier.view.CashierMenu;

public class IndexMenu {

	private final Scanner sc = new Scanner(System.in);
	private final PatientMenu pm = new PatientMenu();
	private final DoctorMenu dm = new DoctorMenu();
	private final MedicMenu mm = new MedicMenu();
	private final ProductMenu prom = new ProductMenu();
	private final CashierMenu cm = new CashierMenu();

	public void mainMenu() {
		while (true) {
			AsciiArtUtils.show("hospital2.txt");
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				pm.main();
			case 2:
				cm.main();
				break;
			case 3:
				dm.main();
				break;
			case 4:
				mm.main();
				break;
			case 5:
				prom.main();
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
