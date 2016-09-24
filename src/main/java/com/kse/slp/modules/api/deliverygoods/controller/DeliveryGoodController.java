package com.kse.ezRoutingAPI.deliverygoods.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kse.ezRoutingAPI.deliverygoods.model.DeliveryGoodInput;
import com.kse.ezRoutingAPI.deliverygoods.model.DeliveryGoodSolution;
import com.kse.ezRoutingAPI.deliverygoods.service.DeliveryGoodService;

@RestController

public class DeliveryGoodController {
	@RequestMapping(value="/delivery-goods-plan", method = RequestMethod.POST)
	public DeliveryGoodSolution computeDeliveryGoodSolution(@RequestBody DeliveryGoodInput input){
		DeliveryGoodService service = new DeliveryGoodService();
		return service.computeDeliveryGoodSolution(input);
	}

}
