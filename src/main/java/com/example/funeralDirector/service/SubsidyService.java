package com.example.funeralDirector.service;

import java.util.ArrayList;
//import java.util.List;
import com.example.funeralDirector.model.dao.SubsidyDao;
import com.example.funeralDirector.model.dto.SubsidyDto;

/** @author fla90*/
public class SubsidyService {
	
	SubsidyDao subsidyDao = new SubsidyDao();
	
	
	
	//총조회
	public ArrayList<SubsidyDto> checkSubsidy(){ 
		
		
		ArrayList<SubsidyDto> sbList = subsidyDao.selectAll();
		if(!sbList.isEmpty()) {
			return sbList;  
		}else {			
			System.out.println("해당되는 데이터가 없습니다");
			return sbList; 
		}
	}
	
	//환자 이름별 조회
		public ArrayList<SubsidyDto> selectBypatient(String patient_name){ 
			
			ArrayList<SubsidyDto> sbpList = subsidyDao.selectBypatient(patient_name);
			
			
			if(!sbpList.isEmpty()) {
				return sbpList;  
			}else {			
				System.out.println("해당되는 데이터가 없습니다");
				return sbpList; 
			}
		}

		
		
	//부조금 추가 
	public int createSubsidy(SubsidyDto subsidyDto) {	
		return subsidyDao.insert(subsidyDto);
	}
	
	//update
	public int updateDeath(int subsidy, String account_info, String account_bank,String subsidy_name) {
		return subsidyDao.updateDeath(subsidy, account_info, account_bank, subsidy_name);
	}
		
	//delete
	public int deleteDeath(String subsidy_name) {
		return subsidyDao.deleteDeath(subsidy_name);
	}
		
	
}
