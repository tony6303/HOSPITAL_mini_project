package com.example.patient.controller;

import com.example.patient.model.dto.Patient;
import com.example.patient.model.dto.Reservation;
import com.example.patient.service.PatientService;

import java.util.List;

public class PatientController {
    private final PatientService patientService = new PatientService();

    public int registerPatient(Patient patient) {
        return patientService.createPatient(patient);
    }

    public Reservation findReservation(String resNo) {
        return patientService.checkReservation(resNo);
    }

    public int cancelReservation(String resNo) {
        return patientService.deleteReserve(resNo);

    }

    public Patient findPatient(String resNo) {
        return patientService.findPatientByPatientNo(resNo);
    }

    public List<Patient> findPatientList() {
        return patientService.findAll();
    }
}
