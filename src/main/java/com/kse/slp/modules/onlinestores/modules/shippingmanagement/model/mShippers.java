package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblshippers")
public class mShippers {

	@Id
	@GeneratedValue
	private int SHP_ID;
	private String SHP_Code;
	private String SHP_DepotAddress;
	private float SHP_DepotLat;
	private float SHP_DepotLng;
	private String SHP_CurrentLocation;
	private String SHP_StatusCode;
	private String SHP_VehicleType;
	private int SHP_Capacity_1;
	private int SHP_Capacity_2;
	private String SHP_Customer_Code;
	public int getSHP_ID() {
		return SHP_ID;
	}
	public void setSHP_ID(int sHP_ID) {
		SHP_ID = sHP_ID;
	}
	public String getSHP_Code() {
		return SHP_Code;
	}
	public void setSHP_Code(String sHP_Code) {
		SHP_Code = sHP_Code;
	}
	public String getSHP_DepotAddress() {
		return SHP_DepotAddress;
	}
	public void setSHP_DepotAddress(String sHP_DepotAddress) {
		SHP_DepotAddress = sHP_DepotAddress;
	}
	public float getSHP_DepotLat() {
		return SHP_DepotLat;
	}
	public void setSHP_DepotLat(float sHP_DepotLat) {
		SHP_DepotLat = sHP_DepotLat;
	}
	public float getSHP_DepotLng() {
		return SHP_DepotLng;
	}
	public void setSHP_DepotLng(float sHP_DepotLng) {
		SHP_DepotLng = sHP_DepotLng;
	}
	public String getSHP_CurrentLocation() {
		return SHP_CurrentLocation;
	}
	public void setSHP_CurrentLocation(String sHP_CurrentLocation) {
		SHP_CurrentLocation = sHP_CurrentLocation;
	}
	public String getSHP_StatusCode() {
		return SHP_StatusCode;
	}
	public void setSHP_StatusCode(String sHP_StatusCode) {
		SHP_StatusCode = sHP_StatusCode;
	}
	public String getSHP_VehicleType() {
		return SHP_VehicleType;
	}
	public void setSHP_VehicleType(String sHP_VehicleType) {
		SHP_VehicleType = sHP_VehicleType;
	}
	public int getSHP_Capacity_1() {
		return SHP_Capacity_1;
	}
	public void setSHP_Capacity_1(int sHP_Capacity_1) {
		SHP_Capacity_1 = sHP_Capacity_1;
	}
	public int getSHP_Capacity_2() {
		return SHP_Capacity_2;
	}
	public void setSHP_Capacity_2(int sHP_Capacity_2) {
		SHP_Capacity_2 = sHP_Capacity_2;
	}
	public String getSHP_Customer_Code() {
		return SHP_Customer_Code;
	}
	public void setSHP_Customer_Code(String sHP_Customer_Code) {
		SHP_Customer_Code = sHP_Customer_Code;
	}
	
}
