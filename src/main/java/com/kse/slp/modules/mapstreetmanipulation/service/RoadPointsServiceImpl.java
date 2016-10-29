package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.mapstreetmanipulation.dao.RoadPointsDAO;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadPoint;
@Service("RoadPointsService")
public class RoadPointsServiceImpl implements RoadPointsService {
	@Autowired
	RoadPointsDAO roadPointDAO;
	@Override
	public List<RoadPoint> getList() {
		// TODO Auto-generated method stub
		return roadPointDAO.getList();
	}

}
