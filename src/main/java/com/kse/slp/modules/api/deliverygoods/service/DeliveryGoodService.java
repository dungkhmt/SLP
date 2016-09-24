package com.kse.slp.modules.api.deliverygoods.service;

import java.util.ArrayList;
import java.util.HashMap;
import com.kse.slp.modules.api.deliverygoods.model.*;



public class DeliveryGoodService {
	public static final int SPEED = 16;// 16m/s
	public static final double APPX = 1.5;// approximation factor for distance
	public static final int serviceDuration = 1800;// 30 minutes for pickup or delivery

	
	private DeliveryRequest[] requests;
	private Store store;
	private Shipper[] shippers;

	
	public String name(){
		return "DeliveryGoodService";
	}
	public DeliveryGoodSolution computeDeliveryGoodSolution(DeliveryGoodInput input){
		requests = input.getRequests();
		shippers = input.getShippers();
		store = input.getStore();
		
		//TO CALL API
		return null;
	}
}
