package com.kse.slp.modules.api.deliverygoods.model;
public class DeliveryRequest {
	private String requestCode;
	private String deliveryAddress;
	private String deliveryLatLng;// location (lat, lng) of the request
	private String earlyDeliveryTime;// early delivery time point
	private String lateDeliveryTime;// late delivery time point
	private double weight;// weight
	private double volumn;// volumn
	public DeliveryRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeliveryRequest(String requestCode, String deliveryAddress,
			String deliveryLatLng, String earlyDeliveryTime,
			String lateDeliveryTime, double weight, double volumn) {
		super();
		this.requestCode = requestCode;
		this.deliveryAddress = deliveryAddress;
		this.deliveryLatLng = deliveryLatLng;
		this.earlyDeliveryTime = earlyDeliveryTime;
		this.lateDeliveryTime = lateDeliveryTime;
		this.weight = weight;
		this.volumn = volumn;
	}
	public String getRequestCode() {
		return requestCode;
	}
	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getDeliveryLatLng() {
		return deliveryLatLng;
	}
	public void setDeliveryLatLng(String deliveryLatLng) {
		this.deliveryLatLng = deliveryLatLng;
	}
	public String getEarlyDeliveryTime() {
		return earlyDeliveryTime;
	}
	public void setEarlyDeliveryTime(String earlyDeliveryTime) {
		this.earlyDeliveryTime = earlyDeliveryTime;
	}
	public String getLateDeliveryTime() {
		return lateDeliveryTime;
	}
	public void setLateDeliveryTime(String lateDeliveryTime) {
		this.lateDeliveryTime = lateDeliveryTime;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getVolumn() {
		return volumn;
	}
	public void setVolumn(double volumn) {
		this.volumn = volumn;
	}
	
	
	
}
