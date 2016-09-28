package com.kse.slp.modules.dichung.service;

import java.util.List;

import com.kse.slp.modules.dichung.model.RouteDetailDiChung;



public interface RouteDetailDiChungService {
	public int saveARouteDetailDiChung(String rDDC_RouteCode,String rDDC_TicketCode,int rDDC_Sequence,int rDDC_Group);
	public void deleteRoutesbyRouteCode(String routeCode);
	public List<RouteDetailDiChung> loadRouteContainerDetailByRouteCode(String routeCode);
}
