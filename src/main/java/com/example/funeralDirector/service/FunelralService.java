package com.example.funeralDirector.service;

import java.util.ArrayList;

import com.example.funeralDirector.model.dao.FunelralDao;
import com.example.funeralDirector.model.dto.FunelralDto;
import com.example.funeralDirector.view.FuneralDirectorMenu;

/** @author fla90*/
public class FunelralService {
	FunelralDao funelralDao = new FunelralDao();

	
	
	//사망자 select
	public void checkDeath() {
		FuneralDirectorMenu menu = new FuneralDirectorMenu();
		
		ArrayList<FunelralDto> fdList = funelralDao.selectAll();
		if(!fdList.isEmpty()) {
			menu.DeathList(fdList);
		}else {
			menu.DeathError("해당되는 데이터가 없습니다");
		}
		
	}
	
	//사망자 이름, 주민으로 select
	public FunelralDto checkADeath(String patientName, String patientNo) {
		FunelralDto funelralDto = funelralDao.findByFunelralId(patientName,patientNo);
		return funelralDto;
	}
	
	//사망자 추가 insert
	public void createDeath (int funelralId2,String deathReason,int patientId) {
		int result = funelralDao.insert(funelralId2,deathReason,patientId);
		if(result > 0) {
			System.out.println("삽입 성공");
		}else {
			System.out.println("삽입 실패");
		}
	}
}
