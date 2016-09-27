package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.mRouteDetailDAO;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.mRoutesDAO;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteUnderCreation;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
import com.kse.slp.modules.utilities.GenerationDateTimeFormat;
@Service("mRoutesService")
public class mRoutesServiceImpl implements mRoutesService {
	@Autowired
	mRoutesDAO routeDAO;
	@Autowired
	mRouteDetailDAO routeDetailDAO;
	@Override
	public List<mRoutes> loadRoutebyShipperCode(String shipperCode) {
		// TODO Auto-generated method stub
		return routeDAO.loadRoutebyShipperCode(shipperCode);
	}

	@Override
	public List<mRouteUnderCreation> getLstRTUnderCreation() {
		// TODO Auto-generated method stub
		return routeDAO.getLstRTUnderCreation();
	}

	public int saveARoute(String routeCode, String route_Shipper_Code,
			String route_Start_Time, String route_StatusCode, String route_BatchCode) {
		mRoutes route=new mRoutes();
		route.setRoute_Code(routeCode);
		route.setRoute_Shipper_Code(route_Shipper_Code);
		route.setRoute_Start_DateTime(route_Start_Time);
		route.setRoute_Status_Code(route_StatusCode);
		route.setRoute_BatchCode(route_BatchCode);
		
		int id = routeDAO.saveARoute(route);
		return id;
	}

	@Override
	public void removeRoutesByRouteCode(String route_Code) {
		// TODO Auto-generated method stub
		routeDAO.removeRouteByRouteCode(route_Code);
	}

	@Override
	public mRoutes loadRoutesUnderCreationByShipperCode(String shipperCode) {
		// TODO Auto-generated method stub
		return routeDAO.loadRoutesUnderCreationByShipperCode(shipperCode);
	}
}
