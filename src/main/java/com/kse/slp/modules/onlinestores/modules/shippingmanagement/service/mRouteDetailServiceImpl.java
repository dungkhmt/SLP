package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.mRouteDetailDAO;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;

@Service("mRouteDetailService")
public class mRouteDetailServiceImpl implements mRouteDetailService {
	@Autowired
	mRouteDetailDAO routeDetailDAO;
	@Override
	public List<mRouteDetail> loadRouteDetailbyRouteCode(String routeCode) {
		// TODO Auto-generated method stub
		return routeDetailDAO.loadRouteDetailbyRouteCode(routeCode);
	}

}
