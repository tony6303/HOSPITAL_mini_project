package main.java.com.example.doctor.controller;

import main.java.com.example.doctor.model.DTO.MedicalRecordsDTO;
import main.java.com.example.doctor.service.DoctorService;

public class DoctorController {
	private final DoctorService doctorService = new DoctorService();

//    public int registerPatient(Doctor patient) {
//        return DoctorService.createPatient(patient);
//    }
//
//    public void searchReservation(String resNo) {
//        DoctorService.checkReservation(resNo);
//    }
//
//    public int cancelReservation(String resNo) {
//        return DoctorService.deleteReserve(resNo);
//
//    }
	
	public void searchRecords(String resNo) {
		doctorService.checkRecords(resNo);
	}
	public void writeRecords(String str,String diseaseName) {
		doctorService.writeRecords(str,diseaseName);
	}
	public void writePha(String str,String pha,int period,int day,String doctorName) {
		doctorService.writePrescribtion(str,pha,period,day,doctorName);
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
