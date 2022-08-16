package com.example.patient.model.dto;

import java.sql.Date;

public class Reservation {
    private Long reservationNo;
    private Date reservationDate;
    private Long patientNo;

    public Reservation(Long reservationNo, Date reservationDate, Long patientNo) {
        this.reservationNo = reservationNo;
        this.reservationDate = reservationDate;
        this.patientNo = patientNo;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNo=" + reservationNo +
                ", reservationDate=" + reservationDate +
                ", patientNo=" + patientNo +
                '}';
    }

    public Long getReservationNo() {
        return reservationNo;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public Long getPatientNo() {
        return patientNo;
    }
}
