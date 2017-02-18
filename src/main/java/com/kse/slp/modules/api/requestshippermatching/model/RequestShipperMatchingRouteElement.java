package com.kse.slp.modules.api.requestshippermatching.model;

public class RequestShipperMatchingRouteElement {
	private String code;
	private String latlng;
	private String action;//PICKUP or DELIVERY
	private String address;// address of this point
	private double distance2Next;// distance to next point
	

	public RequestShipperMatchingRouteElement(String code, String latlng,
			String action, String address, double distance2Next) {
		super();
		this.code = code;
		this.latlng = latlng;
		this.action = action;
		this.address = address;
		this.distance2Next = distance2Next;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getDistance2Next() {
		return distance2Next;
	}
	public void setDistance2Next(double distance2Next) {
		this.distance2Next = distance2Next;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getLatlng() {
		return latlng;
	}
	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}
	
	@Override
	public String toString() {
		return "RequestShipperMatchingRouteElement [code=" + code + ", latlng="
				+ latlng + ", action=" + action + ", address=" + address
				+ ", distance2Next=" + distance2Next + "]";
	}
	
	
	
	
}
