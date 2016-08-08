package com.kse.slp.modules.onlinestores.modules.outgoingarticles.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="tblorders")
public class mOrders implements Serializable {
	@Id
	@GeneratedValue
	int O_ID;
	String O_Code;
	String O_ClientCode;
	String O_OrderDate;
	String O_DueDate;
	String O_DeliveryAddress;
	float O_DeliveryLat;
	float O_DeliveryLng;
	String O_TimeEarly;
	String O_TimeLate;
	String O_Status_Code;
	String O_RouteCode;
	float O_Price;
	
	//@Transient
	//String c_name;
	
	
//	public String getRoute_Code() {
//		return Route_Code;
//	}
//	public void setRoute_Code(String route_Code) {
//		Route_Code = route_Code;
//	}
	public String getO_Status_Code() {
		return O_Status_Code;
	}
	public void setO_Status_Code(String o_Status_Code) {
		O_Status_Code = o_Status_Code;
	}
	public String getO_RouteCode() {
		return O_RouteCode;
	}
	public void setO_RouteCode(String o_RouteCode) {
		O_RouteCode = o_RouteCode;
	}
	public float getO_Price() {
		return O_Price;
	}
	public void setO_Price(float o_Price) {
		O_Price = o_Price;
	}
	
	public String getO_DeliveryAddress() {
		return O_DeliveryAddress;
	}
	public void setO_DeliveryAddress(String o_DeliveryAddress) {
		O_DeliveryAddress = o_DeliveryAddress;
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
	public int getO_ID() {
		return O_ID;
	}
	public void setO_ID(int o_ID) {
		O_ID = o_ID;
	}
	public String getO_Code() {
		return O_Code;
	}
	public void setO_Code(String o_Code) {
		O_Code = o_Code;
	}
	public String getO_ClientCode() {
		return O_ClientCode;
	}
	public void setO_ClientCode(String o_ClientCode) {
		O_ClientCode = o_ClientCode;
	}
	public String getO_OrderDate() {
		return O_OrderDate;
	}
	public void setO_OrderDate(String o_OrderDate) {
		O_OrderDate = o_OrderDate;
	}
	public String getO_DueDate() {
		return O_DueDate;
	}
	public void setO_DueDate(String o_DueDate) {
		O_DueDate = o_DueDate;
	}
	public mOrders(int o_ID, String o_Code, String o_ClientCode, String o_OrderDate, String o_DueDate,
			String o_DeliveryAddress, float o_DeliveryLat, float o_DeliveryLng, String o_TimeEarly, String o_TimeLate,
			String o_Status_Code, String o_RouteCode, float o_Price, String route_Code) {
		super();
		O_ID = o_ID;
		O_Code = o_Code;
		O_ClientCode = o_ClientCode;
		O_OrderDate = o_OrderDate;
		O_DueDate = o_DueDate;
		O_DeliveryAddress = o_DeliveryAddress;
		O_DeliveryLat = o_DeliveryLat;
		O_DeliveryLng = o_DeliveryLng;
		O_TimeEarly = o_TimeEarly;
		O_TimeLate = o_TimeLate;
		O_Status_Code = o_Status_Code;
		O_RouteCode = o_RouteCode;
		O_Price = o_Price;
		//Route_Code = route_Code;
	}
	public mOrders() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "mOrders [O_ID=" + O_ID + ", O_Code=" + O_Code + ", O_ClientCode=" + O_ClientCode + ", O_OrderDate="
				+ O_OrderDate + ", O_DueDate=" + O_DueDate + ", O_DeliveryAddress=" + O_DeliveryAddress
				+ ", O_DeliveryLat=" + O_DeliveryLat + ", O_DeliveryLng=" + O_DeliveryLng + ", O_TimeEarly="
				+ O_TimeEarly + ", O_TimeLate=" + O_TimeLate + ", O_Status_Code=" + O_Status_Code + ", O_RouteCode="
				+ O_RouteCode + ", O_Price=" + O_Price +"]";
	}
	
	
}
