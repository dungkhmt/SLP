package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

import java.util.Arrays;

public class mRouteUnderCreation {
	private String route_Code;
	private String order_Code;
	private int order_Sequence;
	private String shipper_Code;
	private String route_Start_Time;
	public String getRoute_Code() {
		return route_Code;
	}
	public void setRoute_Code(String route_Code) {
		this.route_Code = route_Code;
	}
	public String getOrder_Code() {
		return order_Code;
	}
	public void setOrder_Code(String order_Code) {
		this.order_Code = order_Code;
	}
	public int getOrder_Sequence() {
		return order_Sequence;
	}
	public void setOrder_Sequence(int order_Sequence) {
		this.order_Sequence = order_Sequence;
	}
	public String getShipper_Code() {
		return shipper_Code;
	}
	public void setShipper_Code(String shipper_Code) {
		this.shipper_Code = shipper_Code;
	}
	public String getRoute_Start_Time() {
		return route_Start_Time;
	}
	public void setRoute_Start_Time(String route_Start_Time) {
		this.route_Start_Time = route_Start_Time;
	}
	public mRouteUnderCreation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public mRouteUnderCreation(String route_Code, String order_Code, int order_Sequence, String shipper_Code,
			String route_Start_Time) {
		super();
		this.route_Code = route_Code;
		this.order_Code = order_Code;
		this.order_Sequence = order_Sequence;
		this.shipper_Code = shipper_Code;
		this.route_Start_Time = route_Start_Time;
	}
	@Override
	public String toString() {
		return "mRouteUnderCreation [route_Code=" + route_Code + ", order_Code=" + order_Code + ", order_Sequence="
				+ order_Sequence + ", shipper_Code=" + shipper_Code + ", route_Start_Time=" + route_Start_Time + "]";
	}

	
}
