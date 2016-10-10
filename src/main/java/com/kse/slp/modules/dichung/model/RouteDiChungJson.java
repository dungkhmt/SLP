package com.kse.slp.modules.dichung.model;

import java.util.List;

public class RouteDiChungJson {
	private String Route_Code;
	private String Route_Shipper_Code;
	private String Route_Start_DateTime;
	private String Route_BatchCode;
	private List<RouteDetailDiChung> listPoint;
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
	public String getRoute_Start_DateTime() {
		return Route_Start_DateTime;
	}
	public void setRoute_Start_DateTime(String route_Start_DateTime) {
		Route_Start_DateTime = route_Start_DateTime;
	}
	public String getRoute_BatchCode() {
		return Route_BatchCode;
	}
	public void setRoute_BatchCode(String route_BatchCode) {
		Route_BatchCode = route_BatchCode;
	}
	public List<RouteDetailDiChung> getListPoint() {
		return listPoint;
	}
	public void setListPoint(List<RouteDetailDiChung> listPoint) {
		this.listPoint = listPoint;
	}
	
}
