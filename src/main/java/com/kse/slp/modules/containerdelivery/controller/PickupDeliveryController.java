package com.kse.slp.modules.containerdelivery.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.api.pickupdeliverycontainers.model.PickupDeliveryInput;
import com.kse.slp.modules.api.pickupdeliverycontainers.model.PickupDeliveryRequest;
import com.kse.slp.modules.api.pickupdeliverycontainers.model.PickupDeliveryRoute;
import com.kse.slp.modules.api.pickupdeliverycontainers.model.PickupDeliveryRouteElement;
import com.kse.slp.modules.api.pickupdeliverycontainers.model.PickupDeliverySolution;
import com.kse.slp.modules.api.pickupdeliverycontainers.model.Truck;
import com.kse.slp.modules.containerdelivery.model.RequestBatch;
import com.kse.slp.modules.containerdelivery.model.RouteContainerDeliveryJson;
import com.kse.slp.modules.containerdelivery.model.mPickupDeliveryOrders;
import com.kse.slp.modules.containerdelivery.service.mPickupDeliveryOrdersService;
import com.kse.slp.modules.containerdelivery.service.mRequestBatchService;
import com.kse.slp.modules.containerdelivery.validation.mOrderPickupDeliveryFormAdd;
import com.kse.slp.modules.dichung.model.FormAddFileExcel;
import com.kse.slp.modules.dichung.model.RouteDiChungJson;
import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.model.mArticlesCategory;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mIncomingArticles;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderArticles;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrdersService;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.validation.mOrderFormAdd;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.RouteContainerDetailExtension;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.RouteDetailContainer;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRouteDetailContainerService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRoutesService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mShippersService;
import com.kse.slp.modules.onlinestores.service.mArticlesCategoryService;
import com.kse.slp.modules.usermanagement.model.User;
import com.kse.slp.modules.utilities.GenerationDateTimeFormat;


