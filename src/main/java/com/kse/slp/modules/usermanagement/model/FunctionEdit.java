package com.kse.slp.modules.usermanagement.model;

public class FunctionEdit {
	private int FUNC_Id;
	private String FUNC_Code;
	private String FUNC_Name;
	private int FUNC_ParentId;
	
	private int FUNC_Selected;
	private int FUNC_HasChildren;
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
	
	public int getFUNC_ParentId() {
		return FUNC_ParentId;
	}
	public void setFUNC_ParentId(int fUNC_ParentId) {
		FUNC_ParentId = fUNC_ParentId;
	}
	public int getFUNC_Selected() {
		return FUNC_Selected;
	}
	public void setFUNC_Selected(int fUNC_Selected) {
		FUNC_Selected = fUNC_Selected;
	}
	public int getFUNC_HasChildren() {
		return FUNC_HasChildren;
	}
	public void setFUNC_HasChildren(int fUNC_HasChildren) {
		FUNC_HasChildren = fUNC_HasChildren;
	}
	public FunctionEdit(int fUNC_Id, String fUNC_Code, String fUNC_Name, int fUNC_ParentId, int fUNC_Selected,
			int fUNC_HasChildren) {
		super();
		FUNC_Id = fUNC_Id;
		FUNC_Code = fUNC_Code;
		FUNC_Name = fUNC_Name;
		FUNC_ParentId = fUNC_ParentId;
		FUNC_Selected = fUNC_Selected;
		FUNC_HasChildren = fUNC_HasChildren;
	}
	
}
