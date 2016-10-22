package com.kse.slp.modules.mapstreetmanipulation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblprovinces")
public class Provinces {
	@Id
	@GeneratedValue
	int PROV_ID;
	String PROV_Code;
	String PROV_Name;
	public int getPROV_ID() {
		return PROV_ID;
	}
	public void setPROV_ID(int pROV_ID) {
		PROV_ID = pROV_ID;
	}
	public String getPROV_Code() {
		return PROV_Code;
	}
	public void setPROV_Code(String pROV_Code) {
		PROV_Code = pROV_Code;
	}
	public String getPROV_Name() {
		return PROV_Name;
	}
	public void setPROV_Name(String pROV_Name) {
		PROV_Name = pROV_Name;
	}
	
}
