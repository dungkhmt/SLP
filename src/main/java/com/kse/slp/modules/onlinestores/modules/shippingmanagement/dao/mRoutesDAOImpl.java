package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteUnderCreation;
import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;

@Repository("mRoutesDAO")
@SuppressWarnings({"unchecked","rawtypes"})
public class mRoutesDAOImpl extends BaseDao implements mRoutesDAO {

	@Override
	public List<mRoutes> loadRoutebyShipperCode(String shipperCode) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mRoutes.class);
			criteria.add(Restrictions.eq("Route_Shipper_Code", shipperCode));
			List<mRoutes> r= criteria.list();
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
	public List<mRouteUnderCreation> getLstRTUnderCreation() {
		// TODO Auto-generated method stub
		try{
			begin();
			List<mRouteUnderCreation> lstRTUnCreation = new ArrayList<mRouteUnderCreation>();
			String sql = "SELECT mrd.RTD_RouteCode, mrd.RTD_OrderCode, mrd.RTD_Sequence, mr.Route_Shipper_Code, mr.Route_Start_DateTime"
					+ " FROM mRoutes mr, mRouteDetail mrd"
					+ " WHERE mrd.RTD_RouteCode = mr.Route_Code and mr.Route_Status_Code = '"+Constants.ROUTE_STATUS_UNDER_CREATION+"'"
					+ "	ORDER BY mrd.RTD_RouteCode ASC";
			List<Object[]> sql_result = getSession().createQuery(sql).list();
			
			for(int i=0; i<sql_result.size(); i++){
				mRouteUnderCreation tmp = new mRouteUnderCreation();
				tmp.setRoute_Code((String)sql_result.get(i)[0]);
				tmp.setOrder_Code((String)sql_result.get(i)[1]);
				tmp.setOrder_Sequence((int)sql_result.get(i)[2]);
				tmp.setShipper_Code((String)sql_result.get(i)[3]);
				tmp.setRoute_Start_Time((String)sql_result.get(i)[4]);
				System.out.println(name()+"getLstRTUnderCreation--"+tmp.toString());
				lstRTUnCreation.add(tmp);
			}
			
			commit();
			//System.out.println(name()+"getLstRTUnderCreation--return result: "+lstRTUnCreation.toString());
			return lstRTUnCreation;
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
	
	public int removeARoute(int route_Id) {
		mRoutes route = new mRoutes();
		route.setRoute_ID(route_Id);
		try{
			begin();
			getSession().delete(route);
			commit();
			return 1;
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
	public int saveARoute(mRoutes route){
		try{
			begin();
			int id = 0;
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
	public List<mRoutes> loadRoutebyRouteCode(String routeCode) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mRoutes.class);
			criteria.add(Restrictions.eq("Route_Code", routeCode));
			List<mRoutes> l= criteria.list();
			commit();
		
			return l;
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
	public void removeRouteByRouteCode(String route_Code) {
		// TODO Auto-generated method stub
		try{
			System.out.println(name()+"deleteRoutesbyRouteCode--routeCode: "+route_Code);
			begin();
			List<mRoutes> lsr = getSession().createCriteria(mRoutes.class).add(Restrictions.eq("Route_Code", route_Code)).list();
			if(lsr != null){
				for(mRoutes r: lsr){
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
	
	public String name(){
		return "mRoutesDAOImpl::";
	}

	@Override
	public mRoutes loadRoutesUnderCreationByShipperCode(String shipperCode) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mRoutes.class);
			 criteria.add(Restrictions.and(Restrictions.eq("Route_Shipper_Code", shipperCode),Restrictions.eq("Route_Status_Code", Constants.ROUTE_STATUS_UNDER_CREATION)));
			List<mRoutes> r= criteria.list();
			commit();
			if(r.size()<1) return null;
			return r.get(0);
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
