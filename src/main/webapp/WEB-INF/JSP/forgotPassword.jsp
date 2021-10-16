<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>KHATRI | Reset Password</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style2.css">
</head>
<body>

	<div class="container-center">
		<div class="center-image">
			<img src="https://i.imgur.com/LaimZqD.png" width="30%">
		</div>
		<h2>Don't Worry!</h2>
		<form:form action="${pageContext.request.contextPath}/forgot-password"
			method="post">
			<h4>Just provide your email and we can do the rest</h4>
			<div class="formgroup">
				<input type="text" name="email" /> <label for="email"><br>Email</label>
				<span>enter your email</span>
			</div>
			<div>${forgotPwdMsg}</div>
			<button id="email-btn" type="submit">Send Email</button>
		</form:form>
		<p>
			Did you remember? <a href="${pageContext.request.contextPath}/login">Sign
				In</a>
		</p>
	</div>

</body>
</html>