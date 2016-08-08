package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;

public class mJSONResponseToCreateRoute {
	private List<mOrders> lstOrders;
	private List<mShippers> lstShippers;
	private List<mRouteUnderCreation> lstRTUnCreation;
	public List<mOrders> getLstOrders() {
		return lstOrders;
	}
	public void setLstOrders(List<mOrders> lstOrders) {
		this.lstOrders = lstOrders;
	}
	public List<mShippers> getLstShippers() {
		return lstShippers;
	}
	public void setLstShippers(List<mShippers> lstShippers) {
		this.lstShippers = lstShippers;
	}
	public List<mRouteUnderCreation> getLstRTUnCreation() {
		return lstRTUnCreation;
	}
	public void setLstRTUnCreation(List<mRouteUnderCreation> lstRTUnCreation) {
		this.lstRTUnCreation = lstRTUnCreation;
	}
	public mJSONResponseToCreateRoute() {
		super();
		// TODO Auto-generated constructor stub
	}
	public mJSONResponseToCreateRoute(List<mOrders> lstOrders, List<mShippers> lstShippers,
			List<mRouteUnderCreation> lstRTUnCreation) {
		super();
		this.lstOrders = lstOrders;
		this.lstShippers = lstShippers;
		this.lstRTUnCreation = lstRTUnCreation;
	}
	
	
}
