package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

public class RouteContainerDetailExtension {
	private String clientCode;
	private String clientName;
	private String pickupAdress;
	private String arriveTimePickup;
	private String expectedTimePickup;
	private String deliveryAdress;
	private String arriveTimeDelivery;
	
	private String expectedTimeDelivery;
	private double pickupLat;
	private double pickupLng;
	private double deliveryLat;
	private double deliveryLng;
	private int volumn;
	private int sequence;

	private String type;

	public String getArriveTimeDelivery() {
		return arriveTimeDelivery;
	}
	public void setArriveTimeDelivery(String arriveTimeDelivery) {
		this.arriveTimeDelivery = arriveTimeDelivery;
	}
	public double getPickupLat() {
		return pickupLat;
	}
	public void setPickupLat(double pickupLat) {
		this.pickupLat = pickupLat;
	}
	public double getPickupLng() {
		return pickupLng;
	}
	public void setPickupLng(double pickupLng) {
		this.pickupLng = pickupLng;
	}
	public double getDeliveryLat() {
		return deliveryLat;
	}
	public void setDeliveryLat(double deliveryLat) {
		this.deliveryLat = deliveryLat;
	}
	public double getDeliveryLng() {
		return deliveryLng;
	}
	public void setDeliveryLng(double deliveryLng) {
		this.deliveryLng = deliveryLng;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getPickupAdress() {
		return pickupAdress;
	}
	public void setPickupAdress(String pickupAdress) {
		this.pickupAdress = pickupAdress;
	}
	public String getArriveTimePickup() {
		return arriveTimePickup;
	}
	public void setArriveTimePickup(String arriveTimePickup) {
		this.arriveTimePickup = arriveTimePickup;
	}
	public String getExpectedTimePickup() {
		return expectedTimePickup;
	}
	public void setExpectedTimePickup(String expectedTimePickup) {
		this.expectedTimePickup = expectedTimePickup;
	}
	public String getDeliveryAdress() {
		return deliveryAdress;
	}
	public void setDeliveryAdress(String deliveryAdress) {
		this.deliveryAdress = deliveryAdress;
	}
	
	public String getExpectedTimeDelivery() {
		return expectedTimeDelivery;
	}
	public void setExpectedTimeDelivery(String expectedTimeDelivery) {
		this.expectedTimeDelivery = expectedTimeDelivery;
	}
	
	public int getVolumn() {
		return volumn;
	}
	public void setVolumn(int volumn) {
		this.volumn = volumn;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	@Override
	public String toString() {
		return "RouteContainerDetailExtension [clientCode=" + clientCode
				+ ", clientName=" + clientName + ", pickupAdress="
				+ pickupAdress + ", arriveTimePickup=" + arriveTimePickup
				+ ", expectedTimePickup=" + expectedTimePickup
				+ ", deliveryAdress=" + deliveryAdress
				+ ", arriveTimeDelivery=" + arriveTimeDelivery
				+ ", expectedTimeDelivery=" + expectedTimeDelivery
				+ ", pickupLat=" + pickupLat + ", pickupLng=" + pickupLng
				+ ", deliveryLat=" + deliveryLat + ", deliveryLng="
				+ deliveryLng + ", volumn=" + volumn + ", sequence=" + sequence
				+ ", type=" + type + "]" +"\n" ;
	}
	

	
	
	
}
