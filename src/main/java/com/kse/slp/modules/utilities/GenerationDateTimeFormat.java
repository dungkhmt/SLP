package com.kse.slp.modules.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerationDateTimeFormat {
	public static String genDateTimeFormatyyyyMMddCurrently(){
		Date date= new Date();
		Date currentDate = new Date();
		SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("yyyy.MM.dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		String sCurrentDate = dateformatyyyyMMdd.format(currentDate);
		String sCurrentTime = sdf2.format(currentDate);
		String orderDate=sCurrentDate+"-"+sCurrentTime;
		return orderDate;
	}
}
