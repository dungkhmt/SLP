package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteContainerDetailExtension;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetailContainer;

public interface mRouteDetailContainerService {
	public int saveARouteDetailContainer(String rTDC_RouteCode,String rTDC_OrderCode,String rTDC_Type,int rTDC_Sequence,int rTDC_Quantity);
	public void deleteRoutesbyRouteCode(String routeCode);
	public List<mRouteDetailContainer> loadRouteContainerDetailByRouteCode(String routeCode);
	public int loadQuantityOfOrderInRouteByOrderCode(String orderCode);
	public List<mRouteContainerDetailExtension> loadRouteContainerDetailExtension();
}
