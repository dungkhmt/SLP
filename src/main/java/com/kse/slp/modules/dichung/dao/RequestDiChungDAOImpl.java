package com.kse.slp.modules.dichung.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.dichung.controller.DiChungControler;
import com.kse.slp.modules.dichung.model.RequestDiChung;
import com.kse.slp.modules.onlinestores.common.Constants;
@Repository("RequestDiChungDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class RequestDiChungDAOImpl extends BaseDao implements RequestDiChungDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
	public int saveARequest(RequestDiChung request) {
		try{
			begin();
			int id = 0;
			id = (int)getSession().save(request);
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
	public List<RequestDiChung> getList() {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RequestDiChung.class);
			
			
			List<RequestDiChung> listRequest = criteria.list();
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
	public void updateARequest(RequestDiChung request) {
		try {
	           begin();
	           getSession().update(request);
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
