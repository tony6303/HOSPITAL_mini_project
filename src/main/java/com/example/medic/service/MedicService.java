package com.example.medic.service;

import com.example.medic.model.dao.MedicDao;
import com.example.medic.model.dto.Medic;

public class MedicService {
    MedicDao medicDao = new MedicDao();
    public void create(Medic medic) {
        medicDao.insert(medic);
    }
}
