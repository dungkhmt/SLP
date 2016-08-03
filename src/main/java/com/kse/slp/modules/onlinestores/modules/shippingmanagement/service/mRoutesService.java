package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;

public interface mRoutesService {
	public List<mRoutes> loadRoutebyShipperCode(String shipperCode);

}
