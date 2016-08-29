package com.tolga.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tolga.auth.RecruitmentAuth;
import com.tolga.model.User;

/**
 * Servlet implementation class LinkedinAuth
 */
public class LinkedinAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LinkedinLoginHandler linkedInLogin;
	private RecruitmentAuth recruitmentAuth;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LinkedinAuthServlet() {
		super();
		linkedInLogin = new LinkedinLoginHandler();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		if (code != null) {
			User user = linkedInLogin.signIn(code);
			recruitmentAuth = new RecruitmentAuth();
			boolean isSuccessful = recruitmentAuth.userAuth(user, request, response);
			if (isSuccessful)
				response.sendRedirect("/RecruitmentManager?action=index");
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
