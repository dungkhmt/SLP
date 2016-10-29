package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.mapstreetmanipulation.dao.RoadSegmentsDAO;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadSegment;
@Service("RoadSegmentsService")
public class RoadSegmentsServiceImpl implements RoadSegmentsService {
	@Autowired
	RoadSegmentsDAO roadSegmentDAO;
	@Override
	public List<RoadSegment> getList() {
		// TODO Auto-generated method stub
		return roadSegmentDAO.getList();
	}

}
