<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flippingCards.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
	<%@include file="header.jsp"%>
	<h1 style="text-align: center;">Select A New Card</h1>
	<!-------------------- Flip images gold and titanium card code starts here -------------------->
	<div class="flip-container1"
		style="display: inline-block; margin-left: 350px; margin-right: 100px;">
		<div class="flipper1">
			<div class="front1">

				<img
					src="${pageContext.request.contextPath}/resources/images/Gold_Card.png"
					alt="Denim Jeans" style="width: 90%; margin: 5px;">
				<h2>Gold Card</h2>
				<ul style="text-align: left;">
					<li class="price"><b>Card Type:</b> Gold Card</li>
					<li class="price"><b>First Year fee:</b> Rs.1,000 plus
						applicable taxes</li>
					<li class="price"><b>Second Year onwards:</b> Rs. 4,500 plus
						applicable taxes</li>

					<li class="price"><b>Reward Earn Rate</b> Earn one Membership
						Rewards Point for every Rs.50 spent except for spend at Fuel,
						Insurance, Utilities and Cash Transactions</li>
				</ul>
			</div>
			<div class="back1">
				<ul style="text-align: left;">
					<li class="price">MEMBERSHIP REWARD® POINT</li>
					<li class="price">ADDITIONAL REWARDS ON CARD RENEWAL</li>
					<li class="price">GOLD COLLECTION</li>
					<li class="price">SAVINGS ON TRAVEL</li>
				</ul>

				<!-- <form action="preRegistration.hr">
              	<input type="hidden" value="001">
                <input type="submit" value="Register">
               -->
				<a href="preRegistration.hr?cardType=Gold"><button
						style="font-size: 15px; width: 200px; background-color: black; color: white; padding: 5px;">Register</button></a>
				<form action=""></form>

			</div>
		</div>
	</div>

	<div class="flip-container2" style="display: inline-block;">
		<div class="flipper2">
			<div class="front2">
				<img
					src="${pageContext.request.contextPath}/resources/images/Titatinum_Card.png"
					alt="Denim Jeans" style="width: 90%; margin: 5px;">
				<h2>Titanium Card</h2>
				<ul style="text-align: left;">
					<li class="price"><b>Card Type:</b> Titanium Card</li>
					<li class="price"><b>First Year fee:</b> Rs.1,500 plus
						applicable taxes</li>
					<li class="price"><b>Second Year onwards:</b> Rs. 5,000 plus
						applicable taxes</li>

					<li class="price"><b>Reward Earn Rate</b> Earn one Membership
						Rewards Point for every Rs.50 spent except for spend at Fuel,
						Insurance, Utilities and Cash Transactions</li>
				</ul>

			</div>
			<div class="back2">
				<ul type="none" style="text-align: left;">
					<li class="price">MEMBERSHIP REWARD® POINT</li>
					<li class="price">ADDITIONAL REWARDS ON CARD RENEWAL</li>
					<li class="price">GOLD COLLECTION</li>
					<li class="price">SAVINGS ON TRAVEL</li>
				</ul>
				<!-- <form action="preRegistration.hr">
                <input type="submit" value="Register">
              </form> -->
				<a href="preRegistration.hr?cardType=Platinum"><button
						style="font-size: 15px; width: 200px; background-color: black; color: white; padding: 5px;">Register</button></a>
			</div>
		</div>
	</div>

	<!-------------------- Flip images gold and titanium card code ends here -------------------->
</body>
</html>