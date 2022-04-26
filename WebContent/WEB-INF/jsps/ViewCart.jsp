<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,com.lti.fg.entities.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<%
	if (session.getAttribute("userId") == null)
		response.sendRedirect("home.hr");
%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/addtocart.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css">
<script
	src="${pageContext.request.contextPath}/resources/js/jquerySelector.js"
	async type="text/javascript"></script>

<title>Insert title here</title>

<%
	String userStatus = (String) request.getAttribute("userStatus");
	String cardStatus = (String) request.getAttribute("cardStatus");
	double cardBalance = (double) request.getAttribute("cardBalance");
%>
</head>
<body>

	<%@include file="header.jsp"%>

	<%
		String setAlert = "";
		if (request.getAttribute("setAlert") != null) {
			setAlert = (String) request.getAttribute("setAlert");
		}
		if (setAlert.equalsIgnoreCase("alert")) {
	%>
	<script type="text/javascript">
		alert("Sorry, You have insufficient Credit Points or You are not verified!");
	</script>

	<%
		}
	%>
	<%
		String contextPath = request.getContextPath();
		String link = "";
	%>
	<%
		List<Product> productList = (List<Product>) request.getAttribute("productList");
	%>
	<%
		List<Integer> quantList = (List<Integer>) request.getAttribute("Quantity");
	%>
	<div class="containercart">

		<div class="cartheader">
			<img
				src="${pageContext.request.contextPath}/resources/images/cartIcon.png"
				id="headIcon" alt="">
			<h1 id="shoppingHead">Shopping Cart</h1>
		</div>

		<div class="content">
			<table id="customers">
				<tr>
					<th>Product</th>
					<th>Product name</th>
					<th>Quantity</th>
					<th>Amount</th>
					<th>Action</th>
				</tr>
				<%
					for (int i = 0; i < productList.size(); i++) {
				%>
				<tr>
					<%
						link = contextPath + productList.get(i).getProductImageLink();
					%>
					<td><img src="<%=link%>" style="height: 100px; width: 100px;"
						alt=""></td>
					<td><%=productList.get(i).getProductName()%></td>
					<td>
						<div class="col col-qty layout-inline">

							<%=quantList.get(i)%>

						</div>
					</td>
					<td><%=productList.get(i).getProductCost()%></td>
					<td>
						<div class="product-removal">

							<a
								href="deleteOrder.hr?prodId=<%=productList.get(i).getProductId()%>"><button
									class="remove-product">Remove</button></a>
						</div>
					</td>

				</tr>
				<%
					}
				%>
			</table>
		</div>
		<div class="belowcart">

			<div class="row layout-inline">
				<div class="col">
					<p id="label">TOTAL</p>
					<p class="result" id="result"><%=request.getAttribute("cartCost")%></p>
				</div>
			</div>
			<hr>
			<div class="row layout-inline">
				<div class="col">
					<p id="label">EMI PERIOD</p>
					<select class="dropdowncartcart" id="months" onchange="divideBy()">
						<option class="dropdowncart-content" value="NaN">--
							Select EMI option --</option>
						<option class="dropdowncart-content" value="3">3 Months</option>
						<option class="dropdowncart-content" value="6">6 Months</option>
						<option class="dropdowncart-content" value="9">9 Months</option>
						<option class="dropdowncart-content" value="12">1 Year</option>
					</select>
				</div>
			</div>
			<hr>
			<div class="row layout-inline">
				<div class="col">
					<p id="label">EMI</p>


					<p id="emi"
						style="color: white; transform: translate(200%, -50%); font-weight: bold; font-size: 20px;"></p>

				</div>
			</div>
			<hr>
			<div class="row layout-inline">
				<div class="col">

					<%
						int userId = 0;
						if (session.getAttribute("userId") == null)
							userId = 0;
						else {
							userId = (Integer) session.getAttribute("userId");
						}
					%>
					<form action="confirmOrder.hr" method="post">
						<input type="hidden" value='<%=request.getAttribute("cartCost")%>'
							name="totalCost"> <input type="hidden"
							value="<%=userId%>" name="userId"> <input type="hidden"
							id="tenurev" name="tenure" value=""> <input type="hidden"
							id="userStatus" name="userStatus" value="<%=userStatus%>">
						<input type="hidden" id="cardBalance" name="cardBalance"
							value="<%=cardBalance%>"> <input type="hidden"
							id="cardStatus" name="cardStatus" value="<%=cardStatus%>">
						<input type="submit" value="Buy" id="cartBuyButton">
					</form>
					<%--  <a href="confirmOrder.hr?totalCost=<%=request.getAttribute("cartCost") %>&userId=<%=userId%>&tenure=<%=tenure%>"><button>Buy</button></a> --%>

				</div>
			</div>

		</div>

	</div>
	<div></div>
	<br>
	<a href="home.hr">Home</a>

	<script type="text/javascript">
		function divideBy() {
			var num1 = document.getElementById("result").textContent;
			var num2 = document.getElementById("months").value;
			var num3 = num1 / num2;
			var num4 = Math.round(num3);
			document.getElementById("emi").innerHTML = num4;
			//document.getElementById("tenurev").value = num2;
			$('#tenurev').val(num2);

		}
	</script>


</body>

</html>