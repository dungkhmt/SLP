package com.kse.slp.modules.onlinestores.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.model.mArticlesCategory;

@Repository("mArticlesCategoryDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mArticlesCategoryDAOImpl extends BaseDao implements mArticlesCategoryDAO {

	@Override
	public List<mArticlesCategory> getList() {
		try {
	     	begin();
	     	Criteria criteria = getSession().createCriteria(mArticlesCategory.class);
	     	List<mArticlesCategory> articlesCategoryList = criteria.list();
	        commit();
	        return articlesCategoryList;
	     }catch (HibernateException e) {
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
