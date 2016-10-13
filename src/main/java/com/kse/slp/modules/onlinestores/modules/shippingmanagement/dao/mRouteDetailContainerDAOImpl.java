package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.RouteContainerDetailExtension;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.RouteDetailContainer;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteUnderCreation;
@Repository("mRouteDetailContainerDAO")
@SuppressWarnings({"unchecked","rawtypes"})
public class mRouteDetailContainerDAOImpl extends BaseDao implements mRouteDetailContainerDAO {

	@Override
	public int saveARouteDetailContainer(RouteDetailContainer route) {
		// TODO Auto-generated method stub
		try{
			begin();
			int id=0;
			id = (int)getSession().save(route);
			commit();
			return id;
			
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return 0;
		}finally{
			flush();
			close();
		}
	}

	@Override
	public void deleteRoutesbyRouteCode(String routeCode) {
		try{
			begin();
			
			List<RouteDetailContainer> lsr = getSession().createCriteria(RouteDetailContainer.class).add(Restrictions.eq("RTDC_RouteCode", routeCode)).list();
			if(lsr != null){
				for(RouteDetailContainer r: lsr){
					getSession().delete(r);
				}
			}
			commit();
			
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			
		}finally{
			flush();
			close();
		}
		
	}

	@Override
	public List<RouteDetailContainer> loadRouteContainerDetailByRouteCode(
			String routeCode) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RouteDetailContainer.class);
			criteria.add(Restrictions.eq("RTDC_RouteCode", routeCode));
			List<RouteDetailContainer> r= criteria.list();
			commit();
			return r;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}

	@Override
	public int loadQuantityOfOrderInRouteByOrderCode(String orderCode) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RouteDetailContainer.class).setProjection(Projections.property("RTDC_Quantity"));
			criteria.add(Restrictions.eq("RTDC_OrderCode", orderCode));
			List<Integer> r= criteria.list();
			int ans=0;
			for(int i=0;i<r.size();i++)
				ans+=r.get(i);
			commit();
			return ans;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return -100000;
		}finally{
			flush();
			close();
		}

	}
	
	String name(){
		return "mRouteDetailContainerDAOImpl";
	}

	@Override
	public List<RouteContainerDetailExtension> loadRouteContainerDetailExtension(String routeCode) {
		try{
			begin();
			List<RouteContainerDetailExtension> list = new ArrayList<RouteContainerDetailExtension>();
			String sql = "SELECT mc.C_Code,  mc.C_Name, mo.OPD_PickupAddress ,mo.OPD_EarlyPickupDateTime ,"
					+ "mo.OPD_DeliveryAddress, mo.OPD_EarlyDeliveryDateTime, mo.OPD_PickupLat , mo.OPD_PickupLng , mo.OPD_DeliveryLat , mo.OPD_DeliveryLng ,"
					+ "mrdc.RTDC_Quantity, mrdc.RTDC_Sequence, mrdc.RTDC_Type "
					+ " FROM RouteDetailContainer mrdc, mRoutes mr, mPickupDeliveryOrders mo, mClients mc  "
					+ " WHERE mr.Route_Code= '"+routeCode  +"' and mrdc.RTDC_RouteCode = mr.Route_Code and mr.Route_Status_Code = '"+Constants.ROUTE_STATUS_CONFIRMED+"' and mrdc.RTDC_OrderCode = mo.OPD_Code and mo.OPD_ClientCode = mc.C_Code" //and mo.OPD_ClientCode = mc.C_PhoneNumber 
					+ "	ORDER BY mrdc.RTDC_RouteCode ASC , mrdc.RTDC_Sequence ASC";
			List<Object[]> sql_result = getSession().createQuery(sql).list();
			
			for(int i=0; i<sql_result.size(); i++){
				RouteContainerDetailExtension tmp = new RouteContainerDetailExtension();
				
				tmp.setClientCode((String)sql_result.get(i)[0]);
				tmp.setClientName((String)sql_result.get(i)[1]);
				tmp.setPickupAdress((String)sql_result.get(i)[2]);
				tmp.setExpectedTimePickup((String)sql_result.get(i)[3]);
				tmp.setDeliveryAdress((String)sql_result.get(i)[4]);
				tmp.setExpectedTimeDelivery((String)sql_result.get(i)[5]);
				tmp.setPickupLat((double)sql_result.get(i)[6]);
				tmp.setPickupLng((double)sql_result.get(i)[7]);
				tmp.setDeliveryLat((double)sql_result.get(i)[8]);
				tmp.setDeliveryLng((double)sql_result.get(i)[9]);
				tmp.setVolumn((int)sql_result.get(i)[10]);
				tmp.setSequence((int)sql_result.get(i)[11]);
				
				tmp.setType((String)sql_result.get(i)[12]);
				if(tmp.getType().equals("PICKUP")){
					tmp.setDeliveryAdress("-");
					tmp.setExpectedTimeDelivery("-");
				} else {
					tmp.setPickupAdress("-");
					tmp.setExpectedTimePickup("-");
				}
			
				tmp.setArriveTimePickup("-");
				tmp.setArriveTimeDeleivery("-");
				
				list.add(tmp);
			}
			
			commit();
			//System.out.println(name()+"getLstRTUnderCreation--return result: "+lstRTUnCreation.toString());
			return list;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}

}
