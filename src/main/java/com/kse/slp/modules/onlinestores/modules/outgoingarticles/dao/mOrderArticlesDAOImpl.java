package com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderArticles;

@Repository("mOrderArticlesDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mOrderArticlesDAOImpl extends BaseDao implements mOrderArticlesDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	@Override
	public int saveAOrderArticles(mOrderArticles oa) {
		try{
			begin();
			int id = 0;
			id = (int)getSession().save(oa);
			commit();
			return 0;
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
	public void deletOrderArticles(String orderCode) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			List<mOrderArticles> lstor = getSession().createCriteria(mOrderArticles.class).add(Restrictions.eq("OA_OrderCode", orderCode)).list();
			if(lstor != null){
				for(mOrderArticles or : lstor){
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

}
