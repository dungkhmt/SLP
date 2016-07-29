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
	String OA_OrderCode;
	int OA_Amount;
	String OA_Date;
	float OA_Price;
	public int getOA_ID() {
		return OA_ID;
	}
	public void setOA_ID(int oA_ID) {
		OA_ID = oA_ID;
	}
	public String getOA_OrderCode() {
		return OA_OrderCode;
	}
	public void setOA_OrderCode(String oA_OrderCode) {
		OA_OrderCode = oA_OrderCode;
	}
	public String getOA_Code() {
		return OA_Code;
	}
	public void setOA_Code(String oA_Code) {
		OA_Code = oA_Code;
	}
	


	public int getOA_Amount() {
		return OA_Amount;
	}
	public void setOA_Amount(int oA_Amount) {
		OA_Amount = oA_Amount;
	}
	public String getOA_Date() {
		return OA_Date;
	}
	public void setOA_Date(String oA_Date) {
		OA_Date = oA_Date;
	}
	public float getOA_Price() {
		return OA_Price;
	}
	public void setOA_Price(float oA_Price) {
		OA_Price = oA_Price;
	}

	

}
