package com.kse.slp.modules.utilities;

public class CodeGenerationUtility {
	
	public static String genOrderCode(int id){
		String s = "" + id;
		while(s.length() < 6)
			s = "0" + s;
		return s;
	}
	
	
}
