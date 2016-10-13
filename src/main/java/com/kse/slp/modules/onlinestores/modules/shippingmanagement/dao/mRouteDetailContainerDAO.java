package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.RouteContainerDetailExtension;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.RouteDetailContainer;

public interface mRouteDetailContainerDAO {
	public int saveARouteDetailContainer(RouteDetailContainer route);
	public void deleteRoutesbyRouteCode(String routeCode);
	public List<RouteDetailContainer> loadRouteContainerDetailByRouteCode(String routeCode);
	public int loadQuantityOfOrderInRouteByOrderCode(String orderCode);
	public List<RouteContainerDetailExtension> loadRouteContainerDetailExtension(String routeCode);
}