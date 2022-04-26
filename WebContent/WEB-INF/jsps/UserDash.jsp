<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.lti.fg.entities.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>



<head>
<%if(session.getAttribute("userId")==null)
	response.sendRedirect("home.hr");
	%>
<meta charset="utf-8" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Page Title</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

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


<%
	if(request.getAttribute("auc")==null){
		response.sendRedirect("home.hr");
	}

	ActiveUserCard auc = (ActiveUserCard) request.getAttribute("auc");
	User user = (User) request.getAttribute("user");
	List<OrderHistory> orderHistoryList = (List<OrderHistory>) request.getAttribute("orderHistoryList");
	List<OrderDetails> orderDetailsList = (List<OrderDetails>) request.getAttribute("orderDetailsList");
	List<Product> productList = (List<Product>) request.getAttribute("productList");
	List<List<EmiHistory>> emiHistoryList = (List<List<EmiHistory>>) request.getAttribute("emiHistoryList");
	java.util.Date today = Calendar.getInstance().getTime();
		
%>


<body>
	<%@include file="header.jsp"%>
	<!------------------------ Admin head -------------------------->


	<a class="mobile">MENU</a>

	<!-- ---------------Admin conatiner starts here ------------------------------------------ -->

	<div id="admincontainer">

		<!-- -----------------Side bar code starts here--------------------------- -->
		<div class="sidebar">
			<ul id="nav">
				<li><span> <img
						src="${pageContext.request.contextPath}/resources/images/adminlogo.png"
						width="32" height="32" alt="">
				</span><a href="#" id="cardDetails">Card Details</a></li>

				<li><span> <img
						src="${pageContext.request.contextPath}/resources/images/adminlogo.png"
						width="32" height="32" alt="">
				</span><a href="#" id="orderDetails">Orders</a></li>

				<li><span> <img
						src="${pageContext.request.contextPath}/resources/images/adminlogo.png"
						width="32" height="32" alt="">
				</span><a href="#" id="emiDetails">EMI Details</a></li>


				<li><span> <img
						src="${pageContext.request.contextPath}/resources/images/adminlogo.png"
						width="32" height="32" alt="">
				</span><a href="#" id="uploadDocs">Upload Documents</a></li>
			</ul>
		</div>

		<!-- -----------------Side bar code ends here--------------------------- -->



		<div class="content">
			<h1>Dashboard</h1>
			<img
				src="${pageContext.request.contextPath}/resources/images/welcome.jpg"
				height="300px" width="600px" style="margin-left: 20%;" alt="">
			<br>
		</div>


		<!-- ------------------------------  CARD DETAILS PAGE  ----------------------------- -->
		<div class="cardDetails">

			<div class="inner">
				<div class="card">
					<div id="imgcustomercardimage">
						<img
							src="${pageContext.request.contextPath}/resources/images/logo.png"
							alt="logo" height="30px" width="80px" />
					</div>
					<div class="alignUserInfo">
						<h2 id="number"><%=auc.getCardId()%></h2>
						<h3 id="name"><%=user.getUserName()%></h3>
						<h3>
							Validity :
							<%=auc.getCardValidity()%></h3>
						<h2>
							Type :
							<%=auc.getCardType()%></h2>
					</div>
					<%
						String status = auc.getUserStatus();
						if (status.equalsIgnoreCase("Verified")) {
					%>

					<a href="#"><h3 style="padding: 5% 35%; color: #33cc33;"><%=auc.getUserStatus()%></h3></a>
					<%
						} else if (status.equalsIgnoreCase("not verified") || status.equalsIgnoreCase("rejected")) {
					%>
					<a href="#"><h3 style="padding: 5% 35%; color: tomato;"><%=auc.getUserStatus()%></h3></a>
					<%
						}
					%>
				</div>
			</div>
			<%
				int totalCredit = 0;
				if (auc.getCardType().equalsIgnoreCase("gold"))
					totalCredit = 100000;
				else
					totalCredit = 200000;
			%>

			<div class="cred_info">
				<h3 class="cred_content">
					Total Credit:
					<%=totalCredit%></h3>
				<h3 class="cred_content">
					Credit Used:
					<%=(totalCredit - auc.getCardBalance())%></h3>
				<h3 class="cred_content">
					Credit Balance:
					<%=auc.getCardBalance()%></h3>
			</div>
		</div>
		<!-- ------------------------------  ORDER DETAILS PAGE  ----------------------------- -->
		<div class="orderDetails">
			<h1 class="EMIhead">Order Details</h1>

			<table id="orderRow">
				<tr>
					<th>Order Id</th>
					<th>Product Name</th>
					<th>Date Of Order</th>
					<th>Product Cost</th>
				</tr>
				<%
					for (int i = 0; i < orderHistoryList.size(); i++) {
				%>
				<%
					int prodId = orderHistoryList.get(i).getProductId();
						String productName = "";
						double prodCost = 0.0;
						java.sql.Date orderDate = null;
				%>
				<tr>
					<td style="padding-left: 5%;"><%=orderHistoryList.get(i).getOrderId()%></td>
					<%
						for (Product product : productList) {
								if (product.getProductId() == prodId) {
									productName = product.getProductName();
									prodCost = product.getProductCost();
								}
							}
					%>
					<td style="padding-left: 2%;"><%=productName%></td>
					<%
						for (OrderDetails orderDetails : orderDetailsList) {
								if (orderDetails.getOrderId() == orderDetails.getOrderId()) {
									orderDate = orderDetails.getOrderDate();
								}
							}
					%>
					<td style="padding-left: 2%;"><%=orderDate%></td>
					<td style="padding-left: 2%;"><%=prodCost%></td>
				</tr>
				<%
					}
				%>
			</table>


		</div>
	</div>
	<!-- ------------------------------  EMI DETAILS PAGE  ----------------------------- -->

	<div class="emiDetails" style="display: none;">
		<h1 class="EMIhead">EMI Details</h1>


		<table id="orderRow">


			<tr class="rowToClick">
				<th>Emi No</th>
				<th>Status</th>
				<th>Due Date</th>
				<th>Pay Your Emi</th>

			</tr>
			<%
				for (List<EmiHistory> emiList : emiHistoryList) {
					int count = 1;
					for (EmiHistory emi : emiList) {
			%>
			<tr>

				<td><%=count++%></td>
				<%
					if (emi.getDateOfPayment() != null) {
				%>
				<td>Paid</td>
				<%
					} else {
				%>
				<td>Not Paid</td>
				<%
					}
				%>
				<td><%=emi.getNextPayDate()%></td>

				<td><a
					href="preGenerateOtpForEmi.hr?emiAmount=<%=emi.getEmiAmount()%>&amp;id=<%=emi.getEmiHistoryId()%>"><button>Pay
							Now</button></a></td>
				<%-- <%if(emi.getNextPayDate().equals(today) && emi.getDateOfPayment()==null){ %>
                                	<td><a href="preGenerateOtpForEmi.hr?emiAmount=<%=emi.getEmiAmount()%>&id=<%=emi.getEmiHistoryId()%>"><button type="button" >Pay Now</button></a></td>
                                	<%} else{ %>
                                	<td><a><button type="button" disabled>Wait For Due Date</button></a></td>
                                	<%} %>  --%>
				<!-- <td><input type="button" value="Pay EMI"></td> -->




			</tr>

			<%
				}
				}
			%>

		</table>


	</div>
	<div></div>


	<!-- -------------------------User Documents starts here ------------------------- -->
	<div class="uploadDocs" style="display: none;">

		<h2 class="EMIhead">Please upload your PAN card here</h2>
		<form class="uploadForm" method="POST" action="uploadFile.hr"
			enctype="multipart/form-data">
			File to upload: <input type="file" name="file" id="fileUpload"
				required><br /> Name: <input type="text" name="name"
				id="fileText" style="width: 78%; margin-left: 5%;"><br /> <br />
			<input type="submit" value="Upload" id="submit"> Press here
			to upload the file!
		</form>

	</div>
	<!-- -------------------------SCRIPT STARTS HERE------------------------- -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#productPage").click(function() {
				$(".content").empty();
				var div1 = $(".productPage").load("productGrid.html");
				$(".content").append(div1)
			});

			/*  ------------------------------  CARD PAGE  -----------------------------*/
			$("#cardDetails").click(function() {
				$(".content").empty();
				var div2 = $(".cardDetails").html()
				$(".content").append(div2)
			});
			/*  ------------------------------  ORDER DETAILS PAGE  -----------------------------*/
			$("#orderDetails").click(function() {
				$(".content").empty();
				var div2 = $(".orderDetails").html()
				$(".content").append(div2)

				var acc = document.getElementsByClassName("accordion");
				var i;
				for (i = 0; i < acc.length; i++) {
					acc[i].addEventListener("click", function() {
						this.classList.toggle("active");
						var panel = this.nextElementSibling;
						if (panel.style.maxHeight) {
							panel.style.maxHeight = null;
						} else {
							panel.style.maxHeight = panel.scrollHeight + "px";
						}
					});
				}

			});

			/*  ------------------------------  EMI DETAILS PAGE  -----------------------------*/

			$("#emiDetails").click(function() {
				$(".content").empty();
				var div2 = $(".emiDetails").html()
				$(".content").append(div2)

				var acc = document.getElementsByClassName("accordion");
				var i;
				for (i = 0; i < acc.length; i++) {
					acc[i].addEventListener("click", function() {
						this.classList.toggle("active");
						var panel = this.nextElementSibling;
						if (panel.style.maxHeight) {
							panel.style.maxHeight = null;
						} else {
							panel.style.maxHeight = panel.scrollHeight + "px";
						}
					});
				}
			});

			/*----------------EDIT PROFILE------------------*/

			$("#editProfile").click(function() {
				$(".content").empty();
				var div2 = $(".editProfile").html()
				$(".content").append(div2)
			});

			/*----------------EDIT PROFILE------------------*/

			$("#cardCharges").click(function() {
				$(".content").empty();
				var div2 = $(".cardCharges").html()
				// var div2 = document.getElementsByClassName(".cardCharges").innerHTML='<object type="text/html" data="otp.html"></object>';
				$(".content").append(div2)
			});

			/*----------------EDIT PROFILE------------------*/

			$("#uploadDocs").click(function() {
				$(".content").empty();
				var div2 = $(".uploadDocs").html()
				// var div2 = document.getElementsByClassName(".cardCharges").innerHTML='<object type="text/html" data="otp.html"></object>';
				$(".content").append(div2)

				$('input[type="file"]').change(function(e) {
					var fileName = e.target.files[0].name;
					$("#fileText").val(fileName);

				});

			});

		});
	</script>


</body>



</html>