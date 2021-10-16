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
		<form:form action="${pageContext.request.contextPath}/reset-password"
			method="post" modelAttribute="rpDto">
			<h4>Set Your New Password for SpringAuthentication App</h4>
			<div class="formgroup">
				<form:input type="password" path="newPassword" />
				<label for="email"><br>New Password</label> <span>Set
					Your Password</span>
			</div>
			<div class="formgroup">
				<form:input type="password" path="confirmNewPassword" />
				<label for="email"><br>Confirm New Password</label> <span>Confirm
					New Password</span>
			</div>
			<form:hidden path="email" />
			<form:hidden path="rpvkey" />
			<div>${resetPassMsg}</div>
			<button id="email-btn" type="submit">Reset Password</button>
		</form:form>
		<p>
			Did you remember? <a href="${pageContext.request.contextPath}/login">Sign
				In</a>
		</p>
	</div>

</body>
</html>