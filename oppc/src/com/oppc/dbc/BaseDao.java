package com.oppc.dbc;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;







import com.oppc.user.entity.MenuEntity;
import com.oppc.user.entity.MenuPEntity;


/**
 * @author Administrator
 * 这是进行数据库连接的基本类，每个dao类都实现它
 * 由于hibernate4的改变HibernateDao,hibernateTemplate都不能使用了，只能用最原始的方法session
 * @param <T>
 */
public class BaseDao<T extends java.io.Serializable, F extends java.io.Serializable> {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 初始化Log4j的一个实例
	 */
	private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);
	
	/**
	 *  获取<E>的类型 class[0]表示获取泛型E的类型［1］为泛型F的类型
	 */
	private Class<T> entityClass = (Class<T>) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	
	/**
	 * 获取hiberante session,事务必须是开启的(Required)，否则获取不到
	 * @return
	 */
	 public synchronized Session getSession() {
		if(logger.isDebugEnabled()){
			logger.debug("获取数据库连接");
		}
		return sessionFactory.getCurrentSession();
	}
	 
	 
	 
	 /**
	  * 根据传入的实体持久化对象
	  * @param entity 实体对象
	  */
	 public F save(T entity) {
		try {
			F f=(F) getSession().save(entity);
			getSession().flush();
			getSession().evict(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("保存实体成功," + entity.getClass().getName());
			}
			return f;
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("保存实体异常", e);
			throw e;
		}
	 }
	
	/**
	 * 根据传入的实体添加或更新对象
	 * @param entity　实体对象
	 */
	public void saveOrUpdate(T entity) {
		try {
			getSession().merge(entity);
			getSession().flush();
			getSession().evict(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("添加或更新成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("添加或更新异常", e);
			throw e;
		}
	}
	/**
	 * 根据传入的hsql查询对象，并返回结果
	 * @param hsql hsql查询语句
	 * @param params map查询参数
	 * @param objs 普通查询参数
	 * @return
	 */
	public List getAllByHSql(String hsql,Map<String,Object> params,Object... objs){
		if(StringUtils.isBlank(hsql)){
			return null;
		}
		Query query=this.getSession().createQuery(hsql);
		query.setMaxResults(2000);
		if(null!=params){
			for(String key:params.keySet()){
				query.setParameter(key, params.get(key));
			}
		}
		if(null!=objs){
			for(int i=0;i<objs.length;i++){
				query.setParameter(i, objs[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * 根据传入的hsql查询对象，并返回结果
	 * @param hsql
	 * @return
	 */
	public Object getSignleByHSql(String sql,Object... objs){
		if(StringUtils.isBlank(sql)){
			return null;
		}
		Query query=this.getSession().createQuery(sql);
		if(null!=objs){
			for(int i=0;i<objs.length;i++){
				query.setParameter(i, objs[i]);
			}
		}
		query.setMaxResults(1);
		List list=query.list();
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据传入的sql查询对象，并返回结果
	 * @param sql
	 * @return
	 */
	public List getAllBySql(String sql){
		
		if(StringUtils.isBlank(sql)){
			return null;
		}

		SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(MenuPEntity.class));

		

		return sqlQuery.list();
	}
	
}
