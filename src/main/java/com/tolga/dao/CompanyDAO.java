package com.tolga.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;


import com.tolga.model.Company;

public class CompanyDAO {
	private static Datastore datastore;

	public CompanyDAO(Datastore datastore) {
		if (CompanyDAO.datastore == null) {
			CompanyDAO.datastore = datastore;
		}
	}

	public CompanyDAO() {

	}

	public void init() {

	}

	public void addCompany(Company company) {
		CompanyDAO.datastore.save(company);
	}

	public Company findCompany(ObjectId objectId) {
		final Query<Company> query = datastore.createQuery(Company.class).field("_id").equal(objectId);
		final Company company = query.asList().get(0);
		return company;
	}

	public List<Company> getAllCompanies() {
		final Query<Company> query = datastore.createQuery(Company.class);
		final List<Company> companies = query.asList();
		return companies;
	}

	public void updateCompany(Company company) {
		datastore.save(company);

	}

	public void deleteCompany(Company company) {
		final Query<Company> query = datastore.createQuery(Company.class).field("_id").equal(company.getId());
		datastore.delete(query);
	}

	public static Datastore getDatastore() {
		return datastore;
	}

}
