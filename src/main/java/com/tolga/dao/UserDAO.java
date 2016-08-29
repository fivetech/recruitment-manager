package com.tolga.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;


import com.tolga.model.User;

public class UserDAO {
	private static Datastore datastore;

	public UserDAO(Datastore datastore) {
		if (UserDAO.datastore == null) {
			UserDAO.datastore = datastore;
		}
	}

	public void init() {

	}

	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	public void addUser(User user) {
		UserDAO.datastore.save(user);
	}

	public User findUser(String profileId) {
		final User user = datastore.find(User.class, "profileId", profileId).get();
		return user;
	}
	public User findUserById(ObjectId id) {
		final User user = datastore.find(User.class, "id", id).get();
		return user;
	}
	public List<User> getAllUsers() {
		final Query<User> query = datastore.createQuery(User.class);
		final List<User> users = query.asList();
		return users;
	}

	public void updateUser(User user) {
		datastore.save(user);

	}

	public void deleteUser(User user) {
		final Query<User> query = datastore.createQuery(User.class).field("_id").equal(user.getId());
		datastore.delete(query);
	}

	public static Datastore getDatastore() {
		return datastore;
	}

}
