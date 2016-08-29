package com.tolga.recruitment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tolga.controller.RecruitmentController;

/**
 * Servlet implementation class RecruitmentManager
 */
public class RecruitmentManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RecruitmentController controller = new RecruitmentController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitmentManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		controller.performDecision(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		controller.performDecision(request, response);
		
	}

}
