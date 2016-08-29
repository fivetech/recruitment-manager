package com.tolga.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tolga.auth.LdapAuth;
import com.tolga.auth.LinkedinAuth;
import com.tolga.auth.RecruitmentAuth;
import com.tolga.dao.HumanResourceDAO;
import com.tolga.model.HumanResource;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LinkedinAuth linkedInAuth;
	private HttpSession session;
	private Cookie[] cookies;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		linkedInAuth = new LinkedinAuth();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equals("login")) {
				response.sendRedirect(linkedInAuth.obtainAuthUrl());
			} else if (action.equals("logout")) {
				session = request.getSession();
				session.removeAttribute("user");
				
				cookies = request.getCookies();
				for (Cookie c : cookies) {
					if(c.getName().equals("user")) {
						c.setMaxAge(0);
						c.setValue("");
						c.setPath("/");
						c.setMaxAge(0);
						response.addCookie(c);
					}					
				}
				response.sendRedirect("RecruitmentManager?action=index");
			} else if (action.equals("hr_login")) {
				String email = request.getParameter("hrEmail").trim();
				String password = request.getParameter("hrPass").trim();
				if (email != null && password != null) {
					LdapAuth auth = new LdapAuth();
					boolean isAuthenticated = auth.authenticate(email, password);
					if (isAuthenticated) {
						RecruitmentAuth recruitmentAuth = new RecruitmentAuth();
						HumanResourceDAO humanResourcesDAO = new HumanResourceDAO();
						HumanResource hr = humanResourcesDAO.findHumanResource(email);
						boolean isSuccessful = recruitmentAuth.hrAuth(hr, request, response);
						if (isSuccessful)
							response.sendRedirect("/RecruitmentManager?action=hr_list_adverts");
					}
				}
			} else if(action.equals("hr_logout")) {
				session = request.getSession();
				session.removeAttribute("hr");
				cookies = request.getCookies();
				for (Cookie c : cookies) {
					if(c.getName().equals("hr")) {
						c.setMaxAge(0);
						c.setValue("");
						c.setPath("/");
						c.setMaxAge(0);
						response.addCookie(c);
					}
				}
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
