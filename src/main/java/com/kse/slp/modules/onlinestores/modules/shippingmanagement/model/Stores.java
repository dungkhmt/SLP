package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblstores")
public class Stores {
	@Id
	@GeneratedValue
	int STR_ID;
	String STR_Code;
	String STR_Address;
	String STR_Name;
	String STR_LatLng;
	String STR_CustomerCode;
	public int getSTR_ID() {
		return STR_ID;
	}
	public void setSTR_ID(int sTR_ID) {
		STR_ID = sTR_ID;
	}
	public String getSTR_Code() {
		return STR_Code;
	}
	public void setSTR_Code(String sTR_Code) {
		STR_Code = sTR_Code;
	}
	public String getSTR_Address() {
		return STR_Address;
	}
	public void setSTR_Address(String sTR_Address) {
		STR_Address = sTR_Address;
	}
	public String getSTR_Name() {
		return STR_Name;
	}
	public void setSTR_Name(String sTR_Name) {
		STR_Name = sTR_Name;
	}
	public String getSTR_LatLng() {
		return STR_LatLng;
	}
	public void setSTR_LatLng(String sTR_LatLng) {
		STR_LatLng = sTR_LatLng;
	}
	public String getSTR_CustomerCode() {
		return STR_CustomerCode;
	}
	public void setSTR_CustomerCode(String sTR_CustomerCode) {
		STR_CustomerCode = sTR_CustomerCode;
	}
	
}
