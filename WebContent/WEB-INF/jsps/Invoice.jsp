<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,com.lti.fg.entities.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/invoice.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css">
</head>

<%
	List<Product> productList = (List<Product>) request.getAttribute("productList");
%>
<%
	List<Integer> quantList = (List<Integer>) request.getAttribute("Quantity");
%>
<body>

	<%@include file="header.jsp"%>
	<div class="invoicecontainer">
		<img
			src="${pageContext.request.contextPath}/resources/images/logo.png"
			id="invoicelogo" alt="">

		<p id="thankinvoice">Thank You! For shopping with us</p>



		<img
			src="${pageContext.request.contextPath}/resources/images/print.png"
			id="printbutton" onclick="myFunction()" alt="">

		<div id="taxinvoice">Tax Invoice</div>

		<!------------------Basic information------------------- -->
		<table id="invoicebill">

			<tr>
				<td id="boldhead">GSTIN :</td>
				<td>36BBICA4678D3ZP</td>
			</tr>
			<tr>
				<td id="boldhead">Order Date:</td>
				<td><%=Calendar.getInstance().getTime()%></td>
			</tr>

		</table>



		<!------------------Product details table starts here ------------------------>
		<table id="invoicetable">
			<tr>
				<th>Products</th>
				<th>Quantity</th>
				<th>Amount</th>

			</tr>
			<%
				for (int i = 0; i < productList.size(); i++) {
			%>
			<tr>
				<td><%=productList.get(i).getProductName()%></td>
				<td><%=quantList.get(i)%></td>
				<td><%=productList.get(i).getProductCost()%></td>

			</tr>
			<%
				}
			%>
			<tr>
				<td>Total</td>
				<td colspan="2"><%=request.getAttribute("cartCost")%></td>
			</tr>



		</table>

		<p></p>
		<p id="boldtext">Returns Policy :</p>
		At Finshop we try to deliver perfectly each and every time. But in the
		off-chance that you need to return the item, please do so with the
		original Brand box/price tag, original packing and invoice without
		which it will be really difficult for us to act on your request.
		Please help us in helping you. Terms and conditions apply.<br>
		The goods sold as are intended for end user consumption and not for
		re-sale. <br>
		<p id="boldtext">Office Address :</p>
		Finshop Private Limited,Block 1A, Mahape.
		<p></p>


	</div>

	<script type="text/javascript">
		function myFunction() {

			var printButton = document.getElementById("printbutton");
			printButton.style.visibility = 'hidden';
			window.print();
			printButton.style.visibility = 'visible';
		}
	</script>
</body>
</html>