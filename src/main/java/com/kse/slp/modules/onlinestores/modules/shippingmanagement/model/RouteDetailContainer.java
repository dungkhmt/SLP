package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblroutedetailcontainer")
public class RouteDetailContainer {
	@Id
	@GeneratedValue
	int RTDC_Id;
	String RTDC_RouteCode;
	String RTDC_OrderCode;
	String RTDC_Type;
	String RTDC_ArrivalDateTime;
	int RTDC_Sequence;
	int RTDC_Quantity;
	String RTDC_ParentOrderCode;
	
	public String getRTDC_ParentOrderCode() {
		return RTDC_ParentOrderCode;
	}
	public void setRTDC_ParentOrderCode(String rTDC_ParentOrderCode) {
		RTDC_ParentOrderCode = rTDC_ParentOrderCode;
	}
	public String getRTDC_ArrivalDateTime() {
		return RTDC_ArrivalDateTime;
	}
	public void setRTDC_ArrivalDateTime(String rTDC_ArrivalDateTime) {
		RTDC_ArrivalDateTime = rTDC_ArrivalDateTime;
	}
	public int getRTDC_Id() {
		return RTDC_Id;
	}
	public void setRTDC_Id(int rTDC_Id) {
		RTDC_Id = rTDC_Id;
	}
	public String getRTDC_RouteCode() {
		return RTDC_RouteCode;
	}
	public void setRTDC_RouteCode(String rTDC_RouteCode) {
		RTDC_RouteCode = rTDC_RouteCode;
	}
	public String getRTDC_OrderCode() {
		return RTDC_OrderCode;
	}
	public void setRTDC_OrderCode(String rTDC_OrderCode) {
		RTDC_OrderCode = rTDC_OrderCode;
	}
	public String getRTDC_Type() {
		return RTDC_Type;
	}
	public void setRTDC_Type(String rTDC_Type) {
		RTDC_Type = rTDC_Type;
	}

	public int getRTDC_Sequence() {
		return RTDC_Sequence;
	}
	public void setRTDC_Sequence(int rTDC_Sequence) {
		RTDC_Sequence = rTDC_Sequence;
	}
	public int getRTDC_Quantity() {
		return RTDC_Quantity;
	}
	public void setRTDC_Quantity(int rTDC_Quantity) {
		RTDC_Quantity = rTDC_Quantity;
	}
	@Override
	public String toString() {
		return "RouteDetailContainer [RTDC_Id=" + RTDC_Id + ", RTDC_RouteCode="
				+ RTDC_RouteCode + ", RTDC_OrderCode=" + RTDC_OrderCode
				+ ", RTDC_Type=" + RTDC_Type + ", RTDC_ArrivalDateTime="
				+ RTDC_ArrivalDateTime + ", RTDC_Sequence=" + RTDC_Sequence
				+ ", RTDC_Quantity=" + RTDC_Quantity
				+ ", RTDC_ParentOrderCode=" + RTDC_ParentOrderCode + "]";
	}
	
}
