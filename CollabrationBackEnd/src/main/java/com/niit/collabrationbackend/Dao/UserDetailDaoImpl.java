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

import com.niit.collabrationbackend.Model.UserDetail;

@EnableTransactionManagement
@Repository("userDetailDao")
public class UserDetailDaoImpl implements UserDetailDao {
	
	private static final Logger log = LoggerFactory.getLogger(UserRoleDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDetailDaoImpl(SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public boolean saveOrUpdateUserDetail(UserDetail userDetail) {
		try {
			log.debug("Starting Method saveOrUpdateUserDetail.");
				sessionFactory.getCurrentSession().saveOrUpdate(userDetail);
			log.debug("Ending Method saveOrUpdateUserDetail");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in Method saveOrUpdateUserDetail:-"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean removeUserDetail(int id) {
		try {
			log.debug("Starting Method removeUserRole.");
				sessionFactory.getCurrentSession().createQuery("Update UserRole set status = 0 where roleId = '"+id+"'").executeUpdate();
			log.debug("UserRole removed with Id:-"+id);
			log.debug("Ending Method removeUserRole.");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in removeUserRole with (id = '"+id+"') "+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public UserDetail userGetById(String userId) {
		try {
				log.debug("Staring of Method userGetById with UserId :- "+userId);
				Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDetail WHERE userId = '"+userId+"' AND accountStatus = '1' ");
				@SuppressWarnings("unchecked")
				List<UserDetail> userList = query.list();
				if(userList != null && !userList.isEmpty()){
					log.debug("Record Found in method userGetById with id ="+userId);
					return userList.get(0);
				}else{
					log.debug("No Record Found in userGetById with id ="+userId);
					return null;
				}
		} catch (HibernateException e) {
			log.error("Error Occures in userGetById Method..!! (id = '"+userId+"')");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public UserDetail userGetByEmail(String email) {
		try {
			log.debug("Staring of Method userGetByEmail with UserId :- "+email);
			Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDetail WHERE emailId = '"+email+"' AND accountStatus = '1' ");
			@SuppressWarnings("unchecked")
			List<UserDetail> userList = query.list();
			if(userList != null && !userList.isEmpty()){
				log.debug("Record Found in method userGetByEmail with Email_id ="+email);
				return userList.get(0);
			}else{
				log.debug("No Record Found in userGetByEmail with Email_id ="+email);
				return null;
			}
	} catch (HibernateException e) {
		log.error("Error Occures in userGetByEmail Method..!! (Email_id = '"+email+"')");
		e.printStackTrace();
		return null;
	}
	}

	@Override
	@Transactional
	public UserDetail isValidUser(String email, String password) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDetail WHERE emailId = '"+email+"' AND password = '"+password+"'");
			@SuppressWarnings("unchecked")
			List<UserDetail> list = query.list();
			if(list == null || list.isEmpty()){
				return null;
			}else{
				return list.get(0);
			}
		} catch (HibernateException e) {
			log.error("Error Occures in isValidUser Method..!!");
			e.printStackTrace();
			return null;
		}	
	}

}
