package com.kse.slp.modules.containerdelivery.dao;

import java.util.List;


import com.kse.slp.modules.containerdelivery.model.RequestBatchOnlineStore;

public interface mRequestBatchOnlineStoreDAO {
	public List<RequestBatchOnlineStore> getList();
	public RequestBatchOnlineStore getByCode(String code);
	public List<RequestBatchOnlineStore> getList(String CustomerCode);
}
