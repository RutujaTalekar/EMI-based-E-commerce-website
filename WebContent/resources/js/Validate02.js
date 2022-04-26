$(document).ready(function(){

	$('form[id="submitform"]').validate({
		
		rules: {				
			useremail: {
				required: true,
				email: true,
			},
			psword: {
				required: true,
				minlength: 6,
				maxlength: 10,
			},
			confirmPassword:{
				required:true,
				minlength:6,
				maxlength: 10,
				equalTo:'#psword'
			},
			phoneno:{
				required:true,
				minlength:10,
				maxlength:10
			},
			address:'required',
			username:'required',
			sba:'required',
			ifsc:'required',
		},
		messages: {
			useremail: 'Enter a valid email',
			username: 'This field is required',
			psword: {
				minlength: 'Password must be at least 8 characters long'
			},
			confirmPassword:{
				minlength: 'Password must be at least 8 characters long',
				equalTo:'Passwords don\'t match'
			},
			address:'This field is required',
			phoneno:{
				required:'This field is required',
				minlength:'Invalid Number',
				maxlength:'Invalid Number'
			},
			sba:'This field is required',
			ifsc:'This field is required'
		},
		submitHandler: function(form) {
			form.submit();
		}
	});

});


