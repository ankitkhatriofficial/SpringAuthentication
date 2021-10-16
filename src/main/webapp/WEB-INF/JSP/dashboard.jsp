<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>KHATRI | DashBoard</title>
<style>
body {
	background-color: #03a9f4;
	text-align: center;
	color: white;
}

input {
	text-align: center;
	width: 20%;
	padding: 10px;
	border: solid 1px #333;
	font-size: inherit;
	border: none;
	cursor: pointer;
	font-size: inherit;
	background-color: black;
	color: #fff;
	padding: 10px 50px;
	width: 20%;
}

input:hover {
	color: rgb(150, 150, 150);
}
</style>
</head>
<body>

	<h1>Welcome to your DashBoard..!</h1>
	<h3>Login Authentication was successful. You are logged in now..!</h3>

	<form:form action="${pageContext.request.contextPath}/logout"
		method="post">
		<input type="submit" value="Logout from here" />
	</form:form>
</body>
</html>