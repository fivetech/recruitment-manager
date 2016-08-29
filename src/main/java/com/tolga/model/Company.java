package com.tolga.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.utils.IndexType;

@Entity("companies")
@Indexes(@Index(fields = @Field(value = "$**", type = IndexType.TEXT)))
public class Company {
	@Id
	private ObjectId id = new ObjectId();
	private String companyName;
	private String emailAddress;
	private String pictureUrl;
	@Reference
	private List<HumanResource> humanResources;
	@Reference
	private List<JobAdvert> jobAdverts;
	public Company() {
		// TODO Auto-generated constructor stub
	}

	public Company(String companyName, String emailAddress, String pictureUrl) {
		super();
		this.companyName = companyName;
		this.emailAddress = emailAddress;
		this.pictureUrl = pictureUrl;
	}

	public List<HumanResource> getHumanResources() {
		return humanResources;
	}

	public void setHumanResources(List<HumanResource> humanResources) {
		this.humanResources = humanResources;
	}

	public ObjectId getId() {
		return id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public List<JobAdvert> getJobAdverts() {
		return jobAdverts;
	}

	public void setJobAdverts(List<JobAdvert> jobAdverts) {
		this.jobAdverts = jobAdverts;
	}

}
