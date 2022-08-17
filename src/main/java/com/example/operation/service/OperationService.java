package com.example.operation.service;

import java.util.ArrayList;
import java.util.List;

import com.example.operation.model.DAO.OperationDAO;
import com.example.operation.model.DTO.OperationDTO;

public class OperationService {
	OperationDAO opDao = new OperationDAO();

	public int writeOp(String day, String uniqueness, String op_name, String doctorName, String patientName) {
		return opDao.insertOp(day,uniqueness,op_name,doctorName,patientName);
	}

	public List<OperationDTO> searchOp(String patientName) {
		return opDao.searchOp(patientName);
		
	}

	public int updateOp(String patientName, String date,String newDate) {
		return opDao.updateOp(patientName,date,newDate);
	}

	public int deleteOp(String patientName, String date) {
		return opDao.deleteOp(patientName,date);
	}
	
	
}
