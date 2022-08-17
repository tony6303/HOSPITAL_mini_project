package com.example.medic.model.dto;

import java.sql.Date;

/**
 * @author 최영준
 *
 */
public class Prescription {
	private int patientNo;
	private String patientName;
	private String patientInfo;
	private int phaNo;
	private Date preDate;
	private int phaDayDosage;
	private int phaPeriod;
	private String doctorName;

	public int getPatientNo() {
		return patientNo;
	}

	public void setPatientNo(int patientNo) {
		this.patientNo = patientNo;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(String patientInfo) {
		this.patientInfo = patientInfo;
	}

	public int getPhaNo() {
		return phaNo;
	}

	public void setPhaNo(int phaNo) {
		this.phaNo = phaNo;
	}

	public Date getPreDate() {
		return preDate;
	}

	public void setPreDate(Date preDate) {
		this.preDate = preDate;
	}

	public int getPhaDayDosage() {
		return phaDayDosage;
	}

	public void setPhaDayDosage(int phaDayDosage) {
		this.phaDayDosage = phaDayDosage;
	}

	public int getPhaPeriod() {
		return phaPeriod;
	}

	public void setPhaPeriod(int phaPeriod) {
		this.phaPeriod = phaPeriod;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setdoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Prescription(int patientNo, String patientName, String patientInfo, int phaNo, Date preDate,
			int phaDayDosage, int phaPeriod, String doctorName) {
		super();
		this.patientNo = patientNo;
		this.patientName = patientName;
		this.patientInfo = patientInfo;
		this.phaNo = phaNo;
		this.preDate = preDate;
		this.phaDayDosage = phaDayDosage;
		this.phaPeriod = phaPeriod;
		this.doctorName = doctorName;
	}

}
