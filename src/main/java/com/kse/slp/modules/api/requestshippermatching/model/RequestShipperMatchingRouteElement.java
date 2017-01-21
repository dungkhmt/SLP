package com.kse.slp.modules.api.requestshippermatching.model;

public class RequestShipperMatchingRouteElement {
	private String code;
	private String action;//PICKUP or DELIVERY
	@Override
	public String toString() {
		return "RequestShipperMatchingRouteElement [code=" + code + ", action="
				+ action + "]";
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
	public RequestShipperMatchingRouteElement(String code, String action) {
		super();
		this.code = code;
		this.action = action;
	}
	
}
