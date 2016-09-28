package com.kse.slp.modules.containerdelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.containerdelivery.dao.mRequestBatchDAO;
import com.kse.slp.modules.containerdelivery.model.RequestBatch;

@Service("mRequestBatchService")
public class mRequestBatchServiceImpl implements mRequestBatchService {
	@Autowired
	mRequestBatchDAO requestBatchDAO;
	@Override
	public List<RequestBatch> getList() {
		// TODO Auto-generated method stub
		return requestBatchDAO.getList();
	}
	@Override
	public RequestBatch getByCode(String code) {
		// TODO Auto-generated method stub
		return requestBatchDAO.getByCode(code);
	}
	@Override
	public List<RequestBatch> getList(String CustomerCode) {
		// TODO Auto-generated method stub
		return requestBatchDAO.getList(CustomerCode);
	}

}
