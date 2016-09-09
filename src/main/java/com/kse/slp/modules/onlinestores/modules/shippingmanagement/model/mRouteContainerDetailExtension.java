package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

public class mRouteContainerDetailExtension {
	private String clientCode;
	private String clientName;
	private String pickupAdress;
	private String arriveTimePickup;
	private String expectedTimePickup;
	private String deliveryAdress;
	private String arriveTimeDeleivery;
	private String expectedTimeDelivery;
	private int volumn;
	private int sequence;
	private String driver;
	private String timeStartRoute;
	
	public String getTimeStartRoute() {
		return timeStartRoute;
	}
	public void setTimeStartRoute(String timeStartRoute) {
		this.timeStartRoute = timeStartRoute;
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
	public String getArriveTimeDeleivery() {
		return arriveTimeDeleivery;
	}
	public void setArriveTimeDeleivery(String arriveTimeDeleivery) {
		this.arriveTimeDeleivery = arriveTimeDeleivery;
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
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	@Override
	public String toString() {
		return "mRouteContainerDetailExtension [clientCode=" + clientCode
				+ ", clientName=" + clientName + ", pickupAdress="
				+ pickupAdress + ", arriveTimePickup=" + arriveTimePickup
				+ ", expectedTimePickup=" + expectedTimePickup
				+ ", deliveryAdress=" + deliveryAdress
				+ ", arriveTimeDeleivery=" + arriveTimeDeleivery
				+ ", expectedTimeDelivery=" + expectedTimeDelivery
				+ ", volumn=" + volumn + ", sequence=" + sequence + ", driver="
				+ driver + ", timeStartRoute=" + timeStartRoute + "]";
	}
	
	
}
