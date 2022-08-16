package com.example.patient.model.dto;

public class Patient {
    private final Long patientId;
    private final String patientNo;
    private final String patientName;
    private final String phone;

    public Patient(Long patientId, String patientNo, String patientName, String phone) {
        this.patientId = patientId;
        this.patientNo = patientNo;
        this.patientName = patientName;
        this.phone = phone;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", patientNo='" + patientNo + '\'' +
                ", patientName='" + patientName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
