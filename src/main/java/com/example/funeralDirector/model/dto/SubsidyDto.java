package com.example.funeralDirector.model.dto;

//import java.util.List;

public class SubsidyDto {
	private String SubsidyName; 
	private int Subsidy;
	private String Account_number;
	private String account_bank;
	private int funelral_id;
	
	//생성자
	public SubsidyDto() {
		super();
	}
	
	public SubsidyDto(String subsidyName, int subsidy, String account_number, String account_bank, int insertPatient) {
		this.SubsidyName = subsidyName;
		this.Subsidy = subsidy;
		this.Account_number = account_number;
		this.account_bank = account_bank;
		this.funelral_id = insertPatient;
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


	public int getFunelral_id() {
		return funelral_id;
	}


	public void setFunelral_id(int funelral_id) {
		this.funelral_id = funelral_id;
	}
}
