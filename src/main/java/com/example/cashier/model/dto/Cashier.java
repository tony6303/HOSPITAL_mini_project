package com.example.cashier.model.dto;




public class Cashier {
	private final Long costId;
	private final String diseaseName;
	private final Long price;

	
	// 캐셔에 필드 선언
	
	public Cashier(Long costId, String diseaseName, Long price) {
		
		
		
		this.costId = costId;
		this.diseaseName = diseaseName;
		this.price = price;
	}


	public Long getCostId() {
		return costId;
	}


	public String getDiseaseName() {
		return diseaseName;
	}


	public Long getPrice() {
		return price;
	}


	@Override
	public String toString() {
		return "Cashier{" +
                "costId=" + costId +
                ", diseaseName='" + diseaseName + '\'' +
                ", price='" + price + '\'' +
                '}';
	}
	
}	
	
	
	
	


