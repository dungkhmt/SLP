package com.kse.slp.modules.api.deliverygoods.model;
public class DeliveryGoodRoute {
	private DeliveryGoodRouteElement[] routeElements;

	public DeliveryGoodRoute() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeliveryGoodRoute(DeliveryGoodRouteElement[] routeElements) {
		super();
		this.routeElements = routeElements;
	}

	public DeliveryGoodRouteElement[] getRouteElements() {
		return routeElements;
	}

	public void setRouteElements(DeliveryGoodRouteElement[] routeElements) {
		this.routeElements = routeElements;
	}
	
}
