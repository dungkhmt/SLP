package com.kse.slp.modules.dichung.model;

import org.springframework.web.multipart.MultipartFile;

public class mFormAddFileExcel {
	private String batchCode;
	
	private MultipartFile ordersFile;

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public MultipartFile getOrdersFile() {
		return ordersFile;
	}

	public void setOrdersFile(MultipartFile ordersFile) {
		this.ordersFile = ordersFile;
	}

	public mFormAddFileExcel(String batchCode, MultipartFile ordersFile) {
		super();
		this.batchCode = batchCode;
		this.ordersFile = ordersFile;
	}

	public mFormAddFileExcel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
