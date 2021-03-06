package com.kse.slp.modules.api.dichung.model;

public class SharedTaxiInput {
	private String airportAddress;
	private String airportPos;
	private SharedTaxiRequest[] requests;
	private int[] vehicleCapacities;

	// algorithm parameters
	private int maxWaitTime = 1800;// max pickup time (seconds) from first
									// pickup and the last pickup in sharing
									// requests (default 30 minutes)

	private int forbidenStraightDistance;// in meters.
	// If the straight distance between two pickups is greater than
	// forbidenStraightDistance
	// then these requests cannot be shared
	private int forbidenTimeDistance;// in seconds
	// If the distance between two pickup times is greater than
	// forbidenTimeDistance
	// then these requests cannot be shared
	private int maxStandardSharingDistance = 5000;// max distance (m) allowed
													// for sharing two requests
													// in normal time frame
	private int maxHighTrafficSharingDistance = 3000;// max distance (m) allowed
														// for sharing two
														// requests in high
														// traffic jam time
														// frame

	private int maxWaitTimeAirport = 1800;// max waiting time (seconds) at the
											// airport for picking up people to
											// return (defaut 30 minutes)
	private int minWaitTimeAirport = 0;// min waiting time (seconds) at the
										// airport for picking up people to
										// return (default 0)

	private int maxTime;// in seconds: max computation time

	private double approximationDistanceFactor = 1.5;// real distance is about
														// 1.5 times the
														// straight distance
	private double eps = 10;// (m) epsilon distance
	private double stdSpeed = 5;// (m/s) the standard speed in urban city
	private double highTrafficSpeed = 2;// (m/s) speed in high traffic
	private double speedToAirport = 70 * 10 / 36; // (m/s) speed from external
													// city to airport
	private int deltaRequestTime = 900;// (seconds) the request
											// pickup/delivery time is about +-
											// 15 minutes

	public SharedTaxiInput() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SharedTaxiInput(
													String airportAddress,
													String airportPos,
													SharedTaxiRequest[] requests,
													int[] vehicleCapacities,
													int maxWaitTime,
													int forbidenStraightDistance,
													int forbidenTimeDistance,
													int maxStandardSharingDistance,
													int maxHighTrafficSharingDistance,
													int maxWaitTimeAirport,
													int minWaitTimeAirport,
													int maxTime,
													double approximationDistanceFactor,
													double eps,
													double stdSpeed,
													double highTrafficSpeed,
													double speedToAirport,
													int deltaRequestTime) {
												super();
												this.airportAddress = airportAddress;
												this.airportPos = airportPos;
												this.requests = requests;
												this.vehicleCapacities = vehicleCapacities;
												this.maxWaitTime = maxWaitTime;
												this.forbidenStraightDistance = forbidenStraightDistance;
												this.forbidenTimeDistance = forbidenTimeDistance;
												this.maxStandardSharingDistance = maxStandardSharingDistance;
												this.maxHighTrafficSharingDistance = maxHighTrafficSharingDistance;
												this.maxWaitTimeAirport = maxWaitTimeAirport;
												this.minWaitTimeAirport = minWaitTimeAirport;
												this.maxTime = maxTime;
												this.approximationDistanceFactor = approximationDistanceFactor;
												this.eps = eps;
												this.stdSpeed = stdSpeed;
												this.highTrafficSpeed = highTrafficSpeed;
												this.speedToAirport = speedToAirport;
												this.deltaRequestTime = deltaRequestTime;
											}


	
	public String getAirportAddress() {
		return airportAddress;
	}


	public void setAirportAddress(String airportAddress) {
		this.airportAddress = airportAddress;
	}


	public String getAirportPos() {
		return airportPos;
	}


	public void setAirportPos(String airportPos) {
		this.airportPos = airportPos;
	}


	public double getApproximationDistanceFactor() {
		return approximationDistanceFactor;
	}

	public void setApproximationDistanceFactor(
			double approximationDistanceFactor) {
		this.approximationDistanceFactor = approximationDistanceFactor;
	}

	public double getEps() {
		return eps;
	}

	public void setEps(double eps) {
		this.eps = eps;
	}

	public double getStdSpeed() {
		return stdSpeed;
	}

	public void setStdSpeed(double stdSpeed) {
		this.stdSpeed = stdSpeed;
	}

	public double getHighTrafficSpeed() {
		return highTrafficSpeed;
	}

	public void setHighTrafficSpeed(double highTrafficSpeed) {
		this.highTrafficSpeed = highTrafficSpeed;
	}

	public double getSpeedToAirport() {
		return speedToAirport;
	}

	public void setSpeedToAirport(double speedToAirport) {
		this.speedToAirport = speedToAirport;
	}

	public int getDeltaRequestTime() {
		return deltaRequestTime;
	}

	public void setDeltaRequestTime(int deltaRequestTime) {
		this.deltaRequestTime = deltaRequestTime;
	}

	public int getMaxStandardSharingDistance() {
		return maxStandardSharingDistance;
	}

	public void setMaxStandardSharingDistance(int maxStandardSharingDistance) {
		this.maxStandardSharingDistance = maxStandardSharingDistance;
	}

	public int getMaxHighTrafficSharingDistance() {
		return maxHighTrafficSharingDistance;
	}

	public void setMaxHighTrafficSharingDistance(
			int maxHighTrafficSharingDistance) {
		this.maxHighTrafficSharingDistance = maxHighTrafficSharingDistance;
	}

	public int getMaxWaitTimeAirport() {
		return maxWaitTimeAirport;
	}

	public void setMaxWaitTimeAirport(int maxWaitTimeAirport) {
		this.maxWaitTimeAirport = maxWaitTimeAirport;
	}

	public int getMinWaitTimeAirport() {
		return minWaitTimeAirport;
	}

	public void setMinWaitTimeAirport(int minWaitTimeAirport) {
		this.minWaitTimeAirport = minWaitTimeAirport;
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
