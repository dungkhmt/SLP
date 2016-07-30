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

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tblrole")
public class Role implements GrantedAuthority,Serializable{
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@ManyToMany(mappedBy="roles", fetch = FetchType.EAGER)
	private Set<User> users = new HashSet<User>();
	
	@Override
	public String getAuthority() {
		return this.name;
	}
	
	public Role(){		
	}
	
	public Role(String name){
		this.name = name;		
	}
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	
}
