package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblroutes")
public class mRoutes {
	@Id
	@GeneratedValue

	private int Route_ID;
	private String Route_Code;
	private String Route_Shipper_Code;
	private String Route_Status_Code;
	private String Route_Start_DateTime;
	private String Route_BatchCode;
	
	public String getRoute_BatchCode() {
		return Route_BatchCode;
	}
	public void setRoute_BatchCode(String route_BatchCode) {
		Route_BatchCode = route_BatchCode;
	}
	public String getRoute_Status_Code() {
		return Route_Status_Code;
	}
	public void setRoute_Status_Code(String route_Status_Code) {
		Route_Status_Code = route_Status_Code;
	}
	public String getRoute_Start_DateTime() {
		return Route_Start_DateTime;
	}
	public void setRoute_Start_DateTime(String route_Start_DateTime) {
		Route_Start_DateTime = route_Start_DateTime;
	}
	public int getRoute_ID() {
		return Route_ID;
	}
	public void setRoute_ID(int route_ID) {
		Route_ID = route_ID;
	}
	public String getRoute_Code() {
		return Route_Code;
	}
	public void setRoute_Code(String route_Code) {
		Route_Code = route_Code;
	}
	public String getRoute_Shipper_Code() {
		return Route_Shipper_Code;
	}
	public void setRoute_Shipper_Code(String route_Shipper_Code) {
		Route_Shipper_Code = route_Shipper_Code;
	}
	@Override
	public String toString() {
		return "mRoutes [Route_ID=" + Route_ID + ", Route_Code=" + Route_Code
				+ ", Route_Shipper_Code=" + Route_Shipper_Code
				+ ", Route_Status_Code=" + Route_Status_Code
				+ ", Route_Start_DateTime=" + Route_Start_DateTime
				+ ", Route_BatchCode=" + Route_BatchCode + "]";
	}
	
}
