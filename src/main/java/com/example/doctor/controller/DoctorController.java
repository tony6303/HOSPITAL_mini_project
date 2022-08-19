package com.example.doctor.controller;

import java.util.List;

import com.example.doctor.model.DTO.MedicalRecordsDTO;
import com.example.doctor.service.DoctorService;

/**
 * <pre>
 * Class : DoctorController
 * Comment : 메뉴에서 선택한 서비스로 연결.
 * </pre>
 * @author 이규철
 * */
public class DoctorController {
	private final DoctorService doctorService = new DoctorService();  // 의사 서비스 객체 생성
	
	/** @author 이규철 **/
	public List<MedicalRecordsDTO> searchRecords(String resName,String resNo) { //진료 기록 열람 서비스 호출
		return doctorService.checkRecords(resName,resNo);
	}
	
	/** @author 이규철 **/
	public int writeRecords(String str,String resNo,String diseaseName) {    // 진료 기록 작성 서비스 호출
		return doctorService.writeRecords(str,resNo,diseaseName);
	}
	
	/** @author 이규철 **/
	public int writePha(String str,String pha,int period,int day,String doctorName) {  // 처방전 작성 서비스 호출
		return doctorService.writePrescribtion(str,pha,period,day,doctorName);
	}
	
}
