package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteUnderCreation;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;

public interface mRoutesDAO {
	public List<mRoutes> loadRoutebyShipperCode(String shipperCode);
	public List<mRouteUnderCreation> getLstRTUnderCreation();
	public int removeARoute(int route_Id);
	public int saveARoute(mRoutes route);
	public List<mRoutes> loadRoutebyRouteCode(String routeCode);
	public void removeRouteByRouteCode(String route_Code);
	
}
