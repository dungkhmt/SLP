package com.kse.slp.modules.containerdelivery.model;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.RouteContainerDetailExtension;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.RouteDetailContainer;



public class RouteContainerDeliveryJson {
	private String Route_Code;
	private String Route_Shipper_Code;
	private String Route_Start_DateTime;
	private String Route_BatchCode;
	private List<RouteContainerDetailExtension> listPoint;
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
	
	public List<RouteContainerDetailExtension> getListPoint() {
		return listPoint;
	}
	public void setListPoint(List<RouteContainerDetailExtension> listPoint) {
		this.listPoint = listPoint;
	}
	@Override
	public String toString() {
		return "RouteContainerDeliveryJson [Route_Code=" + Route_Code
				+ ", Route_Shipper_Code=" + Route_Shipper_Code
				+ ", Route_Start_DateTime=" + Route_Start_DateTime
				+ ", Route_BatchCode=" + Route_BatchCode + ", listPoint="
				+ listPoint + "]";
	}
	public RouteContainerDeliveryJson(String route_Code,
			String route_Shipper_Code, String route_Start_DateTime,
			String route_BatchCode,
			List<RouteContainerDetailExtension> listPoint) {
		super();
		Route_Code = route_Code;
		Route_Shipper_Code = route_Shipper_Code;
		Route_Start_DateTime = route_Start_DateTime;
		Route_BatchCode = route_BatchCode;
		this.listPoint = listPoint;
	}
	
}
