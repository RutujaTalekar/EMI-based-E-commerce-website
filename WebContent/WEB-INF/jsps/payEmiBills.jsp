<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/adminpage.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/card.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/otp.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">



<script
	src="${pageContext.request.contextPath}/resources/js/transitionJs.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-1.5.1.min.js"
	type="text/javascript"></script>
<style type="text/css">
#otplogo {
	width: 250px;
	height: 100px;
}

#enterbillotp {
	width: 250px;
	height: 30px;
	margin-bottom: 10%;
	margin-left: 20%;
}

#otppayemi {
	background-color: #4CAF50;
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin-left: 35%;
	margin-right: 10%;
}
</style>
</head>

<body>

	<%
		Integer userId = (Integer) session.getAttribute("userId");
	%>


	<div id="dialog">

		<img
			src="${pageContext.request.contextPath}/resources/images/logo.png"
			id="otplogo" alt="">
		<p id="otpwelcome">Welcome to easy EMI pay</p>
		<p id="weProvide">Please generate OTP to proceed with EMI payment</p>


		<br>

		<form action="generateOtpForEmi.hr" method="post" id="otpform">

			<input type="hidden" name="userId" value="<%=userId%>"> <input
				type="hidden" name="id" value='<%=request.getAttribute("id")%>'>
			<input type="hidden" name="monthlyEmi"
				value='<%=request.getAttribute("monthlyEmi")%>'> <input
				type="submit" value="Generate OTP" id="otppay">
		</form>



		<form action="verifyOtpForEmi.hr" method="post">
			<p id="weProvide">Enter 6 digit OTP :</p>
			<br> <input type="hidden" name="id"
				value='<%=request.getAttribute("id")%>'> <input
				type="hidden" name="generatedOtp"
				value='<%=request.getAttribute("otp")%>'> <input
				type="hidden" name="userId" value="<%=userId%>"> <input
				type="hidden" name="monthlyEmi"
				value='<%=request.getAttribute("monthlyEmi")%>'> <input
				type="text" name="otp" id="enterbillotp"> <input
				type="submit" id="otppayemi" value="Pay EMI" required>
		</form>



	</div>
	<div></div>


</body>
</html>