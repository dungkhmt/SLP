package com.kse.slp.modules.usermanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblstaffcustomer")
public class StaffCustomer {
	@Id
	@GeneratedValue
	int STFCUS_ID;
	String 	STFCUS_Username;
	String STFCUS_FullName;
	String STFCUS_CustomerCode;
	public int getSTFCUS_ID() {
		return STFCUS_ID;
	}
	public void setSTFCUS_ID(int sTFCUS_ID) {
		STFCUS_ID = sTFCUS_ID;
	}
	public String getSTFCUS_Username() {
		return STFCUS_Username;
	}
	public void setSTFCUS_Username(String sTFCUS_Username) {
		STFCUS_Username = sTFCUS_Username;
	}
	public String getSTFCUS_FullName() {
		return STFCUS_FullName;
	}
	public void setSTFCUS_FullName(String sTFCUS_FullName) {
		STFCUS_FullName = sTFCUS_FullName;
	}
	public String getSTFCUS_CustomerCode() {
		return STFCUS_CustomerCode;
	}
	public void setSTFCUS_CustomerCode(String sTFCUS_CustomerCode) {
		STFCUS_CustomerCode = sTFCUS_CustomerCode;
	}
	
}
