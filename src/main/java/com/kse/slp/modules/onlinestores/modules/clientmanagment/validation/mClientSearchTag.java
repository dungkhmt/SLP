package com.kse.slp.modules.onlinestores.modules.clientmanagment.validation;

public class mClientSearchTag {
	public String phone;
	public String name;
	public String adress;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public mClientSearchTag(String phone, String name, String adress) {
		super();
		this.phone = phone;
		this.name = name;
		this.adress = adress;
	}
	
	
}
