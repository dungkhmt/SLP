package com.kse.slp.modules.vrpload3d.model;

import org.springframework.web.multipart.MultipartFile;

public class VRPLoad3DInput {
	private MultipartFile vrpInputRequest;

	public MultipartFile getVrpInputRequest() {
		return vrpInputRequest;
	}

	public void setVrpInputRequest(MultipartFile vrpInputRequest) {
		this.vrpInputRequest = vrpInputRequest;
	}

	public VRPLoad3DInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VRPLoad3DInput(MultipartFile vrpInputRequest) {
		super();
		this.vrpInputRequest = vrpInputRequest;
	}
}
