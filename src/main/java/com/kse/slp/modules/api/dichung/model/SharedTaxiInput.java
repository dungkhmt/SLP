package com.kse.slp.modules.api.dichung.model;

public class SharedTaxiInput {
	private SharedTaxiRequest[] requests;
	private int[] vehicleCapacities; //[4,6]
	private int maxWaitTime;// in seconds (1800)
	
	private int forbidenStraightDistance;// in meters. (6000)
						//If the straight distance between two pickups is greater than forbidenStraightDistance
						// then these requests cannot be shared
	private int forbidenTimeDistance;// in seconds (3600)
						// If the distance between two pickup times is greater than forbidenTimeDistance
						// then these requests cannot be shared
	
	
	private int maxTime;// in seconds: max computation time
	
	
	public SharedTaxiInput() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public SharedTaxiInput(SharedTaxiRequest[] requests,
			int[] vehicleCapacities, int maxWaitTime,
			int forbidenStraightDistance, int forbidenTimeDistance, int maxTime) {
		super();
		this.requests = requests;
		this.vehicleCapacities = vehicleCapacities;
		this.maxWaitTime = maxWaitTime;
		this.forbidenStraightDistance = forbidenStraightDistance;
		this.forbidenTimeDistance = forbidenTimeDistance;
		this.maxTime = maxTime;
	}


	public int getForbidenStraightDistance() {
		return forbidenStraightDistance;
	}


	public void setForbidenStraightDistance(int forbidenStraightDistance) {
		this.forbidenStraightDistance = forbidenStraightDistance;
	}


	public int getForbidenTimeDistance() {
		return forbidenTimeDistance;
	}


	public void setForbidenTimeDistance(int forbidenTimeDistance) {
		this.forbidenTimeDistance = forbidenTimeDistance;
	}


	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	public int[] getVehicleCapacities() {
		return vehicleCapacities;
	}
	public void setVehicleCapacities(int[] vehicleCapacities) {
		this.vehicleCapacities = vehicleCapacities;
	}
	public SharedTaxiRequest[] getRequests() {
		return requests;
	}
	public void setRequests(SharedTaxiRequest[] requests) {
		this.requests = requests;
	}
	public int getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}
	
	
}
