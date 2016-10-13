package com.kse.slp.modules.dichung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.dichung.dao.RouteDetailDiChungDAO;
import com.kse.slp.modules.dichung.model.RouteDetailDiChung;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.RouteDetailContainer;

@Service("RouteDetailDiChungService")
public class RouteDetailDiChungServiceImpl implements RouteDetailDiChungService{
	@Autowired
	RouteDetailDiChungDAO routeDetailDiChungDAO;
	@Override
	public int saveARouteDetailDiChung(String rDDC_RouteCode,
			String rDDC_TicketCode, int rDDC_Sequence, int rDDC_Group,String rDDC_Address,String rDDC_DistanceToNext,String rDDC_TravelTimeToNext,String rDDC_PickupDateTime,String rDDC_LatLng,String rDDC_DeliveryAddress) {
		// TODO Auto-generated method stub
		RouteDetailDiChung route= new RouteDetailDiChung();
		route.setRDDC_RouteCode(rDDC_RouteCode);
		route.setRDDC_TicketCode(rDDC_TicketCode);
		route.setRDDC_Group(rDDC_Group);
		route.setRDDC_Sequence(rDDC_Sequence);
		route.setRDDC_Address(rDDC_Address);
		route.setRDDC_DistanceToNext(rDDC_DistanceToNext);
		route.setRDDC_TravelTimeToNext(rDDC_TravelTimeToNext);
		route.setRDDC_PickupDateTime(rDDC_PickupDateTime);
		route.setRDDC_LatLng(rDDC_LatLng);
		route.setRDDC_DeliveryAddress(rDDC_DeliveryAddress);
		return routeDetailDiChungDAO.saveARouteDetail(route);
	}

	@Override
	public void deleteRoutesbyRouteCode(String routeCode) {
		// TODO Auto-generated method stub
		routeDetailDiChungDAO.deleteRoutesbyRouteCode(routeCode);
	}

	@Override
	public List<RouteDetailDiChung> loadRouteDetailByRouteCode(
			String routeCode) {
		// TODO Auto-generated method stub
		return routeDetailDiChungDAO.loadRouteDetailbyRouteCode(routeCode);
	}

}
