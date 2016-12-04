package com.niit.collabrationbackend.Dao;

import java.util.List;

import com.niit.collabrationbackend.Model.AppliedJobs;
import com.niit.collabrationbackend.Model.JobOpportunities;

public interface JobOpportunitiesDao {

	public boolean saveJob(JobOpportunities job);
	
	public boolean updateJob(JobOpportunities job);
	
	public boolean removeJob(String jobId);
	
	public List<JobOpportunities> getAllJobList();
	
	public boolean applyJob(AppliedJobs job);
	
	public JobOpportunities getJobById(String jobId);
	
	public List<AppliedJobs> getMyAppliedJobs(String userId);
}
