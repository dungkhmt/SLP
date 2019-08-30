package com.kse.slp.modules.timaappraisalscheduling.model;

import org.springframework.web.multipart.MultipartFile;

public class TimaAppraisalSchedulingInput {
	private MultipartFile TimaAppraisalSchedulingInputRequest;

	public MultipartFile getTimaAppraisalSchedulingInputRequest() {
		return TimaAppraisalSchedulingInputRequest;
	}

	public void setTimaAppraisalSchedulingInputRequest(MultipartFile timaAppraisalSchedulingInputRequest) {
		TimaAppraisalSchedulingInputRequest = timaAppraisalSchedulingInputRequest;
	}

	public TimaAppraisalSchedulingInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimaAppraisalSchedulingInput(MultipartFile timaAppraisalSchedulingInputRequest) {
		super();
		TimaAppraisalSchedulingInputRequest = timaAppraisalSchedulingInputRequest;
	}
}
