package com.kse.slp.modules.onlinestores.modules.outgoingarticles.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.api.deliverygoods.model.DeliveryGoodInput;
import com.kse.slp.modules.api.deliverygoods.model.DeliveryGoodRoute;
import com.kse.slp.modules.api.deliverygoods.model.DeliveryGoodSolution;
import com.kse.slp.modules.api.deliverygoods.model.DeliveryRequest;
import com.kse.slp.modules.api.deliverygoods.model.Shipper;
import com.kse.slp.modules.api.deliverygoods.model.Store;
import com.kse.slp.modules.containerdelivery.model.RequestBatch;
import com.kse.slp.modules.containerdelivery.service.mRequestBatchService;
import com.kse.slp.modules.onlinestores.model.mArticlesCategory;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mIncomingArticles;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderArticles;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrdersService;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.validation.mFormAddFileExcel;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.validation.mOrderFormAdd;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRouteDetailService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRoutesService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mShippersService;
import com.kse.slp.modules.onlinestores.service.mArticlesCategoryService;
import com.kse.slp.modules.usermanagement.model.Customer;
import com.kse.slp.modules.usermanagement.model.User;
import com.kse.slp.modules.usermanagement.service.CustomerService;
import com.kse.slp.modules.utilities.GenerationDateTimeFormat;


@Controller("mOrderController")
@RequestMapping(value = {"/outgoingarticles"})
public class mOrderController extends BaseWeb{
	private static final Logger log = Logger.getLogger(mOrderController.class);

	@Autowired
	mArticlesCategoryService articleService;
	@Autowired
	mOrdersService orderService;
	@Autowired
	mRequestBatchService mRequestBatchService;
	@Autowired
	CustomerService CustomerService;
	@Autowired
	mShippersService mShippersService;
	@Autowired
	private mRoutesService mRoutesService;
	@Autowired
	private mRouteDetailService mRouteDetailService;
	
	/*
	@RequestMapping(value = "/add-an-order", method = RequestMethod.GET)
	public String addAOrder(ModelMap model, HttpSession session){
		model.put("orderFormAdd", new mOrderFormAdd());
		List<mArticlesCategory> list= articleService.getList();
		//System.out.print("this is list"+list.size());
		model.put("listArticleCategory", list);
		for(int i=0;i<list.size();i++ )
			System.out.print(list.get(i).toString());
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "outgoingarticles.addAOrder";
		//return "trash.outgoingarticles.add";
		
	}
	
	@RequestMapping(value = "/save-an-order", method = RequestMethod.POST)
	public String saveAOrder(ModelMap model,HttpServletRequest request, HttpSession session,@ModelAttribute("orderFormAdd") mOrderFormAdd orderForm,BindingResult result){
		//System.out.print("This is "+orderForm.getOrderAdress());
		User u=(User) session.getAttribute("currentUser");
		
		String[] orderArticles = request.getParameterValues("orderArticles");
		for(int i=0;i<orderArticles.length;i++){
			System.out.print(orderArticles[i]);
		}
		model.put("orderFormAdd", new mOrderFormAdd());
		if(result.hasErrors()){
			log.info(u.getUsername()+" FAIL");
		}else{
			String code=orderForm.getOrderClientCode();
			String clientCode=orderForm.getOrderClientCode();
			String dueDate=orderForm.getOrderDate();
			float lng=orderForm.getOrderDeliveryLng();
			float lat= orderForm.getOrderDeliveryLat();
			String timeEarly= orderForm.getOrderTimeEarly();
			String timeLate=orderForm.getOrderTimeLate();
			String address=orderForm.getOrderAdress();
			float price=orderForm.getOrderPrice();
			Date date= new Date();
			Date currentDate = new Date();
			SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("yyyy.MM.dd");
			String sCurrentDate = dateformatyyyyMMdd.format(currentDate);
			String orderDate=sCurrentDate;
			orderService.saveAnOrder( clientCode, orderDate, dueDate,address,lat,lng,timeEarly,timeLate,price,orderArticles);
			log.info(u.getUsername()+" DONE");
			return "redirect:list";
		}
		return "redirect:add-an-order";
		
	}
	*/
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listOutGoingArticles(ModelMap model,HttpSession session){
		System.out.print("listIncommingArticles");
		List<mOrders> inArtList = orderService.getList();
		
		System.out.print(inArtList);
	
		model.put("outArtList", inArtList);
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "outgoingarticles.list";
	}
	
	@RequestMapping(value="/uploadFile",method=RequestMethod.GET)
	public String upLoadFile(ModelMap model){
		List<RequestBatch> lstreBatch = mRequestBatchService.getList();
		
		model.put("lstreBatch", lstreBatch);
		model.put("formAdd",new mFormAddFileExcel());
		
		return "outgoingarticles.uploadFile";
	}
	
	@RequestMapping(value="/uploadOrdersFile", method=RequestMethod.POST)
	public String readFile(@ModelAttribute("formAdd") mFormAddFileExcel dataRequest){
		//System.out.println("Upload file");
		String batchCode = dataRequest.getBatchCode();
		//System.out.println("batch Code: "+ batchCode);
		
		//String itr = dataRequest.getOrdersFile().g();
		//String fileName = dataRequest.getOrdersFile().g
		MultipartFile ordersFile = dataRequest.getOrdersFile();
		
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
					orderService.saveAnOrder(clientCode, orderDate, dueDate, deliveryAddress, lat, lng, timeEarly, timeLate, 0 , null,batchCode);
					//System.out.println("=========");
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//System.out.println("Upload file done");
		return "redirect:/onlinestore";
	}

