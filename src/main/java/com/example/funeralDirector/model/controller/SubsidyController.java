package com.example.funeralDirector.model.controller;

import java.util.ArrayList;

import com.example.funeralDirector.model.dto.SubsidyDto;
import com.example.funeralDirector.service.SubsidyService;

public class SubsidyController {
	
  	private final SubsidyService subsidyService = new SubsidyService();
  	
	public ArrayList<SubsidyDto> selectSubsidy() {
		return subsidyService.checkSubsidy();
	}
	
	//환자 이름별 조회 
	public ArrayList<SubsidyDto> selectBypatient(String patient_name) {
		return subsidyService.selectBypatient(patient_name);
	}
	
	//부조금 추가 컨트롤러 insert
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
