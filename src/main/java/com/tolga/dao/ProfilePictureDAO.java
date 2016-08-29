package com.tolga.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.tolga.model.ProfilePicture;
import com.tolga.model.User;

public class ProfilePictureDAO {
	private static Datastore datastore;

	public ProfilePictureDAO(Datastore datastore) {
		if (ProfilePictureDAO.datastore == null) {
			ProfilePictureDAO.datastore = datastore;
		}
	}

	public ProfilePictureDAO() {

	}

	public void init() {

	}

	public void addProfilePicture(ProfilePicture profilePicture) {
		ProfilePictureDAO.datastore.save(profilePicture);
	}

	public ProfilePicture findProfilePicture(User u) {
		final Query<User> query = datastore.createQuery(User.class).field("_id").equal(u.getId());
		final User user = query.asList().get(0);
		return user.getProfilePicture();
	}

	public List<ProfilePicture> getAllProfilePictures() {
		final Query<ProfilePicture> query = datastore.createQuery(ProfilePicture.class);
		final List<ProfilePicture> profilePictures = query.asList();
		return profilePictures;
	}

	public void updateProfilePictures(User user, ProfilePicture profilePicture) {
		final Query<User> query = datastore.createQuery(User.class).field("_id").equal(user.getId());
		final UpdateOperations<User> updateOperations = datastore.createUpdateOperations(User.class)
				.set("profilePicture", profilePicture);
		datastore.update(query, updateOperations);

	}

	public void deleteProfilePicture(ProfilePicture profilePicture) {
		final Query<ProfilePicture> query = datastore.createQuery(ProfilePicture.class).field("_id")
				.equal(profilePicture.getId());
		datastore.delete(query);
	}

	public static Datastore getDatastore() {
		return datastore;
	}

}
