package com.example.patient.view;

import com.example.patient.controller.PatientController;
import com.example.patient.model.dto.Patient;
import com.example.patient.model.dto.Reservation;

import java.util.List;
import java.util.Scanner;

public class PatientMenu {
    private final Scanner sc = new Scanner(System.in);
    private final PatientController pc = new PatientController();

    public void main() {
        while (true) {
            System.out.print("*** 환자 페이지 ***\n1. 환자 정보 등록\n2. 예약 정보 확인\n3. 예약 취소" +
                    "\n4. 환자 리스트 출력\n0. 뒤로가기\n메뉴 번호 선택 : ");
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    showReversion();
                    break;
                case 3:
                    cancelReversion();
                    break;
                case 4:
                    showPatientList();
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

    private void cancelReversion() {
        System.out.println("===== 예약 취소 =====");
        System.out.print("예약자 주민번호 입력 : ");
        String resNo = sc.next();

        int result = pc.cancelReservation(resNo); // request

        // response
        if (result == 0) {
            System.out.println("예약이 정상적으로 취소되었습니다.");
        } else if (result == 1) {
            System.out.println("예약 취소에 실패했습니다.");
        }
    }

    private void showReversion() {
        System.out.println("===== 예약 정보 확인 =====");
        System.out.print("예약자 주민번호 입력 : ");
        String resNo = sc.next();

        Patient patient = pc.findPatient(resNo); // 환자정보 조회1
        if (patient == null) {
            System.out.println("환자 정보가 존재하지 않습니다");
            return;
        }

        Reservation reservation = pc.findReservation(resNo); // request
        if (reservation == null) {
            System.out.println(patient.getPatientName() + "님 예약정보가 존재하지 않습니다.");
            return; // 메서드 종료
        }

        System.out.println(patient.getPatientName() + "님 예약정보입니다.");
        System.out.println(reservation.getReservationDate() + "에 예약되어 있습니다. 감사합니다.");
    }

    private void registerPatient() {
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
        System.out.println("환자정보 등록이 완료되었습니다.");
    }

    private void showPatientList() {
        System.out.println("===== 환자 리스트 확인 =====");
        List<Patient> patientList = pc.findPatientList();
        for (Patient patient : patientList) {
            System.out.println("환자번호 : " + patient.getPatientNo() + ", 이름 : " + patient.getPatientName() + ", 주민번호 : "
                    + patient.getPatientNo() + ", 연락처 : " + patient.getPhone());
        }
    }
}