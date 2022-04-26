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
	href="${pageContext.request.contextPath}/resources/css/tableTransition.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/form.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/otp.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css">


<script
	src="${pageContext.request.contextPath}/resources/js/transitionJs.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-1.5.1.min.js"
	type="text/javascript"></script>

</head>

<body>


	<%
		int userId = (int) request.getAttribute("userId");
		long cardId = (long) request.getAttribute("cardId");
	%>


	<div class="cardCharges" style="margin:10%;">

		<div id="dialog">
			<div id="diaContent">
				<img
					src="${pageContext.request.contextPath}/resources/images/logo.png"
					id="otplogo" alt="">
				<p id="otpwelcome">Welcome to otp Verification</p>
				<p id="weProvide">We provide No Cost EMI but it is mandatory to
					pay the intial charges.</p>



				<br>

				<form action="generateOtp.hr" method="post">
					<input type="hidden" id="otppay" name="userId" value="<%=userId%>"
						align="center"> <input type="hidden" name="cardId"
						value="<%=cardId%>"> <input type="submit"
						value="Generate OTP">
				</form>


				<form action="verifyOtp.hr" method="post">
					<div id="otpform">
						<h3>Please enter the 6-digit verification code we sent via
							SMS:</h3>
						<br> <input type="hidden" name="generatedOtp"
							value='<%=request.getAttribute("otp")%>'><br> <input
							type="hidden" name="userId" value="<%=userId%>"><br>
						<input type="hidden" name="cardId" value="<%=cardId%>"><br>
						<input type="text" name="otp"><br> <input
							type="submit" value="Verify OTP" id="otpverify" required>Verify<a></a><br>
					</div>
				</form>


				<form action="generateOtp.hr" method="post">
					<input type="hidden" id="otppay" name="userId" value="<%=userId%>"
						align="center"> <input type="hidden" name="cardId"
						value="<%=cardId%>"> Didn't receive the code?<br /> <input
						type="submit" value="Resend OTP">
				</form>



			</div>
		</div>
	</div>





</body>
</html>