package com.kse.slp.modules.onlinestores.modules.outgoingarticles.model;

import java.util.List;

public class mAutoRouteJSONResponse {
	private List<mAutoRouteResponseInfo> routes;

	public List<mAutoRouteResponseInfo> getRoutes() {
		return routes;
	}

	public void setRoutes(List<mAutoRouteResponseInfo> routes) {
		this.routes = routes;
	}

	public mAutoRouteJSONResponse(List<mAutoRouteResponseInfo> routes) {
		super();
		this.routes = routes;
	}

	public mAutoRouteJSONResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
}
