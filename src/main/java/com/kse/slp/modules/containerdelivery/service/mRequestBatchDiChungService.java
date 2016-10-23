package com.kse.slp.modules.containerdelivery.service;

import java.util.List;


import com.kse.slp.modules.containerdelivery.model.RequestBatchDiChung;

public interface mRequestBatchDiChungService {
	public List<RequestBatchDiChung> getList();
	public RequestBatchDiChung getByCode(String code);
	public List<RequestBatchDiChung> getList(String CustomerCode);
}
