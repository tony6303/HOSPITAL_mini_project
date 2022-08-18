package com.example.funeralDirector.model.controller;

import com.example.funeralDirector.model.dto.FunelralDto;
import com.example.funeralDirector.service.FunelralService;
//import com.example.funeralDirector.view.FuneralDirectorMenu;

public class FunelralController {
	private final FunelralService funelralService = new FunelralService();
	
	
	//사망자 이름,주민번호 별 한명만 검색
	public FunelralDto selectOne(String patientName,String patientNo) {
		FunelralDto fd = funelralService.checkADeath(patientName, patientNo);
		return fd;
	}
	
	//사망자 insert
	public void registerDeath(int funelralId2,String deathReason,int patientId) {
		funelralService.createDeath(funelralId2,deathReason,patientId);
		
	}
	
	//총 사망자 검색
	public void selectAll() {
		funelralService.checkDeath();
	}
	
}
