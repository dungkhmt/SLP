package com.kse.slp.modules.usermanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblmember")
public class MsoMember implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String role;
	
	public MsoMember(){
		
	}
	
	public MsoMember(String name, String role){
		this.name = name;
		this.role = role;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
