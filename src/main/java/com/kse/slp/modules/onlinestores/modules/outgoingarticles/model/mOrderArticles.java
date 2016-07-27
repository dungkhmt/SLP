package com.kse.slp.modules.onlinestores.modules.outgoingarticles.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tblorderarticles")
public class mOrderArticles implements Serializable {
	@Id
	@GeneratedValue
	int OA_ID;
	String OA_Code;
	String OA_OderCode;
	String OA_Amount;
	String OA_Date;
	String OA_Price;
	public int getOA_ID() {
		return OA_ID;
	}
	public void setOA_ID(int oA_ID) {
		OA_ID = oA_ID;
	}
	public String getOA_Code() {
		return OA_Code;
	}
	public void setOA_Code(String oA_Code) {
		OA_Code = oA_Code;
	}
	public String getOA_OderCode() {
		return OA_OderCode;
	}
	public void setOA_OderCode(String oA_OderCode) {
		OA_OderCode = oA_OderCode;
	}
	public String getOA_Amount() {
		return OA_Amount;
	}
	public void setOA_Amount(String oA_Amount) {
		OA_Amount = oA_Amount;
	}
	public String getOA_Date() {
		return OA_Date;
	}
	public void setOA_Date(String oA_Date) {
		OA_Date = oA_Date;
	}
	public String getOA_Price() {
		return OA_Price;
	}
	public void setOA_Price(String oA_Price) {
		OA_Price = oA_Price;
	}
	

}
