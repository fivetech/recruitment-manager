package com.tolga.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
@Entity("profilepictures")
public class ProfilePicture {
	@Id
	private ObjectId id = new ObjectId();
	private int _total;
	private List<String> values;
	@Reference
	private User user;

	public ProfilePicture() {
		// TODO Auto-generated constructor stub
	}

	public ProfilePicture(int _total, List<String> values, User user) {
		super();
		this._total = _total;
		this.values = values;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int get_total() {
		return _total;
	}

	public void set_total(int _total) {
		this._total = _total;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public ObjectId getId() {
		return id;
	}

}
