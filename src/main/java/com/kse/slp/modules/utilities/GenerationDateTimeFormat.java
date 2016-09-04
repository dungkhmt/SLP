package com.kse.slp.modules.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerationDateTimeFormat {
	public static String genDateTimeFormatyyyyMMddCurrently(){
		Date date= new Date();
		Date currentDate = new Date();
		SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
		String sCurrentDate = dateformatyyyyMMdd.format(currentDate);
		String sCurrentTime = sdf2.format(currentDate);
		String orderDate=sCurrentDate+sCurrentTime;
		return orderDate;
	}
	
	public static String convertDateTimeFormat(String date,String inFormat,String outFormat){
		SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
		try {
			Date cdate = format.parse(date);
			SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat(outFormat);
			String sdateTimeout = dateformatyyyyMMdd.format(cdate);
			
			return sdateTimeout;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
