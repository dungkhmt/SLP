package com.kse.slp.modules.onlinestores.modules.outgoingarticles.validation;

public class mOrderFormAdd {
	String orderClientCode;
	String orderClientName;
	String orderAdress;
	String orderDate;
	public String getOrderClientCode() {
		return orderClientCode;
	}
	
	public String getOrderClientName() {
		return orderClientName;
	}
	public void setOrderClientName(String orderClientName) {
		this.orderClientName = orderClientName;
	}
	public String getOrderAdress() {
		return orderAdress;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public void setOrderClientCode(String orderClientCode) {
		this.orderClientCode = orderClientCode;
	}
	public void setOrderAdress(String orderAdress) {
		this.orderAdress = orderAdress;
	}
	
}
