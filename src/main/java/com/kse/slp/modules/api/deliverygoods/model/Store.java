package com.kse.slp.modules.api.deliverygoods.model;
public class Store {
	private String code;
	private String name;
	private String address;
	private String latlng;
	
	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Store(String code, String name, String address, String latlng) {
		super();
		this.code = code;
		this.name = name;
		this.address = address;
		this.latlng = latlng;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatlng() {
		return latlng;
	}
	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}
	
}
