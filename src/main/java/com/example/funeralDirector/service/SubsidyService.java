package com.example.funeralDirector.service;

import java.util.ArrayList;
import java.util.List;
import com.example.funeralDirector.model.dao.SubsidyDao;
import com.example.funeralDirector.model.dto.SubsidyDto;

public class SubsidyService {
	
	SubsidyDao subsidyDao = new SubsidyDao();
	
	
	
	//총조회
	public ArrayList<SubsidyDto> checkSubsidy(){ // 반환형이 ArrayList<SubsidyDto> 임
		// subsidyDao.seletAll() 의 결과(리턴값) 을 sbList 에 대입함
		ArrayList<SubsidyDto> sbList = subsidyDao.selectAll();
		if(!sbList.isEmpty()) {
//			SubsidyList(sbList);
			return sbList;  // (조건문으로 검증됨)값이 있는 sbList를 반환
		}else {			
			System.out.println("해당되는 데이터가 없습니다");
			return sbList; // 값이 없는 sbList를 반환함 
		}
	}
	
	//환자 이름별 조회
		public ArrayList<SubsidyDto> selectBypatient(String patient_name){ // 반환형이 ArrayList<SubsidyDto> 임
			// subsidyDao.seletAll() 의 결과(리턴값) 을 sbList 에 대입함
			ArrayList<SubsidyDto> sbpList = subsidyDao.selectBypatient(patient_name);
			if(!sbpList.isEmpty()) {
//				SubsidyList(sbList);
				return sbpList;  // (조건문으로 검증됨)값이 있는 sbList를 반환
			}else {			
				System.out.println("해당되는 데이터가 없습니다");
				return sbpList; // 값이 없는 sbList를 반환함 
			}
		}
		//환자 이름별 조회끝

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
