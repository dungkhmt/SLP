package com.kse.ezRoutingAPI.pickupdeliverycontainers.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kse.ezRoutingAPI.pickupdeliverycontainers.model.PickupDeliveryInput;
import com.kse.ezRoutingAPI.pickupdeliverycontainers.model.PickupDeliverySolution;
import com.kse.ezRoutingAPI.pickupdeliverycontainers.service.PickupDeliveryContainerService;


@RestController
public class PickupDeliveryContainersController {

	@RequestMapping(value = "/pickup-delivery-containers-plan", method = RequestMethod.POST)
	public PickupDeliverySolution computePickupDeliveryContainerSolution(
			@RequestBody PickupDeliveryInput input) {
		
		PickupDeliveryContainerService service = new PickupDeliveryContainerService();
		return service.computePickupDeliveryContainerSolution(input);
		
	}
}
