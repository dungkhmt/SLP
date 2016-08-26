package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetailContainer;

public interface mRouteDetailContainerDAO {
	public int saveARouteDetailContainer(mRouteDetailContainer route);
	public void deleteRoutesbyRouteCode(String routeCode);
	public List<mRouteDetailContainer> loadRouteContainerDetailByRouteCode(String routeCode);
	public int loadQuantityOfOrderInRouteByOrderCode(String orderCode);
}
