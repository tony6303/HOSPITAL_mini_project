package com.example.medic.service;

import java.util.ArrayList;

import com.example.medic.model.dao.MedicDao;
import com.example.medic.model.dto.Medic;
import com.example.medic.model.dto.PharmacyData;
import com.example.medic.view.MedicMenu;

/**
 * @author 최영준
 *
 */
public class MedicService {
	MedicDao medicDao = new MedicDao();

	// =================DAO에서 받은 정보의 값을 조작 ===============
	// MEMO dao에서 받은 sql 내용이 있는지 확인하고 컨트롤러로 보내주는곳

	// MEMO - 총재고확인
	// dao 에서 전체조회된 값이 list에 잘 들어와 있는지 확인
	public void checkPha() {
		MedicMenu menu = new MedicMenu();
		ArrayList<Medic> medicList = medicDao.selectAll();
		// 리스트 객체를 이미 만들었기 때문에 null이 될수 없어서 empty로 설정
		if (!medicList.isEmpty()) {
			// 객체가 들어있다면 리스트를 출력
			menu.displayMemberList(medicList);

		} else {
			// 객체가 없다면 오류메세지를 출력
			menu.displayError("해당되는 데이터가 없습니다.");
		}

	}

	// MEMO - 기존재고 업데이트
	// medic = 추가할 제품이름과 추가수량
	// medic2 = 업데이트 조회된 상품의 이름과 수량
	public void updatePha(Medic medic) {
		MedicMenu menu = new MedicMenu();
		Medic medic2 = medicDao.update(medic);
		if (!(medic2 == null)) {
			menu.displayUpdatestock(medic2);
		} else {
			menu.displayError("해당 이름의 재고는 존재하지 않습니다.");
		}

	}

	// MEMO 신제품 주문넣기
	// 입력제약조건은 약 넘버만 검사
	public void insertPha(Medic medic) {
		MedicMenu menu = new MedicMenu();

		// 중복약 번호 검사 , 제품이 존재하면 0 아니면 입력값번호를 가짐
		int checkPhaNo = medicDao.setlectPhaNo(medic);

		// 제품이 존재하면 0 , 안 입고를 시작함
		if (checkPhaNo == 0) {
			medicDao.insert(medic);

		} else {
			menu.displayError("해당 약 번호는 있는 번호입니다. 없는 번호를 입력하세요");
		}

	}

	// MEMO 처방전 출력함수
	/**
	 * @param patientNumber
	 */
	public void printScript(int patientNumber) {
		MedicMenu menu = new MedicMenu();

		//printPD DAO에서 받아온 데이터
		PharmacyData printPD = medicDao.printPD(patientNumber);
		// 처방전 번호에 해당하는 내용이 있으면 display 함수 출력
		if (!(printPD == null)) {
			menu.displayScription(printPD);

		} else {

			menu.displayError("해당되는 처방전 데이터가 없습니다.");
		}

	}

	// 처방전 개수만큼 상품수를 변경하고 다시 조회
	public void updateSc(int minusStock, String phaName) {
		MedicMenu menu = new MedicMenu();

		Medic medic = medicDao.updateScr(minusStock, phaName);
		
		menu.updatePre(medic);
	}

	// MEMO 상품을 제거하는 함수!
	public void deletePha(String phaName) {
		MedicMenu menu = new MedicMenu();
		int abc = medicDao.delete(phaName);
		System.out.println(abc);
		if (abc <= 0) {
			menu.displayError("해당 이름의 상품명은 없습니다.");

			// 상품이 존재하고 삭제를 한다.
		} else if (abc == 1) {
			menu.displayInfo("입력하신 상품 " + phaName + "을(를) 삭제하였습니다.");
		} else if (abc > 1) {
			menu.displayInfo(abc + "가지의 중복된 이름의 상품을 제거하였습니다.");

		}

	}

}
