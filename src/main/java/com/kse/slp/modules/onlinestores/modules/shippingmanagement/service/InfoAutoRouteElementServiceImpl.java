package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.InfoAutoRouteElementDAO;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.infoAutoRouteElement;

@Service("InfoAutoRouteElementService")
public class InfoAutoRouteElementServiceImpl implements
		InfoAutoRouteElementService {

	@Autowired
	InfoAutoRouteElementDAO InfoAutoRouteElementDAO;
	
	@Override
	public List<infoAutoRouteElement> getList(String batchCode) {
		// TODO Auto-generated method stub
		return InfoAutoRouteElementDAO.getList(batchCode);
	}

}
