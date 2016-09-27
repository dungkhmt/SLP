package com.kse.slp.modules.api.dichung.model;

public class SharedTaxiRequest {
	private String ticketCode;
	private String departTime;
	private String chungName;// tên sai
	private String pickupAddress;
	private String deliveryAddress;
	private int numberPassengers;
	public SharedTaxiRequest(String ticketCode, String departTime,
			String chungName, String pickupAddress, String deliveryAddress,
			int numberPassengers) {
		super();
		this.ticketCode = ticketCode;
		this.departTime = departTime;
		this.chungName = chungName;
		this.pickupAddress = pickupAddress;
		this.deliveryAddress = deliveryAddress;
		this.numberPassengers = numberPassengers;
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
	public String getChungName() {
		return chungName;
	}
	public void setChungName(String chungName) {
		this.chungName = chungName;
	}
	public String getPickupAddress() {
		return pickupAddress;
	}
	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public int getNumberPassengers() {
		return numberPassengers;
	}
	public void setNumberPassengers(int numberPassengers) {
		this.numberPassengers = numberPassengers;
	}
	public SharedTaxiRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
