package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

public class mPoiInRoute {
	float lat;
	float lng;
	String orderCode;
	int deliveried;
	public mPoiInRoute(float lat, float lng,String orderCode,int deliveried) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.orderCode=orderCode;
		this.deliveried=deliveried;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public int getDeliveried() {
		return deliveried;
	}
	public void setDeliveried(int deliveried) {
		this.deliveried = deliveried;
	}
	
}
