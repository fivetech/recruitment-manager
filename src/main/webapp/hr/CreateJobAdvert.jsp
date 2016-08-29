<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../template/navbarHr.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<section>
			<div class="wizard">
				<div class="wizard-inner">
					<div class="connecting-line"></div>
					<ul class="nav nav-tabs" role="tablist">

						<li role="presentation" class="active"><a href="#step1"
							data-toggle="tab" aria-controls="step1" role="tab" title="Step 1">
								<span class="round-tab"> <i
									class="glyphicon glyphicon-list-alt hr-icons"></i>
							</span>
						</a></li>

						<li role="presentation" class="disabled"><a href="#step2"
							data-toggle="tab" aria-controls="step2" role="tab" title="Step 2">
								<span class="round-tab"> <i
									class="glyphicon glyphicon-list hr-icons"></i>
							</span>
						</a></li>
						<li role="presentation" class="disabled"><a href="#step3"
							data-toggle="tab" aria-controls="step3" role="tab" title="Step 3">
								<span class="round-tab"> <i
									class="glyphicon glyphicon-calendar hr-icons"></i>
							</span>
						</a></li>

						<li role="presentation" class="disabled"><a href="#complete"
							data-toggle="tab" aria-controls="complete" role="tab"
							title="Complete"> <span class="round-tab"> <i
									class="glyphicon glyphicon-ok hr-icons"></i>
							</span>
						</a></li>
					</ul>
				</div>
				<div class="col-md-8 col-md-offset-2">
					<form role="form" method="post" action="RecruitmentManager?action=hr_create_advert">
						<div class="tab-content">
							<div class="tab-pane active" role="tabpanel" id="step1">
								<ul class="list-inline pull-right">
									<li><button type="button"
											class="btn btn-primary next-step">Save and continue</button></li>
								</ul>
								<h3>Step 1</h3>
								<p>Enter a Job Title</p>
								<div class="input-group col-xs-9">
									<input type="text" class="form-control" id="title" name="title"
										placeholder="Title" required>
								</div>
								<hr />
								<p>Job Descriptions</p>
								<div class="entry input-group col-xs-9 job-description">
									<textarea class="form-control"
										style="margin: 0px; height: 100px; width: 501px; resize: none;"
										name="jobDescriptions" placeholder="Description"></textarea>
									<span class="input-group-btn">
										<button class="btn btn-success btn-add-description"
											type="button">
											<span class="glyphicon glyphicon-plus"></span>
										</button>
									</span>
								</div>
							</div>
							<div class="tab-pane" role="tabpanel" id="step2">
								<ul class="list-inline pull-right">
									<li><button type="button"
											class="btn btn-default prev-step">Previous</button></li>
									<li><button type="button"
											class="btn btn-primary next-step">Save and continue</button></li>
								</ul>
								<h3>Step 2</h3>
								<p>Required Skills</p>
								<div class="entry input-group col-xs-9 job-skills">
									<input type="text" class="form-control" name="requiredSkills"
										placeholder="Skill" /> <span class="input-group-btn">
										<button id="dateActive" class="btn btn-success btn-add-skill"
											type="button">
											<span class="glyphicon glyphicon-plus"></span>
										</button>
									</span>
								</div>								
							</div>
							<div class="tab-pane" role="tabpanel" id="step3">
							<ul class="list-inline pull-right">
									<li><button type="button"
											class="btn btn-default prev-step">Previous</button></li>
									
									<li><button type="submit"
											class="btn btn-primary btn-info-full next-step">Finish</button></li>
								</ul>
								<h3>Step 3</h3>	
								<p>Select Date</p>							
								<table class="table">
									<thead>
										<tr>
											<th>Activated Date <input name="activatedDate" type="text" class="span2 form-control" value=""
												id="dpd1"></th>
											<th>Expiry Date <input name="expiryDate" type="text" class="span2 form-control" value=""
												id="dpd2"></th>
										</tr>
									</thead>
								</table>
							</div>
							<div class="tab-pane" role="tabpanel" id="complete">
								<h3>Complete</h3>
								<p>You have successfully completed all steps.</p>
							</div>
							<div class="clearfix"></div>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
</div>
<br />
<br />
<br />
<!-- /container -->
<!-- script references -->
<script src="../js/jquery-2.2.4.min.js"></script>
<script src="../js/bootstrap-datepicker.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/custom.js"></script>
<script src="../js/createJobAd.js"></script>

</body>
</html>