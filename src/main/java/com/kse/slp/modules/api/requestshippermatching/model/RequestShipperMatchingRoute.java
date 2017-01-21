package com.kse.slp.modules.api.requestshippermatching.model;

import java.util.Arrays;

public class RequestShipperMatchingRoute {
	private RequestShipperMatchingRouteElement[] route;

	public RequestShipperMatchingRouteElement[] getRoute() {
		return route;
	}

	public void setRoute(RequestShipperMatchingRouteElement[] route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "RequestShipperMatchingRoute [route=" + Arrays.toString(route)
				+ "]";
	}
	
}
