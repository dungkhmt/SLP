package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetailContainer;
@Repository("mRouteDetailContainerDAO")
@SuppressWarnings({"unchecked","rawtypes"})
public class mRouteDetailContainerDAOImpl extends BaseDao implements mRouteDetailContainerDAO {

	@Override
	public int saveARouteDetailContainer(mRouteDetailContainer route) {
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
			
			List<mRouteDetailContainer> lsr = getSession().createCriteria(mRouteDetailContainer.class).add(Restrictions.eq("RTDC_RouteCode", routeCode)).list();
			if(lsr != null){
				for(mRouteDetailContainer r: lsr){
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
	public List<mRouteDetailContainer> loadRouteContainerDetailByRouteCode(
			String routeCode) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mRouteDetailContainer.class);
			criteria.add(Restrictions.eq("RTDC_RouteCode", routeCode));
			List<mRouteDetailContainer> r= criteria.list();
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
			Criteria criteria = getSession().createCriteria(mRouteDetailContainer.class).setProjection(Projections.property("RTDC_Quantity"));
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

}
