package main.java.com.example.funeralDirector.service;

import java.util.ArrayList;

import main.java.com.example.funeralDirector.model.dao.FunelralDao;
import main.java.com.example.funeralDirector.model.dto.FunelralDto;
import main.java.com.example.funeralDirector.view.FuneralDirectorMenu;

public class FunelralService {
	FunelralDao funelralDao = new FunelralDao();
	//컨트롤러에서 서비스 건들였으니 서비스 -> dao 건드렬려
	
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
	
	//사망자 이름 주민으로 select
	public FunelralDto checkADeath(String patientName, String patientNo) {
		FunelralDto funelralDto = funelralDao.findByFunelralId(patientName,patientNo);
		//System.out.println(funelralDto);
//		if(funelralDto == null) {
//			System.out.println("log warning : 등록된 사망자는 없습니다");
//		}
		return funelralDto;
	}
	
	//사망자 insert
	public void createDeath (int funelralId2,String deathReason,int patientId) {
		int result = funelralDao.insert(funelralId2,deathReason,patientId);
		if(result > 0) {
			System.out.println("삽입 성공");
		}else {
			System.out.println("삽입 실패");
		}
	}
	
//	//update
//	public int updateDeath(int subsidy, String account_info, String account_bank,String subsidy_name) {
//		return funelralDao.updateDeath(subsidy, account_info, account_bank, subsidy_name);
//	}
	
	//사망자 한명만 select
//	public void checkOneDeath(FunelralDto funelralDto){		
//		FunelralDto funelralDto = funelralDao.findByFunelralId(resNo);
//		if(funelralDto == null) {
//			System.out.println("log warning: 사망자 명단에 없습니다");
//			return;
//		}
//	}
	
	
}
