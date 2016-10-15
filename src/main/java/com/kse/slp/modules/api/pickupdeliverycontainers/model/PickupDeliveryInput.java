package com.kse.slp.modules.api.pickupdeliverycontainers.model;

import java.util.Arrays;
import java.util.List;

public class PickupDeliveryInput {
	private List<PickupDeliveryRequest> requests;
	private Truck[] trucks;
	
	public PickupDeliveryInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	@Override
	public String toString() {
		return "PickupDeliveryInput [requests=" + requests + ", trucks="
				+ Arrays.toString(trucks) + "]";
	}




	public PickupDeliveryInput(List<PickupDeliveryRequest> requests,
			Truck[] trucks) {
		super();
		this.requests = requests;
		this.trucks = trucks;
	}




	public Truck[] getTrucks() {
		return trucks;
	}

	public void setTrucks(Truck[] trucks) {
		this.trucks = trucks;
	}




	public List<PickupDeliveryRequest> getRequests() {
		return requests;
	}




	public void setRequests(List<PickupDeliveryRequest> requests) {
		this.requests = requests;
	}

	
}
