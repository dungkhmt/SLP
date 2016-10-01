package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblstorebatch")
public class StoreBatch {
	@Id
	@GeneratedValue
	int STBAT_ID;
	String STBAT_StoreCode;
	String STBAT_BatchCode;
	public int getSTBAT_ID() {
		return STBAT_ID;
	}
	public void setSTBAT_ID(int sTBAT_ID) {
		STBAT_ID = sTBAT_ID;
	}
	public String getSTBAT_StoreCode() {
		return STBAT_StoreCode;
	}
	public void setSTBAT_StoreCode(String sTBAT_StoreCode) {
		STBAT_StoreCode = sTBAT_StoreCode;
	}
	public String getSTBAT_BatchCode() {
		return STBAT_BatchCode;
	}
	public void setSTBAT_BatchCode(String sTBAT_BatchCode) {
		STBAT_BatchCode = sTBAT_BatchCode;
	}
	
}
