package com.kse.slp.modules.dichung.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.dichung.model.RouteDetailDiChung;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;
@Repository("RouteDetailDiChungDAO")
@SuppressWarnings({"unchecked","rawtypes"})
public class RouteDetailDiChungDAOImpl extends BaseDao implements RouteDetailDiChungDAO  {
	@Override
	public List<RouteDetailDiChung> loadRouteDetailbyRouteCode(String routeCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RouteDetailDiChung.class);
			criteria.add(Restrictions.eq("RDDC_RouteCode", routeCode));
			List<RouteDetailDiChung> r= criteria.list();
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

		List<RouteDetailDiChung> lsr = getSession().createCriteria(RouteDetailDiChung.class).add(Restrictions.eq("RDDC_RouteCode", routeCode)).list();
		if(lsr != null){
			for(RouteDetailDiChung r: lsr){
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
	public int saveARouteDetail(RouteDetailDiChung route) {
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
		return "RouteDetailDiChung::";
	}
}
