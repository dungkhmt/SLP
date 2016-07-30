package com.kse.slp.modules.onlinestores.modules.incomingarticles.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mIncomingArticles;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mSuppliers;

@Repository("mIncomingArticlesDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mIncomingArticlesDAOImpl extends BaseDao implements mIncomingArticlesDAO {

	@Override
	public List<mIncomingArticles> getList() {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mIncomingArticles.class);
			List<mIncomingArticles> incomingArticleList = criteria.list();
			commit();
			
			return incomingArticleList;
			
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
	public List<mIncomingArticles> getOrderedListByDate() {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mIncomingArticles.class);
			criteria.addOrder(Order.desc("IA_Date"));
			List<mIncomingArticles> inArts = criteria.list(); 
			commit();
			return inArts;
			
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
	public int saveAIncomingArticle(mIncomingArticles article) {
		// TODO Auto-generated method stub
		try {
	           begin();
	           int id = 0; 
	           id = (int)getSession().save(article);
	           commit();
	           return id;           
	        } catch (HibernateException e) {
	            e.printStackTrace();
	            rollback();
	            close();
	            return 0;
	        } finally {
	            flush();
	            close();
	        }
	}
}
