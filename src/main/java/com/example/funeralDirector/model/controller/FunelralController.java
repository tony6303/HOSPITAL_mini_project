package com.example.funeralDirector.model.controller;

import com.example.funeralDirector.model.dto.FunelralDto;
import com.example.funeralDirector.service.FunelralService;
import com.example.funeralDirector.view.FuneralDirectorMenu;

public class FunelralController {
	// 컨트롤러 -> 서비스 -> dao -> db
	//서비스 가지와 
	private final FunelralService funelralService = new FunelralService();
	
	//아이디로 한 사망자만 조회
//	public void selectOne() {
//		funelralService.checkOneDeath(null);
//	}
	
	//사망자 한명만 검색
	public FunelralDto selectOne(String patientName,String patientNo) {
		FunelralDto fd = funelralService.checkADeath(patientName, patientNo);
		return fd;
	}
	
	//사망자 insert
	//컨트롤러에서 받아서 서비스 건드려여여
	//컨트롤러는 서비스 호출만 하는 파일이다.
	public void registerDeath(int funelralId2,String deathReason,int patientId) {
		funelralService.createDeath(funelralId2,deathReason,patientId);
		
	}
	
	//총 사망자 검색
	public void selectAll() {
		funelralService.checkDeath();
	}
	
//	//업데이트 
//	public int updateDeath(int subsidy, String account_info, String account_bank,String subsidy_name) {
//		return funelralService.updateDeath(subsidy, account_info, account_bank, subsidy_name);
//	}
	
	
	
	
	
	
}
