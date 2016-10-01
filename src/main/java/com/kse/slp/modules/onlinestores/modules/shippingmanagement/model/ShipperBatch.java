package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblshipperbatch")
public class ShipperBatch {
	@Id
	@GeneratedValue
	int SHPBAT_ID;
	String 	SHPBAT_ShipperCode;
	String SHPBAT_BatchCode;
	public int getSHPBAT_ID() {
		return SHPBAT_ID;
	}
	public void setSHPBAT_ID(int sHPBAT_ID) {
		SHPBAT_ID = sHPBAT_ID;
	}
	public String getSHPBAT_ShipperCode() {
		return SHPBAT_ShipperCode;
	}
	public void setSHPBAT_ShipperCode(String sHPBAT_ShipperCode) {
		SHPBAT_ShipperCode = sHPBAT_ShipperCode;
	}
	public String getSHPBAT_BatchCode() {
		return SHPBAT_BatchCode;
	}
	public void setSHPBAT_BatchCode(String sHPBAT_BatchCode) {
		SHPBAT_BatchCode = sHPBAT_BatchCode;
	}
	
}
