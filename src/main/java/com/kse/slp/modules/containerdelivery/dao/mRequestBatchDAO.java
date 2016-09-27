package com.kse.slp.modules.containerdelivery.dao;

import java.util.List;

import com.kse.slp.modules.containerdelivery.model.RequestBatch;

public interface mRequestBatchDAO {
	public List<RequestBatch> getList();
	public RequestBatch getByCode(String code);
}