@Controller("mPickupDeliveryController")
@RequestMapping(value = {"/containerdelivery"})
public class PickupDeliveryController extends BaseWeb{
	private static final Logger log = Logger.getLogger(PickupDeliveryController.class);
	@Autowired
	mPickupDeliveryOrdersService pickupDeliveryOrders;
	@Autowired
	mRouteDetailContainerService routeDetailContainerService;
	@Autowired
	mRoutesService routeService;
	@Autowired
	mShippersService shipperService;
	@Autowired
	mRequestBatchService requestBatchService;
	/*future fix
	 * reset route ->save vào csdl (null có lưu không) -> tăng increment id rất nhanh
	 * 
	 * xử lý volumn công volumn reset route sẽ không quay về được trạng thái ban đầu
	 * 
	 * kiểm tra lại ord 36 -> remain order block
	 * 
	 * 
	 */
	@RequestMapping(value="/list-pickupdelivery-order",method=RequestMethod.GET)
	public String listPickupDelivery(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		List<mPickupDeliveryOrders> list= pickupDeliveryOrders.getListOrderPickupDelivery();
		model.put("piDelist", list);
		log.info(u.getUsername());
		return "containerdelivery.listpickupdeliveryorder";
	}
	@RequestMapping(value="",method=RequestMethod.GET)
	public String moduleHome(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "containerdelivery.home";
	}
	@RequestMapping(value = "/add-a-pickupdelivery-order", method = RequestMethod.GET)
	public String addAPickupDeliveryOrder(ModelMap model, HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		List<RequestBatch> listBatch= requestBatchService.getList();
		model.put("listBatch", listBatch);
		model.put("orderPickupDeliveryFormAdd", new mOrderPickupDeliveryFormAdd());
		log.info(u.getUsername());
		return "containerdelivery.addapickupdeliveryorder";
	}
	@RequestMapping(value ="add-pickupdelivery-orders-by-xls", method= RequestMethod.GET )
	public String addPickupDeliveryOrders(ModelMap model, HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		List<RequestBatch> listBatch= requestBatchService.getList();
		model.put("listBatch", listBatch);
		model.put("formAdd", new FormAddFileExcel());
		
		return "containerdelivery.addpickupdeliveryordersbyxls";
	}
	@RequestMapping(value="/upload-file-pickupdelivery-orders", method=RequestMethod.POST)
	public String uploadFile(@ModelAttribute("formAdd") FormAddFileExcel request){
		MultipartFile file = request.getOrdersFile();
		System.out.println(name()+"::uploadFile--"+file.getOriginalFilename() + " uploaded");
		String batchCode= request.getBatchCode();
		System.out.println(name()+ batchCode);
		pickupDeliveryOrders.deleteOrdersInBatch(batchCode);
		if(file != null){
			readFilePickupDeliveryOrder(file,batchCode);
		}
		
		return "redirect:/containerdelivery/list-pickupdelivery-order";
	}
	public void readFilePickupDeliveryOrder(MultipartFile file,String batchCode){
		try {
			InputStream readFile = file.getInputStream();
			XSSFWorkbook wb = new XSSFWorkbook(readFile);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			int rows = sheet.getPhysicalNumberOfRows();
			for(int i=1;i<rows;i++){
				System.out.println(name()+"::readFile"+"--row "+i);
				row = sheet.getRow(i);
				String oPD_ClientCode=row.getCell(1).getStringCellValue();
				String oPD_PickupAddress=row.getCell(2).getStringCellValue();
				String latlng= row.getCell(3).getStringCellValue();
				float oPD_PickupLat =Float.parseFloat(latlng.substring(0, latlng.indexOf(',')));
				float oPD_PickupLng =Float.parseFloat(latlng.substring(latlng.indexOf(',')+2));
				String oPD_EarlyPickupDateTime = row.getCell(4).getStringCellValue();
				String oPD_LatePickupDateTime = row.getCell(5).getStringCellValue();
				String oPD_DeliveryAddress=row.getCell(6).getStringCellValue();
				latlng= row.getCell(7).getStringCellValue();
				float oPD_DeliveryLat =Float.parseFloat(latlng.substring(0, latlng.indexOf(',')));
				float oPD_DeliveryLng =Float.parseFloat(latlng.substring(latlng.indexOf(',')+2));
				String oPD_EarlyDeliveryDateTime = row.getCell(8).getStringCellValue();
				String oPD_LateDeliveryDateTime = row.getCell(9).getStringCellValue();
				int oPD_Volumn=(int) row.getCell(10).getNumericCellValue();
				System.out.println(name()+" "+oPD_ClientCode+" "+oPD_PickupAddress+" "+oPD_PickupLat+" "+oPD_PickupLng+" "+oPD_EarlyPickupDateTime+" "+oPD_LatePickupDateTime+" "+oPD_DeliveryAddress+" "+oPD_DeliveryLat+" "+oPD_DeliveryLng+" "+oPD_EarlyDeliveryDateTime+" "+oPD_LateDeliveryDateTime+" "+oPD_Volumn);
				pickupDeliveryOrders.saveAPickupDeliveryOrders(oPD_ClientCode, GenerationDateTimeFormat.genDateTimeFormatStandardCurrently(), oPD_PickupAddress, oPD_PickupLat, oPD_PickupLng, oPD_EarlyPickupDateTime, oPD_LatePickupDateTime, oPD_DeliveryAddress, oPD_DeliveryLat, oPD_DeliveryLng, oPD_EarlyDeliveryDateTime, oPD_LateDeliveryDateTime, oPD_Volumn, batchCode);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@RequestMapping(value = "/save-a-pickupdelivery-order", method = RequestMethod.POST)
	public String saveAPickupDeliveryOrder(ModelMap model, HttpSession session,@ModelAttribute("orderPickupDeliveryFormAdd") mOrderPickupDeliveryFormAdd orderForm,BindingResult result){
		User u=(User) session.getAttribute("currentUser");
		model.put("orderPickupDeliveryFormAdd", new mOrderPickupDeliveryFormAdd());
		if(result.hasErrors()){
			log.info(u.getUsername()+" FAIL");
		} else {
			String oPD_ClientCode=orderForm.getOrderClientCode();
			String oPD_PickupAddress=orderForm.getOrderPickupAddress();
			String oPD_DeliveryAddress=orderForm.getOrderDeliveryAddress();
			double oPD_PickupLat=orderForm.getOrderPickupLat();
			double oPD_PickupLng=orderForm.getOrderPickupLng();
			double oPD_DeliveryLat=orderForm.getOrderDeliveryLat();
			double oPD_DeliveryLng=orderForm.getOrderDeliveryLng();
			String oPD_EarlyPickupDateTime=orderForm.getOrderPickupDateTimeEarly();
			String oPD_LatePickupDateTime=orderForm.getOrderDelieveryDateTimeLate();
			String oPD_EarlyDeliveryDateTime=orderForm.getOrderDelieveryDateTimeEarly();
			String oPD_LateDeliveryDateTime=orderForm.getOrderDelieveryDateTimeLate();
			int oPD_Volumn=orderForm.getOrderVolumn();
			String oPD_BatchCode=orderForm.getOrderBatchCode();
			String oPD_RequestDateTime = GenerationDateTimeFormat.genDateTimeFormatStandardCurrently();
			pickupDeliveryOrders.saveAPickupDeliveryOrders(oPD_ClientCode, oPD_RequestDateTime, oPD_PickupAddress, oPD_PickupLat, oPD_PickupLng, oPD_EarlyPickupDateTime, oPD_LatePickupDateTime, oPD_DeliveryAddress, oPD_DeliveryLat, oPD_DeliveryLng, oPD_EarlyDeliveryDateTime, oPD_LateDeliveryDateTime, oPD_Volumn,oPD_BatchCode);
			log.info(u.getUsername()+" DONE");
			return "redirect:list-pickupdelivery-order";
		}
		return "redirect:add-a-pickupdelivery-order";
	}
	
	@RequestMapping(value="/view-route-detail-container", method= RequestMethod.GET)
	public String viewAllRoute(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername()+" DONE");
		//List<mRouteContainerDetailExtension> listR=routeDetailContainerService.loadRouteContainerDetailExtension(); 
		//model.put("listR",listR );
		
		//String listRJson = gson.toJson(listR);
		//System.out.println(name()+ listRJson);
		//model.put("listRJson", listRJson);
		List<mShippers> listShipper= shipperService.getList();
		
		model.put("listShipper", listShipper);
		
		
		List<RequestBatch> listBatch= requestBatchService.getList();
		model.put("listBatch", listBatch);
		return "containerdelivery.viewallroutecontainer";
	}
	@ResponseBody @RequestMapping(value="/load-route-in-batch", method=RequestMethod.POST)
	public List<RouteContainerDeliveryJson> loadContainerRoutesInBatch(HttpSession session,@RequestBody String batchCode){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		System.out.println(batchCode);
		
		List<mRoutes> lr=routeService.getListByBatchCode(batchCode);
		List<RouteContainerDeliveryJson> res= new ArrayList<>();
		for(int i=0;i< lr.size();i++){
			List<RouteContainerDetailExtension> lRDC= routeDetailContainerService.loadRouteContainerDetailExtension(lr.get(i).getRoute_Code());
			mRoutes mr= lr.get(i);
			RouteContainerDeliveryJson r= new RouteContainerDeliveryJson(mr.getRoute_Code(), mr.getRoute_Shipper_Code(), mr.getRoute_Start_DateTime(), mr.getRoute_BatchCode(), lRDC);
			res.add(r);
		}
		return res;
		
	}
	@RequestMapping(value="/create-route-auto", method=RequestMethod.GET)
	public String createRouteAuto(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		List<RequestBatch> listBatch= requestBatchService.getList();
		model.put("listBatch", listBatch);
		return "containerdelivery.createAutoRoute";
	}
	@ResponseBody @RequestMapping(value="/get-route-auto", method=RequestMethod.POST)
	public boolean getRouteAuto(HttpSession session,@RequestBody String batchCode){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		System.out.println(name()+batchCode);
		List<mShippers> lshp= shipperService.getList();
		Truck trck[]= new Truck[lshp.size()];
		for(int i=0;i<lshp.size();i++){
			Truck t= new Truck("30A-0987"+i, 2, lshp.get(i).getSHP_Code(), lshp.get(i).getSHP_CurrentLocation(), "-");
			trck[i]=t;
		}
		List<mPickupDeliveryOrders> lPDO= pickupDeliveryOrders.getListOrderPickupDelivery(batchCode);
		List<PickupDeliveryRequest> pDR=new ArrayList<PickupDeliveryRequest>();
		int t=0;
		for(int i=0;i<lPDO.size();i++){
			mPickupDeliveryOrders pDO=lPDO.get(i);
			int tmp=pDO.getOPD_Volumn();
			int maxLimit=2;
			int count=0;
			while (tmp >0){
				
				int qDown= tmp <maxLimit ? tmp:maxLimit;
				
				tmp-=qDown;
				PickupDeliveryRequest p= new PickupDeliveryRequest(pDO.getOPD_Code()+"-"+count, pDO.getOPD_PickupAddress(), pDO.getOPD_PickupLat()+", "+pDO.getOPD_PickupLng() , pDO.getOPD_EarlyPickupDateTime(), pDO.getOPD_DeliveryAddress(), pDO.getOPD_DeliveryLat()+", "+pDO.getOPD_DeliveryLng(), pDO.getOPD_EarlyDeliveryDateTime(), qDown);
				pDR.add(p);
				t++;
				count++;
			}
		}
		PickupDeliveryInput pDI=new PickupDeliveryInput(pDR, trck);
		Gson gson = new Gson();
		String json=gson.toJson(pDI);
		System.out.println(name()+json);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
		//HttpPost request = new HttpPost("http://103.18.4.32:8080/ezRoutingAPI/pickup-delivery-containers-plan");
		HttpPost request = new HttpPost("http://localhost:8080/ezRoutingAPI/pickup-delivery-containers-plan");
		StringEntity params = new StringEntity(json, ContentType.APPLICATION_JSON);
	    request.addHeader("content-type", "application/json");
	    request.setEntity(params);
	    System.out.println(request.getEntity());
	    HttpResponse response;
		
			response = httpClient.execute(request);
			HttpEntity  res= response.getEntity();
			String responseString = EntityUtils.toString(res, "UTF-8");
			System.out.println(name() + "::getRouteAuto, responseString = " + responseString);
			PickupDeliverySolution sol= gson.fromJson(responseString,PickupDeliverySolution.class);
			System.out.println(name()+sol);
			List<mRoutes> lr=routeService.getListByBatchCode(batchCode);
			for(int i=0;i<lr.size();i++){
				routeDetailContainerService.deleteRoutesbyRouteCode(lr.get(i).getRoute_Code());
				routeService.removeRoutesByRouteCode(lr.get(i).getRoute_Code());
			}
			PickupDeliveryRoute[] lPDR=sol.getRoutes();
			for(int i=0;i<lPDR.length;i++){
				PickupDeliveryRouteElement lPDRE[]= lPDR[i].getRouteElements();
				String routeCode="conatainerdelivery"+GenerationDateTimeFormat.genDateTimeFormatyyyyMMddCurrently();
				routeService.saveARoute(routeCode, "containerdelivery", "-", Constants.ROUTE_STATUS_CONFIRMED, batchCode);
				for(int j=0;j<lPDRE.length;j++){
					String tmp[]=lPDRE[j].getRequestCode().split("-");
					routeDetailContainerService.saveARouteDetailContainer(routeCode, lPDRE[j].getRequestCode(),tmp[0], lPDRE[j].getAction(),lPDRE[j].getArrivalDateTime(), j, lPDRE[j].getQuantity());
				}
			}
			 //System.out.println(json);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	// 10/9/2016
	public String name(){
		return "mPickupDeliveryController::";
	}
}
