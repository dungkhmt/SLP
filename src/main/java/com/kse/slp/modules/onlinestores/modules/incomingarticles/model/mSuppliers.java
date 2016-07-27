package com.kse.slp.modules.onlinestores.modules.incomingarticles.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblsuppliers")
public class mSuppliers {
	
	@Id
	@GeneratedValue
	private int Supplier_ID;
	private String Supplier_Code;
	private String Supplier_Address;
	private String Supplier_Description;
	private String Supplier_ARCats_Code;
	private String Supplier_Name;
	public int getSupplier_ID() {
		return Supplier_ID;
	}
	public void setSupplier_ID(int supplier_ID) {
		Supplier_ID = supplier_ID;
	}
	public String getSupplier_Code() {
		return Supplier_Code;
	}
	public void setSupplier_Code(String supplier_Code) {
		Supplier_Code = supplier_Code;
	}
	public String getSupplier_Address() {
		return Supplier_Address;
	}
	public void setSupplier_Address(String supplier_Address) {
		Supplier_Address = supplier_Address;
	}
	public String getSupplier_Description() {
		return Supplier_Description;
	}
	public void setSupplier_Description(String supplier_Description) {
		Supplier_Description = supplier_Description;
	}
	public String getSupplier_ARCats_Code() {
		return Supplier_ARCats_Code;
	}
	public void setSupplier_ARCats_Code(String supplier_ARCats_Code) {
		Supplier_ARCats_Code = supplier_ARCats_Code;
	}
	public String getSupplier_Name() {
		return Supplier_Name;
	}
	public void setSupplier_Name(String supplier_Name) {
		Supplier_Name = supplier_Name;
	}
	
	
	
}
