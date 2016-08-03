package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;


public interface mRouteDetailDAO {
	public List<mRouteDetail> loadRouteDetailbyRouteCode(String routeCode);
}
