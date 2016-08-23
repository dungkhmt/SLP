package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.mRouteDetailContainerDAO;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetailContainer;

@Service("mRouteDetailService")
public class mRouteDetailContainerServiceImpl implements mRouteDetailContainerService {
	@Autowired
	mRouteDetailContainerDAO routeDetailContainerDAO;
	
	@Override
	public int saveARouteDetailContainer(String rTDC_RouteCode,String rTDC_OrderCode,String rTDC_Type,String rTDC_Sequence,String rTDC_Quantity) {
		// TODO Auto-generated method stub
		mRouteDetailContainer route= new mRouteDetailContainer();
		route.setRTDC_OrderCode(rTDC_OrderCode);
		route.setRTDC_RouteCode(rTDC_RouteCode);
		route.setRTDC_Sequence(rTDC_Sequence);
		route.setRTDC_Type(rTDC_Type);
		route.setRTDC_Quantity(rTDC_Quantity);
		return routeDetailContainerDAO.saveARouteDetailContainer(route);
	}

	@Override
	public void deleteRoutesbyRouteCode(String routeCode) {
		// TODO Auto-generated method stub
		routeDetailContainerDAO.deleteRoutesbyRouteCode(routeCode);
	}

	@Override
	public List<mRouteDetailContainer> loadRouteContainerDetailByRouteCode(
			String routeCode) {
		// TODO Auto-generated method stub
		return routeDetailContainerDAO.loadRouteContainerDetailByRouteCode(routeCode);
	}

}
