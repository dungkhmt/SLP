package com.kse.slp.modules.containerdelivery.dao;

import java.util.List;


import com.kse.slp.modules.containerdelivery.model.RequestBatchDiChung;

public interface mRequestBatchDiChungDAO {
	public List<RequestBatchDiChung> getList();
	public RequestBatchDiChung getByCode(String code);
	public List<RequestBatchDiChung> getList(String CustomerCode);
}
