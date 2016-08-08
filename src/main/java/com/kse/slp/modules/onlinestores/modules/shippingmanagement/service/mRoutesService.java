package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteUnderCreation;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;

public interface mRoutesService {
	public List<mRoutes> loadRoutebyShipperCode(String shipperCode);
	public List<mRouteUnderCreation> getLstRTUnderCreation();
	public int saveARoute(String route_Code,String route_Shipper_Code,String route_Start_Time,String route_StatusCode);
	public void removeRoutesByRouteCode(String route_Code);
}
