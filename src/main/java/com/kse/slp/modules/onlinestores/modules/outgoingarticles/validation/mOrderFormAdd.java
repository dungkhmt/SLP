package com.kse.slp.modules.onlinestores.modules.outgoingarticles.validation;

public class mOrderFormAdd {
	String batchCode;
	String orderClientCode;
	String orderClientName;
	String orderAdress;
	String orderDate;
	String orderTimeEarly;
	float orderDeliveryLat;
	float orderDeliveryLng;
	float orderPrice;
	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public float getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}

	public float getOrderDeliveryLat() {
		return orderDeliveryLat;
	}

	public void setOrderDeliveryLat(float orderDeliveryLat) {
		this.orderDeliveryLat = orderDeliveryLat;
	}

	public float getOrderDeliveryLng() {
		return orderDeliveryLng;
	}

	public void setOrderDeliveryLng(float orderDeliveryLng) {
		this.orderDeliveryLng = orderDeliveryLng;
	}

	public String getOrderTimeEarly() {
		return orderTimeEarly;
	}

	public void setOrderTimeEarly(String orderTimeEarly) {
		this.orderTimeEarly = orderTimeEarly;
	}

	public String getOrderTimeLate() {
		return orderTimeLate;
	}

	public void setOrderTimeLate(String orderTimeLate) {
		this.orderTimeLate = orderTimeLate;
	}
	String orderTimeLate;
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
