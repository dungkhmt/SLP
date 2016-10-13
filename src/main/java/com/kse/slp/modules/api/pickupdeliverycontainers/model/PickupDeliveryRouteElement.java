package com.kse.slp.modules.api.pickupdeliverycontainers.model;
public class PickupDeliveryRouteElement {
	private String requestCode;
	private String arrivalDateTime;
	private String address;
	private String latlng;
	private String action;// PICKUP or DELIVERY
	private int quantity;
	private String timeToNext;
	private String distanceToNext;
	
	public PickupDeliveryRouteElement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PickupDeliveryRouteElement(String requestCode,
			String arrivalDateTime, String address, String latlng, String action, int quantity, String timeToNext, String distanceToNext) {
		super();
		this.requestCode = requestCode;
		this.arrivalDateTime = arrivalDateTime;
		this.address = address;
		this.latlng = latlng;
		this.action = action;
		this.quantity = quantity;
		this.timeToNext = timeToNext;
		this.distanceToNext = distanceToNext;
	}
	
	public String getTimeToNext() {
		return timeToNext;
	}
	public void setTimeToNext(String timeToNext) {
		this.timeToNext = timeToNext;
	}
	public String getDistanceToNext() {
		return distanceToNext;
	}
	public void setDistanceToNext(String distanceToNext) {
		this.distanceToNext = distanceToNext;
	}
	public String getLatlng() {
		return latlng;
	}
	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getRequestCode() {
		return requestCode;
	}
	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}
	public String getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return "PickupDeliveryRouteElement [requestCode=" + requestCode
				+ ", arrivalDateTime=" + arrivalDateTime + ", address="
				+ address + ", latlng=" + latlng + ", action=" + action
				+ ", quantity=" + quantity + ", timeToNext=" + timeToNext
				+ ", distanceToNext=" + distanceToNext + "]";
	}
	
}
