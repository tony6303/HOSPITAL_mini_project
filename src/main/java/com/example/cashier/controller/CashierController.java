package main.java.com.example.cashier.controller;

import main.java.com.example.cashier.model.dto.Price;
import main.java.com.example.cashier.service.CashierService;

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
	
	
	
	

}
