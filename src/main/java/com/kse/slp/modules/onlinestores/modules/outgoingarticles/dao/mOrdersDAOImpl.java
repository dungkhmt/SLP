package com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Repository;




import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mOrderDetail;
@Repository("mOrdersDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mOrdersDAOImpl extends BaseDao implements mOrdersDAO{
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	@Override
	public int saveAOrder(mOrders order) {
		try{
			begin();
			int id = 0;
			id = (int)getSession().save(order);
			commit();
			return id;
		} catch (HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return -1;
		}finally {
			flush();
			close();
		}
	}
	@Override
	public mOrders getAOrderById(int id) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mOrders.class);
			criteria.add(Restrictions.eq("O_ID", id));
			List<mOrders> o= criteria.list();
			commit();
			return o.get(0);
		} catch (HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally {
			flush();
			close();
		}
	}
	@Override
	public List<mOrders> getList() {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mOrders.class);
			criteria.add(Restrictions.eq("O_Delivered", 0));
			List<mOrders> listOrders = criteria.list();
			commit();
			return listOrders;
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
	public mOrders loadAOrderbyOrderCode(String orderCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mOrders.class);
			criteria.add(Restrictions.eq("O_Code", orderCode));
			List<mOrders> o= criteria.list();
			commit();
			if(o.size()>0)
			return o.get(0);
			else return null;
		} catch (HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally {
			flush();
			close();
		}
	}
	@Override
	public void setDeliveredOrder(mOrders order) {
		try {
	           begin();
	           getSession().update(order);
	           commit();
	        } catch (HibernateException e) {
	            e.printStackTrace();
	            rollback();
	            close();
	        } finally {
	            flush();
	            close();
	        }
	}
	
	@Override
	public List<mOrderDetail> getListOrderDetail() {
		// TODO Auto-generated method stub
		try {
			begin();
			String sql = "SELECT mo.O_Code, mo.O_DeliveryLat, mo.O_DeliveryLat ,mo.O_TimeEarly, mo.O_TimeLate, mo.O_DueDate, mc.C_Name"
					+ " FROM mOrders mo, mClients mc"
					+ " WHERE mo.O_Delivered=0 and mo.O_ClientCode=mc.C_Code"
					+ " ORDER BY mo.O_DueDate ASC";
			Query query = getSession().createQuery(sql);
			List<Object[]> query_result = query.list();
			List<mOrderDetail> lstOrderDetail = new ArrayList<mOrderDetail>();
			
			for(int i=0; i<query_result.size(); i++){
				mOrderDetail tmp = new mOrderDetail();
				tmp.setO_Code((String)query_result.get(i)[0]);
				tmp.setO_DeliveryLat((float)query_result.get(i)[1]);
				tmp.setO_DeliveryLng((float)query_result.get(i)[2]);
				tmp.setO_TimeEarly((String)query_result.get(i)[3]);
				tmp.setO_TimeLate((String)query_result.get(i)[4]);
				tmp.setO_DueDate((String)query_result.get(i)[5]);
				tmp.setC_Name((String)query_result.get(i)[6]);
				System.out.println(name()+"::getListOrderDetail--mOrderDetail["+i+"]"+tmp.toString());
				lstOrderDetail.add(tmp);
			}
			
	        commit();
	        
	        return lstOrderDetail;
	        
	    } catch (HibernateException e) {
	    	e.printStackTrace();
	        rollback();
	        close();
	        return null;
	    } finally {
	    	flush();
	        close();
	    }
	}
	
	public String name(){
		return "mOrdersDAOImpl";
	}
}
