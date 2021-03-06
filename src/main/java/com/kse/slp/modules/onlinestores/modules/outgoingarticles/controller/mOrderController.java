package com.kse.slp.modules.onlinestores.modules.outgoingarticles.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
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
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.api.deliverygoods.model.DeliveryGoodInput;
import com.kse.slp.modules.api.deliverygoods.model.DeliveryGoodRoute;
import com.kse.slp.modules.api.deliverygoods.model.DeliveryGoodSolution;
import com.kse.slp.modules.api.deliverygoods.model.DeliveryRequest;
import com.kse.slp.modules.api.deliverygoods.model.Shipper;
import com.kse.slp.modules.api.deliverygoods.model.Store;
import com.kse.slp.modules.containerdelivery.model.RequestBatchOnlineStore;
import com.kse.slp.modules.containerdelivery.service.mRequestBatchOnlineStoreService;
import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.model.mArticlesCategory;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mIncomingArticles;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mAutoRouteJSONResponse;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mAutoRouteResponseInfo;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderArticles;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderDetail;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderStatus;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.sOrder;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.batchOnlineStoreService;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.batchOnlineStore;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrderArticlesService;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrderStatusService;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrdersService;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.validation.batchFormAdd;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.validation.mClusteringForm;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.validation.mFormAddFileExcel;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.validation.mFormSaveOrderStatus;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.validation.mOrderFormAdd;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.StoreBatch;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.Stores;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.infoAutoRouteElement;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.InfoAutoRouteElementService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.ShipperBatchService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.StoreBatchService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.StoresService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRouteDetailService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRoutesService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mShippersService;
import com.kse.slp.modules.onlinestores.service.mArticlesCategoryService;
import com.kse.slp.modules.usermanagement.model.Customer;
import com.kse.slp.modules.usermanagement.model.StaffCustomer;
import com.kse.slp.modules.usermanagement.model.User;
import com.kse.slp.modules.usermanagement.service.CustomerService;
import com.kse.slp.modules.usermanagement.service.StaffCustomerService;
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
	mOrderArticlesService mOrderArticlesService;
	@Autowired
	mRequestBatchOnlineStoreService mRequestBatchService;
	@Autowired
	CustomerService CustomerService;
	@Autowired
	mShippersService mShippersService;
	@Autowired
	private mRoutesService mRoutesService;
	@Autowired
	private mRouteDetailService mRouteDetailService;
	@Autowired
	InfoAutoRouteElementService InfoAutoRouteElementService;
	@Autowired
	StaffCustomerService StaffCustomerService;
	@Autowired
	ShipperBatchService ShipperBatchService;
	@Autowired 
	StoreBatchService StoreBatchService;
	@Autowired
	StoresService StoresService;
	@Autowired
	batchOnlineStoreService batchOnlineStoreService;
	@Autowired
	mOrderStatusService mOrderStatusService;
	
	@RequestMapping(value = "/allotParcelToShipper", method = RequestMethod.GET)
	public  String allotParcelToShipper(ModelMap model, HttpSession session){
		
		return "outgoingarticles.allotParcelToShipper";
	}
	
	@RequestMapping(value = "/viewOrderStatus", method = RequestMethod.GET)
	public  String orderStatus(ModelMap model, HttpSession session){
		
		return "outgoingarticles.orderStatus";
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/order/getorderstatus", method = RequestMethod.GET)
	public @ResponseBody List<mOrderDetail> getOrderStatus(ModelMap model, HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		
		StaffCustomer staffCustomer = StaffCustomerService.getCusCodeByUserName(u.getUsername());
		return orderService.getListOrderDetail("'"+Constants.ORDER_STATUS_ARRIVED_BUT_NOT_DELIVERIED+"', '"+Constants.ORDER_STATUS_DELIVERIED+"', '"+Constants.ORDER_STATUS_IN_ROUTE+"', '"+Constants.ORDER_STATUS_NOT_IN_ROUTE+"'", "CUS000001");
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/order/getstatus", method = RequestMethod.GET)
	public @ResponseBody List<mOrderStatus> getStatus(ModelMap model, HttpSession session){
		return mOrderStatusService.getList();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/order/save-orderstatus", method = RequestMethod.POST)
	public @ResponseBody void saveOrderStatus(ModelMap model,HttpServletRequest request, HttpSession session, @ModelAttribute("mFormSaveOrderStatus") mFormSaveOrderStatus formOrderStatus, BindingResult result){
		//System.out.print("This is "+orderForm.getOrderAdress());
		User u=(User) session.getAttribute("currentUser");
		
		orderService.updateStatus(formOrderStatus.getO_Code(), formOrderStatus.getO_Status_Code());
	}
	
	
	@RequestMapping(value="/clustering", method = RequestMethod.GET)
	public String clustering(ModelMap model,HttpSession session){
		List<mOrders> mOrders = orderService.getList();
		String json = new Gson().toJson(mOrders );
		model.put("mOrders", json);
		return "outgoingarticles.clustering";
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/clustering/save", method = RequestMethod.POST)
	public @ResponseBody void clussteringSave(ModelMap model,HttpServletRequest request, HttpSession session, @ModelAttribute("mClusteringForm") mClusteringForm mClusteringForm, BindingResult result){
		
		User u=(User) session.getAttribute("currentUser");
		
		StaffCustomer staffCustomer = StaffCustomerService.getCusCodeByUserName(u.getUsername());
		orderService.updateOrderBatch(mClusteringForm.getO_Code(), mClusteringForm.getO_BatchCode());
		
	}
	
	@RequestMapping(value="/parcel", method = RequestMethod.GET)
	public String parcel(ModelMap model,HttpSession session){
		return "outgoingarticles.parcel";
	}
	
	
	@RequestMapping(value="/parcel/getBatchList")
	public @ResponseBody List<batchOnlineStore> getBatchList(ModelMap model,HttpServletRequest request, HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		
		StaffCustomer staffCustomer = StaffCustomerService.getCusCodeByUserName(u.getUsername());
		
		List<batchOnlineStore> batchOnlineStoreData = batchOnlineStoreService.getList(staffCustomer.getSTFCUS_CustomerCode());
		
		return batchOnlineStoreData;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/parcel/save-batch", method = RequestMethod.POST)
	public @ResponseBody batchOnlineStore saveBatch(ModelMap model,HttpServletRequest request, HttpSession session, @ModelAttribute("batchFormAdd") batchFormAdd batchForm, BindingResult result){
		//System.out.print("This is "+orderForm.getOrderAdress());
		User u=(User) session.getAttribute("currentUser");
		
		batchOnlineStore newBatchOnlineStore = new batchOnlineStore(); 
		StaffCustomer staffCustomer = StaffCustomerService.getCusCodeByUserName(u.getUsername());
		newBatchOnlineStore.setREQBAT_CustomerCode(staffCustomer.getSTFCUS_CustomerCode());
		newBatchOnlineStore.setREQBAT_Description(batchForm.getDescription());
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		newBatchOnlineStore.setREQBAT_Code(dateFormat.format(date) + " " + staffCustomer.getSTFCUS_CustomerCode());
		int id = batchOnlineStoreService.save(newBatchOnlineStore);
		newBatchOnlineStore.setREQBAT_ID(id);
		return newBatchOnlineStore;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/parcel/edit/{code}", method = RequestMethod.POST)
	public @ResponseBody void editBatch(ModelMap model,HttpServletRequest request, HttpSession session, @ModelAttribute("batchFormAdd") batchFormAdd batchForm, BindingResult result, @PathVariable("code") int id){

		batchOnlineStoreService.edit(id, batchForm.getDescription());
		
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/parcel/delete", method = RequestMethod.POST)
	public @ResponseBody void deleteBatch(ModelMap model,HttpServletRequest request, HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		batchOnlineStoreService.delete(Integer.parseInt(request.getParameter("id")));
	}
	
	@RequestMapping(value="/commoditystatistics", method = RequestMethod.GET)
	public String commodityStatistics(ModelMap model,HttpSession session){
		return "outgoingarticles.commoditystatistics";
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/commoditystatistics/datadeliveried", method = RequestMethod.GET)
	public @ResponseBody List<sOrder> commodityStatisticsData(ModelMap model,HttpServletRequest request, HttpSession session){
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String type = request.getParameter("type");
		String status = request.getParameter("status");
		
		String stt = "";
		if(status.equals("ALL")) {
			stt = "\""+ Constants.ORDER_STATUS_DELIVERIED + "\",\"" + Constants.ORDER_STATUS_ARRIVED_BUT_NOT_DELIVERIED 
					+ "\",\"" + Constants.ORDER_STATUS_IN_ROUTE + "\",\"" + Constants.ORDER_STATUS_NOT_IN_ROUTE + "\"";
		} else {
			if(status.equals("DELIVERIED")) {
				stt = "\""+ Constants.ORDER_STATUS_DELIVERIED + "\"";
			} else {
				stt = "\""+ Constants.ORDER_STATUS_IN_ROUTE + "\",\"" + Constants.ORDER_STATUS_NOT_IN_ROUTE + "\"";
			}
		}
		
		User u=(User) session.getAttribute("currentUser");
		
		StaffCustomer staffCustomer = StaffCustomerService.getCusCodeByUserName(u.getUsername());

		return orderService.getstaticsOrders(from, to, type, stt, staffCustomer.getSTFCUS_CustomerCode());
		
	}
	
	@RequestMapping(value = "/add-an-order", method = RequestMethod.GET)
	public String addAOrder(ModelMap model, HttpSession session,RedirectAttributes redirectAttributes){
		User u=(User) session.getAttribute("currentUser");
		String username = u.getUsername();
		//System.out.println(name()+"upLoadFile--username: "+username);
		StaffCustomer sc = StaffCustomerService.getCusCodeByUserName(username);
		if(sc == null){
			return "error.500";
		}
		String CustomerCode = sc.getSTFCUS_CustomerCode();
		//System.out.println(name()+"upLoadFile--CustomerCode: "+sc.getSTFCUS_CustomerCode());
		
		List<RequestBatchOnlineStore> lstreBatch = mRequestBatchService.getList(CustomerCode);
		model.put("lstreBatch", lstreBatch);
		model.put("orderFormAdd", new mOrderFormAdd());
		List<mArticlesCategory> list= articleService.getList();
		//System.out.print("this is list"+list.size());
		model.put("listArticleCategory", list);
		for(int i=0;i<list.size();i++ )
			System.out.print(list.get(i).toString());
		//User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "outgoingarticles.addAOrder";
		//return "trash.outgoingarticles.add";
		
	}
	
	@RequestMapping(value = "/save-an-order", method = RequestMethod.POST)
	public String saveAOrder(ModelMap model,HttpServletRequest request, HttpSession session,@ModelAttribute("orderFormAdd") mOrderFormAdd orderForm,BindingResult result,RedirectAttributes redirectAttributes){
		//System.out.print("This is "+orderForm.getOrderAdress());
		User u=(User) session.getAttribute("currentUser");
		
		
		model.put("orderFormAdd", new mOrderFormAdd());
		if(result.hasErrors()){
			log.info(u.getUsername()+" FAIL");
		}else{
			String[] orderArticles = request.getParameterValues("orderArticles");
			if(orderArticles==null) {
				return "redirect:add-an-order";
			}
			for(int i=0;i<orderArticles.length;i++){
				System.out.println(name()+"saveAOrder--orderArticles["+i+"]:"+orderArticles[i]);
			}
			String code=orderForm.getOrderClientCode();
			String clientCode=orderForm.getOrderClientCode();
			String dueDate=orderForm.getOrderDate();
			float lng=orderForm.getOrderDeliveryLng();
			float lat= orderForm.getOrderDeliveryLat();
			String timeEarly= orderForm.getOrderTimeEarly();
			String timeLate=orderForm.getOrderTimeLate();
			String address=orderForm.getOrderAdress();
			float price=orderForm.getOrderPrice();
			String batchCode = orderForm.getBatchCode();
			
			orderService.saveAnOrder(clientCode, dueDate, dueDate, address, lat, lng, timeEarly, timeLate, price , orderArticles,batchCode);
			
			//orderService.saveAnOrder( clientCode, orderDate, dueDate,address,lat,lng,timeEarly,timeLate,price,orderArticles);
			log.info(u.getUsername()+" DONE");
			return "redirect:list";
		}
		return "redirect:add-an-order";
		
	}
	
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listOutGoingArticles(ModelMap model,HttpSession session){
		//System.out.print("listIncommingArticles");
		List<mOrders> inArtList = orderService.getList();
		List<String> lstBatchCode = new ArrayList<String>();
		Map<String,String> test = new HashMap<String, String>();
		for(int i=0; i<inArtList.size(); i++){
			String batchCode = inArtList.get(i).getO_BatchCode();
			if(!test.containsKey(batchCode)){
				lstBatchCode.add(batchCode);
				test.put(batchCode, batchCode);
			}
		}
		//System.out.print(inArtList);
	
		model.put("outArtList", inArtList);
		model.put("lstBatchCode",lstBatchCode);
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "outgoingarticles.list";
	}
	
	@RequestMapping(value="/uploadFile",method=RequestMethod.GET)
	public String upLoadFile(ModelMap model,HttpSession session){
		//List<RequestBatch> lstreBatch = mRequestBatchService.getList();
		User u=(User) session.getAttribute("currentUser");
		String username = u.getUsername();
		//System.out.println(name()+"upLoadFile--username: "+username);
		StaffCustomer sc = StaffCustomerService.getCusCodeByUserName(username);
		if(sc == null){
			return "error.500";
		}
		String CustomerCode = sc.getSTFCUS_CustomerCode();
		//System.out.println(name()+"upLoadFile--CustomerCode: "+sc.getSTFCUS_CustomerCode());
		
		List<RequestBatchOnlineStore> lstreBatch = mRequestBatchService.getList(CustomerCode);
		model.put("lstreBatch", lstreBatch);
		model.put("formAdd",new mFormAddFileExcel());
		
		return "outgoingarticles.uploadFile";
	}
	
	@RequestMapping(value="/uploadOrdersFile", method=RequestMethod.POST)
	public String readFile(@ModelAttribute("formAdd") mFormAddFileExcel dataRequest, ModelMap model){
		//System.out.println("Upload file");
		String batchCode = dataRequest.getBatchCode();
		//System.out.println("batch Code: "+ batchCode);
		List<mOrders> lstOrder = orderService.getListOrderByBatchCode(batchCode);
		for(int i=0; i<lstOrder.size(); i++){
			mOrderArticlesService.deleteOrderArticles(lstOrder.get(i).getO_Code());
		}
		orderService.deleteOrder(batchCode);
		ShipperBatchService.deleteShipperBatch(batchCode);
		StoreBatchService.deleteStoreBatch(batchCode);
		//String itr = dataRequest.getOrdersFile().g();
		//String fileName = dataRequest.getOrdersFile().g
		MultipartFile ordersFile = dataRequest.getOrdersFile();
		
		if(ordersFile != null){
			try {
				
				InputStream readFile = ordersFile.getInputStream();
				XSSFWorkbook wb = new XSSFWorkbook(readFile);
				
				XSSFSheet sheet4 = wb.getSheetAt(3);
				XSSFRow row4;
				int rows4 = sheet4.getPhysicalNumberOfRows();
				Map<String, List<mOrderArticles>> lstMapOrderArticles = new HashMap<String,List<mOrderArticles>>();
				for(int i=1; i<rows4; i++){
					row4 = sheet4.getRow(i);
					String clientCode = "" + (long)row4.getCell(0).getNumericCellValue();
					System.out.println(name()+"readFile--orderArticleCode["+i+"]: "+clientCode);
					String articleCode = row4.getCell(1).getStringCellValue();
					int amount = (int)row4.getCell(2).getNumericCellValue();
					float price = (float)row4.getCell(3).getNumericCellValue();
					mOrderArticles tmorar = new mOrderArticles();
					tmorar.setOA_Code(articleCode);
					tmorar.setOA_Amount(amount);
					tmorar.setOA_Price(price);
					
					//List<mOrderArticles> tmp = lstMapOrderArticles.get(clientCode);
					if(lstMapOrderArticles.containsKey(clientCode)){
						List<mOrderArticles> tmp = lstMapOrderArticles.get(clientCode);
						tmp.add(tmorar);
						lstMapOrderArticles.put(clientCode, tmp);
					
					}else{
						List<mOrderArticles> tmp = new ArrayList<mOrderArticles>();
						tmp.add(tmorar);
						lstMapOrderArticles.put(clientCode, tmp);
					}
				}
				
				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;
				int rows = sheet.getPhysicalNumberOfRows();
				
				Map<String, String> orderArticle = new HashMap<String, String>();
				for(int i=1; i<rows; i++){
					row = sheet.getRow(i);
					String clientCode = ""+(long)row.getCell(1).getNumericCellValue();
					System.out.println(name()+"readFile--O_Code["+i+"]: "+clientCode);
					if(!orderArticle.containsKey(clientCode)){
						String deliveryAddress = row.getCell(2).getStringCellValue();
						//System.out.println(name()+"readFile--Adderss["+i+"]: "+deliveryAddress);
						
						String latlng = row.getCell(3).getStringCellValue();
						int index = latlng.indexOf(",");
						
						float lat = Float.parseFloat(latlng.substring(0,index));
						//System.out.println(name()+"readFile--lat["+i+"]: "+lat);
						
						float lng = Float.parseFloat(latlng.substring(index+1,latlng.length()));
						//System.out.println(name()+"readFile--lng["+i+"]: "+lng);
						String timeEearly="";
						switch(row.getCell(4).getCellType()){
							case XSSFCell.CELL_TYPE_NUMERIC: 
								if (DateUtil.isCellDateFormatted(row.getCell(4))) {
									SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									timeEearly += dateFormat.format(row.getCell(4).getDateCellValue());
				                    System.out.println(name()+"readFile--timeEarly["+i+"]: "+timeEearly);
				                } else {
				                    //System.out.println(cell.getNumericCellValue());
				                	timeEearly += String.valueOf(row.getCell(4).getNumericCellValue());
				                }
								break;
							case XSSFCell.CELL_TYPE_STRING:
								timeEearly += row.getCell(4).getStringCellValue();
								break;
						}
						 
						int index2 = timeEearly.indexOf(" ");
						String orderDate = timeEearly.substring(0,index2);
						//System.out.println(name()+"readFile--orderDate["+i+"]: "+orderDate);
						
						String timeEarly = timeEearly.substring(index2+1,timeEearly.length());
						//System.out.println(name()+"readFile--timeEarly["+i+"]: "+timeEarly);
						
						String timeDueTo="";
						//String timeEearly="";
						
						switch(row.getCell(5).getCellType()){
							case XSSFCell.CELL_TYPE_NUMERIC: 
								if (DateUtil.isCellDateFormatted(row.getCell(5))) {
									SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									timeDueTo += dateFormat.format(row.getCell(5).getDateCellValue());
				                    System.out.println(name()+"readFile--timeDueTo["+i+"]: "+timeDueTo);
				                } else {
				                    //System.out.println(cell.getNumericCellValue());
				                	timeDueTo += String.valueOf(row.getCell(5).getNumericCellValue());
				                }
								break;
							case XSSFCell.CELL_TYPE_STRING:
								timeDueTo += row.getCell(5).getStringCellValue();
								break;
						}
						//System.out.println(name()+"readFile--timeDueTo["+i+"]: "+timeDueTo);
						int index3 = timeDueTo.indexOf(" ");
						String dueDate = timeDueTo.substring(0,index3);
						//System.out.println(name()+"readFile--dueDate["+i+"]: "+dueDate);
						
						
						String timeLate = timeDueTo.substring(index3+1,timeDueTo.length());
						//System.out.println(name()+"readFile--timeLate["+i+"]: "+timeLate);
						String OCode = orderService.saveAnOrder(clientCode, orderDate, dueDate, deliveryAddress, lat, lng, timeEarly, timeLate, 0 , null,batchCode);
						//System.out.println("=========");
						mOrders orderTmp = orderService.loadAnOrderbyOrderCode(OCode);
						orderArticle.put(clientCode, OCode);
						List<mOrderArticles> lstOrderArticles = lstMapOrderArticles.get(clientCode);
						float price = 0;
						for(int in=0; in<lstOrderArticles.size(); in++){
							mOrderArticles tmp = lstOrderArticles.get(in);
							String oA_Code = tmp.getOA_Code();
							String oA_Amount = ""+tmp.getOA_Amount();
							float oA_Price = tmp.getOA_Price();
							price += oA_Price*tmp.getOA_Amount();
							mOrderArticlesService.saveAOrderArticles(oA_Code, OCode, oA_Amount, orderDate, oA_Price);
						}
						orderTmp.setO_Price(price);
						orderService.updateAnOrder(orderTmp);
					}
				}
				
				XSSFSheet sheet1 = wb.getSheetAt(1);
				XSSFRow rowsheet1;
				
				int rows1 = sheet1.getPhysicalNumberOfRows();
				for(int i=1; i<rows1; i++){
					rowsheet1 = sheet1.getRow(i);
					String storeCode = rowsheet1.getCell(1).getStringCellValue();
					System.out.println(name()+"readFile--storeCode["+i+"]: "+storeCode);
					StoreBatchService.saveAStoreBatch(storeCode, batchCode);
				}
				
				XSSFSheet sheet2 = wb.getSheetAt(2);
				XSSFRow rowsheet2;
				
				int rows2 = sheet2.getPhysicalNumberOfRows();
				for(int i=1; i<rows2; i++){
					rowsheet2 = sheet2.getRow(i);
					String shipperCode = rowsheet2.getCell(1).getStringCellValue();
					System.out.println(name()+"readFile--shipperCode["+i+"]: "+shipperCode);
					ShipperBatchService.saveAShipperBatch(shipperCode, batchCode);
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//System.out.println("Upload file done");
		//model.put("lstBatchCode",batchCode);
		return "redirect:list";
	}

	@RequestMapping(value="/createAutoRoute",method = RequestMethod.GET)
	public String createAutoRoute(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		String username = u.getUsername();
		//System.out.println(name()+"createAutoRoute--username: "+username);
		StaffCustomer sc = StaffCustomerService.getCusCodeByUserName(username);
		if(sc == null){
			return "error.500";
		}
		String CustomerCode = sc.getSTFCUS_CustomerCode();
		//System.out.println(name()+"createAutoRoute--CustomerCode: "+sc.getSTFCUS_CustomerCode());
		
		List<RequestBatchOnlineStore> lstreBatch = mRequestBatchService.getList(CustomerCode);
		System.out.println(name() + "::lstBatch = ");
		for(RequestBatchOnlineStore b: lstreBatch){
			System.out.println(b.getREQBAT_Description() + "\t" + b.getREQBAT_Code());
		}
		model.put("lstreBatch", lstreBatch);
		
		return "outgoingarticles.createAutoRoute";
	}
	
	@RequestMapping(value="/callServiceCreateRoute", method=RequestMethod.POST)
	public String callServiceCreateRoute(@RequestBody String batch){
		System.out.println(name()+"callServiceCreateRoute--callbatch " +batch);
		URL url;
		try {
			List<mOrders> lstOrder = orderService.getListOrderByBatchCode(batch);
			DeliveryRequest[] deliveryRequest = new DeliveryRequest[lstOrder.size()];
			for(int i=0; i<lstOrder.size(); i++){
				String requestCode = lstOrder.get(i).getO_Code();
				String deliveryAddress = lstOrder.get(i).getO_DeliveryAddress();
				String deliveryLatLng = lstOrder.get(i).getO_DeliveryLat()+", "+lstOrder.get(i).getO_DeliveryLng();
				String earlyDeliveryTime = lstOrder.get(i).getO_OrderDate()+" " +lstOrder.get(i).getO_TimeEarly();
				String lateDeliveryTime = lstOrder.get(i).getO_DueDate()+" "+lstOrder.get(i).getO_TimeLate();
				double weight = 1;//10.0;
				double volumn = 0.0;
				deliveryRequest[i] = new DeliveryRequest(requestCode, deliveryAddress, deliveryLatLng, earlyDeliveryTime, lateDeliveryTime, weight, volumn);
			}
			
			//User user = (User) session.getAttribute("currentUser");
			//String customerCode = mRequestBatchService.getByCode(batch).getREQBAT_CustomerCode();
			//Customer cus = CustomerService.getByCode(customerCode);
			//System.out.println(name()+"callServiceCreateRoute--cus: "+cus.toString());
			List<Stores> lstStore = StoresService.getListStoreInBatch(batch);
			
			Store store = new Store(lstStore.get(0).getSTR_Code(), lstStore.get(0).getSTR_Name(), lstStore.get(0).getSTR_Address(), lstStore.get(0).getSTR_LatLng()); 
			
			System.out.println(name() + "::callServiceCreateRoute, lstOrders = " + lstOrder.size() + " = requests = " + deliveryRequest.length);
			//List<mShippers> lstShipper = mShippersService.getByCustomerCode(customerCode);
			List<mShippers> lstShipper = mShippersService.getListInBatch(batch);
			Shipper shippers[] = new Shipper[lstShipper.size()];
			for(int i=0; i<lstShipper.size();i++){
				String shipperCode = lstShipper.get(i).getSHP_Code();
				String name = lstShipper.get(i).getSHP_User_Name();
				String currentLatLng = lstShipper.get(i).getSHP_CurrentLocation();
				double weight = lstShipper.get(i).getSHP_Capacity_1();//50.0;
				double volumn = lstShipper.get(i).getSHP_Capacity_2();//100.0;
				shippers[i] = new Shipper(shipperCode, name, currentLatLng, weight, volumn);
				
				System.out.println(name() + "::callServiceCreateRoute, shipper weight = " + weight + " = " + shippers[i].getWeight() + "/" + shippers[i].getVolumn());
			}
			
			//System.exit(-1);
			
			DeliveryGoodInput data = new DeliveryGoodInput(deliveryRequest, store, shippers);
			Gson gson = new Gson();
			String datajson = gson.toJson(data);
			System.out.println(name()+"callServiceCreateRoute---data send:");
			System.out.println(datajson);
			
			HttpPost post = new HttpPost("http://localhost:8080/ezRoutingAPI/delivery-goods-plan");
			StringEntity params = new StringEntity(datajson, ContentType.APPLICATION_JSON);
			post.addHeader("content-type", "application/json");
			post.setEntity(params);
			
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(post);
			HttpEntity res = response.getEntity();
			String responseString = EntityUtils.toString(res, "UTF-8");
			
			DeliveryGoodSolution solution = gson.fromJson(responseString, DeliveryGoodSolution.class);			
			List<mRoutes> lstr = mRoutesService.getListByBatchCode(batch);
			System.out.println(name()+"callServiceCreateRoute--length of route to remove"+lstr.size());
			
			if(lstr != null){
				for(int i=0; i<lstr.size(); i++){
					mRoutesService.removeRoutesByRouteCode(lstr.get(i).getRoute_Code());
					mRouteDetailService.removeRoutesByRouteCode(lstr.get(i).getRoute_Code());
				}
			}
			
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
		
		return "redirect:viewAutoRoute";
	}
	@RequestMapping("/viewAutoRoute")
	public String viewAutoRoute(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		String username = u.getUsername();
		//System.out.println(name()+"createAutoRoute--username: "+username);
		StaffCustomer sc = StaffCustomerService.getCusCodeByUserName(username);
		if(sc == null){
			return "error.500";
		}
		String CustomerCode = sc.getSTFCUS_CustomerCode();
		//System.out.println(name()+"createAutoRoute--CustomerCode: "+sc.getSTFCUS_CustomerCode());
		
		List<RequestBatchOnlineStore> lstreBatch = mRequestBatchService.getList(CustomerCode);
		List<mShippers> lstShipper = mShippersService.getList();
		model.put("lstBatch", lstreBatch);
		model.put("lstShipper", lstShipper);
		return "outgoingarticles.viewAutoRoute";
	}
	
	@RequestMapping("/viewAssignedBatchRoute")
	public @ResponseBody List<mAutoRouteResponseInfo> viewBatchRoute(@RequestBody String batch){
		//System.out.println(name()+"viewBatchRoute--batch"+batch);
		List<mRoutes> lstRoute = mRoutesService.getListByBatchCode(batch);
		List<mAutoRouteResponseInfo> response = new ArrayList<mAutoRouteResponseInfo>();
		for(int i=0; i<lstRoute.size(); i++){
			String routeCode = lstRoute.get(i).getRoute_Code();
			String shipperCode = lstRoute.get(i).getRoute_Shipper_Code();
			List<Stores> lstStr = StoresService.getListStoreInBatch(batch);
			String storeLatLng = lstStr.get(0).getSTR_LatLng();
			List<infoAutoRouteElement> routeElement = InfoAutoRouteElementService.getList(routeCode);
			mAutoRouteResponseInfo tmp = new mAutoRouteResponseInfo(storeLatLng,shipperCode, routeCode,routeElement);
			//System.out.println(name()+"viewBatchRoute--response:"+tmp.toString());
			response.add(tmp);
		}
		//mAutoRouteJSONResponse data = new mAutoRouteJSONResponse(response);
		
		return response;
	}
	
	public String name(){
		return "mOrderController::";
	}
}
