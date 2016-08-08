package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;





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

}
