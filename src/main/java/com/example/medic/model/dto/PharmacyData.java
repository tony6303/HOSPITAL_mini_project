package com.example.medic.model.dto;

import java.sql.Date;

public class PharmacyData {
	    private int preNo;
		private String patientName;
		private String patientNo;
		private String doctorName;
		private Date preDate;
		private String phaName;
	    private String phaType;
		private int phaPeriod;
		private int phaDayDosage;
	    private int phaPrice;
	    private int phaStock;
	
	    public PharmacyData() {
			// TODO Auto-generated constructor stub
		}

		public PharmacyData(int preNo, String patientName, String patientNo, String doctorName, Date preDate,
				String phaName, String phaType, int phaPeriod, int phaDayDosage, int phaPrice, int phaStock) {
			this.preNo = preNo;
			this.patientName = patientName;
			this.patientNo = patientNo;
			this.doctorName = doctorName;
			this.preDate = preDate;
			this.phaName = phaName;
			this.phaType = phaType;
			this.phaPeriod = phaPeriod;
			this.phaDayDosage = phaDayDosage;
			this.phaPrice = phaPrice;
			this.phaStock = phaStock;
		}

		public int getPreNo() {
			return preNo;
		}

		public void setPreNo(int preNo) {
			this.preNo = preNo;
		}

		public String getPatientName() {
			return patientName;
		}

		public void setPatientName(String patientName) {
			this.patientName = patientName;
		}

		public String getPatientNo() {
			return patientNo;
		}

		public void setPatientNo(String patientNo) {
			this.patientNo = patientNo;
		}

		public String getDoctorName() {
			return doctorName;
		}

		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}

		public Date getPreDate() {
			return preDate;
		}

		public void setPreDate(Date preDate) {
			this.preDate = preDate;
		}

		public String getPhaName() {
			return phaName;
		}

		public void setPhaName(String phaName) {
			this.phaName = phaName;
		}

		public String getPhaType() {
			return phaType;
		}

		public void setPhaType(String phaType) {
			this.phaType = phaType;
		}

		public int getPhaPeriod() {
			return phaPeriod;
		}

		public void setPhaPeriod(int phaPeriod) {
			this.phaPeriod = phaPeriod;
		}

		public int getPhaDayDosage() {
			return phaDayDosage;
		}

		public void setPhaDayDosage(int phaDayDosage) {
			this.phaDayDosage = phaDayDosage;
		}

		public int getPhaPrice() {
			return phaPrice;
		}

		public void setPhaPrice(int phaPrice) {
			this.phaPrice = phaPrice;
		}

		public int getPhaStock() {
			return phaStock;
		}

		public void setPhaStock(int phaStock) {
			this.phaStock = phaStock;
		}
}
