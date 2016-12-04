package com.kse.slp.modules.dichung.model;

import org.springframework.web.multipart.MultipartFile;

public class FormInterCityRequest {
	private MultipartFile longTripRequests;


	public FormInterCityRequest(MultipartFile longTripRequests) {
		super();
		this.longTripRequests = longTripRequests;
	}


	public MultipartFile getLongTripRequests() {
		return longTripRequests;
	}


	public void setLongTripRequests(MultipartFile longTripRequests) {
		this.longTripRequests = longTripRequests;
	}


	public FormInterCityRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
