<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.notification .badge {
	top: -10;
	right: -10px;
	padding: 2px 6px;
	border-radius: 50%;
	background-color: red;
	color: white;
}
</style>
</head>

<%
	int length = 0;
	if (request.getAttribute("lengthOfCart") != null)
		length = (int) request.getAttribute("lengthOfCart");
%>
<body>
	<header id="primary-header">
	<div class="header">
		<a href="home.hr" class="logo">Finance Management</a>
		<div class="header-right">
			<a href="home.hr">Home</a>

			<%
				if (session.getAttribute("userId") != null) {
					int userId = (int) session.getAttribute("userId");
			%>

			<a href="userDash.hr">My Profile</a> <a
				href="viewCart.hr?userId=<%=userId%>" class="notification">View
				Cart<span class="badge"><%=length%></span>
			</a> <a href="logout.hr">Log out</a>
			<%
				} else {
			%>
			<button
				onclick="document.getElementById('id01').style.display='block'"
				style="width: auto;" class="loginbuttonhead">Login</button>
			<a href="selectCard.hr">Register</a>
			<%
				}
			%>
			<div class="dropdown">
				<button class="dropbtn">FAQ's</button>
				<div class="dropdown-content">
					<a href="goldFaq.hr">Gold card FAQ</a> <a href="platinumFaq.hr">Platinum
						Card FAQ</a>
				</div>
			</div>

		</div>
	</div>
	</header>

	<!-----------------------------------Login form starts here-------------------------------------->

	<div id="id01" class="modal">
		<div class="modal-content animate">
			<form action="login.hr" method="post">
				<div class="imgcontainer">
					<span
						onclick="document.getElementById('id01').style.display='none'"
						class="close" title="Close Modal">&times;</span> <img
						src="${pageContext.request.contextPath}/resources/images/login.png"
						alt="Avatar" class="avatar">
				</div>

				<div class="container">
					<label for="uname"><b>Email Id</b></label> <input type="text"
						placeholder="Enter Email Id" name="email" required> <label
						for="psw"><b>Password</b></label> <input type="password"
						placeholder="Enter Password" name="uPass" required>

					<button type="submit" id="loginbutton">Login</button>

				</div>


			</form>
			<a href="forgotPassword.hr">Forgot Password?</a>
			Not Registered? <a href="selectCard.hr"
				style="text-decoration: none;">Register Now</a>
		</div>
	</div>

	<script type="text/javascript">
		// Get the modal
		var modal = document.getElementById('id01');

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>

	<!-----------------------------------Login form ends here-------------------------------------->
</body>
</html>