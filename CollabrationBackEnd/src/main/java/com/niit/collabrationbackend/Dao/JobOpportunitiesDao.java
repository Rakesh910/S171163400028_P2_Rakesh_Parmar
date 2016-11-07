package com.niit.collabrationbackend.Dao;

import java.util.List;

import com.niit.collabrationbackend.Model.JobOpportunities;

public interface JobOpportunitiesDao {

	public boolean saveJob(JobOpportunities job);
	
	public boolean updateJob(JobOpportunities job);
	
	public boolean removeJob(String jobId);
	
	public List<JobOpportunities> getAllJobList();
	
	public JobOpportunities getJobById(String jobId);
}
