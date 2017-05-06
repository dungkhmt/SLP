package com.kse.slp.modules.tspd.model;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class TSPDSolutionFile {
	private MultipartFile tspdSolutionFile;

	public MultipartFile getTspdSolutionFile() {
		return tspdSolutionFile;
	}

	public void setTspdSolutionFile(MultipartFile tspdSolutionFile) {
		this.tspdSolutionFile = tspdSolutionFile;
	}


}
