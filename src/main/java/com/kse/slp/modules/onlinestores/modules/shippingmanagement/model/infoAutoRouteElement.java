package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

public class infoAutoRouteElement {
	String clientCode;
	String clientName;
	String clientAddress;
	float addLat;
	float addLng;
	String expectedTime;
	int routeSequence;
	
	public int getRouteSequence() {
		return routeSequence;
	}
	public void setRouteSequence(int routeSequence) {
		this.routeSequence = routeSequence;
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
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public float getAddLat() {
		return addLat;
	}
	public void setAddLat(float addLat) {
		this.addLat = addLat;
	}
	public float getAddLng() {
		return addLng;
	}
	public void setAddLng(float addLng) {
		this.addLng = addLng;
	}
	public String getExpectedTime() {
		return expectedTime;
	}
	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}
	
	public infoAutoRouteElement(String clientCode, String clientName,
			String clientAddress, float addLat, float addLng,
			String expectedTime, int routeSequence) {
		super();
		this.clientCode = clientCode;
		this.clientName = clientName;
		this.clientAddress = clientAddress;
		this.addLat = addLat;
		this.addLng = addLng;
		this.expectedTime = expectedTime;
		this.routeSequence = routeSequence;
	}
	public infoAutoRouteElement() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "infoAutoRouteElement [clientCode=" + clientCode
				+ ", clientName=" + clientName + ", clientAddress="
				+ clientAddress + ", addLat=" + addLat + ", addLng=" + addLng
				+ ", expectedTime=" + expectedTime + ", routeSequence="
				+ routeSequence + "]";
	}
	
	
}
