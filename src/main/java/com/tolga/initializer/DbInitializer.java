package com.tolga.initializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mongodb.MongoClient;
import com.tolga.dao.ApplicationDAO;
import com.tolga.dao.CompanyDAO;
import com.tolga.dao.HumanResourceDAO;
import com.tolga.dao.JobAdvertDAO;
import com.tolga.dao.ProfilePictureDAO;
import com.tolga.dao.UserDAO;
import com.tolga.model.Company;
import com.tolga.model.HumanResource;
import com.tolga.model.JobAdvert;

public class DbInitializer {

	private final Morphia morphia = new Morphia();
	private final Gson gson = new Gson();
	private ServletContext context;
	private Datastore datastore;
	private List<Company> companies;
	private List<HumanResource> humanResources;
	private List<JobAdvert> jobAdverts;

	public DbInitializer(ServletContext context) {
		this.context = context;
	}

	public void init() {
		morphia.mapPackage(context.getInitParameter("MappedPackageName"));
		this.datastore = morphia.createDatastore(new MongoClient(), context.getInitParameter("DbName"));
		datastore.getDB().dropDatabase();
		datastore.ensureIndexes();
		initDAOs();
	}

	private void initDAOs() {
		UserDAO userDAO = new UserDAO(datastore);
		ProfilePictureDAO profilePictureDAO = new ProfilePictureDAO(datastore);
		JobAdvertDAO jobAdvertDAO = new JobAdvertDAO(datastore);
		CompanyDAO companyDAO = new CompanyDAO(datastore);
		ApplicationDAO applicationDAO = new ApplicationDAO(datastore);
		HumanResourceDAO humanResourceDAO = new HumanResourceDAO(datastore);
		profilePictureDAO.init();
		userDAO.init();
		jobAdvertDAO.init();
		companyDAO.init();
		applicationDAO.init();
		humanResourceDAO.init();
	}

	public void saveSampleCompanies() throws JsonIOException, JsonSyntaxException, FileNotFoundException {

		TypeToken<List<Company>> companiesType = new TypeToken<List<Company>>() {
		};
		TypeToken<List<HumanResource>> hrType = new TypeToken<List<HumanResource>>() {
		};
		companies = gson.fromJson(new JsonReader(new FileReader(new File(context.getRealPath("json/companies.json")))),
				companiesType.getType());
		humanResources = gson.fromJson(
				new JsonReader(new FileReader(new File(context.getRealPath("json/humanResources.json")))),
				hrType.getType());
		mapHRToCompany();

		datastore.save(humanResources);
		datastore.save(companies);

	}

	public void saveSampleJobAdverts() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		TypeToken<List<JobAdvert>> jobAdvertsType = new TypeToken<List<JobAdvert>>() {
		};
		jobAdverts = gson.fromJson(
				new JsonReader(new FileReader(new File(context.getRealPath("json/jobAdverts.json")))),
				jobAdvertsType.getType());
		mapCompanyToJobAdvert();
		datastore.save(jobAdverts);
		datastore.save(companies);

	}

	private void mapCompanyToJobAdvert() {
		for (Company company : companies) {
			List<JobAdvert> jobAds = new ArrayList<>();
			for (JobAdvert job : jobAdverts) {
				if (job.getCompanyName().equals(company.getCompanyName())) {
					job.setCompany(company);
					jobAds.add(job);
				}
			}
			company.setJobAdverts(jobAds);
		}
	}

	private void mapHRToCompany() {
		for (Company company : companies) {
			List<HumanResource> hrs = new ArrayList<>();
			for (HumanResource hr : humanResources) {
				if (hr.getCompanyName().equals(company.getCompanyName())) {
					hr.setCompany(company);
					hrs.add(hr);
				}
			}
			company.setHumanResources(hrs);
		}
	}
}
