package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.RouteContainerDetailExtension;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.RouteDetailContainer;

public interface mRouteDetailContainerService {
	public int saveARouteDetailContainer(String rTDC_RouteCode,String rTDC_OrderCode,String rTDC_Type,String rTDC_TimeToNext,int rTDC_Sequence,int rTDC_Quantity);
	public void deleteRoutesbyRouteCode(String routeCode);
	public List<RouteDetailContainer> loadRouteContainerDetailByRouteCode(String routeCode);
	public int loadQuantityOfOrderInRouteByOrderCode(String orderCode);
	public List<RouteContainerDetailExtension> loadRouteContainerDetailExtension(String routeCode);
}
