package com.niit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.ProfilePicture;
@Repository
public class ProfileUploadDaoImpl implements ProfileUploadDao{
	@Autowired
private SessionFactory sessionFactory;
	public void save(ProfilePicture profilePicture) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		session.saveOrUpdate(profilePicture);
		session.flush();
		trans.commit();
		session.close();
		
	}
	public ProfilePicture getProfilePic(String username) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		ProfilePicture profilePic=(ProfilePicture)
		session.get(ProfilePicture.class, username);
		trans.commit();
		session.close();
		return profilePic;
	}

}