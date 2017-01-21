package com.kse.slp.modules.api.requestshippermatching.model;

import java.util.Arrays;

public class RequestShipperMatchingSolution {
	private RequestShipperMatchingRoute[] routes;

	public RequestShipperMatchingRoute[] getRoutes() {
		return routes;
	}

	public void setRoutes(RequestShipperMatchingRoute[] routes) {
		this.routes = routes;
	}

	@Override
	public String toString() {
		return "RequestShipperMatchingSolution [routes="
				+ Arrays.toString(routes) + "]";
	}	
	
}
