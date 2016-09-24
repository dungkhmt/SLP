package com.kse.slp.modules.api.pickupdeliverycontainers.model;
public class PickupDeliverySolution {
	private PickupDeliveryRoute[] routes;

	public PickupDeliverySolution() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PickupDeliverySolution(PickupDeliveryRoute[] routes) {
		super();
		this.routes = routes;
	}

	public PickupDeliveryRoute[] getRoutes() {
		return routes;
	}

	public void setRoutes(PickupDeliveryRoute[] routes) {
		this.routes = routes;
	}
	
}
