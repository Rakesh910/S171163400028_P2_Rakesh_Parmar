package com.niit.collabrationbackend.Dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collabrationbackend.Model.Friend;

@EnableTransactionManagement
@Repository("friendDao")
public class FriendDaoImpl implements FriendDao {
	private static final Logger log = LoggerFactory.getLogger(FriendDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public FriendDaoImpl(SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Friend> getMyFriends(String userId) {

		return null;
	}

	@Override
	public Friend get(String userId, String friendId) {

		return null;
	}

	@Override
	public boolean save(Friend friend) {

		return false;
	}

	@Override
	public boolean update(Friend friend) {

		return false;
	}

	@Override
	public void remove(String userId, String friendId) {

		
	}

	@Override
	public List<Friend> getNewFriendRequests(String userId) {

		return null;
	}

	@Override
	public void setOnLine(String userId) {

		
	}

	@Override
	public void setOffLine(String userId) {

		
	}
}
