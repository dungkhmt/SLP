package com.kse.slp.modules.dichung.dao;

import java.util.List;

import com.kse.slp.modules.dichung.model.RequestDiChung;

public interface RequestDiChungDAO {
	public int saveARequest(RequestDiChung request);
	public void updateARequest(RequestDiChung request);
	public List<RequestDiChung> getList();
	public List<RequestDiChung> getListInBatch(String batchCode);
	public void deleteRequestDiChungInBatch(String batchCode);
}
