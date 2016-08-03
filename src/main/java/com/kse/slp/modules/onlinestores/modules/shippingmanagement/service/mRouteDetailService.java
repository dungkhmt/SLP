package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;

public interface mRouteDetailService {
	public List<mRouteDetail> loadRouteDetailbyRouteCode(String routeCode);
}
