package com.kse.slp.modules.dichung.model;

public class SharedLongTripElement {
	private String ticketCode;
	private String departTime;
	private String pickupAddress;
	private String pickupPosition;// lat-lng
	private String deliveryAddress;
	private String deliveryPosition;// lat-lng
	
	public SharedLongTripElement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public SharedLongTripElement(String ticketCode, String departTime,
			String pickupAddress, String pickupPosition,
			String deliveryAddress, String deliveryPosition) {
		super();
		this.ticketCode = ticketCode;
		this.departTime = departTime;
		this.pickupAddress = pickupAddress;
		this.pickupPosition = pickupPosition;
		this.deliveryAddress = deliveryAddress;
		this.deliveryPosition = deliveryPosition;
	}


	public String getTicketCode() {
		return ticketCode;
	}
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}
	public String getDepartTime() {
		return departTime;
	}
	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}
	public String getPickupAddress() {
		return pickupAddress;
	}
	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}
	public String getPickupPosition() {
		return pickupPosition;
	}
	public void setPickupPosition(String pickupPosition) {
		this.pickupPosition = pickupPosition;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getDeliveryPosition() {
		return deliveryPosition;
	}
	public void setDeliveryPosition(String deliveryPosition) {
		this.deliveryPosition = deliveryPosition;
	}
	
}
