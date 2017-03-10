package com.kse.slp.modules.usermanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="tbluserfunctions")
public class UserFunctions {
	@Id
	@GeneratedValue
	private int USERFUNC_Id;
	private String USERFUNC_Code;
	private String USERFUNC_UserCode;
	private String USERFUNC_FuncCode;
	@OneToOne
	@JoinColumn(name = "USERFUNC_FuncCode", referencedColumnName = "FUNC_Code",insertable = false, updatable = false)
	private Function function;
	
	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}
	public int getUSERFUNC_Id() {
		return USERFUNC_Id;
	}
	public void setUSERFUNC_Id(int uSERFUNC_Id) {
		USERFUNC_Id = uSERFUNC_Id;
	}
	public String getUSERFUNC_Code() {
		return USERFUNC_Code;
	}
	public void setUSERFUNC_Code(String uSERFUNC_Code) {
		USERFUNC_Code = uSERFUNC_Code;
	}
	public String getUSERFUNC_UserCode() {
		return USERFUNC_UserCode;
	}
	public void setUSERFUNC_UserCode(String uSERFUNC_UserCode) {
		USERFUNC_UserCode = uSERFUNC_UserCode;
	}
	public String getUSERFUNC_FuncCode() {
		return USERFUNC_FuncCode;
	}
	public void setUSERFUNC_FuncCode(String uSERFUNC_FuncCode) {
		USERFUNC_FuncCode = uSERFUNC_FuncCode;
	}
	@Override
	public String toString() {
		return "UserFunctions [USERFUNC_Id=" + USERFUNC_Id + ", USERFUNC_Code="
				+ USERFUNC_Code + ", USERFUNC_UserCode=" + USERFUNC_UserCode
				+ ", USERFUNC_FuncCode=" + USERFUNC_FuncCode + ", function="
				+ function + "]";
	}
	
}
