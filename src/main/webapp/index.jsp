<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template/navbar.jsp"></jsp:include>

<div class="container">
	<div class="jumbotron text-center">
		<h1>How can I find a job ?</h1>
		<p class="lead">All you have to do is to type something that describes your dream job and our full text search system finds it immediately. </p>

		<form action="RecruitmentManager?action=text_search" method="post">
			<div id="custom-search-input">
				<div class="input-group col-md-12">
					<input type="text" name="text-search"
						class="  search-query form-control" placeholder="Find a job" /> <span
						class="input-group-btn">
						<button class="btn btn-danger" type="button">
							<span class=" glyphicon glyphicon-search"></span>
						</button>
					</span>
				</div>
			</div>
		</form>
	</div>

	<div class="row">
		<div class="col-lg-6">
			<h4>Full-Text Search</h4>
			<p>Full-Text search capabilities make searching fast and efficient.</p>

			<h4>Fast E-mail delivery</h4>
			<p>When you application is accepted, you receive an email immediately. </p>
		</div>

		<div class="col-lg-6">
			<h4>Sign in with LinkedIn</h4>
			<p>Sign in with LinkedIn feature makes authentication fast and secure </p>

			<h4>Easy Application</h4>
			<p>Users can apply an job in just seconds.</p>
		</div>
	</div>

	<hr>
</div>
<!-- /container -->
<!-- script references -->
<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/custom.js" type="text/javascript"></script>

</body>
</html>