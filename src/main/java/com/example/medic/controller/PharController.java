package com.example.medic.controller;

import com.example.medic.model.dto.Medic;
import com.example.medic.service.MedicService;

/**
 * @author 최영준
 *
 */
public class PharController {
	MedicService medicService = new MedicService();

	// MEMO 총재고를 조회하는 메소드 호출
	// 리스트 값을 받았다.
	public void selectAll() {
		medicService.checkPha();
	}

	// MEMO - 기존재고 이름과 수량을 받아 변경하는 메소드 호출
	public void updatePha(Medic medic) {
		medicService.updatePha(medic);
	}

	// MEMO - 신제품의 정보를 담아서 새롭게 등록하는 메소드 호출
	// 컨트롤러에서 insert된 정보를 받아서 서비스에다가 전달
	public void insertNewPhar(Medic medic) {
		medicService.insertPha(medic);
	}

	// MEMO - 조제확인서 출력하는 메소드 호출
	// 컨트롤러에서는 서비스에 있는 메소드를 호출한다.
	public void PrintScription(int patientNumber) {
		medicService.printScript(patientNumber);
	}



	// MEMO - 해당 품목을 날리는 메소드를 호출
	public void deletePha(String phaName) {
		medicService.deletePha(phaName);

	}

}
