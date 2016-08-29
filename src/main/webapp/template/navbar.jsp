<%@page import="com.tolga.model.HumanResource"%>
<%@page import="com.tolga.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("user") != null) {
		User user = (User) session.getAttribute("user");
		pageContext.setAttribute("user", user);
	}
	if (session.getAttribute("hr") != null) {
		HumanResource hr = (HumanResource) session.getAttribute("hr");
		pageContext.setAttribute("hr", hr);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recruitment</title>
<meta name="generator" content="Bootply" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-social.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/sweetalert.css" rel="stylesheet">
<script src="js/sweetalert-dev.js" type="text/javascript"></script>
<script type="text/javascript" src="//platform.linkedin.com/in.js">
	api_key:  your_key
	authorize: true
</script>
</head>
<body>
	<%
		User user = (User) pageContext.getAttribute("user");
		HumanResource hr = (HumanResource) pageContext.getAttribute("hr");
	%>

	<div class="navbar navbar-default">
		<div class="container">
			<a class="navbar-brand" href="RecruitmentManager?action=index"> <span
				class="fa fa-users"></span> Recruitment
			</a>
			<ul class="nav navbar-nav">
				<li class="active"><a href="RecruitmentManager?action=index"><span
						class="fa fa-home"></span> Home</a></li>
				<li><a href="RecruitmentManager?action=list_jobs"><span
						class="fa fa-building"></span> Jobs</a></li>
				<li>
					<%
						if (hr != null) {
					%> <a href="/RecruitmentManager?action=hr_list_adverts"> <span
						class="fa fa-database"></span> HR Panel
				</a> <%
 	}
 %>
				</li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li id="search-list"><form class="navbar-form" id="search-form"
						action="RecruitmentManager?action=text_search" method="post">
						<div class="form-group has-feedback">
							<label for="search" class="sr-only">Search</label> <input
								type="text" class="form-control" name="text-search" id="search"
								placeholder="Find a job"> <span
								class="glyphicon glyphicon-search form-control-feedback"></span>
						</div>
					</form></li>
				<%
					if (user != null) {
				%>
				<li><a href="RecruitmentManager?action=my_jobs"><span
						class="fa fa-briefcase"></span> My Jobs</a></li>
				<li class="dropdown" id="my-user-profile"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <span
						class="glyphicon glyphicon-user"></span>  <strong><%=user.getFirstName()%></strong>
						<span class="glyphicon glyphicon-chevron-down"></span>
				</a>
					<ul class="dropdown-menu">
						<li>
							<div class="navbar-login">
								<div class="row">
									<div class="col-lg-4">
										<p class="text-center">
											<span class="icon-size"> <img
												src=<%=user.getProfilePicture().getValues().size() > 0 ? user.getProfilePicture().getValues().get(0)
						: ""%> />
											</span>
										</p>
									</div>
									<div class="col-lg-8">
										<p class="text-left">
											<strong><%=user.getFirstName() + " " + user.getLastName()%></strong>
										</p>
										<p class="text-left small"><%=user.getEmailAddress()%></p>
										<p class="text-left">
											<a href="#" class="btn btn-primary btn-block btn-sm">Edit
												Profile</a>
										</p>
									</div>
								</div>
							</div>
						</li>
						<li class="divider"></li>
						<li>
							<div class="navbar-login navbar-login-session">
								<div class="row">
									<div class="col-lg-12">
										<p>
											<a id="logout-a" href="javascript:void(0)"
												class="btn btn-danger btn-block">Logout</a>
										</p>
									</div>
								</div>
							</div>
						</li>
					</ul></li>

				<%
					} else {
				%>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><b>Login</b> <span class="caret"></span></a>
					<ul id="login-dp" class="dropdown-menu">
						<li>
							<div class="row">
								<div class="col-md-12">
									Users
									<div class="social-buttons">
										<a class="btn btn-block btn-social btn-linkedin"
											href="/LoginServlet?action=login"> <span
											class="fa fa-linkedin"></span> Sign in with LinkedIn
										</a>
									</div>
									<%
										if (hr == null) {
									%>
									HR Login
									<form class="form" role="form" method="post"
										action="/LoginServlet?action=hr_login" accept-charset="UTF-8"
										id="login-nav">
										<div class="form-group">
											<label class="sr-only" for="exampleInputEmail2">Email
												address</label> <input type="email" name="hrEmail"
												class="form-control" id="exampleInputEmail2"
												placeholder="Email address" required>
										</div>
										<div class="form-group">
											<label class="sr-only" for="exampleInputPassword2">Password</label>
											<input type="password" class="form-control" name="hrPass"
												id="exampleInputPassword2" placeholder="Password" required>
										</div>
										<div class="form-group">
											<button type="submit" class="btn btn-primary btn-block">Sign
												in</button>
										</div>
									</form>
									<%
										}
									%>

								</div>
							</div>
						</li>
					</ul></li>
				<%
					}
				%>
				<%
					if (hr == null) {
				%>
				<li><a href="RecruitmentManager?action=hr_login_page"><span
						class="fa fa-sign-in"></span> HR Login</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>