package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.mapstreetmanipulation.dao.RoadTypesDAO;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadType;
@Service("RoadTypeService")
public class RoadTypeServiceImpl implements RoadTypeService{
	@Autowired
	RoadTypesDAO roadTypeService;
	@Override
	public List<RoadType> getListRoadType() {
		// TODO Auto-generated method stub
		return roadTypeService.getListRoadType();
	}

}
