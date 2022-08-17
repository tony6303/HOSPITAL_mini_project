package com.example.doctor.model.DTO;

import java.sql.Date;

public class MedicalRecordsDTO {
	private int mr_no;
	private Date mr_date;
	private int patient_id;
	private int cost_id;
	private String disease_name;
	
	public MedicalRecordsDTO() {
	}
	public MedicalRecordsDTO(int mr_no, Date mr_date, int patient_id, int cost_id,String disease_name) {
		this.mr_no = mr_no;
		this.mr_date = mr_date;
		this.patient_id = patient_id;
		this.cost_id = cost_id;
		this.disease_name = disease_name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "진료번호: "+mr_no+"\n 진료날짜 : " +mr_date+"\n환자 번호: "+patient_id+"\n질병 번호 : "+cost_id+"\n병명 : " + disease_name+"\n ------------------------\n";
	}
	public int getMr_no() {
		return mr_no;
	}

	public void setMr_no(int mr_no) {
		this.mr_no = mr_no;
	}

	public Date getMr_date() {
		return mr_date;
	}

	public void setMr_date(Date mr_date) {
		this.mr_date = mr_date;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public int getCost_id() {
		return cost_id;
	}

	public void setCost_id(int cost_id) {
		this.cost_id = cost_id;
	}
	
	
	
	
}
