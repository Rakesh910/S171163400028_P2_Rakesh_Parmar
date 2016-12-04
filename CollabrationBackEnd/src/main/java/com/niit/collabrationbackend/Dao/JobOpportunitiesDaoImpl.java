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

import com.niit.collabrationbackend.Model.AppliedJobs;
import com.niit.collabrationbackend.Model.JobOpportunities;

@EnableTransactionManagement
@Repository("jobDao")
public class JobOpportunitiesDaoImpl implements JobOpportunitiesDao{

	private static final Logger log = LoggerFactory.getLogger(JobOpportunitiesDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public JobOpportunitiesDaoImpl(SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public boolean saveJob(JobOpportunities job) {
		try {
			log.debug("Starting Method saveJob.");
				sessionFactory.getCurrentSession().save(job);
			log.debug("Ending Method saveJob");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in Method saveJob:-"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	@Transactional
	public boolean updateJob(JobOpportunities job) {
		try {
			log.debug("Starting Method updateJob.");
				sessionFactory.getCurrentSession().update(job);
			log.debug("Ending Method updateJob");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in Method updateJob:-"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean removeJob(String jobId) {
		try {
			log.debug("Starting Method removeJob.");
				sessionFactory.getCurrentSession().createQuery("Update JobOpportunities set jobStatus = 0 where jobId = '"+jobId+"'").executeUpdate();
			log.debug("Job removed with Id:-"+jobId);
			log.debug("Ending Method removeJob.");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in removeJob with (id = '"+jobId+"') "+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public List<JobOpportunities> getAllJobList() {
		try {
			log.debug("Starting of Method getAllJobList");
			Query query = sessionFactory.getCurrentSession().createQuery("FROM JobOpportunities");
			log.debug("Starting of get Job List");
			@SuppressWarnings("unchecked")
			List<JobOpportunities> list = query.list();
			if(list==null || list.isEmpty()){
				log.debug("No Job's are Availible");
			}
		log.debug("Ending of Method getAllJobList");
		return list;
		}catch (HibernateException e) {
			log.error("Error Occured in Method getAllJobList :-"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public JobOpportunities getJobById(String jobId) {
		try {
			log.debug("Staring of Method getJobById with jobId :- "+jobId);
			Query query = sessionFactory.getCurrentSession().createQuery("FROM JobOpportunities WHERE jobId = '"+jobId+"' AND jobStatus = 1");
			@SuppressWarnings("unchecked")
			List<JobOpportunities> jobList = query.list();
			if(jobList != null && !jobList.isEmpty()){
				log.debug("Record Found in method getJobById with eventId ="+jobId);
				return jobList.get(0);
			}else{
				log.debug("No Record Found in getJobById with eventId ="+jobId);
				return null;
			}
	} catch (HibernateException e) {
		log.error("Error Occures in getJobById Method..!! (eventId = '"+jobId+"')");
		e.printStackTrace();
		return null;
	}
	}

	@Override
	@Transactional
	public boolean applyJob(AppliedJobs job) {
		try {
			log.debug("Starting Method applyJob.");
				sessionFactory.getCurrentSession().save(job);
			log.debug("Ending Method applyJob");
			return true;
		} catch (HibernateException e) {
			log.error("Error Occured in Method applyJob:-"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public List<AppliedJobs> getMyAppliedJobs(String userId) {
		try {
			log.debug("Starting of Method getMyAppliedJobs");
			Query query = sessionFactory.getCurrentSession().createQuery("FROM AppliedJobs WHERE userId = '"+userId+"'");
			log.debug("Starting of getMyAppliedJob List");
			@SuppressWarnings("unchecked")
			List<AppliedJobs> list = query.list();
			if(list==null || list.isEmpty()){
				log.debug("No Job's are Availible");
			}
		log.debug("Ending of Method getMyAppliedJobs");
		return list;
		}catch (HibernateException e) {
			log.error("Error Occured in Method getMyAppliedJobs :-"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
