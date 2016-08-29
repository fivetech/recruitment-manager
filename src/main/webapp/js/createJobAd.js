$(document).ready(function () {
    //Initialize tooltips
    $('.nav-tabs > li a[title]').tooltip();
    
    //Wizard
    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

        var $target = $(e.target);
    
        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    });

    $(".next-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        $active.next().removeClass('disabled');
        nextTab($active);

    });
    $(".prev-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        prevTab($active);

    });
});

function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
}
function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
}
$(function()
		{
		    $(document).on('click', '.btn-add-description', function(e)
		    {
		        e.preventDefault();

		        var controlForm = $('#step1:first'),
		            currentEntry = $(this).parents('#step1 .entry:first'),
		            newEntry = $(currentEntry.clone()).appendTo(controlForm);

		        newEntry.find('textarea').val('');
		        controlForm.find('.entry:not(:last) .btn-add-description')
		            .removeClass('btn-add-description').addClass('btn-remove-description')
		            .removeClass('btn-success').addClass('btn-danger')
		            .html('<span class="glyphicon glyphicon-minus"></span>');
		    }).on('click', '.btn-remove-description', function(e)
		    {
				$(this).parents('#step1 .entry:first').remove();

				e.preventDefault();
				return false;
			});
		});
$(function()
		{
		    $(document).on('click', '.btn-add-skill', function(e)
		    {
		        e.preventDefault();

		        var controlForm = $('#step2:first'),
		            currentEntry = $(this).parents('#step2 .entry:first'),
		            newEntry = $(currentEntry.clone()).appendTo(controlForm);
		        

		        newEntry.find('input').val('');
		        controlForm.find('.entry:not(:last) .btn-add-skill')
		            .removeClass('btn-add-skill').addClass('btn-remove-skill')
		            .removeClass('btn-success').addClass('btn-danger')
		            .html('<span class="glyphicon glyphicon-minus"></span>');
		        
		        
		    }).on('click', '.btn-remove-skill', function(e)
		    {
				$(this).parents('#step2 .entry:first').remove();

				e.preventDefault();
				return false;
			});
		});

/*Datepicker*/
var nowTemp = new Date();
var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
 
var checkin = $('#dpd1').datepicker({
	format: 'dd/mm/yyyy',
	onRender: function(date) {
    return date.valueOf() < now.valueOf() ? 'disabled' : '';
  }
}).on('changeDate', function(ev) {
  if (ev.date.valueOf() > checkout.date.valueOf()) {
    var newDate = new Date(ev.date)
    newDate.setDate(newDate.getDate() + 1);
    checkout.setValue(newDate);
  }
  checkin.hide();
  $('#dpd2')[0].focus();
}).data('datepicker');
var checkout = $('#dpd2').datepicker({
	format: 'dd/mm/yyyy',
	onRender: function(date) {
    return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
  }
}).on('changeDate', function(ev) {
  checkout.hide();
}).data('datepicker');