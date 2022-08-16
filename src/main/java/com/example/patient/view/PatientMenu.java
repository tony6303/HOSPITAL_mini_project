package com.example.patient.view;

import com.example.patient.controller.PatientController;
import com.example.patient.model.dto.Patient;

import java.util.Scanner;

public class PatientMenu {
    private final Scanner sc = new Scanner(System.in);
    private final PatientController pc = new PatientController();

    public void main() {
        while (true) {
            System.out.print("*** 환자 페이지 ***\n1. 환자 정보 등록\n2. 예약 정보 확인\n3. 예약 취소" +
                    "\n0. 뒤로가기\n메뉴 번호 선택 : ");
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    환자정보등록();
                    break;
                case 2:
                    예약정보확인();
                    break;
                case 3:
                    예약취소();
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

    private void 예약취소() {
        System.out.println("===== 예약 취소 =====");
        System.out.print("예약자 주민번호 입력 : ");
        String resNo = sc.next();

        int result = pc.cancelReservation(resNo); // request

        // response
        if (result == 0) {
            System.out.println("예약 취소에 실패했습니다.");
        } else if (result == 1) {
            System.out.println("예약이 취소되었습니다.");
        }
    }

    private void 예약정보확인() {
        System.out.println("===== 예약 정보 확인 =====");
        System.out.print("예약자 주민번호 입력 : ");
        String resNo = sc.next();

        // TODO : 예약정보 Entity Type으로 전달 받아서, view 출력
        pc.searchReservation(resNo); // request

        // 예약정보가 정상적으로 존재 할 시,
        // 000님 예약정보입니다.
        // 진료과 : 이비인후과, 예약시간 4시

        // 예약정보가 null일 시,
        // 예약정보가 존재하지 않습니다.
    }

    private void 환자정보등록() {
        System.out.println("===== 환자 정보 등록 =====");
        System.out.print("이름 입력 : ");
        String name = sc.next();
        System.out.print("주민번호 입력 : ");
        String resNo = sc.next();
        System.out.print("연락처 입력 : ");
        String phone = sc.next();

        Patient patient = new Patient(null, resNo, name, phone);

        // request
        int result = pc.registerPatient(patient);

        // response
        if (result == 0) {
            System.out.println("환자정보 등록이 완료되었습니다.");
        } else if (result == 1) {
            System.out.println("환자정보 등록에 실패했습니다.");
        }
    }
}