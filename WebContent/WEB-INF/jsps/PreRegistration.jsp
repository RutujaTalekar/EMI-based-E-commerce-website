<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/form.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/Validate02.js"
	async></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jqueryValidation.js"
	async type="text/javascript"></script>

<style type="text/css">
label.error {
	display: block;
	width: 100%;
	color: tomato;
	text-align: right;
}

input.error {
	border-bottom-color: tomato;
	border-bottom-width: 5px;
}
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<h3>Registration form</h3>

	<div class="container">

		<%
			String cardType = (String) request.getAttribute("cardType");
		%>
		<form action="registration.hr" id="submitform" method="post">

			<label for="username">Username</label> <input type="text"
				id="username" name="username" placeholder="Enter username">

			<label for="email">Email id</label> <input type="email"
				id="useremail" name="useremail" placeholder="Your Email Id..">

			<label for="phoneno">Phone no.</label> <input type="number"
				id="phoneno" name="phoneno" placeholder="Enter Phone no."> <label
				for="password">Password</label> <input type="password"
				placeholder="Password" id="psword" name="psword"> <label
				for="confirm_password">Confirm Password</label> <input
				type="password" placeholder="Confirm Password" id="confirmPassword"
				name="confirmPassword"> <label for="address">Address</label>
			<input type="text" id="address" name="address"> <label
				for="sba">Saving Bank Account</label> <input type="number" id="sba"
				name="sba"> <label for="ifsc">IFSC code</label> <input
				type="text" id="ifsc" name="ifsc"> <input type="hidden"
				value="<%=cardType%>" name="cardType"> <input type="submit"
				value="Submit" id="submit">
		</form>
	</div>

</body>
</html>