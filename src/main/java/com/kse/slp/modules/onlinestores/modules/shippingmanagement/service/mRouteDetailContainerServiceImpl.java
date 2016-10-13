package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.mRouteDetailContainerDAO;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.RouteContainerDetailExtension;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.RouteDetailContainer;

@Service("mRouteDetailContainerService")
public class mRouteDetailContainerServiceImpl implements mRouteDetailContainerService {
	@Autowired
	mRouteDetailContainerDAO routeDetailContainerDAO;
	
	@Override
	public int saveARouteDetailContainer(String rTDC_RouteCode,String rTDC_OrderCode,String rTDC_Type,int rTDC_Sequence,int rTDC_Quantity) {
		// TODO Auto-generated method stub
		RouteDetailContainer route= new RouteDetailContainer();
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
	public List<RouteDetailContainer> loadRouteContainerDetailByRouteCode(
			String routeCode) {
		// TODO Auto-generated method stub
		return routeDetailContainerDAO.loadRouteContainerDetailByRouteCode(routeCode);
	}

	@Override
	public int loadQuantityOfOrderInRouteByOrderCode(String orderCode) {
		// TODO Auto-generated method stub
		return routeDetailContainerDAO.loadQuantityOfOrderInRouteByOrderCode(orderCode);
	}

	@Override
	public List<RouteContainerDetailExtension> loadRouteContainerDetailExtension(String routeCode) {
		// TODO Auto-generated method stub
		return routeDetailContainerDAO.loadRouteContainerDetailExtension(routeCode);
	}
	

}
