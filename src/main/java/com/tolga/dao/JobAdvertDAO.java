package com.tolga.dao;


import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.tolga.model.Company;
import com.tolga.model.JobAdvert;

public class JobAdvertDAO {
	private static Datastore datastore;

	public JobAdvertDAO(Datastore datastore) {
		if (JobAdvertDAO.datastore == null) {
			JobAdvertDAO.datastore = datastore;
		}
	}

	public JobAdvertDAO() {

	}

	public void init() {

	}

	public void addJobAdvert(JobAdvert jobAdvert) {
		JobAdvertDAO.datastore.save(jobAdvert);
	}

	public JobAdvert findJobAdvert(ObjectId _id) {
		final JobAdvert jobAdvert = datastore.find(JobAdvert.class, "id", _id).get();
		return jobAdvert;
	}

	public List<JobAdvert> getAllJobAdverts() {
		final Query<JobAdvert> query = datastore.createQuery(JobAdvert.class);
		final List<JobAdvert> jobAdverts = query.asList();
		return jobAdverts;
	}

	public void updateJobAdvert(JobAdvert jobAdvert) {
		datastore.save(jobAdvert);

	}

	public List<JobAdvert> getJobAdvertsOfCompany(Company company) {
		final Query<JobAdvert> query = datastore.createQuery(JobAdvert.class).field("company").equal(company);
		final List<JobAdvert> jobAdverts = query.asList();
		return jobAdverts;
	}

	public void deleteJobAdvert(JobAdvert jobAdvert) {
		final Query<JobAdvert> query = datastore.createQuery(JobAdvert.class).field("_id").equal(jobAdvert.getId());
		datastore.delete(query);
	}

	public List<JobAdvert> searchByTitle(String searchTitle) {
		List<JobAdvert> adverts = datastore.createQuery(JobAdvert.class).search(searchTitle).order("company").asList();
		List<Company> companies = datastore.createQuery(Company.class).search(searchTitle).order("companyName").asList();
		for(Company c: companies) {
			adverts.addAll(c.getJobAdverts());
		}
		return adverts;
	}

	public static Datastore getDatastore() {
		return datastore;
	}
}
