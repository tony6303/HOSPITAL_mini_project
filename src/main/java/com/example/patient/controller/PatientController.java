package com.example.patient.controller;

import com.example.patient.model.dto.Patient;
import com.example.patient.model.dto.Reservation;
import com.example.patient.service.PatientService;

import java.util.List;

public class PatientController {
    private final PatientService patientService = new PatientService();

    /**
     * 환자 등록
     * @param patient : 환자객체
     * @return : 성공시 1 실패시 0
     */
    public int registerPatient(Patient patient) {
        return patientService.createPatient(patient);
    }

    /**
     * 예약 확인
     * @param resNo : 주민번호
     * @return : 환자주민번호에 해당하는 예약객체
     */
    public Reservation findReservation(String resNo) {
        return patientService.checkReservation(resNo);
    }


    /**
     * 예약 취소
     * 예약 DAO 에서 주민번호로 DTO 가져온다음 null 이 아니면 삭제
     * @param resNo : 환자 주민번호
     * @return : NPE 등 에러 발생시 1 리턴, 정상적인 경우 0 리턴
     */
    public int cancelReservation(String resNo) {
        return patientService.deleteReserve(resNo);

    }


    /**
     * 환자 조회
     * @param resNo : 환자 주민번호
     * @return : 입력받은 주민번호에 대한 환자객체
     */
    public Patient findPatient(String resNo) {
        return patientService.findPatientByPatientNo(resNo);
    }

    /**
     * 환자리스트 조회
     * @return : 환자 리스트
     */
    public List<Patient> findPatientList() {
        return patientService.findAll();
    }
}
