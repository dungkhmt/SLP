package com.kse.slp.modules.dichung.model;



public class SharedLongTripInput {
	private SharedLongTripRequest[] requests;
	private int minimumSharedPrice;// minimum total revenue for sharing ride
	private int[] vehicleCapacities;

	// algorithm parameters
	private int maxWaitTime = 3600;// max pickup time (seconds) from first
									// pickup and the last pickup in sharing
									// requests (default 60 minutes)

	private int forbidenStraightDistance = 20000;// in meters.
	// If the straight distance between two pickups is greater than
	// forbidenStraightDistance
	// then these requests cannot be shared
	private int forbidenTimeDistance = 3600;// in seconds
	// If the distance between two pickup times is greater than
	// forbidenTimeDistance
	// then these requests cannot be shared
	private int maxStandardSharingDistance = 20000;// max distance (m) allowed
													// for sharing two requests
													// in normal time frame
	private int maxHighTrafficSharingDistance = 15000;// max distance (m)
														// allowed
														// for sharing two
														// requests in high
														// traffic jam time
	// frame

	private int algorithmMaxTime = 30;// in seconds: max computation time

	private double approximationDistanceFactor = 1.5;// real distance is about
														// 1.5 times the
														// straight distance
	private double eps = 10;// (m) epsilon distance
	private double stdSpeed = 5;// (m/s) the standard speed in urban city
	private double highTrafficSpeed = 2;// (m/s) speed in high traffic
	private double stableSpeed = 70 * 10 / 36; // (m/s) speed traveling between
												// cities
	private int deltaRequestTime = 900;// (seconds) the request
										// pickup/delivery time is about +-
										// 15 minutes

	public SharedLongTripInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SharedLongTripInput(SharedLongTripRequest[] requests,
			int minimumSharedPrice, int[] vehicleCapacities, int maxWaitTime,
			int forbidenStraightDistance, int forbidenTimeDistance,
			int maxStandardSharingDistance, int maxHighTrafficSharingDistance,
			int algorithmMaxTime, double approximationDistanceFactor,
			double eps, double stdSpeed, double highTrafficSpeed,
			double stableSpeed, int deltaRequestTime) {
		super();
		this.requests = requests;
		this.minimumSharedPrice = minimumSharedPrice;
		this.vehicleCapacities = vehicleCapacities;
		this.maxWaitTime = maxWaitTime;
		this.forbidenStraightDistance = forbidenStraightDistance;
		this.forbidenTimeDistance = forbidenTimeDistance;
		this.maxStandardSharingDistance = maxStandardSharingDistance;
		this.maxHighTrafficSharingDistance = maxHighTrafficSharingDistance;
		this.algorithmMaxTime = algorithmMaxTime;
		this.approximationDistanceFactor = approximationDistanceFactor;
		this.eps = eps;
		this.stdSpeed = stdSpeed;
		this.highTrafficSpeed = highTrafficSpeed;
		this.stableSpeed = stableSpeed;
		this.deltaRequestTime = deltaRequestTime;
	}

	public SharedLongTripRequest[] getRequests() {
		return requests;
	}

	public void setRequests(SharedLongTripRequest[] requests) {
		this.requests = requests;
	}

	public int getMinimumSharedPrice() {
		return minimumSharedPrice;
	}

	public void setMinimumSharedPrice(int minimumSharedPrice) {
		this.minimumSharedPrice = minimumSharedPrice;
	}

	public int[] getVehicleCapacities() {
		return vehicleCapacities;
	}

	public void setVehicleCapacities(int[] vehicleCapacities) {
		this.vehicleCapacities = vehicleCapacities;
	}

	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
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

	public int getAlgorithmMaxTime() {
		return algorithmMaxTime;
	}

	public void setAlgorithmMaxTime(int algorithmMaxTime) {
		this.algorithmMaxTime = algorithmMaxTime;
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

	public double getStableSpeed() {
		return stableSpeed;
	}

	public void setStableSpeed(double stableSpeed) {
		this.stableSpeed = stableSpeed;
	}

	public int getDeltaRequestTime() {
		return deltaRequestTime;
	}

	public void setDeltaRequestTime(int deltaRequestTime) {
		this.deltaRequestTime = deltaRequestTime;
	}

}
