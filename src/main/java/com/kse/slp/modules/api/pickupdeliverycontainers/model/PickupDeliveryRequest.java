package com.kse.slp.modules.api.pickupdeliverycontainers.model;
public class PickupDeliveryRequest {
	private String requestCode;// code of the request
	
	private String pickupAddress;// pickup address of the request
	private String pickupLatLng;// location (lat,lng) of the pickup
	private String pickupDateTime;// expected date-time of the pickup
	
	private String deliveryAddress;// delivery address of the request
	private String deliveryLatLng;// location (lat,lng) of the delivery
	private String deliveryDateTime;// expected date-time of the delivery
	
	private int quantity;
	
	public PickupDeliveryRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PickupDeliveryRequest(String requestCode, String pickupAddress,
			String pickupLatLng, String pickupDateTime, String deliveryAddress,
			String deliveryLatLng, String deliveryDateTime, int quantity) {
		super();
		this.requestCode = requestCode;
		this.pickupAddress = pickupAddress;
		this.pickupLatLng = pickupLatLng;
		this.pickupDateTime = pickupDateTime;
		this.deliveryAddress = deliveryAddress;
		this.deliveryLatLng = deliveryLatLng;
		this.deliveryDateTime = deliveryDateTime;
		this.quantity = quantity;
	}

	public String getPickupLatLng() {
		return pickupLatLng;
	}

	public void setPickupLatLng(String pickupLatLng) {
		this.pickupLatLng = pickupLatLng;
	}

	public String getDeliveryLatLng() {
		return deliveryLatLng;
	}

	public void setDeliveryLatLng(String deliveryLatLng) {
		this.deliveryLatLng = deliveryLatLng;
	}

	public String getRequestCode() {
		return requestCode;
	}
	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}
	public String getPickupAddress() {
		return pickupAddress;
	}
	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}
	public String getPickupDateTime() {
		return pickupDateTime;
	}
	public void setPickupDateTime(String pickupDateTime) {
		this.pickupDateTime = pickupDateTime;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getDeliveryDateTime() {
		return deliveryDateTime;
	}
	public void setDeliveryDateTime(String deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
