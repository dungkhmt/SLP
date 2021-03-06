package com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Repository;


















import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderDetail;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.sOrder;
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
			//criteria.createAlias("O_BatchCode", "O_BatchCode");
			//criteria.addOrder(Order.asc("O_BatchCode.value"));
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
	
	@Override
	public List<mOrderDetail> getListOrderDetail(String status, String CUSCode) {
		// TODO Auto-generated method stub
		try {
			begin();
			String sql = "SELECT mo.O_Status_Code, mo.O_Code, mo.O_OrderDate,  mo.O_DeliveryAddress, mo.O_DeliveryLat, mo.O_DeliveryLng ,mo.O_TimeEarly, mo.O_TimeLate, mo.O_DueDate, mc.C_Name, mrb.REQBAT_Description"
					+ " FROM mOrders mo, mClients mc, batchOnlineStore mrb"
					+ " WHERE mo.O_Status_Code IN ("+status+") and mo.O_ClientCode = mc.C_Code and mrb.REQBAT_Code = mo.O_BatchCode and mrb.REQBAT_CustomerCode = :C_Code ORDER BY mo.O_DueDate ASC";
//			='"+Constants.ORDER_STATUS_NOT_IN_ROUTE+
//					"' or mo.O_Status_Code='"+Constants.ORDER_STATUS_ARRIVED_BUT_NOT_DELIVERIED+"')
			Query query = getSession().createQuery(sql);
			query.setParameter("C_Code", CUSCode);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List query_result = query.list();
			List<mOrderDetail> lstOrderDetail = new ArrayList<mOrderDetail>();
			//0=Chưa được gán cho shipper, 1=OR000137, 2=2016-09-06, 3=200 Lê Duẩn, Hà Nội, 4=21.0173, 5=105.841, 6=18:00:00, 7=19:00:00, 8=2016-09-06, 9=Phan Anh Tú, 10=Các request lô 2016-09-24 10:00:00
			for(int i=0; i<query_result.size(); i++){
				Map tmp = (Map) query_result.get(i);
				mOrderDetail temp = new mOrderDetail();
				temp.setO_Code(tmp.get("1").toString());
				temp.setO_DeliveryLat(Float.valueOf(tmp.get("4").toString()));
				temp.setO_DeliveryLng(Float.valueOf(tmp.get("5").toString()));
				temp.setO_TimeEarly(tmp.get("6").toString());
				temp.setO_TimeLate(tmp.get("7").toString());
				temp.setO_DueDate(tmp.get("8").toString());
				temp.setC_Name(tmp.get("9").toString());
				temp.setREQBAT_Description(tmp.get("10").toString());
				temp.setO_DeliveryAddress(tmp.get("3").toString());
				temp.setO_Status_Code(tmp.get("0").toString());
				temp.setO_OrderDate(tmp.get("2").toString());
				//System.out.println(name()+"::getListOrderDetail--mOrderDetail["+i+"]"+tmp.toString());
				lstOrderDetail.add(temp);
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
	@Override
	public List<String> getListDueDate() {
		// TODO Auto-generated method stub
		try{
			
			begin();
			String sql = "SELECT DISTINCT mo.O_DueDate"
					+ " FROM mOrders mo"
					+ "	WHERE mo.O_Status_Code='"+Constants.ORDER_STATUS_NOT_IN_ROUTE+"' "
							+ "OR mo.O_Status_Code='"+Constants.ORDER_STATUS_ARRIVED_BUT_NOT_DELIVERIED+"'"
					+ " ORDER BY mo.O_DueDate ASC";
			List<String> lstOrDate = getSession().createQuery(sql).list();
			//System.out.println(name()+"::getListDueDate--lstOrDate: "+lstOrDate.toString());
			commit();
			
			return lstOrDate;
			
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
	public List<mOrders> getListOrderByDueDate(String DueDate) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mOrders.class);
			criteria.add(Restrictions.eq("O_DueDate", DueDate));
			
			Criterion status1 = Restrictions.eq("O_Status_Code", Constants.ORDER_STATUS_ARRIVED_BUT_NOT_DELIVERIED);
			Criterion status2 = Restrictions.eq("O_Status_Code", Constants.ORDER_STATUS_NOT_IN_ROUTE);
			
			LogicalExpression orExp = Restrictions.or(status1, status2);
			
			criteria.add(orExp);
			
			List<mOrders> o= criteria.list();
			commit();
//			for(int i=0; i<o.size(); i++){
//				System.out.println(name()+"::getListOrderByDueDate--"+o.get(i).toString());
//			}
			return o;
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
	public List<mOrders> getListOrderByCUSCode(String cUSCode) {
		// TODO 
		return null;
	};
	
	@Override
	public void updateStatus(String order_Code,String status) {
		// TODO Auto-generated method stub
		try{
			begin();
			String hql = "UPDATE mOrders set O_Status_Code = :status "  + 
		             "WHERE O_Code = :order_Code";
			Query query = getSession().createQuery(hql);
			query.setParameter("status", status);
			query.setParameter("order_Code", order_Code);
			int result = query.executeUpdate();
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
	public List<mOrders> getListOrderByBatchCode(String batchCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mOrders.class);
			criteria.add(Restrictions.eq("O_BatchCode", batchCode));
	
			List<mOrders> o= criteria.list();
			commit();
//			for(int i=0; i<o.size(); i++){
//				System.out.println(name()+"::getListOrderByDueDate--"+o.get(i).toString());
//			}
			return o;
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
	public void updateOrderBatch( String O_Code, String O_BatchCode) {
		try{
			begin();
			String hql = "UPDATE mOrders set O_BatchCode = :O_BatchCode "  + 
		             "WHERE O_Code = :O_Code";
			Query query = getSession().createQuery(hql);
			query.setParameter("O_BatchCode", O_BatchCode);
			query.setParameter("O_Code", O_Code);
			int result = query.executeUpdate();
			System.out.println("Order Rows affected: " + result);
			commit();
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
		}finally{
			flush();
			close();
		}
	};
	
	@Override
	public void deleteOrder(String batchCode) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			List<mOrders> lstor = getSession().createCriteria(mOrders.class).add(Restrictions.eq("O_BatchCode", batchCode)).list();
			if(lstor != null){
				for(mOrders or : lstor){
					getSession().delete(or);
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
	public List<sOrder> staticsOrders(String from, String to, String type, String status, String cus_Code) {
		Transaction tx = null;
		try {
			tx = getSession().beginTransaction();
			String sQuery = "";
			if(type.equals("day")) {
				sQuery = "SELECT DATE_FORMAT(DATE(O_DueDate), '%d-%m-%Y') date, SUM(O_Price) total FROM slp.tblorders WHERE (O_BatchCode LIKE :C_Code) AND O_Status_Code IN (" +status+ ") AND DATE(O_DueDate) >= DATE( :from ) AND DATE(O_DueDate) <= DATE( :to ) GROUP BY DATE_FORMAT(DATE(O_DueDate), '%d-%m-%Y')";
			} else {
				if(type.equals("week")) {
					sQuery = "SELECT concat(substr(YEARWEEK(O_DueDate), 5,2), \"-\", substr(YEARWEEK(O_DueDate),1,4)) date, SUM(O_Price) total FROM slp.tblorders WHERE (O_BatchCode LIKE :C_Code) AND O_Status_Code IN (" +status+ ") AND DATE(O_DueDate) >= DATE( :from ) AND DATE(O_DueDate) <= DATE( :to ) GROUP BY YEARWEEK(O_DueDate) ";
				} else {
					if(type.equals("month")) {
						sQuery = "SELECT DATE_FORMAT(DATE(O_DueDate), '%m-%Y\') date, SUM(O_Price) total FROM slp.tblorders WHERE (O_BatchCode LIKE :C_Code) AND O_Status_Code IN (" +status+ ") AND DATE(O_DueDate) >= DATE( :from ) AND DATE(O_DueDate) <= DATE( :to ) GROUP BY DATE_FORMAT(DATE(O_DueDate), '%m-%Y\')";
					} else {
						sQuery = "SELECT DATE_FORMAT(DATE(O_DueDate), '%Y') date, SUM(O_Price) total FROM slp.tblorders WHERE (O_BatchCode LIKE :C_Code) AND O_Status_Code IN (" +status+ ") AND DATE(O_DueDate) >= DATE( :from ) AND DATE(O_DueDate) <= DATE( :to ) GROUP BY DATE_FORMAT(DATE(O_DueDate), '%Y')";
					}
				}
			}
			
			SQLQuery query = getSession().createSQLQuery(sQuery);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setParameter("from", from);
			query.setParameter("to", to);
			query.setParameter("C_Code", "%" + cus_Code + "%");
			List data = query.list();
			List<sOrder> lstStaticsOrder = new ArrayList<sOrder>();
			for(int i=0; i<data.size(); i++){
				Map tmp = (Map) data.get(i);
				sOrder temp = new sOrder();
				temp.setDate(tmp.get("date").toString());
				temp.setTotal(Float.parseFloat(tmp.get("total").toString()));
				lstStaticsOrder.add(temp);
			}
			
	        commit();
	        
	        return lstStaticsOrder;
	        
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
}
