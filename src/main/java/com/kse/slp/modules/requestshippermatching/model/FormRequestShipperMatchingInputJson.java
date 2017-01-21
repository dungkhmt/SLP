package com.kse.slp.modules.requestshippermatching.model;

import org.springframework.web.multipart.MultipartFile;

public class FormRequestShipperMatchingInputJson {
	private MultipartFile shipperMatchingRequest;

	public MultipartFile getShipperMatchingRequest() {
		return shipperMatchingRequest;
	}

	public void setShipperMatchingRequest(MultipartFile shipperMatchingRequest) {
		this.shipperMatchingRequest = shipperMatchingRequest;
	}

	public FormRequestShipperMatchingInputJson(
			MultipartFile shipperMatchingRequest) {
		super();
		this.shipperMatchingRequest = shipperMatchingRequest;
	}

	public FormRequestShipperMatchingInputJson() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
