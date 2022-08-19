package com.example.doctor.service;

import java.util.List;

import com.example.doctor.model.DAO.MedicalRecordsDao;
import com.example.doctor.model.DTO.MedicalRecordsDTO;

/**
 * <pre>
 * Class : DoctorService
 * Comment : 수술 일정 메인 화면
 * </pre>
 * @author 이규철
 * */
public class DoctorService {
	MedicalRecordsDao mrDao = new MedicalRecordsDao();
	
	/** @author 이규철 **/
	public List<MedicalRecordsDTO> checkRecords(String resName,String resNo) {  // 진료 기록 열람 서비스
		return  mrDao.checkRecords(resName,resNo);
	}
	
	/** @author 이규철 **/
	public int writeRecords(String str,String resNo,String diseaseName) {  // 진료 기록 작성 서비스
		return mrDao.writeRecords(str,resNo,diseaseName);
	}

	
	/** @author 이규철 **/
	public int writePrescribtion(String str, String pha, int period, int day,String doctorName) {  // 처방전 작성 서비스
		return mrDao.writePrescribtion(str,pha,period,day,doctorName);	
	}
	
}
