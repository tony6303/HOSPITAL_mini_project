package main.java.com.example.funeralDirector.model.controller;

import java.util.ArrayList;

import main.java.com.example.funeralDirector.model.dto.SubsidyDto;
import main.java.com.example.funeralDirector.service.SubsidyService;

public class SubsidyController {
	//부조금 서비스 객체 선언해줘야 쓸 수 있지!!!
	//private final SubsidyDto subsidyDto = new SubsidyDto();
  	private final SubsidyService subsidyService = new SubsidyService();
	//객체 선언한걸로 부조금 체크 가져와 !!
	public ArrayList<SubsidyDto> selectSubsidy() {
		return subsidyService.checkSubsidy();
	}
	
	//부조금 추가 컨트롤러 intsert
	public int registerSubsidy(SubsidyDto subsidyDto){
		return subsidyService.createSubsidy(subsidyDto);
	}
	
	//업데이트 
		public int updateDeath(int subsidy, String account_info, String account_bank,String subsidy_name) {
			return subsidyService.updateDeath(subsidy, account_info, account_bank, subsidy_name);
		}
		
	//delete
	public int deleteDeath(String subsidy_name) {
		return subsidyService.deleteDeath(subsidy_name);
	}
}
