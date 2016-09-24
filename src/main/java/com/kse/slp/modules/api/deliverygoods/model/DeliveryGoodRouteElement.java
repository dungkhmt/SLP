package com.kse.slp.modules.api.deliverygoods.model;
public class DeliveryGoodRouteElement {
	private String requestCode;
	private String deliveryAddress;
	private String latlng;
	private String arrivalDateTime;
	private String time2Next;
	private String distance2Next;
	public DeliveryGoodRouteElement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeliveryGoodRouteElement(String requestCode, String deliveryAddress,
			String latlng, String arrivalDateTime, String time2Next,
			String distance2Next) {
		super();
		this.requestCode = requestCode;
		this.deliveryAddress = deliveryAddress;
		this.latlng = latlng;
		this.arrivalDateTime = arrivalDateTime;
		this.time2Next = time2Next;
		this.distance2Next = distance2Next;
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
	public String getLatlng() {
		return latlng;
	}
	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}
	public String getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	public String getTime2Next() {
		return time2Next;
	}
	public void setTime2Next(String time2Next) {
		this.time2Next = time2Next;
	}
	public String getDistance2Next() {
		return distance2Next;
	}
	public void setDistance2Next(String distance2Next) {
		this.distance2Next = distance2Next;
	}
	
	
}
