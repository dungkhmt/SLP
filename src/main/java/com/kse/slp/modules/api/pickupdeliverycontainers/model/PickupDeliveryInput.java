package com.kse.slp.modules.api.pickupdeliverycontainers.model;

public class PickupDeliveryInput {
	private PickupDeliveryRequest[] requests;
	private Truck[] trucks;
	
	public PickupDeliveryInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PickupDeliveryInput(PickupDeliveryRequest[] requests, Truck[] trucks) {
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

	public PickupDeliveryRequest[] getRequests() {
		return requests;
	}

	public void setRequests(PickupDeliveryRequest[] requests) {
		this.requests = requests;
	}
	
}
