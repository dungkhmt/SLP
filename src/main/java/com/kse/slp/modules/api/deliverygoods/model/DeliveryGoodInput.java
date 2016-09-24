package com.kse.slp.modules.api.deliverygoods.model;

public class DeliveryGoodInput {
	private DeliveryRequest[] requests;
	private Store store;
	private Shipper[] shippers;
	public DeliveryGoodInput() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeliveryGoodInput(DeliveryRequest[] requests, Store store,
			Shipper[] shippers) {
		super();
		this.requests = requests;
		this.store = store;
		this.shippers = shippers;
	}
	public DeliveryRequest[] getRequests() {
		return requests;
	}
	public void setRequests(DeliveryRequest[] requests) {
		this.requests = requests;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Shipper[] getShippers() {
		return shippers;
	}
	public void setShippers(Shipper[] shippers) {
		this.shippers = shippers;
	}
	
	
	
}
