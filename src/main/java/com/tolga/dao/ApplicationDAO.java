package com.tolga.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.tolga.model.Application;
import com.tolga.model.Company;
import com.tolga.model.JobAdvert;
import com.tolga.model.User;

public class ApplicationDAO {
	private static Datastore datastore;

	public ApplicationDAO(Datastore datastore) {
		if (ApplicationDAO.datastore == null) {
			ApplicationDAO.datastore = datastore;
		}
	}

	public ApplicationDAO() {

	}

	public void init() {

	}

	public void addApplication(Application application) {
		ApplicationDAO.datastore.save(application);
	}

	public Application findApplication(ObjectId profileId) {
		final Application application = datastore.find(Application.class, "id", profileId).get();
		return application;
	}

	public List<Application> getAllApplication() {
		final Query<Application> query = datastore.createQuery(Application.class);
		final List<Application> applications = query.asList();
		return applications;
	}

	public List<Application> getApplicationsOfUser(User user) {
		final Query<Application> query = datastore.createQuery(Application.class).field("user").equal(user);
		final List<Application> applications = query.asList();
		return applications;
	}

	public List<Application> getApplicationsOfAdvert(JobAdvert jobAdvert) {
		final Query<Application> query = datastore.createQuery(Application.class).field("jobAdvert").equal(jobAdvert);
		final List<Application> applications = query.asList();
		return applications;
	}

	public List<Application> getApplicationsOfCompany(Company company) {
		final JobAdvert jobAdvert = datastore.find(JobAdvert.class, "company", company).get();
		final Query<Application> query = datastore.createQuery(Application.class).field("jobAdvert").equal(jobAdvert);
		final List<Application> applications = query.asList();
		return applications;
	}

	public boolean isAppliedToJob(User user, JobAdvert jobAdvert) {
		List<Application> applications = getApplicationsOfAdvert(jobAdvert);
		for (Application app : applications) {
			if (app.getUser().getId().equals(user.getId())) {
				return true;
			}
		}
		return false;
	}

	public void updateApplication(Application application) {
		datastore.save(application);
	}

	public void deleteApplication(Application application) {
		final Query<Application> query = datastore.createQuery(Application.class).field("_id")
				.equal(application.getId());
		datastore.delete(query);
	}

	public static Datastore getDatastore() {
		return datastore;
	}
}
