package com.example.funeralDirector.model.dto;

import java.util.Date;

public class FunelralDto {
	 private int funelralId;
	    private Date dateDeath;
	    private String deathReason;
	    private int patientId;

	    //생성자
	    public FunelralDto() {
			super();
		}
	    
		public FunelralDto(int funelralId, Date dateDeath, String deathReason, int patientId) {
	        this.funelralId = funelralId;
	        this.dateDeath = dateDeath;
	        this.deathReason = deathReason;
	        this.patientId = patientId;
	    }

	    //toString
	    @Override
	    public String toString() {
	        return "FunelralDto{" +
	                "funelralId=" + funelralId +
	                ", dateDeath=" + dateDeath +
	                ", deathReason='" + deathReason + '\'' +
	                ", patientId=" + patientId +
	                '}';
	    }

	    //getter & setter
	    public int getFunelralId() {
	        return funelralId;
	    }

	    public void setFunelralId(int funelralId) {
	        this.funelralId = funelralId;
	    }

	    public Date getDateDeath() {
	        return dateDeath;
	    }

	    public void setDateDeath(Date dateDeath) {
	        this.dateDeath = dateDeath;
	    }

	    public String getDeathReason() {
	        return deathReason;
	    }

	    public void setDeathReason(String deathReason) {
	        this.deathReason = deathReason;
	    }

	    public int getPatientId() {
	        return patientId;
	    }

	    public void setPatientId(int patientId) {
	        this.patientId = patientId;
	    }

}
