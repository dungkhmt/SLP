package com.kse.slp.modules.api.requestshippermatching.model;

public class Shipper {
	private String code;
	private String location;// latlng
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Shipper(String code, String location) {
		super();
		this.code = code;
		this.location = location;
	}
	public Shipper() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Shipper [code=" + code + ", location=" + location + "]";
	}
	
	
}
