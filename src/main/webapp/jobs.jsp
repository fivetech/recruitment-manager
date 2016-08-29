<%@page import="com.tolga.model.Application"%>
<%@page import="com.tolga.model.User"%>
<%@page import="com.tolga.model.JobAdvert"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	@SuppressWarnings("unchecked")
	List<JobAdvert> adverts = (List<JobAdvert>) request.getAttribute("jobAdverts");
	pageContext.setAttribute("adverts", adverts);
%>


<jsp:include page="template/navbar.jsp"></jsp:include>
<%
	if (session.getAttribute("user") != null) {
		User user = (User) session.getAttribute("user");
		pageContext.setAttribute("user", user);
	}
%>
<div class="container">
	<div class="row">
		<div class="well">
			<h1 class="text-center">Find Your Job</h1>
			<div class="list-group">
				<c:forEach items="${adverts}" var="advert">
					<c:if test="${advert.active eq true }">
						<div class="list-group-item" id="advert-${advert.id}">
							<div class="media col-md-3">
								<figure class="pull-left">
									<img
										class="media-object img-rounded img-responsive company-img"
										src="${advert.company.pictureUrl}" alt="placehold.it/350x250">
								</figure>
							</div>
							<div class="col-md-6">
								<h4 class="list-group-item-heading">${advert.title}</h4>
								<p class="list-group-item-text">${advert.company.companyName}</p>
							</div>
							<div class="col-md-3 text-center">
								<h4>
									${advert.applicationsCount} <small> application </small>
								</h4>
								<button type="button" data-id="${advert.id}"
									class="btn btn-default btn-lg btn-block swt-btn-view">
									View</button>
								<br />
								<c:if test="${user ne null }">
									<c:forEach items="${advert.applications}" var="adApp">
										<c:if test="${adApp.user.id eq user.id }">
											<button type="button" data-user="${user.id}"
												data-id="${advert.id}" data-title="${advert.title}"
												class="btn btn-success btn-lg btn-block swt-btn-applied">
												Applied</button>
										</c:if>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>
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