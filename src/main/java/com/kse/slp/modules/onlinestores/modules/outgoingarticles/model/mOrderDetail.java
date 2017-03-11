package com.kse.slp.modules.onlinestores.modules.outgoingarticles.model;

import javax.persistence.Entity;

public class mOrderDetail {
	private String O_Code;
	private float O_DeliveryLat;
	private float O_DeliveryLng;
	private String O_DeliveryAddress;
	private String O_TimeEarly;
	private String O_TimeLate;
	private String O_OrderDate;
	private String O_DueDate;
	private String C_Name;
	private String OST_Name;
	private String REQBAT_Description;
	
	public String getO_DeliveryAddress() {
		return O_DeliveryAddress;
	}
	public void setO_DeliveryAddress(String o_DeliveryAddress) {
		O_DeliveryAddress = o_DeliveryAddress;
	}
	public String getO_OrderDate() {
		return O_OrderDate;
	}
	public void setO_OrderDate(String o_OrderDate) {
		O_OrderDate = o_OrderDate;
	}
	public String getREQBAT_Description() {
		return REQBAT_Description;
	}
	public void setREQBAT_Description(String rEQBAT_Description) {
		REQBAT_Description = rEQBAT_Description;
	}
	public String getOST_Name() {
		return OST_Name;
	}
	public void setOST_Name(String oST_Name) {
		OST_Name = oST_Name;
	}
	public String getO_Code() {
		return O_Code;
	}
	public void setO_Code(String o_Code) {
		O_Code = o_Code;
	}
	public float getO_DeliveryLat() {
		return O_DeliveryLat;
	}
	public void setO_DeliveryLat(float o_DeliveryLat) {
		O_DeliveryLat = o_DeliveryLat;
	}
	public float getO_DeliveryLng() {
		return O_DeliveryLng;
	}
	public void setO_DeliveryLng(float o_DeliveryLng) {
		O_DeliveryLng = o_DeliveryLng;
	}
	public String getO_TimeEarly() {
		return O_TimeEarly;
	}
	public void setO_TimeEarly(String o_TimeEarly) {
		O_TimeEarly = o_TimeEarly;
	}
	public String getO_TimeLate() {
		return O_TimeLate;
	}
	public void setO_TimeLate(String o_TimeLate) {
		O_TimeLate = o_TimeLate;
	}
	public String getO_DueDate() {
		return O_DueDate;
	}
	public void setO_DueDate(String o_DueDate) {
		O_DueDate = o_DueDate;
	}
	public String getC_Name() {
		return C_Name;
	}
	public void setC_Name(String c_Name) {
		C_Name = c_Name;
	}
	public mOrderDetail(String o_Code, float o_DeliveryLat, float o_DeliveryLng, String o_TimeEarly, String o_TimeLate,
			String o_DueDate, String c_Name) {
		super();
		O_Code = o_Code;
		O_DeliveryLat = o_DeliveryLat;
		O_DeliveryLng = o_DeliveryLng;
		O_TimeEarly = o_TimeEarly;
		O_TimeLate = o_TimeLate;
		O_DueDate = o_DueDate;
		C_Name = c_Name;
	}
	public mOrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "mOrderDetail [O_Code=" + O_Code + ", O_DeliveryLat=" + O_DeliveryLat + ", O_DeliveryLng="
				+ O_DeliveryLng + ", O_TimeEarly=" + O_TimeEarly + ", O_TimeLate=" + O_TimeLate + ", O_DueDate="
				+ O_DueDate + ", C_Name=" + C_Name + "]";
	}
	
	
}
