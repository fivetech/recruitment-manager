<%@page import="com.tolga.model.JobAdvert"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	@SuppressWarnings("unchecked")
	List<JobAdvert> adverts = (List<JobAdvert>) request.getAttribute("jobAdverts");
	pageContext.setAttribute("adverts", adverts);
%>
<jsp:include page="../template/navbarHr.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<section id="myjobscontent" class="content">
			<div class="col-md-8 col-md-offset-2">
				<div id="myjobspanel" class="panel panel-default">
					<div class="panel-body">
						<div class="pull-left">
							<h3>Job Adverts</h3>
						</div>
						<div class="pull-right">
							<div class="btn-group">
								<button type="button" class="btn btn-success btn-filter"
									data-target="active">Active</button>								
								<button type="button" class="btn btn-danger btn-filter"
									data-target="passive">Passive</button>
								<button type="button" class="btn btn-default btn-filter"
									data-target="all">All</button>
							</div>
						</div>
						<div class="table-container">
							<table id="myjobs" class="table table-filter">
								<tbody>
									<c:forEach items="${adverts}" var="advert">
										<c:if test="${advert.active == true}">
											<tr class="hr-advert-item" data-href="RecruitmentManager?action=hr_list_advert_apps&advertId=${advert.id}" data-status="active">
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
															src="${advert.company.pictureUrl}"
															class="media-photo">
														</a>
														<div>
															<span class="media-meta pull-right">${advert.dateExpired}</span>
															<h4 class="title">
																${advert.title} <span class="pull-right pagado">(Active)</span>
															</h4>
															<p class="summary">${advert.company.companyName}</p>
														</div>
													</div>
												</td>
											</tr>
										</c:if>										
										<c:if test="${advert.active == false}">
											<tr class="hr-advert-item" data-href="RecruitmentManager?action=hr_list_advert_apps&advertId=${advert.id}" data-status="passive">
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
															src="${advert.company.pictureUrl}"
															class="media-photo">
														</a>
														<div>
															<span class="media-meta pull-right">${advert.dateExpired}</span>
															<h4 class="title">
																${advert.title} <span class="pull-right cancelado">(Passive)</span>
															</h4>
															<p class="summary">${advert.company.companyName}</p>
														</div>
													</div>
												</td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
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