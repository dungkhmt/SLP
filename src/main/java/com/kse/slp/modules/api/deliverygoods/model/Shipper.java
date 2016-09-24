package com.kse.slp.modules.api.deliverygoods.model;
public class Shipper {
	private String shipperCode;// userID
	private String name;// fullname
	private String currentLatLng;// current location
	private double weight;// weight capacity
	private double volumn;// volumn capacity
	public Shipper() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Shipper(String shipperCode, String name, String currentLatLng, double weight,
			double volumn) {
		super();
		this.shipperCode = shipperCode;
		this.name = name;
		this.currentLatLng = currentLatLng;
		this.weight = weight;
		this.volumn = volumn;
	}
	public String getShipperCode() {
		return shipperCode;
	}
	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}
	public String getCurrentLatLng() {
		return currentLatLng;
	}
	public void setCurrentLatLng(String currentLatLng) {
		this.currentLatLng = currentLatLng;
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
