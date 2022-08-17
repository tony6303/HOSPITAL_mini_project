package main.java.com.example.cashier.service;

import main.java.com.example.cashier.model.dao.CashierDao;
import main.java.com.example.cashier.model.dto.Price;

public class CashierService {
	
	CashierDao cashierDao = new CashierDao();  



	public int reservationinsert(String resNo) {
		return cashierDao.reservationinsert(resNo);
	}



	public Price priceselect(String resNo) {
		return cashierDao.priceselect(resNo);
	}



	public int priceUpdate(String disName) {
		return cashierDao.priceUpdate(disName);
	}



	public int salaryselect(int salary) {
		return cashierDao.salaryselect(salary);
	}
	
	

}
