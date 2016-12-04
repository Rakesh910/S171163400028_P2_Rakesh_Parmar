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

import com.niit.collabrationbackend.Model.Friend;

@EnableTransactionManagement
@Repository("friendDao")
public class FriendDaoImpl implements FriendDao {
	private static final Logger log = LoggerFactory.getLogger(FriendDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public FriendDaoImpl(SessionFactory sessionFactory ) {
		try {
			this.sessionFactory = sessionFactory;
		} catch (Exception e) {
			log.error("**********Unable To Connect Database**********");
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public List<Friend> getMyFriends(String userId) {
		try {
			log.debug("**********Starting of Method getMyFriends.**********");
				Query query = sessionFactory.getCurrentSession().createQuery("FROM Friend WHERE friendId = '"+userId+"' OR userId = '"+userId+"' "); //AND friendStatus = '"+ " A'
				log.debug("**********Starting of get FriendsList.**********");
				@SuppressWarnings("unchecked")
				List<Friend> list = query.list();
				if(list==null || list.isEmpty()){
					log.debug("**********No Friends are Availible.**********");
				}
			log.debug("**********Ending of Method getMyFriends.**********");
			log.debug("**********Number of Records Found :-" + list.size()+".**********");
			return list;
		}catch (HibernateException e) {
			log.error("**********Error Occured in Method getMyFriends :-"+e.getMessage()+".**********");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public Friend get(String userId, String friendId) {
		try {
			log.debug("**********Starting of Method getFriendById.**********");
				Query query = sessionFactory.getCurrentSession().createQuery("FROM Friend WHERE userId='"+userId+"' AND friendId = '"+friendId+"'");
				log.debug("**********Starting of get FriendsList.**********");
				@SuppressWarnings("unchecked")
				List<Friend> list = query.list();
				if(list==null || list.isEmpty()){
					log.debug("**********No Friend are Availible.**********");
					return null;
				}else{
					log.debug("**********Ending of Method getFriendById.**********");
					return list.get(0);
				}
		}catch (HibernateException e) {
			log.error("**********Error Occured in Method getFriendById :-"+e.getMessage()+".**********");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public boolean save(Friend friend) {
		try {
			log.debug("**********Starting of Method Friend Save.**********");
				sessionFactory.getCurrentSession().save(friend);
				log.debug("**********Ending of Method Friend Save.**********");
				return true;
		} catch (Exception e) {
				e.printStackTrace();
				log.debug("**********Error Occuring while Save Friend.**********");
				return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Friend friend) {
		try {
			log.debug("**********Starting of Method Friend update.**********");
				sessionFactory.getCurrentSession().update(friend);
				log.debug("**********Ending of Method Friend update.**********");
				return true;
		} catch (Exception e) {
				e.printStackTrace();
				log.debug("**********Error Occuring while update Friend.**********");
				return false;
		}
	}

	@Override
	@Transactional
	public boolean remove(String userId, String friendId) {
		try {
			log.debug("**********Starting of Method remove Friend .**********");
				sessionFactory.getCurrentSession().createQuery("UPDATE Friend SET friendStatus = 'R' WHERE userId = '"+userId+"' AND friendId = '"+friendId+"'");
				log.debug("**********Ending of Method remove Friend.**********");
				return true;
		} catch (Exception e) {
				e.printStackTrace();
				log.debug("**********Error Occuring while remove Friend.**********");
				return false;
		}
		
	}

	@Override
	@Transactional
	public List<Friend> getNewFriendRequests(String userId) {
		try {
			log.debug("----->LOGGED IN USER :-"+userId);
			log.debug("**********Starting of Method getNewFriendRequests.**********");
				Query query = sessionFactory.getCurrentSession().createQuery("FROM Friend WHERE friendId = '"+userId+"' AND friendStatus = 'P'");
				log.debug("**********Starting of get New Friends Request.**********");
				@SuppressWarnings("unchecked")
				List<Friend> list = query.list();
				if(list==null || list.isEmpty()){
					log.debug("**********No New Friend Request are Availible.**********");
				}
			log.debug("**********Ending of Method getNewFriendRequests.**********");
			log.debug("**********Number of Records Found :-" + list.size()+".**********");
			return list;
		}catch (HibernateException e) {
			log.error("**********Error Occured in Method getMyFriends :-"+e.getMessage()+".**********");
			e.printStackTrace();
			return null;
		}
	}

	
}
