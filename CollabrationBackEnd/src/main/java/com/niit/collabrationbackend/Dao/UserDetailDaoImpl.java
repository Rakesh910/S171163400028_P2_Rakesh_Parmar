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
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDetailDaoImpl(SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<UserDetail> getAllUsers() {
		try {
			log.debug("**********Starting of Method getAllUsers.**********");
				Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDetail WHERE accountStatus = 1");
				log.debug("**********Starting of get UsersList.**********");
				@SuppressWarnings("unchecked")
				List<UserDetail> list = query.list();
				if(list==null || list.isEmpty()){
					log.debug("**********No User's are Availible.**********");
				}
			log.debug("**********Ending of Method getAllUsers.**********");
			log.debug("**********Number of Records Found :-" + list.size()+".**********");
			return list;
		}catch (HibernateException e) {
			log.error("**********Error Occured in Method getAllUsers :-"+e.getMessage()+".**********");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public List<UserDetail> getAllUsersForApproval() {
		try {
			log.debug("**********Starting of Method getAllUsersForApproval.**********");
				Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDetail WHERE approveStatus = 'P'");
				log.debug("**********Starting of get UsersList for Approval.**********");
				@SuppressWarnings("unchecked")
				List<UserDetail> list = query.list();
				if(list==null || list.isEmpty()){
					log.debug("**********No User's are Availible.**********");
				}
			log.debug("**********Ending of Method getAllUsersForApproval.**********");
			log.debug("**********Number of Records Found :-" + list.size()+".**********");
			return list;
		}catch (HibernateException e) {
			log.error("**********Error Occured in Method getAllUsersForApproval :-"+e.getMessage()+".**********");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public boolean saveUser(UserDetail userDetail) {
		try {
			log.debug("Starting Method saveUser.");
				sessionFactory.getCurrentSession().save(userDetail);
			log.debug("Ending Method saveUser");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in Method saveUser:-"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateUser(UserDetail userDetail) {
		try {
			log.debug("Starting Method updateUser.");
				sessionFactory.getCurrentSession().update(userDetail);
				sessionFactory.getCurrentSession().flush();
			log.debug("Ending Method updateUser");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in Method updateUser:-"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteUser(String userId) {
		try {
			log.debug("Starting Method deleteUser.");
				sessionFactory.getCurrentSession().createQuery("Update UserDetail set accountStatus = 0 where userId = '"+userId+"'").executeUpdate();
			log.debug("UserDetail removed with Id:-"+userId);
			log.debug("Ending Method deleteUser.");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in deleteUser with (id = '"+userId+"') "+e.getMessage());
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	@Transactional
	public UserDetail isValidUser(String email, String password) {
		try {
			log.debug("**********Starting of Method isValidUser.**********");
				Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDetail WHERE emailId = '"+email+"' AND password = '"+password+"' AND accountStatus = 1");
				log.debug("**********Starting of get UsersList.**********");
				@SuppressWarnings("unchecked")
				List<UserDetail> list = query.list();
				if(list != null && !list.isEmpty()){
					log.debug("**********Ending of Method isValidUser.**********");
					return list.get(0);
				}else{
					log.debug("**********No User's are Availible.**********");
					log.debug("**********Ending of Method isValidUser.**********");
					return null;
				}
		}catch (HibernateException e) {
			log.error("**********Error Occured in Method isValidUser :-"+e.getMessage()+".**********");
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	@Transactional
	public UserDetail userGetById(String userId) {
		try {
			log.debug("**********Starting of Method userGetById :- "+userId+".**********");
				Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDetail WHERE userId='"+userId+"' AND accountStatus = '1'");
				log.debug("**********Starting of get UsersList.**********");
				@SuppressWarnings("unchecked")
				List<UserDetail> list = query.list();
				if(list==null || list.isEmpty()){
					log.debug("**********No User's are Availible.**********");
					return null;
				}else{
					log.debug("**********Ending of Method userGetById.**********");
					return list.get(0);
				}
		}catch (HibernateException e) {
			log.error("**********Error Occured in Method userGetById :-"+e.getMessage()+".**********");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public boolean approveUser(String userId, String status) {
		char accountStatus;
		try {
			log.debug("Starting Method approveUser.");
			if(status.equals("A")){
				accountStatus = '1';
			}else{
				accountStatus = '0';
			}
				sessionFactory.getCurrentSession().createQuery("Update UserDetail set accountStatus = '"+accountStatus+"' , approveStatus = '"+status+"' where userId = '"+userId+"'").executeUpdate();
			log.debug("User approved with Id:-"+userId);
			log.debug("Ending Method approveUser.");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in approveBlog with (id = '"+userId+"') "+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public void setOnLine(String userId) {
		try {
			log.debug("**********Starting of Method setOnLine.With Id **********"+userId);
				sessionFactory.getCurrentSession().createQuery("UPDATE UserDetail SET is_Online = 'Y' WHERE userId = '"+userId+"'").executeUpdate();
				log.debug("**********Ending of Method setOnLine.**********");
		} catch (Exception e) {
				e.printStackTrace();
				log.debug("**********Error Occuring while SetOnline Finction..**********");
		}		
	}

	@Override
	@Transactional
	public void setOffLine(String userId) {
		try {
			log.debug("**********Starting of Method setOnLine. with Id : **********"+userId);
				sessionFactory.getCurrentSession().createQuery("UPDATE UserDetail SET is_Online = 'N' WHERE userId = '"+userId+"'").executeUpdate();
				log.debug("**********Ending of Method setOnLine.**********");
		} catch (Exception e) {
				e.printStackTrace();
				log.debug("**********Error Occuring while SetOnline Finction..**********");
		}
	}
}
