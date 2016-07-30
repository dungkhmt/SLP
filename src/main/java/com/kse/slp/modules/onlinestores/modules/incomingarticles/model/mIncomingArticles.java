package com.kse.slp.modules.onlinestores.modules.incomingarticles.model;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblincomingarticles")
public class mIncomingArticles implements Serializable {
	
	@Id
	@GeneratedValue
	private int IA_ID;
	private String IA_ArticleCode;
	private int IA_Amount;
	private float IA_Price;
	private String IA_Supplier_Code;
	private String IA_Date;
	
	public int getIA_ID() {
		return IA_ID;
	}
	public void setIA_ID(int iA_ID) {
		IA_ID = iA_ID;
	}
	public String getIA_ArticleCode() {
		return IA_ArticleCode;
	}
	public void setIA_ArticleCode(String iA_ArticleCode) {
		IA_ArticleCode = iA_ArticleCode;
	}
	public int getIA_Amount() {
		return IA_Amount;
	}
	public void setIA_Amount(int iA_Amount) {
		IA_Amount = iA_Amount;
	}
	public float getIA_Price() {
		return IA_Price;
	}
	public void setIA_Price(float iA_Price) {
		IA_Price = iA_Price;
	}
	public String getIA_Date() {
		return IA_Date;
	}
	public void setIA_Date(String iA_Date) {
		IA_Date = iA_Date;
	}
	public String getIA_Supplier_Code() {
		return IA_Supplier_Code;
	}
	public void setIA_Supplier_Code(String iA_Supplier_Code) {
		IA_Supplier_Code = iA_Supplier_Code;
	}
	@Override
	public String toString() {
		return "mIncomingArticles [IA_ID=" + IA_ID + ", IA_ArticleCode=" + IA_ArticleCode + ", IA_Amount=" + IA_Amount
				+ ", IA_Price=" + IA_Price + ", IA_Supplier_Code=" + IA_Supplier_Code + ", IA_Date=" + IA_Date + "]";
	}
	
	
}
