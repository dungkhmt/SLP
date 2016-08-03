package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.mRoutesDAO;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
@Service("mRoutesService")
public class mRoutesServiceImpl implements mRoutesService {
	@Autowired
	mRoutesDAO routeDAO;
	
	@Override
	public List<mRoutes> loadRoutebyShipperCode(String shipperCode) {
		// TODO Auto-generated method stub
		return routeDAO.loadRoutebyShipperCode(shipperCode);
	}

}
