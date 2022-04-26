<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="changePassword.hr" method="post">
		Enter New Password : <br>
		<input type="password" name="password" required><br><br>
		Enter again : <br>
		<input type="password" name="rpassword" required><br><br>
		<input type="hidden" name="email" value="<%=request.getAttribute("email")%>">
		<input type="submit" value="Change Password">
	</form>	
</body>
</html>