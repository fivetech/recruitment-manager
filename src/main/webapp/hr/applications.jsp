<%@page import="com.tolga.model.Application"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	@SuppressWarnings("unchecked")
	List<Application> applications = (List<Application>) request.getAttribute("applications");
	pageContext.setAttribute("apps", applications);

	String exception = (String) request.getAttribute("AdvertNotFoundException");
	pageContext.setAttribute("exception", exception);
%>
<jsp:include page="../template/navbarHr.jsp"></jsp:include>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title" id="myModalLabel"></h4>
			</div>
			<div class="modal-body">
				<div class="profile-modal-center">
					<img src="" name="aboutme" width="140" height="140" border="0"
						class="img-circle" />
					<h3 class="media-heading"></h3>
					<span><strong>Skills: </strong></span> <span
						class="label label-warning">HTML5/CSS</span> <span
						class="label label-info">Adobe CS 5.5</span> <span
						class="label label-info">Microsoft Office</span> <span
						class="label label-success">Windows XP, Vista, 7</span>
				</div>
				<hr />
				<div class="app-introduction">
					<span class="text-left"> <strong class="app-intro-head">App
							introduction: </strong><br />
					</span> <br />
				</div>
			</div>
			<div class="modal-footer">
				<span>
					<button type="button" class="btn btn-danger reject-button"
						data-dismiss="modal">Reject</button>
				</span> <span>
					<button type="button" class="btn btn-success accept-button"
						data-dismiss="modal">Accept</button>
				</span>
			</div>
		</div>
	</div>
</div>


<div class="container">
	<div class="row">
		<section id="myjobscontent" class="content">
			<div class="col-md-8 col-md-offset-2">
				<div id="myjobspanel" class="panel panel-default">
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
									<c:if test="${fn:length(applications) gt 0 }">
										<c:out value="${applications[0].jobAdvert.title }"></c:out> applications
							</c:if>
								</h3>
							</div>
							<c:if test="${fn:length(applications) eq 0 }">
								<div class="alert alert-info" role="alert">
									<span class="glyphicon glyphicon-exclamation-sign"
										aria-hidden="true"></span> <span class="sr-only"></span> There
									is no application to this advert
								</div>
							</c:if>

							<c:if test="${fn:length(applications) gt 0 }">
								<div class="pull-right">
									<div class="btn-group">
										<button type="button" class="btn btn-success btn-filter"
											data-target="ACCEPTED">Accepted</button>
										<button type="button" class="btn btn-warning btn-filter"
											data-target="INPROGRESS">In Progress</button>
										<button type="button" class="btn btn-danger btn-filter"
											data-target="REJECTED">Rejected</button>
										<button type="button" class="btn btn-default btn-filter"
											data-target="all">All</button>
									</div>
								</div>


								<div class="table-container">
									<table id="myjobs" class="table table-filter">
										<tbody>
											<c:forEach items="${apps}" var="app">
												<tr class="hr-app-item" data-toggle="modal"
													data-target="#myModal"
													data-href="RecruitmentManager?action=hr_change_app_status&appId=${app.id}"
													data-intro="${app.introduction }"
													data-status="${app.status }"
													data-picture="${app.user.profilePicture.values[0] }"
													data-user="${app.user.firstName+=' '+=app.user.lastName}">
													<td>
														<div class="ckbox myjobs-check">
															<input type="checkbox" id="checkbox1"> <label
																for="checkbox1"></label>
														</div>
													</td>
													<td><a href="javascript:;" class="star"> <i
															class="glyphicon glyphicon-star"></i>
													</a></td>
													<td>
														<div class="media">
															<a href="#" class="pull-left"><c:if
																	test="${app.user.profilePicture.values ne null }">
																	<img class="profile-picture"
																		src="${app.user.profilePicture.values[0]}">
																</c:if> <c:if test="${app.user.profilePicture.values eq null }">
																	<c:out value="No Image"></c:out>
																</c:if> </a>
															<div>
																<span class="media-meta pull-right">${advert.dateExpired}</span>
																<h4 class="title">
																	<c:out value="${app.user.firstName }"></c:out>
																	<c:out value="${app.user.lastName }"></c:out>
																	<span class="pull-right pagado"><c:if
																			test="${app.status eq 'ACCEPTED' }">
																			<span class="label label-success"><c:out
																					value="${app.status }"></c:out></span>
																		</c:if> <c:if test="${app.status eq 'REJECTED' }">
																			<span class="label label-danger"><c:out
																					value="${app.status }"></c:out></span>
																		</c:if> <c:if test="${app.status eq 'INPROGRESS' }">
																			<span class="label label-warning"><c:out
																					value="${app.status }"></c:out></span>
																		</c:if></span> <span>(<c:out value="${app.dateCreated }"></c:out>)
																	</span>
																</h4>
																<p class="summary">
																	<c:out value="${app.user.emailAddress }"></c:out>
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