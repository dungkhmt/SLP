package com.kse.slp.modules.containerdelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kse.slp.modules.containerdelivery.dao.mRequestBatchDiChungDAO;

import com.kse.slp.modules.containerdelivery.model.RequestBatchDiChung;

@Service("mRequestBatchDiChungService")
public class mRequestBatchDiChungServiceImpl implements mRequestBatchDiChungService {
	@Autowired
	mRequestBatchDiChungDAO requestBatchDAO;
	@Override
	public List<RequestBatchDiChung> getList() {
		// TODO Auto-generated method stub
		return requestBatchDAO.getList();
	}
	@Override
	public RequestBatchDiChung getByCode(String code) {
		// TODO Auto-generated method stub
		return requestBatchDAO.getByCode(code);
	}
	@Override
	public List<RequestBatchDiChung> getList(String CustomerCode) {
		// TODO Auto-generated method stub
		return requestBatchDAO.getList(CustomerCode);
	}

}
