<%@page import="com.tolga.model.AppStatus"%>
<%@page import="com.tolga.model.Application"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	@SuppressWarnings("unchecked")
	List<Application> myJobs = (List<Application>) request.getAttribute("applications");
	pageContext.setAttribute("myjobs", myJobs);
	pageContext.setAttribute("INPROGRESS", "INPROGRESS");
%>
<jsp:include page="template/navbar.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<section id="myjobscontent" class="content">
			<div class="col-md-8 col-md-offset-2">
				<div id="myjobspanel" class="panel panel-default">
					<div class="panel-body">
						<c:if test="${fn:length(myjobs) eq 0 }">
							
								<div class="alert alert-info" role="alert">
									<span class="glyphicon glyphicon-exclamation-sign"
										aria-hidden="true"></span> <span class="sr-only">Error:</span>
									There is no application yet
								</div>
							
						</c:if>
						<c:if test="${fn:length(myjobs) gt 0 }">
							<div class="pull-left">
								<h3>My Jobs</h3>
							</div>
							<div class="pull-right">
								<div class="btn-group">
									<button type="button" class="btn btn-success btn-filter"
										data-target="accepted">Accepted</button>
									<button type="button" class="btn btn-warning btn-filter"
										data-target="inprogress">In Progress</button>
									<button type="button" class="btn btn-danger btn-filter"
										data-target="rejected">Rejected</button>
									<button type="button" class="btn btn-default btn-filter"
										data-target="all">All</button>
								</div>
							</div>
							<div class="table-container">
								<table id="myjobs" class="table table-filter">
									<tbody>
										<c:forEach items="${myjobs}" var="myjob">
											<c:if test="${myjob.status == 'ACCEPTED'}">
												<tr data-status="accepted">
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
															<a href="#" class="pull-left"> <img
																src="${myjob.jobAdvert.company.pictureUrl}"
																class="media-photo">
															</a>
															<div>
																<span class="media-meta pull-right">${myjob.jobAdvert.dateCreated}</span>
																<h4 class="title">
																	${myjob.jobAdvert.title} <span
																		class="pull-right pagado">(Accepted)</span>
																</h4>
																<p class="summary">${myjob.jobAdvert.company.companyName}</p>
															</div>
														</div>
													</td>
												</tr>
											</c:if>
											<c:if test="${myjob.status == 'INPROGRESS'}">
												<tr data-status="inprogress">
													<td>
														<div class="ckbox myjobs-check">
															<input type="checkbox" id="checkbox3"> <label
																for="checkbox3"></label>
														</div>
													</td>
													<td><a href="javascript:;" class="star"> <i
															class="glyphicon glyphicon-star"></i>
													</a></td>
													<td>
														<div class="media">
															<a href="#" class="pull-left"> <img
																src="${myjob.jobAdvert.company.pictureUrl}"
																class="media-photo">
															</a>
															<div>
																<span class="media-meta pull-right">${myjob.jobAdvert.dateCreated}</span>
																<h4 class="title">
																	${myjob.jobAdvert.title} <span
																		class="pull-right pendiente">(In progress)</span>
																</h4>
																<p class="summary">${myjob.jobAdvert.company.companyName}</p>
															</div>
														</div>
													</td>
												</tr>
											</c:if>
											<c:if test="${myjob.status == 'REJECTED'}">
												<tr data-status="rejected">
													<td>
														<div class="ckbox myjobs-check">
															<input type="checkbox" id="checkbox2"> <label
																for="checkbox2"></label>
														</div>
													</td>
													<td><a href="javascript:;" class="star"> <i
															class="glyphicon glyphicon-star"></i>
													</a></td>
													<td>
														<div class="media">
															<a href="#" class="pull-left"> <img
																src="${myjob.jobAdvert.company.pictureUrl}"
																class="media-photo">
															</a>
															<div>
																<span class="media-meta pull-right">${myjob.jobAdvert.dateCreated}</span>
																<h4 class="title">
																	${myjob.jobAdvert.title} <span
																		class="pull-right cancelado">(Rejected)</span>
																</h4>
																<p class="summary">${myjob.jobAdvert.company.companyName}</p>
															</div>
														</div>
													</td>
												</tr>
											</c:if>
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
<!-- script references -->
<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/custom.js" type="text/javascript"></script>
</body>
</html>