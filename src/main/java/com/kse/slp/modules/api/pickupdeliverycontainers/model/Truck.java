package com.kse.slp.modules.api.pickupdeliverycontainers.model;
public class Truck {
	private String code;// number code
	private int capacity;// number of containers
	private String driver;// name of the driver
	private String currentLatLng;// current location (lat,lng)
	private String depotLatLng;// location (lat,lng) of the depot if not null
	public Truck() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Truck(String code, int capacity, String driver,
			String currentLatLng, String depotLatLng) {
		super();
		this.code = code;
		this.capacity = capacity;
		this.driver = driver;
		this.currentLatLng = currentLatLng;
		this.depotLatLng = depotLatLng;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getCurrentLatLng() {
		return currentLatLng;
	}
	public void setCurrentLatLng(String currentLatLng) {
		this.currentLatLng = currentLatLng;
	}
	public String getDepotLatLng() {
		return depotLatLng;
	}
	public void setDepotLatLng(String depotLatLng) {
		this.depotLatLng = depotLatLng;
	}
	
	
	
}
