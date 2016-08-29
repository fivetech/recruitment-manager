<%@page import="com.tolga.model.User"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.tolga.model.JobAdvert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	JobAdvert advert = (JobAdvert) request.getAttribute("jobAdvert");
	if (session.getAttribute("user") != null) {
		User user = (User) session.getAttribute("user");
		pageContext.setAttribute("user", user);
		boolean isApplied = (boolean) request.getAttribute("isApplied");
		pageContext.setAttribute("isApplied", isApplied);
	}
	String exception = null;
	if (request.getAttribute("JobNotFoundException") != null) {
		exception = (String) request.getAttribute("JobNotFoundException");
	}
%>
<jsp:include page="template/navbar.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-8">
			<div id="postlist">
				<div class="panel">
					<%
						if (exception != null || advert == null) {
					%>
					<div class="alert alert-danger" role="alert">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span> <span class="sr-only">Error:</span>
						Job Not Found
					</div>
					<%
						} else {
					%>
					<div class="panel-heading">
						<div class="text-center">
							<div class="row">
								<div class="col-sm-9">
									<h3 class="pull-left"><%=advert.getTitle()%></h3>
								</div>
								<div class="col-sm-3">
									<h4 class="pull-right">
										<small><em>Date Expires:<br /> <%=DateFormat.getDateInstance().format(advert.getDateExpired())%></em></small>
									</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<a href="#" id="none-border" class="thumbnail"> <img
							class="company-img-mid" alt="Image"
							src=<%=advert.getCompany().getPictureUrl()%>>
						</a>
						<div class="text-center">
							<c:choose>
								<c:when test="${isApplied eq true }">
									<button type="button" data-id="<%=advert.getId()%>"
										data-title="<%=advert.getTitle()%>" data-user="${user.id}"
										class="btn btn-success swt-btn-applied">Applied this
										job</button>
								</c:when>
								<c:otherwise>
									<button type="button" data-id="<%=advert.getId()%>"
										data-title="<%=advert.getTitle()%>" data-user="${user.id}"
										class="btn btn-primary apply-button-details">Apply
										this job</button>
								</c:otherwise>
							</c:choose>
						</div>
						<br />
						<ul>
							<%
								for (String description : advert.getDescription()) {
							%>
							<li class="description-item"><%=description%></li>
							<%
								}
							%>
						</ul>
					</div>

					<div class="panel-footer">
						<%
							for (String skill : advert.getSkills()) {
						%>
						<span class="label label-default"><%=skill%></span>
						<%
							}
						%>
					</div>
					<%
						}
					%>
				</div>
			</div>
			<br />
		</div>
	</div>
</div>




<!-- /container -->
<!-- script references -->
<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/custom.js" type="text/javascript"></script>

</body>
</html>