package com.kse.slp.modules.usermanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblfunctions")
public class Function implements Serializable {
	
	@Id
	@GeneratedValue
	private int FUNC_Id;
	private String FUNC_Code;
	private String FUNC_Name;
	private String FUNC_Url;
	private int FUNC_ParentId;
	private String FUNC_TitleClass;
	private String FUNC_SelectedClass;
	private int FUNC_HasChildren;
	private String FUNC_Icon;
	
	public String getFUNC_Icon() {
		return FUNC_Icon;
	}
	public void setFUNC_Icon(String fUNC_Icon) {
		FUNC_Icon = fUNC_Icon;
	}
	public int getFUNC_Id() {
		return FUNC_Id;
	}
	public void setFUNC_Id(int fUNC_Id) {
		FUNC_Id = fUNC_Id;
	}
	public String getFUNC_Code() {
		return FUNC_Code;
	}
	public void setFUNC_Code(String fUNC_Code) {
		FUNC_Code = fUNC_Code;
	}
	public String getFUNC_Name() {
		return FUNC_Name;
	}
	public void setFUNC_Name(String fUNC_Name) {
		FUNC_Name = fUNC_Name;
	}
	public String getFUNC_Url() {
		return FUNC_Url;
	}
	public void setFUNC_Url(String fUNC_Url) {
		FUNC_Url = fUNC_Url;
	}
	public int getFUNC_ParentId() {
		return FUNC_ParentId;
	}
	public void setFUNC_ParentId(int fUNC_ParentId) {
		FUNC_ParentId = fUNC_ParentId;
	}
	public String getFUNC_TitleClass() {
		return FUNC_TitleClass;
	}
	public void setFUNC_TitleClass(String fUNC_TitleClass) {
		FUNC_TitleClass = fUNC_TitleClass;
	}
	public String getFUNC_SelectedClass() {
		return FUNC_SelectedClass;
	}
	public void setFUNC_SelectedClass(String fUNC_SelectedClass) {
		FUNC_SelectedClass = fUNC_SelectedClass;
	}
	public int getFUNC_HasChildren() {
		return FUNC_HasChildren;
	}
	public void setFUNC_HasChildren(int fUNC_HasChildren) {
		FUNC_HasChildren = fUNC_HasChildren;
	}
	@Override
	public String toString() {
		return "Function [FUNC_Id=" + FUNC_Id + ", FUNC_Code=" + FUNC_Code
				+ ", FUNC_Name=" + FUNC_Name + ", FUNC_Url=" + FUNC_Url
				+ ", FUNC_ParentId=" + FUNC_ParentId + ", FUNC_TitleClass="
				+ FUNC_TitleClass + ", FUNC_SelectedClass="
				+ FUNC_SelectedClass + ", FUNC_HasChildren=" + FUNC_HasChildren
				+ ", FUNC_Icon=" + FUNC_Icon + "]";
	}
	
}
