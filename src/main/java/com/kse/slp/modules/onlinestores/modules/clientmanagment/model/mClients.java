package com.kse.slp.modules.onlinestores.clientmanagment.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tblclients")
public class mClients implements Serializable{
	@Id
	@GeneratedValue
	int C_ID;
	String C_Code;
	String C_Adress;
	String C_Name;
	String C_PhoneNumber;
	float C_Lng;
	float C_Lat;
	int C_Freq;
	String C_Description;
	String C_FacebookAccount;
	String C_Email;
	public int getC_ID() {
		return C_ID;
	}
	public void setC_ID(int c_ID) {
		C_ID = c_ID;
	}
	public String getC_Code() {
		return C_Code;
	}
	public void setC_Code(String c_Code) {
		C_Code = c_Code;
	}
	public String getC_Adress() {
		return C_Adress;
	}
	public void setC_Adress(String c_Adress) {
		C_Adress = c_Adress;
	}
	public String getC_Name() {
		return C_Name;
	}
	public void setC_Name(String c_Name) {
		C_Name = c_Name;
	}
	public String getC_PhoneNumber() {
		return C_PhoneNumber;
	}
	public void setC_PhoneNumber(String c_PhoneNumber) {
		C_PhoneNumber = c_PhoneNumber;
	}
	public float getC_Lng() {
		return C_Lng;
	}
	public void setC_Lng(float c_Lng) {
		C_Lng = c_Lng;
	}
	public float getC_Lat() {
		return C_Lat;
	}
	public void setC_Lat(float c_Lat) {
		C_Lat = c_Lat;
	}
	public int getC_Freq() {
		return C_Freq;
	}
	public void setC_Freq(int c_Freq) {
		C_Freq = c_Freq;
	}
	public String getC_Description() {
		return C_Description;
	}
	public void setC_Description(String c_Description) {
		C_Description = c_Description;
	}
	public String getC_FacebookAccount() {
		return C_FacebookAccount;
	}
	public void setC_FacebookAccount(String c_FacebookAccount) {
		C_FacebookAccount = c_FacebookAccount;
	}
	public String getC_Email() {
		return C_Email;
	}
	public void setC_Email(String c_Email) {
		C_Email = c_Email;
	}
	
}
