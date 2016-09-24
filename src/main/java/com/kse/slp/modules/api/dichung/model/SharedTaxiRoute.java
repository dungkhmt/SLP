package com.kse.slp.modules.api.dichung.model;

public class SharedTaxiRoute {
	private SharedTaxiRouteElement[] ticketCodes;
	private int nbPeople;
	private String arrTimeDestination;
	
	public SharedTaxiRoute() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public SharedTaxiRoute(SharedTaxiRouteElement[] ticketCodes, int nbPeople,
			String arrTimeDestination) {
		super();
		this.ticketCodes = ticketCodes;
		this.nbPeople = nbPeople;
		this.arrTimeDestination = arrTimeDestination;
	}



	public int getNbPeople() {
		return nbPeople;
	}



	public void setNbPeople(int nbPeople) {
		this.nbPeople = nbPeople;
	}



	public String getArrTimeDestination() {
		return arrTimeDestination;
	}



	public void setArrTimeDestination(String arrTimeDestination) {
		this.arrTimeDestination = arrTimeDestination;
	}



	public SharedTaxiRouteElement[] getTicketCodes() {
		return ticketCodes;
	}

	public void setTicketCodes(SharedTaxiRouteElement[] ticketCodes) {
		this.ticketCodes = ticketCodes;
	}

	
}
