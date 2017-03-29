package com.kse.slp.modules.tspd.model;

import org.springframework.web.multipart.MultipartFile;

public class TSPDInput {
	private MultipartFile tspdInputRequest;

	public MultipartFile getTspdInputRequest() {
		return tspdInputRequest;
	}

	public void setTspdInputRequest(MultipartFile tspdInputRequest) {
		this.tspdInputRequest = tspdInputRequest;
	}

	public TSPDInput(MultipartFile tspdInputRequest) {
		super();
		this.tspdInputRequest = tspdInputRequest;
	}

	public TSPDInput() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
