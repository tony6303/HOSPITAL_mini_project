package com.example.operation.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.operation.model.DTO.OperationDTO;
import com.example.operation.service.OperationService;

public class OperationController {
	private final OperationService OperationService = new OperationService();
	
//	public void searchRecords(String resNo) {
//		doctorService.checkRecords(resNo);
//	}

	public int writeOp(String day, String uniqueness, String op_name, String doctorName, String patientName) {
		return OperationService.writeOp(day,uniqueness,op_name,doctorName,patientName);
	}

	public List<OperationDTO> searchOp(String patientName) {
		
		return OperationService.searchOp(patientName);
	}

	public int updateOp(String patientName, String date,String newDate) {
		return OperationService.updateOp(patientName,date,newDate);
		
	}

	public int deletOp(String patientName, String date) {
		return OperationService.deleteOp(patientName,date);
	}
}
