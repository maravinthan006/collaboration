package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Friend;
import com.niit.model.User;
@Repository
public class FriendDaoImpl implements FriendDao{
	@Autowired
private SessionFactory sessionFactory;
	
	
	
	public List<User> getSuggestedUsers(User user) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		SQLQuery query=session.createSQLQuery("select * from Register where username in (select username from Register where username!=? minus (select from_id from Friend where to_id=?"
				+ "union select to_id from Friend where from_id=?"
				+ "))");
		query.setString(0, user.getUsername());
		query.setString(1, user.getUsername());
		query.setString(2, user.getUsername());
		query.addEntity(User.class);
		List<User> users=query.list();
		trans.commit();
		session.close();
		return users;
	}

	
	public void friendRequest(String from, String to){
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		Friend friend =new Friend();
		friend.setFrom(from);
		friend.setTo(to);
		friend.setStatus('P');
		session.save(friend);
		session.flush();
		trans.commit();
		session.close();
	}


	public List<Friend> pendingRequests(String toUsername) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		Query query=session.createQuery("from Friend where to=? and status=?");
		query.setString(0, toUsername);
		query.setCharacter(1, 'P');
		List<Friend> pendingRequests=query.list();
		trans.commit();
		session.close();
		return pendingRequests;
	}


	public void updatePendingRequest(String from, String username, char status) {
		Session session=sessionFactory.openSession();
		SQLQuery query=session.createSQLQuery("update Friend set status=? where from_id=? and to_id=?");
		Transaction trans=session.beginTransaction();
		query.setCharacter(0, status);
		query.setString(1,from );
		query.setString(2, username);
		int count=query.executeUpdate();
		System.out.println("Number of records updated " + count);
		session.flush();
		trans.commit();
		session.close();
	}
	
	
	public List<Friend> listOfFriends(String username){
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		SQLQuery query=session.createSQLQuery("select * from Friend where (from_id=? or to_id=?) and status=?");
		query.setString(0, username);
		query.setString(1, username);
		query.setCharacter(2, 'A');
		query.addEntity(Friend.class);
		List<Friend> friends=query.list();
		trans.commit();
		session.close();
		return friends;
	}
	

}