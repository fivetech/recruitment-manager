package com.tolga.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("applications")
//@Indexes(@Index(fields = @Field("salary")))
public class Application {
	@Id
	private ObjectId id = new ObjectId();
	@Reference
	private User user;
	@Reference
	private JobAdvert jobAdvert;
	private Date dateCreated;
	private String status;
	private String introduction;
	
	public Application() {
		// TODO Auto-generated constructor stub
	}

	public Application(User user, JobAdvert jobAdvert, Date dateCreated, String status, String introduction) {
		super();
		this.user = user;
		this.jobAdvert = jobAdvert;
		this.dateCreated = dateCreated;
		this.introduction = introduction;
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JobAdvert getJobAdvert() {
		return jobAdvert;
	}

	public void setJobAdvert(JobAdvert jobAdvert) {
		this.jobAdvert = jobAdvert;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
