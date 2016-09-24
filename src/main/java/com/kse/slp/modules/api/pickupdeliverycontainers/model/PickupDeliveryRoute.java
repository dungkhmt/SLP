package com.kse.slp.modules.api.pickupdeliverycontainers.model;
public class PickupDeliveryRoute {
	private PickupDeliveryRouteElement[] routeElements;

	public PickupDeliveryRoute() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PickupDeliveryRoute(PickupDeliveryRouteElement[] routeElements) {
		super();
		this.routeElements = routeElements;
	}

	public PickupDeliveryRouteElement[] getRouteElements() {
		return routeElements;
	}

	public void setRouteElements(PickupDeliveryRouteElement[] routeElements) {
		this.routeElements = routeElements;
	}
	
}
