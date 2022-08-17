package com.example.doctor.controller;

import java.util.List;

import com.example.doctor.model.DTO.MedicalRecordsDTO;
import com.example.doctor.service.DoctorService;

public class DoctorController {
	private final DoctorService doctorService = new DoctorService();
	
	public List<MedicalRecordsDTO> searchRecords(String resNo) {
		return doctorService.checkRecords(resNo);
	}
	public int writeRecords(String str,String diseaseName) {
		return doctorService.writeRecords(str,diseaseName);
	}
	public int writePha(String str,String pha,int period,int day,String doctorName) {
		return doctorService.writePrescribtion(str,pha,period,day,doctorName);
	}
//	public int writeOperation(Operation op) {
//		
//	}
//	public void searchOperation(String resNo) {
//		
//	}
//	public int deleteOperation() {
//		
//	}
//	public int updateOperation(Operation op) {
//		
//	}
}
