package com.example.cashier.service;

import java.util.List;

import com.example.cashier.model.dao.CashierDao;
import com.example.cashier.model.dto.Price;
import com.example.patient.model.dto.Patient;

/**
 * @author cpzhr(박경민)
 *
 */
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



	public Patient selectByResNo(String resNo) {
		return cashierDao.selectByResNo(resNo);
	}



	public int selectPriceByDiseaseName(String disName) {
		return cashierDao.selectPriceByDiseaseName(disName);
	}
	
	

}