	@RequestMapping(value="/createAutoRoute",method = RequestMethod.GET)
	public String createAutoRoute(ModelMap model){
		List<RequestBatch> lstreBatch = mRequestBatchService.getList();
		
		model.put("lstreBatch", lstreBatch);
		
		return "outgoingarticles.createAutoRoute";
	}
	
	@RequestMapping(value="/callServiceCreateRoute", method=RequestMethod.POST)
	public String callServiceCreateRoute(@RequestBody String batch, HttpSession session){
		System.out.println(name()+"callServiceCreateRoute--callbatch" +batch);
		URL url;
		try {
			url = new URL("http://103.18.4.32:8080/ezRoutingAPI/delivery-goods-plan");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-type", "application/json");
			
			List<mOrders> lstOrder = orderService.getListOrderByBatchCode(batch);
			DeliveryRequest[] deliveryRequest = new DeliveryRequest[lstOrder.size()];
			for(int i=0; i<lstOrder.size(); i++){
				String requestCode = lstOrder.get(i).getO_Code();
				String deliveryAddress = lstOrder.get(i).getO_DeliveryAddress();
				String deliveryLatLng = lstOrder.get(i).getO_DeliveryLat()+", "+lstOrder.get(i).getO_DeliveryLng();
				String earlyDeliveryTime = lstOrder.get(i).getO_OrderDate()+" " +lstOrder.get(i).getO_TimeEarly();
				String lateDeliveryTime = lstOrder.get(i).getO_DueDate()+" "+lstOrder.get(i).getO_TimeLate();
				double weight = 10.0;
				double volumn = 0.0;
				deliveryRequest[i] = new DeliveryRequest(requestCode, deliveryAddress, deliveryLatLng, earlyDeliveryTime, lateDeliveryTime, weight, volumn);
			}
			
			//User user = (User) session.getAttribute("currentUser");
			String customerCode = mRequestBatchService.getByCode(batch).getREQBAT_CustomerCode();
			Customer cus = CustomerService.getByCode(customerCode);
			System.out.println(name()+"callServiceCreateRoute--cus: "+cus.toString());
			Store store = new Store(cus.getCus_Code(), cus.getCus_Name(), cus.getCus_Address(), (cus.getCus_Lat()+", "+cus.getCus_Lng())); 
			
			List<mShippers> lstShipper = mShippersService.getByCustomerCode(customerCode);
			Shipper shippers[] = new Shipper[lstShipper.size()];
			for(int i=0; i<lstShipper.size();i++){
				String shipperCode = lstShipper.get(i).getSHP_Code();
				String name = lstShipper.get(i).getSHP_User_Name();
				String currentLatLng = lstShipper.get(i).getSHP_CurrentLocation();
				double weight = 50.0;
				double volumn = 100.0;
				shippers[i] = new Shipper(shipperCode, name, currentLatLng, weight, volumn);
			}
			DeliveryGoodInput data = new DeliveryGoodInput(deliveryRequest, store, shippers);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String datajson = ow.writeValueAsString(data);
			System.out.println(name()+"callServiceCreateRoute---data send:");
			System.out.println(datajson);
			OutputStream os = conn.getOutputStream();
			os.write(datajson.getBytes());
			os.flush();
			
			BufferedReader br =  new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String reciveData = br.readLine();
			System.out.println(name()+"callServiceCreateRoute--recived Data: \n "+reciveData);
			
			ObjectMapper mapper = new ObjectMapper();
			DeliveryGoodSolution solution = mapper.readValue(reciveData, DeliveryGoodSolution.class);
			System.out.println(name()+"callServiceCreateRoute--length of route"+solution.getRoutes().length);
			for(int i=0; i<solution.getRoutes().length; i++){
				DeliveryGoodRoute route = solution.getRoutes()[i];
				String route_Shipper_Code = route.getRouteElements()[0].getRequestCode();
				String route_Code = route_Shipper_Code + GenerationDateTimeFormat.genDateTimeFormatyyyyMMddCurrently();
				mRoutesService.saveARoute(route_Code, route_Shipper_Code, "", "",batch);
				for(int j=1; j<route.getRouteElements().length; j++){
					String rTD_OrderCode = route.getRouteElements()[j].getRequestCode();
					String rTD_RouteCode = route_Code;
					mRouteDetailService.saveARouteDetail(rTD_OrderCode, rTD_RouteCode, j);
				}
			}
			System.out.println(name()+"callServiceCreateRoute--done");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/onlinestore";
	}
	@RequestMapping("/viewAutoRoute")
	public String viewAutoRoute(ModelMap model){
		List<RequestBatch> lstBatch = mRequestBatchService.getList();
		model.put("lstBatch", lstBatch);
		return "outgoingarticles.viewAutoRoute";
	}
	
	@RequestMapping("/viewAssignedBatchRoute")
	public String viewBatchRoute(@RequestBody String batch){
		System.out.println(name()+"viewBatchRoute--batch"+batch);
		return "{}";
	}
	
	public String name(){
		return "mOrderController::";
	}
}
