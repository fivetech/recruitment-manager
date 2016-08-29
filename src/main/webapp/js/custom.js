/**
 * 
 */
/*
 * $('.navbar .dropdown').hover(function() {
 * $(this).find('.dropdown-menu').first().stop(true, true).slideDown(150); },
 * function() { $(this).find('.dropdown-menu').first().stop(true,
 * true).slideUp(105) });
 */

/* Sweet alert Apply Button */
$('.swt-btn-apply')
		.click(
				function() {
					var jobId = $(this).data('id');
					var companyImg = $('#advert-' + jobId + ' img').attr('src');
					var advertTitle = $(this).data('title');
					var user = $(this).data('user');
					if (user != "") {
						swal(
								{
									imageUrl : companyImg,
									title : advertTitle,
									text : 'Write an introduction',
									type : 'input',
									showCancelButton : true,
									confirmButtonText : "Apply",
									closeOnConfirm : false,
									animation : "slide-from-top",
									inputPlaceholder : "Write something",
								},
								function(inputValue) {
									if (inputValue === false)
										return false;

									if (inputValue === "") {
										swal
												.showInputError("You need to write something!");
										return false;
									}
									$
											.post(
													"RecruitmentManager?action=apply&jobId="
															+ jobId
															+ "&introduction="
															+ inputValue,
													function(data) {
														swal(
																"Nice!",
																'Your introduction: '
																		+ inputValue,
																"success");
														setTimeout(
																function() {
																	location.href = "RecruitmentManager?action=list_jobs";
																},1000);
													});
								});
					} else {
						swal({
							title : "You need to login to make application",
							showConfirmButton : false,
							showCancelButton : true,
							text : "<a style='width:50%;margin-left: auto;margin-right: auto;' class='btn btn-block btn-social btn-linkedin' href='/LoginServlet?action=login'> <span class='fa fa-linkedin'></span> Sign in with LinkedIn </a>",
							html : true
						});
					}

				});
/* sweet alert view button */
$('.swt-btn-view').click(function() {
	var jobId = $(this).data('id');
	location.href = "RecruitmentManager?action=job_details&jobId=" + jobId;
});
/* user logout */
$('#logout-a').click(function() {
	$.post("LoginServlet?action=logout", function(data) {
		logout();
	});
});

function logout() {
	try {
		IN.User.logout();
	} catch (err) {
		console.log(err);
	}
	setTimeout(goToHome());
}

function goToHome() {
	location.href = "RecruitmentManager?action=index";
}
/*hr logout*/
$('#hr-logout').click(function() {
	var href = $(this).data("href");
	$.post(href, function(data) {
		setTimeout(function() {
			location.href = "RecruitmentManager?action=index";
		});
	});
});
/* Apply button details */
$('.apply-button-details')
		.click(
				function() {
					var jobId = $(this).data('id');
					var companyImg = $('.company-img-mid').attr('src');
					var advertTitle = $(this).data('title');
					var user = $(this).data('user');
					if (user != "") {
						swal(
								{
									imageUrl : companyImg,
									title : $(this).data('title'),
									text : 'Write an introduction',
									type : 'input',
									showCancelButton : true,
									confirmButtonText : "Apply",
									closeOnConfirm : false,
									animation : "slide-from-top",
									inputPlaceholder : "Write something",
								},
								function(inputValue) {
									if (inputValue === false)
										return false;

									if (inputValue === "") {
										swal
												.showInputError("You need to write something!");
										return false;
									}
									$
											.post(
													"RecruitmentManager?action=apply&jobId="
															+ jobId
															+ "&introduction="
															+ inputValue,
													function(data) {
														swal(
																"Nice!",
																'Your introduction: '
																		+ inputValue,
																"success");
														setTimeout(
																function() {
																	location.href = "RecruitmentManager?action=list_jobs";
																},1000);
													});
								});
					} else {
						swal({
							title : "You need to login to make application",
							showConfirmButton : false,
							showCancelButton : true,
							text : "<a style='width:50%;margin-left: auto;margin-right: auto;' class='btn btn-block btn-social btn-linkedin' href='/LoginServlet?action=login'> <span class='fa fa-linkedin'></span> Sign in with LinkedIn </a>",
							html : true
						});
					}

				});
/* Table Filter */
$(document).ready(function() {

	$('.star').on('click', function() {
		$(this).toggleClass('star-checked');
	});

	$('.ckbox label').on('click', function() {
		$(this).parents('tr').toggleClass('selected');
	});

	$('.btn-filter').on('click', function() {
		var $target = $(this).data('target');
		if ($target != 'all') {
			$('.table tr').css('display', 'none');
			$('.table tr[data-status="' + $target + '"]').fadeIn('slow');
		} else {
			$('.table tr').css('display', 'none').fadeIn('slow');
		}
	});

});
/* Job advert applied */
$('.swt-btn-applied').click(function() {
	sweetAlert("Oops...", "You already applied this job", "error");
});
/* HR Advert Item */
$('.hr-advert-item').click(function() {
	var hrefLoc = $(this).data('href');
	location.href = hrefLoc;
});
/* HR Application Item */
var href;
$('.hr-app-item').click(function() {
	var pictureUrl = $(this).data('picture');
	var name = $(this).data('user');
	var intro = $(this).data('intro');
	href = $(this).data('href');
	$('.profile-modal-center img').attr("src", pictureUrl);
	$('.profile-modal-center .media-heading').text(name);
	$('.app-introduction span').text(intro);
	$('.app-intro-head').text("Introduction: ");
	$('#myModalLabel').text("More About " + name);
});

/* Accept Button */
$('.accept-button').click(function() {
	$.post(href + "&status=accepted", function(data) {
		setTimeout(function() {
			location.reload();
		});
	});
});
/* Reject Button */
$('.reject-button').click(function() {
	$.post(href + "&status=rejected", function(data) {
		setTimeout(function() {
			location.reload();
		});
	});
});
/*hr-app-user*/
$('.hr-app-user').click(function(){
	var id = $(this).data("id");
	location.href="RecruitmentManager?action=hr_user_applications&userId="+id;
});