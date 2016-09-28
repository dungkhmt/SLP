package com.kse.slp.modules.dichung.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.api.dichung.model.SharedTaxiInput;
import com.kse.slp.modules.api.dichung.model.SharedTaxiRequest;
import com.kse.slp.modules.api.dichung.model.SharedTaxiRoute;
import com.kse.slp.modules.api.dichung.model.SharedTaxiRouteElement;
import com.kse.slp.modules.api.dichung.model.SharedTaxiSolution;
import com.kse.slp.modules.containerdelivery.model.RequestBatch;
import com.kse.slp.modules.containerdelivery.model.mPickupDeliveryOrders;
import com.kse.slp.modules.containerdelivery.service.mRequestBatchService;
import com.kse.slp.modules.dichung.dao.RouteDetailDiChungDAO;
import com.kse.slp.modules.dichung.model.RequestDiChung;
import com.kse.slp.modules.dichung.model.RouteDetailDiChung;
import com.kse.slp.modules.dichung.model.mFormAddFileExcel;
import com.kse.slp.modules.dichung.service.RequestDiChungService;
import com.kse.slp.modules.dichung.service.RouteDetailDiChungService;
import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRoutesService;
import com.kse.slp.modules.usermanagement.model.User;
import com.kse.slp.modules.utilities.GenerationDateTimeFormat;



