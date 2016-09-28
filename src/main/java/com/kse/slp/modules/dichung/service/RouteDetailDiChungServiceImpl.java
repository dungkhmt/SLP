package com.kse.slp.modules.dichung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.dichung.dao.RouteDetailDiChungDAO;
import com.kse.slp.modules.dichung.model.RouteDetailDiChung;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetailContainer;

@Service("RouteDetailDiChungService")
public class RouteDetailDiChungServiceImpl implements RouteDetailDiChungService{
	@Autowired
	RouteDetailDiChungDAO routeDetailDiChungDAO;
	@Override
	public int saveARouteDetailDiChung(String rDDC_RouteCode,
			String rDDC_TicketCode, int rDDC_Sequence, int rDDC_Group) {
		// TODO Auto-generated method stub
		RouteDetailDiChung route= new RouteDetailDiChung();
		route.setRDDC_RouteCode(rDDC_RouteCode);
		route.setRDDC_TicketCode(rDDC_TicketCode);
		route.setRDDC_Group(rDDC_Group);
		route.setRDDC_Sequence(rDDC_Sequence);
		return routeDetailDiChungDAO.saveARouteDetail(route);
	}

	@Override
	public void deleteRoutesbyRouteCode(String routeCode) {
		// TODO Auto-generated method stub
		routeDetailDiChungDAO.deleteRoutesbyRouteCode(routeCode);
	}

	@Override
	public List<RouteDetailDiChung> loadRouteContainerDetailByRouteCode(
			String routeCode) {
		// TODO Auto-generated method stub
		return routeDetailDiChungDAO.loadRouteDetailbyRouteCode(routeCode);
	}

}
