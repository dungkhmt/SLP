package com.kse.slp.modules.usermanagement.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbluser")
public class User implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;
	private String username;
	private String password;
	private String CustomerCode;
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)  
    @JoinTable(name="tbluserrole", 
    		   joinColumns=@JoinColumn(name="user_id", referencedColumnName = "id"), 
    		   inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName = "id")) 
	private Set<Role> roles = new HashSet<Role>();
		
	public User(){		
	}
	
	public User(String username, String password, HashSet<Role> roles){
		this.username = username;
		this.password = password;
		this.setAuthorities(roles);
	}	
		
	public String getCustomerCode() {
		return CustomerCode;
	}

	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Role> getAuthorities() {
		return roles;
	}

	public void setAuthorities(Set<Role> roles) {
		this.roles = roles;
	}	
	
}
