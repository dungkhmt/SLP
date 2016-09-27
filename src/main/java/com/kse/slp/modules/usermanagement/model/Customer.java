package com.kse.slp.modules.usermanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblcustomers")
public class Customer {
	@Id
	@GeneratedValue
	private int Cus_ID;
	private String Cus_Code;
	private String Cus_Name;
	private String Cus_Phone;
	private String Cus_Address;
	private float Cus_Lat;
	private float Cus_Lng;
	public int getCus_ID() {
		return Cus_ID;
	}
	public void setCus_ID(int cus_ID) {
		Cus_ID = cus_ID;
	}
	public String getCus_Code() {
		return Cus_Code;
	}
	public void setCus_Code(String cus_Code) {
		Cus_Code = cus_Code;
	}
	public String getCus_Name() {
		return Cus_Name;
	}
	public void setCus_Name(String cus_Name) {
		Cus_Name = cus_Name;
	}
	public String getCus_Phone() {
		return Cus_Phone;
	}
	public void setCus_Phone(String cus_Phone) {
		Cus_Phone = cus_Phone;
	}
	public String getCus_Address() {
		return Cus_Address;
	}
	public void setCus_Address(String cus_Address) {
		Cus_Address = cus_Address;
	}
	public float getCus_Lat() {
		return Cus_Lat;
	}
	public void setCus_Lat(float cus_Lat) {
		Cus_Lat = cus_Lat;
	}
	public float getCus_Lng() {
		return Cus_Lng;
	}
	public void setCus_Lng(float cus_Lng) {
		Cus_Lng = cus_Lng;
	}
	public Customer(int cus_ID, String cus_Code, String cus_Name,
			String cus_Phone, String cus_Address, float cus_Lat, float cus_Lng) {
		super();
		Cus_ID = cus_ID;
		Cus_Code = cus_Code;
		Cus_Name = cus_Name;
		Cus_Phone = cus_Phone;
		Cus_Address = cus_Address;
		Cus_Lat = cus_Lat;
		Cus_Lng = cus_Lng;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Customer [Cus_ID=" + Cus_ID + ", Cus_Code=" + Cus_Code
				+ ", Cus_Name=" + Cus_Name + ", Cus_Phone=" + Cus_Phone
				+ ", Cus_Address=" + Cus_Address + ", Cus_Lat=" + Cus_Lat
				+ ", Cus_Lng=" + Cus_Lng + "]";
	}
	
	
}
