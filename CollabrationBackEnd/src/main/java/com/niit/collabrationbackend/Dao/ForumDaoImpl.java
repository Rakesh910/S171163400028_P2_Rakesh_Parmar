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

import com.niit.collabrationbackend.Model.Forum;
import com.niit.collabrationbackend.Model.ForumComment;

@EnableTransactionManagement
@Repository("forumDao")
public class ForumDaoImpl implements ForumDao {

	private static final Logger log = LoggerFactory.getLogger(ForumDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumDaoImpl(SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}	
	
	@Override
	@Transactional
	public boolean saveForum(Forum forum) {
		try {
			log.debug("Starting Method saveForum.");
				sessionFactory.getCurrentSession().save(forum);
			log.debug("Ending Method saveForum");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in Method saveForum:-"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public List<Forum> getAllForums() {
		try {
			log.debug("Starting of Method getAllForums");
			Query query = sessionFactory.getCurrentSession().createQuery("FROM Forum WHERE forumStatus = 1");
			log.debug("Starting of get ForumList");
			@SuppressWarnings("unchecked")
			List<Forum> list = query.list();
			if(list==null || list.isEmpty()){
				log.debug("No Forum's Are Available");
			}
		log.debug("Ending of Method getAllForums");
		return list;
		}catch (HibernateException e) {
			log.error("Error Occured in Method getAllForums :-"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public Forum getForumById(String forumId) {
		try {
			log.debug("Staring of Method getForumById with forumId :- "+forumId);
			Query query = sessionFactory.getCurrentSession().createQuery("FROM Forum WHERE forumId = '"+forumId+"' AND forumStatus = '1'");
			@SuppressWarnings("unchecked")
			List<Forum> forumList = query.list();
			if(forumList != null && !forumList.isEmpty()){
				log.debug("Record Found in method getForumById with id ="+forumId);
				return forumList.get(0);
			}else{
				log.debug("No Record Found in getForumById with id ="+forumId);
				return null;
			}
	} catch (HibernateException e) {
		log.error("Error Occures in getForumById Method..!! (id = '"+forumId+"')");
		e.printStackTrace();
		return null;
	}
	}

	@Override
	@Transactional
	public boolean forumlikes(String forumId) {
		try {
			log.debug("Starting Method forumlikes.");
				sessionFactory.getCurrentSession().createQuery("Update Forum set forumLike = forumLike+1 where forumId = '"+forumId+"'").executeUpdate();
			log.debug("Blog removed with Id:-"+forumId);
			log.debug("Ending Method forumlikes.");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in forumlikes with (id = '"+forumId+"') "+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean forumdislikes(String forumId) {
		try {
			log.debug("Starting Method forumdislikes.");
				sessionFactory.getCurrentSession().createQuery("Update Forum set forumDislike = forumDislike+1 where forumId = '"+forumId+"'").executeUpdate();
			log.debug("Blog removed with Id:-"+forumId);
			log.debug("Ending Method forumdislikes.");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in forumdislikes with (id = '"+forumId+"') "+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean saveForumComment(ForumComment forumComment) {
		try {
			log.debug("Starting Method saveForum.");
				sessionFactory.getCurrentSession().save(forumComment);
			log.debug("Ending Method saveForum");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in Method saveForum:-"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public List<ForumComment> getAllForumComment(String forumId) {
		try {
			log.debug("Starting of Method getAllForumComment");
			Query query = sessionFactory.getCurrentSession().createQuery("FROM ForumComment WHERE forumId = '"+forumId+"'");
			log.debug("Starting of get ForumCommentList");
			@SuppressWarnings("unchecked")
			List<ForumComment> list = query.list();
			if(list==null || list.isEmpty()){
				log.debug("No Forum Comment's Are Available");
			}
		log.debug("Ending of Method getAllForumComment");
		return list;
		}catch (HibernateException e) {
			log.error("Error Occured in Method getAllForumComment :-"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
