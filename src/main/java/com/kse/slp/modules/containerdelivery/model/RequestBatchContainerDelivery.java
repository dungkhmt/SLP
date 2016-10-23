package com.kse.slp.modules.containerdelivery.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblrequestbatchcontainerdelivery")
public class RequestBatchContainerDelivery implements Serializable {
	@Id
	@GeneratedValue
	int REQBAT_ID;
	String REQBAT_Code;
	String REQBAT_Description;
	String 	REQBAT_CustomerCode;
	public int getREQBAT_ID() {
		return REQBAT_ID;
	}
	public void setREQBAT_ID(int rEQBAT_ID) {
		REQBAT_ID = rEQBAT_ID;
	}
	public String getREQBAT_Code() {
		return REQBAT_Code;
	}
	public void setREQBAT_Code(String rEQBAT_Code) {
		REQBAT_Code = rEQBAT_Code;
	}
	public String getREQBAT_Description() {
		return REQBAT_Description;
	}
	public void setREQBAT_Description(String rEQBAT_Description) {
		REQBAT_Description = rEQBAT_Description;
	}
	public String getREQBAT_CustomerCode() {
		return REQBAT_CustomerCode;
	}
	public void setREQBAT_CustomerCode(String rEQBAT_CustomerCode) {
		REQBAT_CustomerCode = rEQBAT_CustomerCode;
	}
	
	
}
