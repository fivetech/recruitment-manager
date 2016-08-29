package com.tolga.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;
import org.mongodb.morphia.utils.IndexType;

@Entity("jobadverts")
@Indexes(@Index(fields = @Field(value = "$**", type = IndexType.TEXT)))
public class JobAdvert {
	@Id
	private ObjectId id = new ObjectId();
	private String title;
	private List<String> description;
	private List<String> skills;
	private boolean isActive;
	private Date dateCreated;
	private Date dateActivated;
	private Date dateExpired;
	@Reference
	private Company company;
	@Reference
	private List<Application> applications = new ArrayList<Application>();
	@Transient
	private String companyName;

	public JobAdvert() {

	}

	public JobAdvert(Company company, String title, List<String> description, List<String> skills, boolean isActive,
			Date dateCreated, Date dateActivated, Date dateExpired, List<Application> applications) {
		super();
		this.company = company;
		this.title = title;
		this.description = description;
		this.skills = skills;
		this.isActive = isActive;
		this.dateCreated = dateCreated;
		this.dateActivated = dateActivated;
		this.dateExpired = dateExpired;
		this.applications = applications;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateActivated() {
		return dateActivated;
	}

	public void setDateActivated(Date dateActivated) {
		this.dateActivated = dateActivated;
	}

	public Date getDateExpired() {
		return dateExpired;
	}

	public void setDateExpired(Date dateExpired) {
		this.dateExpired = dateExpired;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void addApplication(Application application) {
		this.applications.add(application);
	}

	public int getApplicationsCount() {
		return applications.size();
	}
	
}
