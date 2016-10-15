package com.kse.slp.modules.onlinestores.modules.outgoingarticles.model;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.infoAutoRouteElement;

public class mAutoRouteResponseInfo {
	private String storeLatLng;
	private String shipperCode;
	private String routeCode;
	private List<infoAutoRouteElement> routeElement;
	
	public String getRouteCode() {
		return routeCode;
	}
	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}
	public String getStoreLatLng() {
		return storeLatLng;
	}
	public void setStoreLatLng(String storeLatLng) {
		this.storeLatLng = storeLatLng;
	}
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
	public mAutoRouteResponseInfo(String storeLatLng, String shipperCode,String routeCode,
			List<infoAutoRouteElement> routeElement) {
		super();
		this.storeLatLng = storeLatLng;
		this.routeCode = routeCode;
		this.shipperCode = shipperCode;
		this.routeElement = routeElement;
	}
	public mAutoRouteResponseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "mAutoRouteResponseInfo [storeLatLng= "+storeLatLng+", shipperCode=" + shipperCode
				+ ", routeElement=" + routeElement + "]";
	}
}
