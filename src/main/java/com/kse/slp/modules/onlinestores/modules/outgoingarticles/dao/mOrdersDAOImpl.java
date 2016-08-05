package com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Repository;





import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
@Repository("mOrdersDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mOrdersDAOImpl extends BaseDao implements mOrdersDAO{
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	@Override
	public int saveAnOrder(mOrders order) {
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
	public mOrders getAnOrderById(int id) {
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
			criteria.add(Restrictions.ne("O_Status_Code",Constants.ORDER_STATUS_DELIVERIED ));
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
	public mOrders loadAnOrderbyOrderCode(String orderCode) {
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
	public void updateAnOrder(mOrders order) {
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
	

}
