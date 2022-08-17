package com.example.operation.model.DTO;

import java.sql.Date;
import java.sql.Timestamp;

public class OperationDTO {
	private int op_no;
	private Timestamp op_date;
	private String uniqueness;
	private String op_name;
	private int doctor_id;
	private int patient_id;
	
	public OperationDTO(int op_no, Timestamp op_date, String uniqueness, String op_name, int doctor_id, int patient_id) {
		super();
		this.op_no = op_no;
		this.op_date = op_date;
		this.uniqueness = uniqueness;
		this.op_name = op_name;
		this.doctor_id = doctor_id;
		this.patient_id = patient_id;
	}

	@Override
	public String toString() {
		return "수술 번호 : " +op_no+" 날짜 : " +op_date+" 특이사항 : " +uniqueness+"\n수술 이름 : " +op_name+" 의사 번호 : " +doctor_id+"환자 아이디 : " +patient_id;
	}

	public int getOp_no() {
		return op_no;
	}

	public void setOp_no(int op_no) {
		this.op_no = op_no;
	}

	public Timestamp getOp_date() {
		return op_date;
	}

	public void setOp_date(Timestamp op_date) {
		this.op_date = op_date;
	}

	public String getUniqueness() {
		return uniqueness;
	}

	public void setUniqueness(String uniqueness) {
		this.uniqueness = uniqueness;
	}

	public String getOp_name() {
		return op_name;
	}

	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
}
