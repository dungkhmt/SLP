package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.mRouteDetailDAO;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;

@Service("mRouteDetailService")
public class mRouteDetailServiceImpl implements mRouteDetailService {
	@Autowired
	mRouteDetailDAO routeDetailDAO;
	@Override
	public List<mRouteDetail> loadRouteDetailbyRouteCode(String routeCode) {
		// TODO Auto-generated method stub
		return routeDetailDAO.loadRouteDetailbyRouteCode(routeCode);
	}
	@Override
	public int saveARouteDetail(String rTD_OrderCode, String rTD_RouteCode, int rTD_Sequence) {
		// TODO Auto-generated method stub
		mRouteDetail routeDetail = new mRouteDetail();
		routeDetail.setRTD_OrderCode(rTD_OrderCode);
		routeDetail.setRTD_RouteCode(rTD_RouteCode);
		routeDetail.setRTD_Sequence(rTD_Sequence);
		int id = routeDetailDAO.saveARouteDetail(routeDetail);
		return id;
	}
	@Override
	public void removeRoutesByRouteCode(String route_Code) {
		// TODO Auto-generated method stub
		routeDetailDAO.deleteRoutesbyRouteCode(route_Code);
	}

}
