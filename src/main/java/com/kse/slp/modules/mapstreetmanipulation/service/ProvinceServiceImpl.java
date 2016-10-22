package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.mapstreetmanipulation.dao.ProvinceDAO;
import com.kse.slp.modules.mapstreetmanipulation.model.Province;
@Service("ProvinceService")
public class ProvinceServiceImpl implements ProvinceService{
	@Autowired
	ProvinceDAO proviceDAO;
	@Override
	public List<Province> getListProvince() {
		// TODO Auto-generated method stub
		return proviceDAO.getListProvince();
	}

}
