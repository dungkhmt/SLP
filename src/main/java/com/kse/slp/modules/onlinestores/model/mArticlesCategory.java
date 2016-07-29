package com.kse.slp.modules.onlinestores.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblarticlescategory")
public class mArticlesCategory implements Serializable {
	@Id
	@GeneratedValue
	int ARCat_ID;
	String ARCat_Code;
	String ARCat_Name;
	String ARCat_Description;
	public int getARCat_ID() {
		return ARCat_ID;
	}
	public void setARCat_ID(int aRCat_ID) {
		ARCat_ID = aRCat_ID;
	}
	public String getARCat_Code() {
		return ARCat_Code;
	}
	public void setARCat_Code(String aRCat_Code) {
		ARCat_Code = aRCat_Code;
	}
	public String getARCat_Name() {
		return ARCat_Name;
	}
	public void setARCat_Name(String aRCat_Name) {
		ARCat_Name = aRCat_Name;
	}
	public String getARCat_Description() {
		return ARCat_Description;
	}
	public void setARCat_Description(String aRCat_Description) {
		ARCat_Description = aRCat_Description;
	}
	@Override
	public String toString() {
		return "mArticlesCategory [ARCat_ID=" + ARCat_ID + ", ARCat_Code="
				+ ARCat_Code + ", ARCat_Name=" + ARCat_Name
				+ ", ARCat_Description=" + ARCat_Description + "]";
	}
	
}
