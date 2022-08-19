package com.example.operation.service;

import java.util.ArrayList;
import java.util.List;

import com.example.operation.model.DAO.OperationDAO;
import com.example.operation.model.DTO.OperationDTO;

/**
 * <pre>
 * Class : OperationService
 * Comment : 수술 일정 메인 화면
 * </pre>
 * @author 이규철
 * */
public class OperationService {
	OperationDAO opDao = new OperationDAO();

	/** @author 이규철 **/
	public int writeOp(String day, String uniqueness, String op_name, String doctorName, String patientName) { // 수술 일정 작성 서비스
		return opDao.insertOp(day,uniqueness,op_name,doctorName,patientName);
	}

	/** @author 이규철 **/
	public List<OperationDTO> searchOp(String patientName,String patientNo) { // 수술 일정 조회 서비스
		return opDao.searchOp(patientName,patientNo);
	}

	/** @author 이규철 **/
	public int updateOp(String patientName,String patientNo, String date,String newDate) {  // 수술 일정 업데이트 서비스
		return opDao.updateOp(patientName,patientNo,date,newDate);
	}

	/** @author 이규철 **/
	public int deleteOp(String patientName,String patientNo, String date) {  //수술 일정 삭제 서비스
		return opDao.deleteOp(patientName,patientNo,date);
	}
	
	
}
