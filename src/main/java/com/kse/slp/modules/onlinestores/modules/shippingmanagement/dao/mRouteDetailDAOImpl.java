package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;
@Repository("mRouteDetailDAO")
@SuppressWarnings({"unchecked","rawtypes"})
public class mRouteDetailDAOImpl extends BaseDao implements mRouteDetailDAO {

	@Override
	public List<mRouteDetail> loadRouteDetailbyRouteCode(String routeCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mRouteDetail.class);
			criteria.add(Restrictions.eq("RTD_RouteCode", routeCode));
			List<mRouteDetail> r= criteria.list();
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
	public void deleteRoutesbyRouteCode(String routeCode) {
		// TODO Auto-generated method stub
	try{
		begin();
		System.out.println(name()+"deleteRoutesbyRouteCode--routeCode: "+routeCode);
		List<mRouteDetail> lsr = getSession().createCriteria(mRouteDetail.class).add(Restrictions.eq("RTD_RouteCode", routeCode)).list();
		if(lsr != null){
			for(mRouteDetail r: lsr){
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
	public int saveARouteDetail(mRouteDetail route) {
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

	public String name(){
		return "mRouteDetailDAOImpl::";
	}
	
}
