package com.example.cashier.controller;

import java.util.List;

import com.example.cashier.model.dto.Price;
import com.example.cashier.service.CashierService;
import com.example.patient.model.dto.Patient;

public class CashierController {
	
	private final CashierService cashierService = new CashierService();

	public int registerReservation(String resNo) {
		return cashierService.reservationinsert(resNo);
	}

	public Price price(String resNo) {
		
		return cashierService.priceselect(resNo);
	}

	public int priceUpdate(String disName) {
		return cashierService.priceUpdate(disName);
	}

	public int salaryselect(int salary) {
		return cashierService.salaryselect(salary);
	}

	public List<Patient> checkResNo(String resNo) {
		return cashierService.selectByResNo(resNo);
	}
	
	
	
	

}
