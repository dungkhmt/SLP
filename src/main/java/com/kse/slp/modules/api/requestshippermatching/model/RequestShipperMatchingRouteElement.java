package com.kse.slp.modules.api.requestshippermatching.model;

public class RequestShipperMatchingRouteElement {
	private String code;
	private String latlng;
	private String action;//PICKUP or DELIVERY
	

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
	public RequestShipperMatchingRouteElement(String code, String latlng,
			String action) {
		super();
		this.code = code;
		this.latlng = latlng;
		this.action = action;
	}
	@Override
	public String toString() {
		return "RequestShipperMatchingRouteElement [code=" + code + ", latlng="
				+ latlng + ", action=" + action + "]";
	}
}
