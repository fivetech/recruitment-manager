package com.tolga.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("blockedUsers")
//@Indexes(@Index(fields = @Field("salary")))
public class BlockedUser {
	@Id
	private ObjectId id = new ObjectId();
	@Reference
	private User user;
	@Reference
	private Company company;
	
	public BlockedUser() {
		// TODO Auto-generated constructor stub
	}
	
	public BlockedUser(ObjectId id, User user, Company company) {
		super();
		this.id = id;
		this.user = user;
		this.company = company;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
