package com.example.medic.view;

import com.example.medic.controller.MedicController;
import com.example.medic.model.dto.Medic;

public class MedicMenu {

    MedicController mc = new MedicController();

    public void main() {
        약등록();
    }

    public void 약등록() {
        Medic medic = new Medic(1L, "두통약", "etc", 3000L, 10L);
        mc.발주(medic);

    }
}
