package com.example.operation.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.operation.model.DTO.OperationDTO;
import com.example.operation.service.OperationService;

/** @author 작성자 **/
public class OperationController {  // 어떤 서비스를 실행할지 컨트롤 하는 클래스
	private final OperationService OperationService = new OperationService();   // 서비스 객체 생성
	
	/** @author 작성자 **/
	public int writeOp(String day, String uniqueness, String op_name, String doctorName, String patientName) {  // 수술 작성 서비스 호출
		return OperationService.writeOp(day,uniqueness,op_name,doctorName,patientName);   // 날짜, 특이사항, 수술명, 의사번호, 환자 번호를 입력 받는다.
	}

	/** @author 작성자 **/
	public List<OperationDTO> searchOp(String patientName,String patientNo) {     //  수술 일정 열람 서비스 호출
		return OperationService.searchOp(patientName,patientNo);     // 환자 이름을 입력 받는다.
	}

	/** @author 작성자 **/
	public int updateOp(String patientName,String patientNo, String date,String newDate) {   // 수술 일정 수정 서비스 호출
		return OperationService.updateOp(patientName,patientNo,date,newDate);      // 환자명, 수술 날짜, 수정할 수술 날짜 입력 받는다.
	}

	/** @author 작성자 **/
	public int deletOp(String patientName,String patientNo, String date) {        // 수술 일정 삭제 서비스 호출
		return OperationService.deleteOp(patientName,patientNo,date);      // 환자명, 수술 날짜 입력받는다.
	}
}
