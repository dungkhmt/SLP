package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.infoAutoRouteElement;

public interface InfoAutoRouteElementDAO {
	public List<infoAutoRouteElement> getList(String BatchCode);
}
