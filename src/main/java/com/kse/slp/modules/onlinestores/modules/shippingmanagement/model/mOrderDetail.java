package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

import javax.persistence.Entity;

public class mOrderDetail {
	private String O_Code;
	private float O_DeliveryLat;
	private float O_DeliveryLng;
	private String O_TimeEarly;
	private String O_TimeLate;
	private String O_DueDate;
	private String C_Name;
	
	
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
