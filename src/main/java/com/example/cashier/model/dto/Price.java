package com.example.cashier.model.dto;

import java.sql.Date;

public class Price {

	private int patient_id;
	private String patient_no;
	private String patient_name;
	private Date mr_date;
	private String disease_name;
	private int price;

	public Price() {
		// TODO Auto-generated constructor stub
	}

	public Price(int patient_id, String patient_no, String patient_name, Date mr_date, String disease_name, int price) {
		this.patient_id = patient_id;
		this.patient_name = patient_name;
		this.patient_no = patient_no;
		this.mr_date = mr_date;
		this.disease_name = disease_name;
		this.price = price;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public String getPatient_no() {
		return patient_no;
	}

	public void setPatient_no(String patient_no) {
		this.patient_no = patient_no;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public Date getMr_date() {
		return mr_date;
	}

	public void setMr_date(Date mr_date) {
		this.mr_date = mr_date;
	}

	public String getDisease_name() {
		return disease_name;
	}

	public void setDisease_name(String disease_name) {
		this.disease_name = disease_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
