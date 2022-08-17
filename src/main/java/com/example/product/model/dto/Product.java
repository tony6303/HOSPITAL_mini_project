package com.example.product.model.dto;

import java.util.Date;
/**
 * @author tony6(이대엽)
 *
 */
public class Product {
	private int pId;
	private String pName;
	private int amount;
	private Date receiveDate;
	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(String pName, int amount) {
		this.pName = pName;
		this.amount = amount;
	}
	


	public Product(int pId, String pName, int amount, Date receiveDate) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.amount = amount;
		this.receiveDate = receiveDate;
	}



	public int getpId() {
		return pId;
	}



	public void setpId(int pId) {
		this.pId = pId;
	}



	public String getpName() {
		return pName;
	}



	public void setpName(String pName) {
		this.pName = pName;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public Date getReceiveDate() {
		return receiveDate;
	}



	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}



	@Override
	public String toString() {
		return pId + "\t" + pName + "\t" + amount + "\t" + receiveDate;
	}



	
}
