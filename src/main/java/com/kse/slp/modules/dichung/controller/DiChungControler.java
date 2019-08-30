package com.kse.slp.modules.dichung.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
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
import org.apache.xmlbeans.impl.common.IOUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import com.kse.slp.modules.containerdelivery.model.RequestBatchDiChung;
import com.kse.slp.modules.containerdelivery.model.mPickupDeliveryOrders;
import com.kse.slp.modules.containerdelivery.service.mRequestBatchDiChungService;
import com.kse.slp.modules.dichung.dao.RouteDetailDiChungDAO;
import com.kse.slp.modules.dichung.model.FormInterCityRequest;
import com.kse.slp.modules.dichung.model.RequestDiChung;
import com.kse.slp.modules.dichung.model.RouteDetailDiChung;
import com.kse.slp.modules.dichung.model.FormAddFileExcel;
import com.kse.slp.modules.dichung.model.RouteDiChungJson;
import com.kse.slp.modules.dichung.model.SharedLongTripSolution;
import com.kse.slp.modules.dichung.service.RequestDiChungService;
import com.kse.slp.modules.dichung.service.RouteDetailDiChungService;
import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRoutesService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mShippersService;
import com.kse.slp.modules.usermanagement.model.StaffCustomer;
import com.kse.slp.modules.usermanagement.model.User;
import com.kse.slp.modules.usermanagement.service.StaffCustomerServiceImpl;
import com.kse.slp.modules.utilities.GenerationDateTimeFormat;



@Controller("DiChungControler")
@RequestMapping(value = {"/dichung"})
public class DiChungControler extends BaseWeb {
	@Autowired
	mRequestBatchDiChungService requestBatchService;
	@Autowired
	RequestDiChungService requestDiChungService;
	@Autowired
	mRoutesService routeService;
	@Autowired
	RouteDetailDiChungService routeDetailDiChungService;
	@Autowired
	mShippersService shipperService;
	@Autowired
	StaffCustomerServiceImpl staffCustomerService;
	private static final Logger log = Logger.getLogger(DiChungControler.class);
	@RequestMapping(value="",method=RequestMethod.GET)
	public String listPickupDelivery(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "dichung.home";
	}

	@RequestMapping(value = "/upload-dichung-longtrip-request", method = RequestMethod.GET)
	public String uploadLongTripRequests(ModelMap model, HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		model.put("formLongTripRequest", new FormInterCityRequest());
		return "dichung.uploadLongTripRequest";
		
	}
	
