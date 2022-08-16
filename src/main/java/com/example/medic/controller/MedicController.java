package com.example.medic.controller;

import com.example.medic.model.dto.Medic;
import com.example.medic.service.MedicService;

public class MedicController {
    MedicService medicService = new MedicService();
    public void 발주(Medic medic) {
        System.out.println(medic);
        medicService.create(medic);
    }
}
