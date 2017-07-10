package com.niit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Job;
@Repository
public class JobDaoImpl implements JobDao{
@Autowired
private SessionFactory sessionfactory;

public void saveJobDetails(Job job) {
	Session session=sessionfactory.openSession();
	Transaction trans=session.beginTransaction();
	session.save(job);
	session.flush();
	trans.commit();
	session.close();
		
	}


public List<Job> getAllJobDetails() {
	Session session=sessionfactory.openSession();
	Transaction trans=session.beginTransaction();
	Query query=session.createQuery("from Job");
	List<Job> jobs=query.list();
	trans.commit();
	session.close();
	return jobs;
	
}



	public Job getJobById(int id) {
		Session session=sessionfactory.openSession();
		Transaction trans=session.beginTransaction();
		Job job=(Job)session.get(Job.class, id);
		trans.commit();
		session.close();
		return job;
	
	}


	


	

}
