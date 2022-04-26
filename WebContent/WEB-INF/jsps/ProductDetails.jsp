<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.lti.fg.entities.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/productDescription.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css">

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
	String contextPath = request.getContextPath();
	Product product = (Product) request.getAttribute("product");
	String link = "";

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
					<a href="goldFaq.html">Gold card FAQ</a> <a href="#">Titatinum
						Card FAQ</a>
				</div>
			</div>

		</div>
	</div>
	</header>

	<!-----------------------------------Login form starts here-------------------------------------->

	<div id="id01" class="modal">

		<form class="modal-content animate" action="login.hr" method="post">
			<div class="imgcontainer">
				<span onclick="document.getElementById('id01').style.display='none'"
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
				<label> <input type="checkbox" checked="checked"
					name="remember"> Remember me
				</label>
			</div>

			<div class="container" style="background-color: #f1f1f1">
				<button type="button"
					onclick="document.getElementById('id01').style.display='none'"
					class="cancelbtn">Cancel</button>
				<span class="psw">Forgot <a href="#">password?</a></span>
			</div>
		</form>
	</div>

	<div class="productcontainer">

		<!-- Left Column / Headphones Image -->
		<div class="left-column">
			<%
				link = contextPath + product.getProductImageLink();
			%>
			<img id="productimage" src="<%=link%>" alt="">

		</div>


		<!-- Right Column -->
		<div class="right-column">

			<!-- Product Description -->
			<div class="product-description">

				<h1><%=product.getProductName()%></h1>
				<p><%=product.getProductDescription()%></p>
			</div>

			<!-- Product Pricing -->
			<div class="product-price">
				<span id="price"> &#8377;<%=product.getProductCost()%>
				</span>
				<%
					if (session.getAttribute("userId") != null) {
				%>
				<a href="addToCart.hr?id=<%=product.getProductId()%>"
					id="addtocartbutton" onclick="">Add to cart</a>
				<%
					} else {
				%>
				<button
					onclick="document.getElementById('id01').style.display='block'"
					style="width: auto;" class="loginbuttonhead">Add To Cart</button>
				<%
					}
				%>
				<a href="home.hr"><button type="button"
						onclick="document.getElementById('id01').style.display='none'"
						class="cancelbtn">Cancel</button></a>

			</div>

		</div>
		<!-------------Right column ends here------- -->

	</div>


	<%
		if (session.getAttribute("userId") != null) {
	%>
	<script type="text/javascript">
		function addtocart() {
			alert("Product added to cart");
		}
		// Get the modal
		var modal = document.getElementById('id01');

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>
	<%
		}
	%>

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

</body>
</html>