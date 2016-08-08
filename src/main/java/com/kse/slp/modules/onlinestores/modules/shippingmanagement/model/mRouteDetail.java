package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblroutedetail")
public class mRouteDetail {
	@Id
	@GeneratedValue
	private int RTD_ID;
	private String RTD_OrderCode;
	private String RTD_RouteCode;
	private int RTD_Sequence;
	public int getRTD_ID() {
		return RTD_ID;
	}
	public void setRTD_ID(int rTD_ID) {
		RTD_ID = rTD_ID;
	}
	public String getRTD_OrderCode() {
		return RTD_OrderCode;
	}
	public void setRTD_OrderCode(String rTD_OrderCode) {
		RTD_OrderCode = rTD_OrderCode;
	}
	public String getRTD_RouteCode() {
		return RTD_RouteCode;
	}
	public void setRTD_RouteCode(String rTD_RouteCode) {
		RTD_RouteCode = rTD_RouteCode;
	}
	public int getRTD_Sequence() {
		return RTD_Sequence;
	}
	public void setRTD_Sequence(int rTD_Sequence) {
		RTD_Sequence = rTD_Sequence;
	}
	@Override
	public String toString() {
		return "mRouteDetail [RTD_ID=" + RTD_ID + ", RTD_OrderCode="
				+ RTD_OrderCode + ", RTD_RouteCode=" + RTD_RouteCode
				+ ", RTD_Sequence=" + RTD_Sequence + "]";
	}
	

}
