package com.tolga.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tolga.model.HumanResource;
import com.tolga.model.User;

public class RecruitmentAuth {

	private HttpSession session;
	private Cookie cookie;

	public boolean userAuth(User user, HttpServletRequest request, HttpServletResponse response) {
		if (user.getProfileId() != null) {
			session = request.getSession(true);
			session.setAttribute("user", user);
			cookie = new Cookie("sessionId", session.getId());
			response.addCookie(cookie);
			return true;
		}
		return false;
	}

	public boolean hrAuth(HumanResource hr, HttpServletRequest request, HttpServletResponse response) {
		if (hr != null) {
			session = request.getSession(true);
			session.setAttribute("hr", hr);
			cookie = new Cookie("sessionId", session.getId());
			response.addCookie(cookie);
			return true;
		}
		return false;
	}
}
