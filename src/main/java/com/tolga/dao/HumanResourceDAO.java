package com.tolga.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import com.tolga.model.HumanResource;

public class HumanResourceDAO {
	private static Datastore datastore;

	public HumanResourceDAO(Datastore datastore) {
		HumanResourceDAO.datastore = datastore;
	}

	public HumanResourceDAO() {

	}

	public void init() {

	}

	public void addHumanResource(HumanResource hr) {
		HumanResourceDAO.datastore.save(hr);
	}

	public HumanResource findHumanResource(String emailAddress) {
		final HumanResource hr = datastore.find(HumanResource.class, "emailAddress", emailAddress).get();
		return hr;
	}

	public List<HumanResource> getAllHumanResources() {
		final Query<HumanResource> query = datastore.createQuery(HumanResource.class);
		final List<HumanResource> hrs = query.asList();
		return hrs;
	}

	public void updateHumanResource(HumanResource hr) {
		datastore.save(hr);

	}

	public void deleteHumanResource(HumanResource hr) {
		final Query<HumanResource> query = datastore.createQuery(HumanResource.class).field("_id").equal(hr.getId());
		datastore.delete(query);
	}

	public static Datastore getDatastore() {
		return datastore;
	}
}
