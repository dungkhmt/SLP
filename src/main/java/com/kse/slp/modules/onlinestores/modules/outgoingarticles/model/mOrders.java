package com.kse.slp.modules.onlinestores.modules.outgoingarticles.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tblorders")
public class mOrders implements Serializable {
	@Id
	@GeneratedValue
	int O_ID;
	String O_Code;
	String O_ClientCode;
	String O_OrderDate;
	String O_DueDate;
	public int getO_ID() {
		return O_ID;
	}
	public void setO_ID(int o_ID) {
		O_ID = o_ID;
	}
	public String getO_Code() {
		return O_Code;
	}
	public void setO_Code(String o_Code) {
		O_Code = o_Code;
	}
	public String getO_ClientCode() {
		return O_ClientCode;
	}
	public void setO_ClientCode(String o_ClientCode) {
		O_ClientCode = o_ClientCode;
	}
	public String getO_OrderDate() {
		return O_OrderDate;
	}
	public void setO_OrderDate(String o_OrderDate) {
		O_OrderDate = o_OrderDate;
	}
	public String getO_DueDate() {
		return O_DueDate;
	}
	public void setO_DueDate(String o_DueDate) {
		O_DueDate = o_DueDate;
	}
	
}
