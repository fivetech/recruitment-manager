package com.tolga.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;

import com.tolga.dao.ApplicationDAO;
import com.tolga.dao.CompanyDAO;
import com.tolga.dao.JobAdvertDAO;
import com.tolga.dao.UserDAO;
import com.tolga.model.Application;
import com.tolga.model.Company;
import com.tolga.model.HumanResource;
import com.tolga.model.JobAdvert;
import com.tolga.model.User;

public class RecruitmentController {

	private JobAdvertDAO jobAdvertDAO = new JobAdvertDAO();
	private ApplicationDAO applicationDAO = new ApplicationDAO();
	private CompanyDAO companyDAO = new CompanyDAO();
	private UserDAO userDAO = new UserDAO();
	private HttpSession session;

	public void performDecision(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		session = request.getSession();
		String action = null;
		if (request.getMethod().equals("GET")) {
			if (request.getParameter("action") != null) {
				action = request.getParameter("action").toString();
				if (action.equals("list_jobs")) {
					List<JobAdvert> jobAdverts = jobAdvertDAO.getAllJobAdverts();
					request.setAttribute("jobAdverts", jobAdverts);
					request.getRequestDispatcher("jobs.jsp").forward(request, response);
				} else if (action.equals("index")) {
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else if (action.equals("job_details")) {
					if (request.getParameter("jobId") != null) {
						ObjectId jobId = null;
						try {
							jobId = new ObjectId(request.getParameter("jobId"));
						} catch (java.lang.IllegalArgumentException e) {
							request.setAttribute("JobNotFoundException", "Job not found");
							request.getRequestDispatcher("jobDetails.jsp").forward(request, response);
							return;
						}

						JobAdvert jobAdvert = jobAdvertDAO.findJobAdvert(jobId);
						if (session.getAttribute("user") != null) {
							ObjectId userId = ((User) session.getAttribute("user")).getId();
							User user = new User();
							user.setId(userId);
							boolean isApplied = applicationDAO.isAppliedToJob(user, jobAdvert);
							request.setAttribute("isApplied", isApplied);
						}
						request.setAttribute("jobAdvert", jobAdvert);
						request.getRequestDispatcher("jobDetails.jsp").forward(request, response);
					}
				} else if (action.equals("my_jobs")) {
					if (session.getAttribute("user") != null) {
						User user = (User) session.getAttribute("user");
						List<Application> applications = applicationDAO.getApplicationsOfUser(user);
						request.setAttribute("applications", applications);
						request.getRequestDispatcher("myjobs.jsp").forward(request, response);
					}
				} else if (action.equals("hr_list_adverts")) {
					if (session.getAttribute("hr") != null) {
						HumanResource hr = (HumanResource) session.getAttribute("hr");
						List<JobAdvert> jobAdverts = jobAdvertDAO.getJobAdvertsOfCompany(hr.getCompany());
						request.setAttribute("jobAdverts", jobAdverts);
						request.getRequestDispatcher("hr/index.jsp").forward(request, response);
					}
				} else if (action.equals("hr_list_advert_apps")) {
					if (request.getParameter("advertId") != null) {
						ObjectId advertId = null;
						try {
							advertId = new ObjectId(request.getParameter("advertId"));
						} catch (java.lang.IllegalArgumentException e) {
							request.setAttribute("AdvertNotFoundException", "Advert Not Found");
							request.getRequestDispatcher("hr/applications.jsp").forward(request, response);
							return;
						}

						JobAdvert jobAdvert = jobAdvertDAO.findJobAdvert(advertId);

						List<Application> applications = applicationDAO.getApplicationsOfAdvert(jobAdvert);
						request.setAttribute("applications", applications);
						request.getRequestDispatcher("hr/applications.jsp").forward(request, response);
					}
				} else if (action.equals("hr_create_advert")) {
					HumanResource hr = (HumanResource) session.getAttribute("hr");
					if (hr != null) {
						request.getRequestDispatcher("hr/CreateJobAdvert.jsp").forward(request, response);
					}

				} else if (action.equals("hr_list_applications")) {

					HumanResource hr = (HumanResource) session.getAttribute("hr");
					if (hr != null) {
						Company company = companyDAO.findCompany(hr.getCompany().getId());
						List<Application> applicationsOfCompany = applicationDAO.getApplicationsOfCompany(company);
						Set<User> distUsers = new HashSet<>();
						for (Application app : applicationsOfCompany) {
							distUsers.add(app.getUser());
						}
						request.setAttribute("users", new ArrayList<>(distUsers));
						request.getRequestDispatcher("hr/users.jsp").forward(request, response);
					}

				} else if (action.equals("hr_user_applications")) {
					if (request.getParameter("userId") != null) {
						ObjectId userId = null;
						try {
							userId = new ObjectId(request.getParameter("userId"));
						} catch (java.lang.IllegalArgumentException e) {
							request.setAttribute("UserNotFoundException", "User not found");
							request.getRequestDispatcher("hr/userProfile.jsp").forward(request, response);
							return;
						}
						HumanResource hr = (HumanResource) session.getAttribute("hr");
						Company company = companyDAO.findCompany(hr.getCompany().getId());
						User user = userDAO.findUserById(userId);
						List<Application> userApplications = applicationDAO.getApplicationsOfUser(user);
						List<Application> userCompanyApplications = new ArrayList<>();
						for (Application a : userApplications) {
							if (a.getJobAdvert().getCompany().getId().equals(company.getId())) {
								userCompanyApplications.add(a);
							}
						}
						request.setAttribute("userCompanyApplications", userCompanyApplications);
						request.setAttribute("user", user);
						request.getRequestDispatcher("hr/userProfile.jsp").forward(request, response);
					}

				}
				else if(action.equals("hr_login_page")) {
					request.getRequestDispatcher("hr/hrLogin.jsp").forward(request, response);
				}
				else {
					response.sendRedirect("error/404.jsp");
				}
			}

		} else if (request.getMethod().equals("POST")) {
			if (request.getParameter("action") != null) {
				action = request.getParameter("action").toString();
				if (action.equals("apply")) {
					if (request.getParameter("jobId") != null) {
						ObjectId jobId = new ObjectId(request.getParameter("jobId"));
						String introduction = request.getParameter("introduction") != null
								? request.getParameter("introduction") : "Empty";
						applyJob(jobId, introduction);
					}
				} else if (action.equals("hr_change_app_status")) {
					if (request.getParameter("appId") != null && request.getParameter("status") != null) {
						ObjectId appId = new ObjectId(request.getParameter("appId"));
						String status = request.getParameter("status");
						Application app = applicationDAO.findApplication(appId);
						app.setStatus(status.toUpperCase());
						applicationDAO.updateApplication(app);
						request.setAttribute("userEmail", app.getUser().getEmailAddress());
						request.setAttribute("appStatus", app.getStatus());
						request.setAttribute("companyUrl", app.getJobAdvert().getCompany().getPictureUrl());
						request.setAttribute("jobTitle", app.getJobAdvert().getTitle());
						 request.getRequestDispatcher("MailServlet").forward(request,
						 response);

					}
				} else if (action.equals("hr_create_advert")) {
					if (request.getParameter("title") != null && request.getParameterValues("jobDescriptions") != null
							&& request.getParameterValues("requiredSkills") != null
							&& request.getParameter("activatedDate") != null
							&& request.getParameter("expiryDate") != null && session.getAttribute("hr") != null) {

						HumanResource hr = (HumanResource) session.getAttribute("hr");
						Company company = hr.getCompany();
						List<String> skills = new ArrayList<>();
						List<String> descriptions = new ArrayList<>();

						String title = request.getParameter("title");
						String[] jobDescriptions = request.getParameterValues("jobDescriptions");
						String[] requiredSkills = request.getParameterValues("requiredSkills");
						String activatedDate = request.getParameter("activatedDate");
						String expiryDate = request.getParameter("expiryDate");

						JobAdvert jobAdvert = new JobAdvert();
						jobAdvert.setCompany(company);
						jobAdvert.setTitle(title);
						Date expiry = null;
						Date activated = null;
						Date created = null;
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("tr", "TR"));
						try {

							activated = sdf.parse(activatedDate);
							expiry = sdf.parse(expiryDate);
							created = sdf.parse(sdf.format(new Date()));

							jobAdvert.setDateActivated(activated);
							jobAdvert.setDateCreated(created);
							jobAdvert.setDateExpired(expiry);
						} catch (ParseException e) {
							e.printStackTrace();
						}

						for (String skill : requiredSkills) {
							skills.add(skill);
						}
						for (String description : jobDescriptions) {
							descriptions.add(description);
						}
						jobAdvert.setDescription(descriptions);
						jobAdvert.setSkills(skills);
						if (activatedDate.equals(sdf.format(new Date()))) {
							jobAdvert.setActive(true);
						}

						jobAdvertDAO.addJobAdvert(jobAdvert);
						response.sendRedirect("RecruitmentManager?action=hr_list_adverts");
					}
				}
				else if(action.equals("text_search")) {
					String textSearch = request.getParameter("text-search");
					if(textSearch != null && !textSearch.isEmpty()) {
						List<JobAdvert> jobAdverts = jobAdvertDAO.searchByTitle(textSearch);
						request.setAttribute("jobAdverts", jobAdverts);
						request.getRequestDispatcher("jobs.jsp").forward(request, response);
						
					}
				}
			}

		}
	}

	private void applyJob(ObjectId jobId, String introduction) {
		User user = (User) session.getAttribute("user");
		JobAdvert jobAdvert = jobAdvertDAO.findJobAdvert(jobId);
		Application application = new Application(user, jobAdvert, new Date(), "INPROGRESS", introduction);
		jobAdvert.addApplication(application);
		jobAdvertDAO.updateJobAdvert(jobAdvert);
		applicationDAO.addApplication(application);
	}
}
