package com.niit.collabrationbackend.Dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.niit.collabrationbackend.Model.Blog;

@EnableTransactionManagement
@Repository("blogDao")
public class BlogDaoImpl implements BlogDao {

	private static final Logger log = LoggerFactory.getLogger(BlogDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public BlogDaoImpl(SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}	
	
	@Override
	@Transactional
	public boolean saveBlog(Blog blog) {
		try {
			log.debug("Starting Method saveBlog.");
				sessionFactory.getCurrentSession().save(blog);
			log.debug("Ending Method saveBlog");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in Method saveBlog:-"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateBlog(Blog blog) {
		try {
			log.debug("Starting Method updateBlog.");
				sessionFactory.getCurrentSession().update(blog);
			log.debug("Ending Method updateBlog");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in Method updateBlog:-"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	
	@Override
	@Transactional
	public boolean removeBlog(String blogId) {
		try {
			log.debug("Starting Method removeBlog.");
				sessionFactory.getCurrentSession().createQuery("Update Blog set blogStatus = 0 where blogId = '"+blogId+"'").executeUpdate();
			log.debug("Blog removed with Id:-"+blogId);
			log.debug("Ending Method removeBlog.");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in removeBlog with (id = '"+blogId+"') "+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public List<Blog> blogListByUserId(String userId) {
		try {
			log.debug("Starting of Method blogListByUserId");
			Query query = sessionFactory.getCurrentSession().createQuery("FROM Blog WHERE userId = '"+userId+"'");
			log.debug("Starting of get BlogList");
			@SuppressWarnings("unchecked")
			List<Blog> list = query.list();
			if(list==null || list.isEmpty()){
				log.debug("No Blog's are Availible");
			}
		log.debug("Ending of Method blogListByUserId");
		return list;
		}catch (HibernateException e) {
			log.error("Error Occured in Method blogListByUserId :-"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public List<Blog> blogListForApproval() {
		try {
			log.debug("Starting of Method blogListForApproval");
			Query query = sessionFactory.getCurrentSession().createQuery("FROM Blog WHERE approvalStatus = 'P'");
			log.debug("Starting of get BlogList");
			@SuppressWarnings("unchecked")
			List<Blog> list = query.list();
			if(list==null || list.isEmpty()){
				log.debug("No Blog's Approval are Pending");
			}
		log.debug("Ending of Method blogListForApproval");
		return list;
		}catch (HibernateException e) {
			log.error("Error Occured in Method blogListForApproval :-"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public Blog getBlogById(String blogId,String status) {
		try {
			log.debug("Staring of Method getBlogById with blogId :- "+blogId);
			Query query = sessionFactory.getCurrentSession().createQuery("FROM Blog WHERE blogId = '"+blogId+"' AND blogStatus = '"+status+"'");
			@SuppressWarnings("unchecked")
			List<Blog> blogList = query.list();
			if(blogList != null && !blogList.isEmpty()){
				log.debug("Record Found in method getBlogById with id ="+blogId);
				return blogList.get(0);
			}else{
				log.debug("No Record Found in getBlogById with id ="+blogId);
				return null;
			}
	} catch (HibernateException e) {
		log.error("Error Occures in getBlogById Method..!! (id = '"+blogId+"')");
		e.printStackTrace();
		return null;
	}
	}
	
/*	@Override
	@Transactional
	public List<Blog> getBlogById(String blogId) {
		try {
			log.debug("Staring of Method getBlogById with blogId :- "+blogId);
			Query query = sessionFactory.getCurrentSession().createQuery("FROM Blog WHERE blogId = '"+blogId+"' AND blogStatus = 1");
			@SuppressWarnings("unchecked")
			List<Blog> blogList = query.list();
			if(blogList != null && !blogList.isEmpty()){
				log.debug("Record Found in method getBlogById with id ="+blogId);
				return blogList;
			}else{
				log.debug("No Record Found in getBlogById with id ="+blogId);
				return null;
			}
	} catch (HibernateException e) {
		log.error("Error Occures in getBlogById Method..!! (id = '"+blogId+"')");
		e.printStackTrace();
		return null;
	}
	}*/


	@Override
	@Transactional
	public List<Blog> getAllBlogs() {
		try {
			log.debug("Starting of Method getAllBlogs");
			Query query = sessionFactory.getCurrentSession().createQuery("FROM Blog WHERE blogStatus = 1");
			log.debug("Starting of get BlogList");
			@SuppressWarnings("unchecked")
			List<Blog> list = query.list();
			if(list==null || list.isEmpty()){
				log.debug("No Blog's Are Available");
			}
		log.debug("Ending of Method getAllBlogs");
		return list;
		}catch (HibernateException e) {
			log.error("Error Occured in Method getAllBlogs :-"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public boolean approveBlog(String blogId,String status) {
		char blogStatus;
		try {
			log.debug("Starting Method approveBlog.");
			if(status.equals("A")){
				blogStatus = '1';
			}else{
				blogStatus = '0';
			}
				sessionFactory.getCurrentSession().createQuery("Update Blog set blogStatus = '"+blogStatus+"' , approvalStatus = '"+status+"' where blogId = '"+blogId+"'").executeUpdate();
			log.debug("Blog approved with Id:-"+blogId);
			log.debug("Ending Method approveBlog.");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in approveBlog with (id = '"+blogId+"') "+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}
