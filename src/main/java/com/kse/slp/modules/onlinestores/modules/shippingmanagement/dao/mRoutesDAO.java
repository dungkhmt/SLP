package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteUnderCreation;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;

public interface mRoutesDAO {
	public List<mRoutes> loadRoutebyShipperCode(String shipperCode);
	public List<mRouteUnderCreation> getLstRTUnderCreation();
}
