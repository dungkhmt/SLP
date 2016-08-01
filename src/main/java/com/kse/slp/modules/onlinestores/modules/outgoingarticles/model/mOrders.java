package com.kse.slp.modules.onlinestores.modules.outgoingarticles.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
	String O_DeliveryAdress;
	float O_DeliveryLat;
	float O_DeliveryLng;
	String O_TimeEarly;
	String O_TimeLate;
	
	public String getO_DeliveryAdress() {
		return O_DeliveryAdress;
	}
	public void setO_DeliveryAdress(String o_DeliveryAdress) {
		O_DeliveryAdress = o_DeliveryAdress;
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
	
}
