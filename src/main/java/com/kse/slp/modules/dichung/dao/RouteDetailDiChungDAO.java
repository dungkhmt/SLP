package com.kse.slp.modules.dichung.dao;

import java.util.List;

import com.kse.slp.modules.dichung.model.RouteDetailDiChung;



public interface RouteDetailDiChungDAO {
	public List<RouteDetailDiChung> loadRouteDetailbyRouteCode(String routeCode);
	public void deleteRoutesbyRouteCode(String routeCode);
	public int saveARouteDetail(RouteDetailDiChung route);
}
