package com.tolga.login;

import java.io.IOException;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tolga.auth.LinkedinAuth;
import com.tolga.dao.ProfilePictureDAO;
import com.tolga.dao.UserDAO;
import com.tolga.model.ProfilePicture;
import com.tolga.model.User;

public class LinkedinLoginHandler {
	private LinkedinAuth linkedInAuth = new LinkedinAuth();
	private UserDAO userDAO = new UserDAO();
	private Gson gson = new Gson();
	private JsonParser parser = new JsonParser();
	private String responseJSON;
	private static final String PROTECTED_RESOURCE_URL = "https://api.linkedin.com/v1/people/~:(%s)";

	public User signIn(String code) throws IOException {
		final OAuth2AccessToken accessToken = linkedInAuth.getService().getAccessToken(code);
		final String query = "id,first-name,last-name,picture-urls::(original),email-address";

		// Request profile with query
		/*
		 * final OAuthRequest requestProfile = new OAuthRequest(Verb.GET,
		 * String.format(PROTECTED_RESOURCE_URL, query),
		 * linkedInAuth.getService());
		 */

		// Request profile
		final OAuthRequest requestProfile = new OAuthRequest(Verb.GET, String.format(PROTECTED_RESOURCE_URL, query),
				linkedInAuth.getService());
		// Set request headers
		requestProfile.addHeader("x-li-format", "json");
		requestProfile.addHeader("Accept-Language", "tr-TR");

		// Signin request and get response
		linkedInAuth.getService().signRequest(accessToken, requestProfile);
		final Response responseProfile = requestProfile.send();
		this.responseJSON = responseProfile.getBody();
		User user = createUser();

		return user;
	}

	// Need to be optimized
	private User createUser() {

		User user = null;
		User userExists = null;
		ProfilePicture profilePicture = null;
		String profileIdStr = null;
		JsonElement profileId = null;
		JsonElement firstName = null;
		JsonElement lastName = null;
		JsonElement emailAddress = null;
		JsonElement pictureUrls = null;
		profilePicture = gson.fromJson(pictureUrls, ProfilePicture.class);
		JsonElement jsonTree = parser.parse(responseJSON);
		if (jsonTree.isJsonObject()) {
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			profileId = jsonObject.get("id");
			firstName = jsonObject.get("firstName");
			lastName = jsonObject.get("lastName");
			emailAddress = jsonObject.get("emailAddress");
			pictureUrls = jsonObject.get("pictureUrls");
			profilePicture = gson.fromJson(pictureUrls, ProfilePicture.class);
			profileIdStr = profileId.getAsString();
		}
		userExists = userDAO.findUser(profileIdStr);
		if(userExists == null) {
			user = new User(profileId.getAsString(), firstName.getAsString(), lastName.getAsString(),
					emailAddress.getAsString(), profilePicture);
			profilePicture.setUser(user);		
			ProfilePictureDAO profilePictureDAO = new ProfilePictureDAO();
			profilePictureDAO.addProfilePicture(profilePicture);
			userDAO.addUser(user);
			return user;
		}

		return userExists;
	}
}
