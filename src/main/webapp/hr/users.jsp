<%@page import="com.tolga.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	@SuppressWarnings("unchecked")
	List<User> users = (List<User>) request.getAttribute("users");
	pageContext.setAttribute("users", users);
%>
<jsp:include page="../template/navbarHr.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<section id="hr-users-content" class="content">
			<div class="col-md-8 col-md-offset-2">
				<div id ="hr-users-panel" class="panel panel-default">
					<div class="panel-body">
						<c:if test="${exception ne null }">
							<div class="pull-left">
								<div class="alert alert-danger" role="alert">
									<span class="glyphicon glyphicon-exclamation-sign"
										aria-hidden="true"></span> <span class="sr-only">Error:</span>
									Advert Not Found
								</div>
							</div>
						</c:if>
						<c:if test="${exception eq null }">
							<div class="pull-left">
								<h3>
									Users
								</h3>
							</div>							
							
							<div class="table-container">
								<table id="hr-users" class="table table-filter">
									<tbody>
										<c:forEach items="${users}" var="user">
											<tr class="hr-app-user" data-id="${user.id }">
												<td>
													<div class="media">
														<a href="#" class="pull-left"><c:if
																test="${user.profilePicture.values ne null }">
																<img class="profile-picture"
																	src="${user.profilePicture.values[0]}">
															</c:if> <c:if test="${user.profilePicture.values eq null }">
																<c:out value="No Image"></c:out>
															</c:if> </a>
														<div>
															<h4 class="title">
																<c:out value="${user.firstName }"></c:out>
																<c:out value="${user.lastName }"></c:out>
															</h4>
															<p class="summary">
																<c:out value="${user.emailAddress }"></c:out>
															</p>
														</div>
													</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

						</c:if>

					</div>
				</div>
			</div>
		</section>

	</div>
</div>

<!-- /container -->
<!-- script references -->
<script src="../js/jquery-2.2.4.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/custom.js"></script>
</body>
</html>