	@RequestMapping(value="/solve-long-trip-requests", method=RequestMethod.POST)
	public String solveLongTripRequests(ModelMap model, @ModelAttribute("formLongTripRequest") FormInterCityRequest requests){
		System.out.println(name() + "::solveLongTripRequest");
		MultipartFile mfile = requests.getLongTripRequests();
		try{
			InputStream file = mfile.getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(file,writer,"UTF-8");
			String json = writer.toString();
			System.out.println(json);
			Gson gson = new Gson();
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			try {
			    //HttpPost request = new HttpPost("http://103.18.4.32:8080/ezRoutingAPI/shared-taxi-plan-dichung");
				//HttpPost request = new HttpPost("http://192.168.76.15:8080/ezRoutingAPI/shared-taxi-plan-dichung");
				HttpPost request = new HttpPost("http://172.16.20.67:9898/ezRoutingAPI/shared-long-trip-plan-dichung");
			    StringEntity params = new StringEntity(json, ContentType.APPLICATION_JSON);
			    request.addHeader("content-type", "application/json");
			    request.setEntity(params);
			    System.out.println(request.getEntity());
			    HttpResponse response = httpClient.execute(request);
			    HttpEntity  res= response.getEntity();
			    String responseString = EntityUtils.toString(res, "UTF-8");
			    System.out.println(name() + "::solveLongTripRequests, responseString = " + responseString);
			    SharedLongTripSolution sts= gson.fromJson(responseString, SharedLongTripSolution.class);
			    
			    model.put("sharedLongTripSolution", sts);
			    
			    System.out.println(sts);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			file.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "dichung.viewLongTripSolution";
	}
	@RequestMapping(value="/add-dichungrequests-by-xls",method=RequestMethod.GET)
	public String addDiChungRequestsXls(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		StaffCustomer sc = staffCustomerService.getCusCodeByUserName(u.getUsername());
		List<RequestBatchDiChung> listBatch= requestBatchService.getList(sc.getSTFCUS_CustomerCode());
		
		model.put("listBatch", listBatch);
		model.put("formAdd", new FormAddFileExcel());
		return "dichung.adddichungrequestsbyxls";
	}
	@RequestMapping(value="/upload-file-request-dichung", method=RequestMethod.POST)
	public String uploadFile(@ModelAttribute("formAdd") FormAddFileExcel request){
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
		return "redirect:/dichung";
	}
	public void readFileRequestDiChung(MultipartFile file,String batchCode){
		try {
			InputStream readFile = file.getInputStream();
			XSSFWorkbook wb = new XSSFWorkbook(readFile);
			XSSFSheet sheet = wb.getSheetAt(1);
			
			XSSFRow row;
			//get batch code
			//row = sheet2.getRow(0);
			//batchCode=row.getCell(0).getStringCellValue();
			int rows = sheet.getPhysicalNumberOfRows();
			for(int i=1;i<rows;i++){
				System.out.println(name()+"::readFile"+"--row "+i);
				row = sheet.getRow(i);
				String 	rEQDC_TicketCode=""+row.getCell(0).getRawValue();
				
				// read and standardize the date-time
				String tmp=row.getCell(1).getStringCellValue();
				System.out.println(name() + "::readFile, raw datetime = " + tmp);
				//tmp=GenerationDateTimeFormat.convertDateTimeFormat(tmp, "HH:mm dd/mm/yy", "yyyy-MM-dd HH:mm:ss");
				String[] s = tmp.split(" ");
				String[] s1 = s[0].split(":");
				String hour = s1[0];
				String minute = s1[1];
				s1 = s[1].split("/");
				
				String dd = s1[0];
				String month = s1[1];
				String year = s1[2];
				tmp = year + "-" + month + "-" + dd + " " + hour + ":" + minute + ":" + "00";
				System.out.println(name() + "::readFile, standard datetime = " + tmp);
				
				String  rEQDC_DepartTime=tmp;
				
				String  rEQDC_ChunkName=row.getCell(2).getStringCellValue();
				String  rEQDC_PickupAddress=row.getCell(3).getStringCellValue();
				String  rEQDC_DeliveryAddress=row.getCell(4).getStringCellValue();
				String rEQDC_PickupPos=row.getCell(5).getStringCellValue();
				String rEQDC_DeliveryPos=row.getCell(6).getStringCellValue();
				int rEQDC_NumberPassengers=(int) row.getCell(7).getNumericCellValue();
						
				System.out.println(name()+" "+rEQDC_TicketCode+" "+rEQDC_DepartTime+" "+rEQDC_ChunkName+" "+rEQDC_PickupAddress+" "+rEQDC_DeliveryAddress+" "+rEQDC_NumberPassengers);
				requestDiChungService.saveARequest(rEQDC_TicketCode, rEQDC_DepartTime, rEQDC_ChunkName, rEQDC_PickupAddress, rEQDC_DeliveryAddress,rEQDC_PickupPos,rEQDC_DeliveryPos, rEQDC_NumberPassengers,batchCode);
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
		System.out.println(session.getAttribute("CustomerCode"));
		StaffCustomer sc = staffCustomerService.getCusCodeByUserName(u.getUsername());
		List<RequestBatchDiChung> listBatch= requestBatchService.getList(sc.getSTFCUS_CustomerCode());
		model.put("listBatch", listBatch);
		return "dichung.createroute";
	}
	@ResponseBody @RequestMapping(value="/get-route-auto", method=RequestMethod.POST)
	public boolean getRouteAuto(HttpSession session,@RequestBody String batchCode){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		SharedTaxiInput stinpu= new SharedTaxiInput();
		int tmp[]= {4,6};
		stinpu.setAirportAddress("Noi Bai International Airport, Phú Cường, Hanoi, Vietnam");
		stinpu.setAirportPos("21.213018,105.802752");
		stinpu.setVehicleCapacities(tmp);
		stinpu.setMaxWaitTime(900);
		stinpu.setForbidenStraightDistance(10000);
		stinpu.setForbidenTimeDistance(3600);
		stinpu.setMaxTime(5);
		stinpu.setMaxStandardSharingDistance(5000);
		stinpu.setMaxHighTrafficSharingDistance(3000);
		stinpu.setMaxWaitTimeAirport(1800);
		stinpu.setMinWaitTimeAirport(900);
		stinpu.setApproximationDistanceFactor(1.5);
		stinpu.setEps(10);// meter
		stinpu.setStdSpeed(5);// 5m/s
		stinpu.setHighTrafficSpeed(2); // 2m/s
		stinpu.setSpeedToAirport(19); // 19m/s ~ 70 km/h
		stinpu.setDeltaRequestTime(900);// earlier or later pickup datetime within 15 minute

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
			str.setPickupPos(r.getREQDC_PickupPos());
			str.setDeliveryPos(r.getREQDC_DeliveryPos());
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
			//HttpPost request = new HttpPost("http://192.168.76.15:8080/ezRoutingAPI/shared-taxi-plan-dichung");
			HttpPost request = new HttpPost("http://172.16.20.67:9898/ezRoutingAPI/shared-taxi-plan-dichung-airport");
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
		    
		   // routeService.saveARoute(route_Code, "dichung", "-",Constants.ROUTE_STATUS_CONFIRMED , batchCode);
		    System.out.println(name() + "::getRouteAuto, number of routes = " + str.length);
		    
		    List <mRoutes> lr=routeService.getListByBatchCode(batchCode);
		    
		    for(int i=0;i<lr.size();i++){
		    	routeDetailDiChungService.deleteRoutesbyRouteCode(lr.get(i).getRoute_Code());
		    	routeService.removeRoutesByRouteCode(lr.get(i).getRoute_Code());
		    }
		    
		    
		    for(int i=0;i<str.length;i++){
			    SharedTaxiRouteElement stre[]= str[i].getTicketCodes();
			    String route_Code = "dichung" + GenerationDateTimeFormat.genDateTimeFormatyyyyMMddCurrently()+"T"+i;// time format +stt in response
			    routeService.saveARoute(route_Code, "dichung", "-",Constants.ROUTE_STATUS_CONFIRMED , batchCode);
			    System.out.println(name() + i+ "::getRouteAuto, route[" + i + "].length = " + stre.length);
			    for(int j=0;j<stre.length;j++){
			    	//routeDetailDiChungService.saveARouteDetailDiChung(route_Code, stre[j].getTicketCode(), j, i,stre[j].getAddress(),stre[j].getDistanceToNext(),stre[j].getTravelTimeToNext(),stre[j].getPickupDateTime(),stre[j].getLatlng(),stre[j].getDeliveryAddress());
			    	routeDetailDiChungService.saveARouteDetailDiChung(route_Code, stre[j].getTicketCode(), j, i,
			    			stre[j].getAddress(),stre[j].getDistanceToNext(),stre[j].getTravelTimeToNext(),
			    			stre[j].getExpectedPickupDateTime(),stre[j].getLatlng(),stre[j].getDeliveryAddress());
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
		StaffCustomer sc = staffCustomerService.getCusCodeByUserName(u.getUsername());
		List<RequestBatchDiChung> listBatch= requestBatchService.getList(sc.getSTFCUS_CustomerCode());
		model.put("listBatch", listBatch);
		List<mShippers> lSH=shipperService.getList();
		model.put("listShipper", lSH);
		return "dichung.viewroute";
	}
	@ResponseBody @RequestMapping(value="/load-route-in-batch", method=RequestMethod.POST)
	public List<RouteDiChungJson> loadRouteInBatch(HttpSession session,@RequestBody String batchCode){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		System.out.println(batchCode);
		
		List<mRoutes> lr= routeService.getListByBatchCode(batchCode);
		List<RouteDiChungJson> res= new ArrayList<RouteDiChungJson>();
		System.out.println(name()+lr);
		for(int i=0;i< lr.size();i++){
			mRoutes r= lr.get(i);
			RouteDiChungJson rdJ= new RouteDiChungJson();
			rdJ.setListPoint(routeDetailDiChungService.loadRouteDetailByRouteCode(r.getRoute_Code()));
			rdJ.setRoute_BatchCode(r.getRoute_BatchCode());
			rdJ.setRoute_Code(r.getRoute_Code());
			rdJ.setRoute_Shipper_Code(r.getRoute_Shipper_Code());
			rdJ.setRoute_Start_DateTime(r.getRoute_Start_DateTime());
			res.add(rdJ);
		}
		System.out.println(name()+" ::loadRouteInBatch"+res);
		return res;
	}
	
	String name(){
		return "DiChungControler:: ";
	}
}
