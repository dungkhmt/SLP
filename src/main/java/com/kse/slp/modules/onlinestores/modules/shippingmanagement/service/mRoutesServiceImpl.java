package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.mRouteDetailDAO;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.mRoutesDAO;
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
	public void saveARoute(String routeCode, String route_Shipper_Code,
			String route_Start_Time, String route_StatusCode) {
		mRoutes route=new mRoutes();
		route.setRoute_Code(GenerationDateTimeFormat.genDateTimeFormatyyyyMMddCurrently());
		route.setRoute_Shipper_Code(route_Shipper_Code);
		route.setRoute_Start_Time(route_Start_Time);
		
		route.setRoute_Status_Code(Constants.ROUTE_STATUS_UNDER_CREATION);
		if(routeCode==null) 
			routeDAO.saveARoute(route);
		else {
			List<mRoutes> listRoute= routeDAO.loadRoutebyRouteCode(routeCode);
			for(int i=0;i<listRoute.size();i++){
				routeDAO.removeARoute(listRoute.get(i).getRoute_ID());
			}
			routeDAO.saveARoute(route);
			routeDetailDAO.deleteRoutesbyRouteCode(routeCode);
		}
		
	}
	
}