@Controller("DiChungControler")
@RequestMapping(value = {"/dichung"})
public class DiChungControler extends BaseWeb {
	@Autowired
	mRequestBatchService requestBatchService;
	@Autowired
	RequestDiChungService requestDiChungService;
	@Autowired
	mRoutesService routeService;
	@Autowired
	RouteDetailDiChungService routeDetailDiChungService;
	private static final Logger log = Logger.getLogger(DiChungControler.class);
	@RequestMapping(value="",method=RequestMethod.GET)
	public String listPickupDelivery(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "dichung.home";
	}
	@RequestMapping(value="/add-dichungrequests-by-xls",method=RequestMethod.GET)
	public String addDiChungRequestsXls(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		List<RequestBatch> listBatch= requestBatchService.getList();
		model.put("listBatch", listBatch);
		model.put("formAdd", new mFormAddFileExcel());
		return "dichung.adddichungrequestsbyxls";
	}
	@RequestMapping(value="/upload-file-request-dichung", method=RequestMethod.POST)
	public @ResponseBody String uploadFile(@ModelAttribute("formAdd") mFormAddFileExcel request){
		System.out.println(name());
		//Iterator<String> itr = request.getFileNames();
		MultipartFile file = request.getOrdersFile();
		System.out.println(name()+"::uploadFile--"+file.getOriginalFilename() + " uploaded");
		String batchCode= request.getBatchCode();
		System.out.println(name()+ batchCode);
		requestDiChungService.deleteRequestDiChungInBatch(batchCode);
		if(file != null){
			readFileRequestDiChung(file,batchCode);
		}
		return "{}";
	}
	public void readFileRequestDiChung(MultipartFile file,String batchCode){
		try {
			InputStream readFile = file.getInputStream();
			XSSFWorkbook wb = new XSSFWorkbook(readFile);
			XSSFSheet sheet = wb.getSheetAt(1);
			XSSFSheet sheet2 = wb.getSheetAt(2);
			XSSFRow row;
			//get batch code
			//row = sheet2.getRow(0);
			//batchCode=row.getCell(0).getStringCellValue();
			int rows = sheet.getPhysicalNumberOfRows();
			for(int i=1;i<rows;i++){
				System.out.println(name()+"::readFile"+"--row "+i);
				row = sheet.getRow(i);
				String 	rEQDC_TicketCode=""+row.getCell(0).getRawValue();
				String tmp=row.getCell(1).getStringCellValue();
				tmp=GenerationDateTimeFormat.convertDateTimeFormat(tmp, "HH:mm dd/mm/yy", "yyyy-MM-dd HH:mm:ss");
				String  rEQDC_DepartTime=tmp;
				String  rEQDC_ChunkName=row.getCell(2).getStringCellValue();
				String  rEQDC_PickupAddress=row.getCell(3).getStringCellValue();
				String  rEQDC_DeliveryAddress=row.getCell(4).getStringCellValue();
				int rEQDC_NumberPassengers=(int) row.getCell(5).getNumericCellValue();
						
				System.out.println(name()+" "+rEQDC_TicketCode+" "+rEQDC_DepartTime+" "+rEQDC_ChunkName+" "+rEQDC_PickupAddress+" "+rEQDC_DeliveryAddress+" "+rEQDC_NumberPassengers);
				requestDiChungService.saveARequest(rEQDC_TicketCode, rEQDC_DepartTime, rEQDC_ChunkName, rEQDC_PickupAddress, rEQDC_DeliveryAddress, rEQDC_NumberPassengers,batchCode);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@RequestMapping(value="/create-route-auto", method=RequestMethod.GET)
	public String createRouteAuto(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		List<RequestBatch> listBatch= requestBatchService.getList(u.getCustomerCode());
		model.put("listBatch", listBatch);
		return "dichung.createroute";
	}
	@ResponseBody @RequestMapping(value="/get-route-auto", method=RequestMethod.POST)
	public boolean getRouteAuto(HttpSession session,@RequestBody String batchCode){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		SharedTaxiInput stinpu= new SharedTaxiInput();
		int tmp[]= {4,6};
		stinpu.setVehicleCapacities(tmp);
		stinpu.setMaxWaitTime(900);
		stinpu.setForbidenStraightDistance(10000);
		stinpu.setForbidenTimeDistance(3600);
		stinpu.setMaxTime(5);
		List<RequestDiChung> list = requestDiChungService.getListInBatch(batchCode); 
		SharedTaxiRequest lstr[]= new SharedTaxiRequest[list.size()];
		for(int i=0;i<list.size();i++){
			RequestDiChung r= list.get(i);
			SharedTaxiRequest str=new SharedTaxiRequest();
			str.setChungName(r.getREQDC_ChunkName());
			str.setDeliveryAddress(r.getREQDC_DeliveryAddress());
			str.setDepartTime(r.getREQDC_DepartTime());
			str.setNumberPassengers(r.getREQDC_NumberPassengers());
			str.setPickupAddress(r.getREQDC_PickupAddress());
			str.setTicketCode(r.getREQDC_TicketCode());
			lstr[i]=str;
		}
		stinpu.setRequests(lstr);
		Gson gson = new Gson();
		String json=gson.toJson(stinpu);
		System.out.println(name()+json);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
		    //HttpPost request = new HttpPost("http://103.18.4.32:8080/ezRoutingAPI/shared-taxi-plan-dichung");
			HttpPost request = new HttpPost("http://localhost:8080/ezRoutingAPI/shared-taxi-plan-dichung");
		    StringEntity params = new StringEntity(json, ContentType.APPLICATION_JSON);
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    System.out.println(request.getEntity());
		    HttpResponse response = httpClient.execute(request);
		    HttpEntity  res= response.getEntity();
		    String responseString = EntityUtils.toString(res, "UTF-8");
		    System.out.println(name() + "::getRouteAuto, responseString = " + responseString);
		    SharedTaxiSolution sts= gson.fromJson(responseString, SharedTaxiSolution.class);
		    System.out.println(sts);
		    SharedTaxiRoute str[]= sts.getRoutes();
		    System.out.println(name() + "::getRouteAuto, number of routes = " + str.length);
		    String route_Code = "dichung" + GenerationDateTimeFormat.genDateTimeFormatyyyyMMddCurrently();
		    routeService.saveARoute(route_Code, "dichung", "-",Constants.ROUTE_STATUS_CONFIRMED , batchCode);
		    System.out.println(name() + "::getRouteAuto, number of routes = " + str.length);
		    for(int i=0;i<str.length;i++){
			    SharedTaxiRouteElement stre[]= str[i].getTicketCodes();
			    System.out.println(name() + "::getRouteAuto, route[" + i + "].length = " + stre.length);
			    for(int j=0;j<stre.length;j++){
			    	routeDetailDiChungService.saveARouteDetailDiChung(route_Code, stre[j].getTicketCode(), j, i,stre[j].getAddress(),stre[j].getDistanceToNext(),stre[i].getTravelTimeToNext());
			    }
			    System.out.println(name() + "::getRouteAuto, route[" + i + "].length = " + stre.length + ", i = " + i + ", str.length = " + str.length);
		    }
		// handle response here...
		} catch (Exception ex) {
		    // handle exception here
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return true;
	}
	@RequestMapping(value="/view-route", method=RequestMethod.GET)
	public String viewRoute(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		List<RequestBatch> listBatch= requestBatchService.getList(u.getCustomerCode());
		model.put("listBatch", listBatch);
		return "dichung.viewroute";
	}
	@ResponseBody @RequestMapping(value="/load-route-in-batch", method=RequestMethod.POST)
	public List<RouteDetailDiChung> loadRouteInBatch(HttpSession session,@RequestBody String batchCode){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		System.out.println(batchCode);
		List<RouteDetailDiChung> res= new ArrayList<RouteDetailDiChung>();
		List<mRoutes> lr= routeService.getListByBatchCode(batchCode);
		System.out.println(name()+lr);
		for(int i=0;i< lr.size();i++){
			res.addAll(routeDetailDiChungService.loadRouteContainerDetailByRouteCode(lr.get(i).getRoute_Code()));
		}
		
		return res;
	}
	String name(){
		return "DiChungControler:: ";
	}
}
