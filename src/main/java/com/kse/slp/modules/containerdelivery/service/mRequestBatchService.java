package com.kse.slp.modules.containerdelivery.service;

import java.util.List;

import com.kse.slp.modules.containerdelivery.model.RequestBatch;

public interface mRequestBatchService {
	public List<RequestBatch> getList();
	public RequestBatch getByCode(String code);
	public List<RequestBatch> getList(String CustomerCode);
}
