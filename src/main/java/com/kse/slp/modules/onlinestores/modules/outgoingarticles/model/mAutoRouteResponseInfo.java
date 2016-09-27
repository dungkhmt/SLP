package com.kse.slp.modules.onlinestores.modules.outgoingarticles.model;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.infoAutoRouteElement;

public class mAutoRouteResponseInfo {
	private String shipperCode;
	private List<infoAutoRouteElement> routeElement;
	
	public String getShipperCode() {
		return shipperCode;
	}
	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}
	public List<infoAutoRouteElement> getRouteElement() {
		return routeElement;
	}
	public void setRouteElement(List<infoAutoRouteElement> routeElement) {
		this.routeElement = routeElement;
	}
	public mAutoRouteResponseInfo(String shipperCode,
			List<infoAutoRouteElement> routeElement) {
		super();
		this.shipperCode = shipperCode;
		this.routeElement = routeElement;
	}
	public mAutoRouteResponseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "mAutoRouteResponseInfo [shipperCode=" + shipperCode
				+ ", routeElement=" + routeElement + "]";
	}
}
