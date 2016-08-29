<%@page import="com.tolga.model.HumanResource"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	if (session.getAttribute("hr") != null) {
		HumanResource hr = (HumanResource) session.getAttribute("hr");
		pageContext.setAttribute("hr", hr);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HR Panel</title>
<meta name="generator" content="Bootply" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/bootstrap-social.css" rel="stylesheet">
<link href="../css/styles.css" rel="stylesheet">
<link href="../css/hrStyles.css" rel="stylesheet">
<link href="../css/font-awesome.min.css" rel="stylesheet">
<link href="../css/sweetalert.css" rel="stylesheet">
<link href="../css/datepicker.css" rel="stylesheet">
<script src="../js/sweetalert-dev.js" type="text/javascript"></script>
</head>
<body>
	<div class="navbar navbar-default">
		<div class="container">
			<a class="navbar-brand" href="../RecruitmentManager?action=hr_list_adverts"><span
							class="fa fa-database"></span> HR
				Panel</a>
			<ul class="nav navbar-nav">
				<li><a
					href="../RecruitmentManager?action=index"><span
						class="fa fa-home"></span> Home</a></li>
					<li><a
					href="../RecruitmentManager?action=hr_list_adverts"><span
						class="fa fa-newspaper-o"></span> Job Adverts</a></li>
				<li><a href="../RecruitmentManager?action=hr_list_applications"><span
						class="fa fa-wpforms"></span> Applications</a></li>
				<li><a href="../RecruitmentManager?action=hr_create_advert"><span
						class="fa fa-plus"></span> Create Job Advert</a></li>
				<li class="divider-vertical"></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li id="search-list"><form class="navbar-form" id="search-form" action="RecruitmentManager?action=text_search" method="post">
						<div class="form-group has-feedback">
							<label for="search" class="sr-only">Search</label> <input
								type="text" class="form-control" name="text-search" id="search"
								placeholder="search"> <span
								class="glyphicon glyphicon-search form-control-feedback"></span>
						</div>
					</form></li>
				<li class="dropdown" id="my-user-profile"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <span
						class="glyphicon glyphicon-user"></span>  <strong><c:out
								value="${hr.firstName }"></c:out></strong> <span
						class="glyphicon glyphicon-chevron-down"></span>
				</a>
					<ul class="dropdown-menu">
						<li>
							<div class="navbar-login">
								<div class="row">
									<div class="col-lg-4">
										<p class="text-center">
											<span class="icon-size">
											 <img
												src="${hr.company.pictureUrl }" />
											</span>
										</p>
									</div>
									<div class="col-lg-8">
										<p class="text-left">
											<strong><c:out value="${hr.firstName }"></c:out> <c:out
													value="${hr.lastName }"></c:out></strong>
										</p>
										<p class="text-left small">
											<c:out value="${hr.emailAddress }"></c:out>
										</p>
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
											<a id="hr-logout" data-href="LoginServlet?action=hr_logout" href="javascript:void(0)"
												class="btn btn-danger btn-block">Logout</a>
										</p>
									</div>
								</div>
							</div>
						</li>
					</ul></li>

			</ul>
		</div>
	</div>