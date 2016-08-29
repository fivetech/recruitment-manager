<%@page import="com.tolga.model.User"%>
<%@page import="com.tolga.model.Application"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	@SuppressWarnings("unchecked")
	List<Application> applications = (List<Application>) request.getAttribute("userCompanyApplications");
	pageContext.setAttribute("applications", applications);
	User user = (User) request.getAttribute("user");
	pageContext.setAttribute("user", user);
%>
<jsp:include page="../template/navbarHr.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<div class="col-md-offset-2 col-md-8 col-lg-offset-13 col-lg-16">
			<div id="custom-profile-panel" class="well profile">
				<div class="col-sm-12">
					<div class="col-xs-12 col-sm-8">
						<h2>
							<c:out value="${user.firstName}"></c:out>
							<c:out value="${user.lastName}"></c:out>
						</h2>
						<p>
							<strong>About: </strong> Web Designer / UI.
						</p>
						<p>
							<strong>Hobbies: </strong> Read, out with friends, listen to
							music, draw and learn new things.
						</p>
						<p>
							<strong>Skills: </strong> <span class="tags">html5</span> <span
								class="tags">css3</span> <span class="tags">jquery</span> <span
								class="tags">bootstrap3</span>
						</p>
					</div>
					<div class="col-xs-12 col-sm-4 text-center">
						<figure>
							<img
								src="https://media.licdn.com/mpr/mprx/0_0rpXCcQZy0tSCRnKRC3G-I6Um1-SCUn1j33G1WsZyy-eCBRy0J38Xx6Ruyx5AR90DChQq2bR05Oe8ERJfUSwv2FZe5OH8Eo1EUS6hcR4jx738jUsE0gFoJy4un"
								alt="" class="img-circle img-responsive">
						</figure>
						<br />
					</div>

					<table class="table table-filter-custom">
						<tbody>
							<c:forEach items="${applications }" var="app">
								<tr>
									<td>
										<div class="media">
											<a href="#" class="pull-left"> <img
												src="${app.jobAdvert.company.pictureUrl }"
												class="media-photo" />
											</a>
											<div>
												<span class="media-meta pull-right">${app.dateCreated }</span>
												<h4 class="title">${app.jobAdvert.title }</h4>
												<p class="summary">${app.jobAdvert.company.companyName }</p>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /container -->
<!-- script references -->
<script src="../js/jquery-2.2.4.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/custom.js"></script>
</body>
</html>