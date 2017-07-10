package com.niit.dao;

import java.util.List;

import com.niit.model.Job;

public interface JobDao {

	Job getJobById(int id);

	List<Job> getAllJobDetails();

	void saveJobDetails(Job job);

}
