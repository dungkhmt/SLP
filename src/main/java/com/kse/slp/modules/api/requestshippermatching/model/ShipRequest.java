package com.kse.slp.modules.api.requestshippermatching.model;

public class ShipRequest {
	private String code;
	private String pickupAddress;
	private String pickupLocation;// latlng
	private String deliveryAddress;
	private String deliveryLocation;// latlng
	
	
	public ShipRequest(String code, String pickupAddress,
			String pickupLocation, String deliveryAddress,
			String deliveryLocation) {
		super();
		this.code = code;
		this.pickupAddress = pickupAddress;
		this.pickupLocation = pickupLocation;
		this.deliveryAddress = deliveryAddress;
		this.deliveryLocation = deliveryLocation;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getPickupAddress() {
		return pickupAddress;
	}


	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}


	public String getPickupLocation() {
		return pickupLocation;
	}


	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}


	public String getDeliveryAddress() {
		return deliveryAddress;
	}


	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}


	public String getDeliveryLocation() {
		return deliveryLocation;
	}


	public void setDeliveryLocation(String deliveryLocation) {
		this.deliveryLocation = deliveryLocation;
	}


	public ShipRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "ShipRequest [code=" + code + ", pickupAddress=" + pickupAddress
				+ ", pickupLocation=" + pickupLocation + ", deliveryAddress="
				+ deliveryAddress + ", deliveryLocation=" + deliveryLocation
				+ "]";
	}
	
	
	
}
