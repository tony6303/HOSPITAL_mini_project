package main.java.com.example.funeralDirector.model.dto;

import java.util.List;

import main.java.com.example.funeralDirector.model.dao.SubsidyDao;

public class SubsidyDto {
	private String SubsidyName; 
	private int Subsidy;
	private String Account_number;
	private String account_bank;
	
	//생성자
	public SubsidyDto() {
		super();
	}
	
	
//	public SubsidyDto(String subsidyName, int subsidy, String account_number, String account_bank) {
//		super();
//		this.SubsidyName = subsidyName;
//		this.Subsidy = subsidy;
//		this.Account_number = account_number;
//		this.account_bank = account_number;
//	}

	public SubsidyDto(String subsidyName, int subsidy, String account_number, String account_bank) {
		super();
		SubsidyName = subsidyName;
		Subsidy = subsidy;
		Account_number = account_number;
		this.account_bank = account_bank;
	}


	//getter & setter
	public String getSubsidyName() {
		return SubsidyName;
	}


	public void setSubsidyName(String subsidyName) {
		SubsidyName = subsidyName;
	}


	public int getSubsidy() {
		return Subsidy;
	}


	public void setSubsidy(int subsidy) {
		Subsidy = subsidy;
	}


	public String getAccount_number() {
		return Account_number;
	}


	public void setAccount_number(String account_number) {
		Account_number = account_number;
	}


	public String getAccount_bank() {
		return account_bank;
	}


	public void setAccount_bank(String account_bank) {
		this.account_bank = account_bank;
	}


	//toString
//	@Override
//	public String toString() {
//		return 
//				  "부조한 한 사람 : " + getSubsidyName() +"\n"
//				+ "부조한 금액 : " + getSubsidy()+"원" +"\n"
//				+ "들어온 계좌 : "+ getAccount_number() +"\n"
//				+ "은행 : "+ getAccount_bank() +"\n";
//				//"돈낸사람 " + SubsidyName + "\n"
//				//+ "Subsidy=" + Subsidy +"\n"
//				//+ "Account_number=" + Account_number  +"\n"
//				//+ "getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
//				//+ super.toString() + "]";
//	}


	

//	public List<SubsidyDao> selectAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
