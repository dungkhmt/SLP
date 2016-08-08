package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

import java.util.Arrays;

public class mJSONRequestCreateRoute {
	private String route_Code;
	private String shipper_Code;
	private String route_Start_Time;
	private String[] orders_In_Route;
	public String getRoute_Code() {
		return route_Code;
	}
	public void setRoute_Code(String route_Code) {
		this.route_Code = route_Code;
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
	public String[] getOrders_In_Route() {
		return orders_In_Route;
	}
	public void setOrders_In_Route(String[] orders_In_Route) {
		this.orders_In_Route = orders_In_Route;
	}
	public mJSONRequestCreateRoute(String route_Code, String shipper_Code, String route_Start_Time,
			String[] orders_In_Route) {
		super();
		this.route_Code = route_Code;
		this.shipper_Code = shipper_Code;
		this.route_Start_Time = route_Start_Time;
		this.orders_In_Route = orders_In_Route;
	}
	public mJSONRequestCreateRoute() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "mJSONRequestCreateRoute [route_Code=" + route_Code + ", shipper_Code=" + shipper_Code
				+ ", route_Start_Time=" + route_Start_Time + ", orders_In_Route=" + Arrays.toString(orders_In_Route)
				+ "]";
	}
	
	
}
