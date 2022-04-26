<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,com.lti.fg.entities.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/productGrid.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/productDescription.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>


<body>


	<a id='#top' />
	<%@include file="header.jsp"%>

	<%
		String contextPath = request.getContextPath();
		String link = "";
	%>


	<%
		List<Product> productList = (List<Product>) request.getAttribute("productList");
	%>
	<div class="row" style="padding: 0px 30px; margin-bottom: 20px">
		<%
			for (int i = 0; i < productList.size(); i++) {
		%>
		<a href="productDetails.hr?id=<%=productList.get(i).getProductId()%>"><div
				class="column">
				<%
					link = contextPath + productList.get(i).getProductImageLink();
				%>
				<img src="<%=link%>" alt="Norway" class="productimg">
				<p>
					<a
						href="productDetails.hr?id=<%=productList.get(i).getProductId()%>"><b><%=productList.get(i).getProductName()%></b></a>
				</p>
				<p>
					Price -
					<%=productList.get(i).getProductCost()%>
				</p>

			</div></a>
		<%
			}
		%>
	</div>
	<!-- -----------------------------Display product Description using modal ends here------------------------------ -->

	<br>
	<a href="#top">Back To Top</a>


</body>

</html>