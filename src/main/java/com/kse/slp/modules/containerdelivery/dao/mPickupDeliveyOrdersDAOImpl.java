package com.kse.slp.modules.containerdelivery.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.containerdelivery.model.mPickupDeliveryOrders;
import com.kse.slp.modules.dichung.model.RequestDiChung;
import com.kse.slp.modules.onlinestores.common.Constants;


@Repository("mPickupDeliveyOrdersDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mPickupDeliveyOrdersDAOImpl extends BaseDao implements mPickupDeliveryOrdersDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
	public int saveAnOrder(mPickupDeliveryOrders order) {
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
	public List<mPickupDeliveryOrders> getList() {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mPickupDeliveryOrders.class);
			criteria.add(Restrictions.eq("OPD_StatusCode",Constants.ORDER_STATUS_NOT_IN_ROUTE ));
			
			List<mPickupDeliveryOrders > listOrders = criteria.list();
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
	public List<mPickupDeliveryOrders> getListInBatch(String batchCode) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mPickupDeliveryOrders.class);
			criteria.add(Restrictions.eq("OPD_BatchCode",batchCode));
			List<mPickupDeliveryOrders> listRequest = criteria.list();
			commit();
			return listRequest;
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
	public mPickupDeliveryOrders loadAPickupDeliveryOrderbyCode(String orderCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mPickupDeliveryOrders.class);
			criteria.add(Restrictions.eq("OPD_Code", orderCode));
			List<mPickupDeliveryOrders> o= criteria.list();
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
	public void updateAnOrder(mPickupDeliveryOrders order) {
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
	public void deleteOrdersInBatch(String batchCode) {
		try{
			begin();
			
			List<mPickupDeliveryOrders> lrdc = getSession().createCriteria(mPickupDeliveryOrders.class).add(Restrictions.eq("OPD_BatchCode", batchCode)).list();
			if(lrdc != null){
				for(mPickupDeliveryOrders r: lrdc){
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
}
