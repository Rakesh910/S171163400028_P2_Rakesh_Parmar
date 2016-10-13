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

import com.niit.collabrationbackend.Model.UserRole;

@EnableTransactionManagement
@Repository("userRoleDao")
public class UserRoleDaoImpl implements UserRoleDao {
	
	private static final Logger log = LoggerFactory.getLogger(UserRoleDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public UserRoleDaoImpl(SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	@Transactional
	public List<UserRole> getAllUserRoles() {
		try {
			log.debug("Starting of Method getAllUserRoles");
				Query query = sessionFactory.getCurrentSession().createQuery("FROM UserRole WHERE status = 1");
				log.debug("Starting of get UserRole List");
				@SuppressWarnings("unchecked")
				List<UserRole> list = query.list();
				if(list==null || list.isEmpty()){
					log.debug("No UserRoles are Availible");
				}
			log.debug("Ending of Method getAllUserRoles");
			return list;
		}catch (HibernateException e) {
			log.error("Error Occured in Method getAllUserRoles :-"+e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	@Transactional
	public boolean userRoleSaveOrUpdate(UserRole userRole) {
		try {
			log.debug("Starting Method UserRoleSaveOrUpdate.");
				sessionFactory.getCurrentSession().saveOrUpdate(userRole);
			log.debug("Ending Method UserRoleSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in Method UserRoleSaveOrUpdate:-"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean removeUserRole(int id) {
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
}
