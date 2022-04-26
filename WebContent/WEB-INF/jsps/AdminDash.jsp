<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, com.lti.fg.entities.*,java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/adminpage.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"
	type="text/javascript"></script>
<title>Insert title here</title>

<style type="text/css">
.header-right {
	float: right;
	font-size: 25px;
}
</style>
</head>
<%
	String context = request.getContextPath();
	String rootPath = System.getProperty("catalina.home");
	File dir = new File(rootPath + File.separator + "tmpFiles");
%>
<%
	List<ActiveUserCard> aucList = (List<ActiveUserCard>) request.getAttribute("aucList");
	List<User> userList = (List<User>) request.getAttribute("userList");
%>

<body>



	<!------------------------ Admin head ------------------------- -->
	<div id="adminheader">
		<div class="logo">
			<a href="#">Welcome Admin</a>

		</div>
		<div class="header-right">
			<a href="home.hr" style="color: white; margin-right: 30px;">Home</a>
		</div>
	</div>


	<a class="mobile">MENU</a>

	<!-- ---------------Admin container starts here ------------------------------------------ -->

	<div id="admincontainer">




		<!-- -----------------Side bar code starts here--------------------------- -->
		<div class="sidebar">
			<ul id="nav">

				<%-- <li><span> <img
						src="${pageContext.request.contextPath}/resources/images/adminlogo.png"
						width="32" height="32">
				</span><a href="#" id="viewuser">View User</a></li> --%>


				<li><span> <img
						src="${pageContext.request.contextPath}/resources/images/adminlogo.png"
						width="32" height="32" alt="">
				</span><a href="#" id="requestuser">Users</a></li>



			</ul>



		</div>

		<!-- -----------------Side bar code ends here--------------------------- -->



		<div class="content">
			<h1>Dashboard</h1>
			<p>The details of user are....</p>
			<br>

		</div>




		<!-- -----------------Request user starts here--------------------------- -->

		<div class="requestuser">
			<h1>List of Requests</h1>
			<table id="adminusertable">
				<div></div>
				<tr>
					<th>User Id</th>
					<th>User name</th>
					<th>User email</th>
					<th>Card Id</th>
					<th>View Document</th>
					<th>User Status</th>
					<th>Card Status</th>
					<th>Activate/Deactivate</th>
					<th>Delete User</th>
				</tr>

		<%
			String name = "";
			String email = "";
			for (ActiveUserCard auc : aucList) {
		%>
				<tr>
					<%
						for (User user : userList) {

								if (user.getUserId() == auc.getUserId()) {
									name = user.getUserName();
									email = user.getUserEmail();
								}
							}
					%>

					<td><%=auc.getUserId()%></td>
					<td><%=name%></td>
					<td><%=email%></td>
					<td><%=auc.getCardId()%></td>
					<td>
						<%
							String link = request.getContextPath() + "/resources/uploadedFiles/" + auc.getLinkToPdf();
						%>
						<%-- <embed src="<%=link %>" id="pdfView" width="500" height="375" type='application/pdf' style="display:none;"> --%>
						<a href="<%=link%>" target="_blank">View Pdf</a>
					</td>
					<td><%=auc.getUserStatus()%></td>
					<td><%=auc.getCardStatus()%></td>
					<td><a href="verifyUser.hr?userId=<%=auc.getUserId()%>"><button>Activate</button></a>
						<a href="rejectUser.hr?userId=<%=auc.getUserId()%>"><button>Deactivate</button></a>
					</td>
					<td><a href="deleteUser.hr?userId=<%=auc.getUserId()%>"><button>Delete</button></a>
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</div>






	</div>
	<!-- --------Admin conatiner ends here ------------------- -->



	<script type="text/javascript">
		$(document).ready(function() {

			$("#viewuser").click(function() {
				$(".content").empty();
				var div1 = $(".viewuser").html()
				$(".content").append(div1)

				$("#active").click(function() {
					$("#active").hide();
					$("#deactive").show();
				});
				$("#deactive").click(function() {
					$("#active").show();
					$("#deactive").hide();
				});
			});

			$("#requestuser").click(function() {
				$(".content").empty();
				var div2 = $(".requestuser").html()
				$(".content").append(div2)

				$("#active").click(function() {
					$("#active").hide();
					$("#deactive").show();
				});
				$("#deactive").click(function() {
					$("#active").show();
					$("#deactive").hide();
				});

				$("#pdf").click(function() {
					$("#pdfView").toggle();
				});
			});

		});
	</script>




</body>
</html>