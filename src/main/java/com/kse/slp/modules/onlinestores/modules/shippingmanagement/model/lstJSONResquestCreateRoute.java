package com.kse.slp.modules.onlinestores.modules.shippingmanagement.model;

import java.util.List;

public class lstJSONResquestCreateRoute {
	List<mJSONRequestCreateRoute> lstJSONresroute;

	public List<mJSONRequestCreateRoute> getLstJSONresroute() {
		return lstJSONresroute;
	}

	public void setLstJSONresroute(List<mJSONRequestCreateRoute> lstJSONresroute) {
		this.lstJSONresroute = lstJSONresroute;
	}

	public lstJSONResquestCreateRoute(List<mJSONRequestCreateRoute> lstJSONresroute) {
		super();
		this.lstJSONresroute = lstJSONresroute;
	}

	public lstJSONResquestCreateRoute() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "lstJSONResquestCreateRoute [lstJSONresroute=" + lstJSONresroute.toString() + "]";
	}
	
}
