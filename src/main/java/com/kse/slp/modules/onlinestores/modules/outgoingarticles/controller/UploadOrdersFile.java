package com.kse.slp.modules.onlinestores.modules.outgoingarticles.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrdersService;


@Controller("UploadOrdersFile")
@RequestMapping(value = {"/outgoingarticles"})
public class UploadOrdersFile {

	@Autowired
	mOrdersService mOrdersService;
	
	@RequestMapping(value="/uploadFile",method=RequestMethod.GET)
	public String upLoadFile(){
		return "outgoingarticles.uploadFile";
	}
	
	@RequestMapping(value="/uploadOrdersFile", method=RequestMethod.POST)
	public @ResponseBody String readFile(MultipartHttpServletRequest request){
		System.out.println("Upload file");
		Iterator<String> itr = request.getFileNames();
		MultipartFile ordersFile = request.getFile(itr.next());
		
		if(ordersFile != null){
			try {
				
				InputStream readFile = ordersFile.getInputStream();
				XSSFWorkbook wb = new XSSFWorkbook(readFile);
				
				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;
				
				int rows = sheet.getPhysicalNumberOfRows();
				
				for(int i=1; i<rows; i++){
					row = sheet.getRow(i);
					String clientCode = ""+(int)row.getCell(1).getNumericCellValue();
					//System.out.println(name()+"readFile--O_Code["+i+"]: "+clientCode);
				
					String deliveryAddress = row.getCell(2).getStringCellValue();
					//System.out.println(name()+"readFile--Adderss["+i+"]: "+deliveryAddress);
					
					String latlng = row.getCell(3).getStringCellValue();
					int index = latlng.indexOf(",");
					
					float lat = Float.parseFloat(latlng.substring(0,index));
					//System.out.println(name()+"readFile--lat["+i+"]: "+lat);
					
					float lng = Float.parseFloat(latlng.substring(index+1,latlng.length()));
					//System.out.println(name()+"readFile--lng["+i+"]: "+lng);
					
					String timeEearly = row.getCell(4).getStringCellValue();
					int index2 = timeEearly.indexOf(" ");
					String orderDate = timeEearly.substring(0,index2);
					//System.out.println(name()+"readFile--orderDate["+i+"]: "+orderDate);
					
					String timeEarly = timeEearly.substring(index2+1,timeEearly.length());
					//System.out.println(name()+"readFile--timeEarly["+i+"]: "+timeEarly);
					
					String timeDueTo = row.getCell(5).getStringCellValue();
					int index3 = timeDueTo.indexOf(" ");
					String dueDate = timeDueTo.substring(0,index3);
					//System.out.println(name()+"readFile--dueDate["+i+"]: "+dueDate);
					
					String timeLate = timeDueTo.substring(index3+1,timeDueTo.length());
					//System.out.println(name()+"readFile--timeLate["+i+"]: "+timeLate);
					mOrdersService.saveAnOrder(clientCode, orderDate, dueDate, deliveryAddress, lat, lng, timeEarly, timeLate, 0 , null);
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return "{}";
	}
	
	public String name(){
		return "UploadOrdersFile::";
	}
}
