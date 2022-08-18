package com.example.doctor.controller;

import java.util.List;

import com.example.doctor.model.DTO.MedicalRecordsDTO;
import com.example.doctor.service.DoctorService;

public class DoctorController {
	private final DoctorService doctorService = new DoctorService();
	
	public List<MedicalRecordsDTO> searchRecords(String resName,String resNo) {
		return doctorService.checkRecords(resName,resNo);
	}
	public int writeRecords(String str,String resNo,String diseaseName) {
		return doctorService.writeRecords(str,resNo,diseaseName);
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
