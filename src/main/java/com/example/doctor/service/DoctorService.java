package com.example.doctor.service;

import java.util.List;

import com.example.doctor.model.DAO.MedicalRecordsDao;
import com.example.doctor.model.DTO.MedicalRecordsDTO;

public class DoctorService {
	MedicalRecordsDao mrDao = new MedicalRecordsDao();
	
	public List<MedicalRecordsDTO> checkRecords(String resName) {
		return  mrDao.checkRecords(resName);
		
	}
	public int writeRecords(String str,String diseaseName) {
		return mrDao.writeRecords(str,diseaseName);
	}

	public int writePrescribtion(String str, String pha, int period, int day,String doctorName) {
		return mrDao.writePrescribtion(str,pha,period,day,doctorName);
		
		
	}
	
}
