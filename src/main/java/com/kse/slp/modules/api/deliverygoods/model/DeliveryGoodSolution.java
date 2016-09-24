package com.kse.slp.modules.api.deliverygoods.model;
public class DeliveryGoodSolution {
	private DeliveryGoodRoute[] routes;

	public DeliveryGoodSolution() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeliveryGoodSolution(DeliveryGoodRoute[] routes) {
		super();
		this.routes = routes;
	}

	public DeliveryGoodRoute[] getRoutes() {
		return routes;
	}

	public void setRoutes(DeliveryGoodRoute[] routes) {
		this.routes = routes;
	}
	
	
}
