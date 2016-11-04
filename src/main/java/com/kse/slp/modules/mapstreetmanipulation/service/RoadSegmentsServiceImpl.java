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
	@Override
	public void saveARoadSegment(int code, int fromPoint, int toPoint,
			double distance, int speed, String bidi) {
		// TODO Auto-generated method stub
		RoadSegment segment = new RoadSegment();
		segment.setRSEG_Bidirectional(bidi);
		segment.setRSEG_Code(code);
		segment.setRSEG_Distance(distance);
		segment.setRSEG_FromPoint(fromPoint);
		segment.setRSEG_Speed(speed);
		segment.setRSEG_ToPoint(toPoint);
		int id = roadSegmentDAO.saveASegment(segment);
		segment.setRSEG_Code(id);
		roadSegmentDAO.updateASegment(segment);
	}
	@Override
	public void deleteASegmentByCod(int code) {
		// TODO Auto-generated method stub
		roadSegmentDAO.deleteSegmentByCode(code);
	}

}
