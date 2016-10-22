package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.mapstreetmanipulation.dao.RoadsDAO;

public class RoadsServiceImpl extends BaseDao implements RoadsService {

	@Autowired
	RoadsDAO RoadsDAO;
	
	@Override
	public List<String> getListByProvince(String proCode) {
		// TODO Auto-generated method stub
		return RoadsDAO.getListByProvince(proCode);
	}

}
