package com.kse.slp.modules.dichung.model;

public class SharedLongTripRequest {
	private String ticketCode;
	private String pickupAddress;
	private String pickupLatLng;
	private String deliveryAddress;
	private String deliveryLatLng;
	private String departDateTime;
	private int nbPassengers;
	private boolean sharing;
	private boolean oneway;
	private int price;
	
	private String Itinerary;// chunkName 
	
	public SharedLongTripRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public SharedLongTripRequest(String ticketCode, String pickupAddress,
			String pickupLatLng, String deliveryAddress, String deliveryLatLng,
			String departDateTime, int nbPassengers, boolean sharing,
			boolean oneway, int price, String itinerary) {
		super();
		this.ticketCode = ticketCode;
		this.pickupAddress = pickupAddress;
		this.pickupLatLng = pickupLatLng;
		this.deliveryAddress = deliveryAddress;
		this.deliveryLatLng = deliveryLatLng;
		this.departDateTime = departDateTime;
		this.nbPassengers = nbPassengers;
		this.sharing = sharing;
		this.oneway = oneway;
		this.price = price;
		Itinerary = itinerary;
	}

	public void print(){
		System.out.println("Information of a request ");
		System.out.println("Ticket code: " + ticketCode);
		System.out.println("Itinerary: ");
		System.out.println("Pickup address: " + pickupAddress);
		System.out.println("Delivery address: " + deliveryAddress);
		System.out.println("Departure time: " + departDateTime);
		System.out.println("Number of passengers: " + nbPassengers);
		System.out.println("Price: " + price);
	}

	public String getItinerary() {
		return Itinerary;
	}
	public void setItinerary(String itinerary) {
		Itinerary = itinerary;
	}
	public String getTicketCode() {
		return ticketCode;
	}
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}
	public String getPickupAddress() {
		return pickupAddress;
	}
	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}
	public String getPickupLatLng() {
		return pickupLatLng;
	}
	public void setPickupLatLng(String pickupLatLng) {
		this.pickupLatLng = pickupLatLng;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getDeliveryLatLng() {
		return deliveryLatLng;
	}
	public void setDeliveryLatLng(String deliveryLatLng) {
		this.deliveryLatLng = deliveryLatLng;
	}
	public String getDepartDateTime() {
		return departDateTime;
	}
	public void setDepartDateTime(String departDateTime) {
		this.departDateTime = departDateTime;
	}
	public int getNbPassengers() {
		return nbPassengers;
	}
	public void setNbPassengers(int nbPassengers) {
		this.nbPassengers = nbPassengers;
	}
	public boolean isSharing() {
		return sharing;
	}
	public void setSharing(boolean sharing) {
		this.sharing = sharing;
	}
	public boolean isOneway() {
		return oneway;
	}
	public void setOneway(boolean oneway) {
		this.oneway = oneway;
	}
	
	
	
}
