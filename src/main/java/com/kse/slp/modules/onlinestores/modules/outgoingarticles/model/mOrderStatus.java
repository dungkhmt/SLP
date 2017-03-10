package com.kse.slp.modules.onlinestores.modules.outgoingarticles.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tblorderstatus")
public class mOrderStatus implements Serializable {
	@Id
	@GeneratedValue
	private int OST_ID;
	private String OST_Code;
	private String OST_Name;
	public int getOST_ID() {
		return OST_ID;
	}
	public void setOST_ID(int oST_ID) {
		OST_ID = oST_ID;
	}
	public String getOST_Code() {
		return OST_Code;
	}
	public void setOST_Code(String oST_Code) {
		OST_Code = oST_Code;
	}
	public String getOST_Name() {
		return OST_Name;
	}
	public void setOST_Name(String oST_Name) {
		OST_Name = oST_Name;
	}

}
