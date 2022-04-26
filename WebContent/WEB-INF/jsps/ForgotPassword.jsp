<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%if(request.getAttribute("otp").equals("notSent")) {%>
	<form action="generateOtpForgotPassword.hr" method="post">
		Enter Your email:<br>
		<input type="email" name="email" required><br><br>
		<input type="submit" value="Send Otp">
	</form>
	<%} else if(request.getAttribute("otp").equals("sent") || request.getAttribute("isVerified").equals("no")){%>
		<form action="verifyOtpForgotPassword.hr" method="post">
			Enter Otp:<br>
			<input type="text" name="enteredOtp" required><br><br>
			<input type="hidden" name="sentOtp" value="<%=request.getAttribute("otpp")%>">
			<input type="hidden" name="email" value="<%=request.getAttribute("email")%>">
			<input type="submit" value="Verify Otp">
		</form>
	<%}%>
</body>
</html>