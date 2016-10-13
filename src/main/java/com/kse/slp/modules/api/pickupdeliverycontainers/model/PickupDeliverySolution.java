package com.kse.slp.modules.api.pickupdeliverycontainers.model;

import java.util.Arrays;

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

	@Override
	public String toString() {
		return "PickupDeliverySolution [routes=" + Arrays.toString(routes)
				+ "]";
	}
	
}
