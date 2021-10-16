<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>KHATRI | Welcome to Home Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<!-- Nav Bar Setup -->
	<div class="nav">
		<input type="checkbox" id="nav-check">
		<div class="nav-header">
			<div class="nav-title">KHATRI</div>
		</div>
		<div class="nav-btn">
			<label for="nav-check"> <span></span> <span></span> <span></span>
			</label>
		</div>
		<div class="nav-links">
			<a
				href="https://www.github.com/ankitkhatriofficial/AuthenticationManager"
				target="_blank">Source Code</a> <a
				href="https://www.linkedin.com/in/ankitkhatriofficial"
				target="_blank">LinkedIn</a> <a
				href="https://www.github.com/ankitkhatriofficial" target="_blank">Github</a>
		</div>
	</div>

	<!-- Login/Signup Modals -->
	<div class="body-container">
		<article class="container">
			<div class="block">
				<section class="block__item block-item">
					<h2 class="block-item__title">WELCOME BACK..!</h2>
					<button class="block-item__btn signin-btn">Login</button>
				</section>
				<section class="block__item block-item">
					<h2 class="block-item__title">NEW HERE?</h2>
					<button class="block-item__btn signup-btn">Register
						Yourself</button>
				</section>
			</div>

			<div class="form-box" id="abc">

				<form:form action="${pageContext.request.contextPath}/login"
					class="form form_signin" method="post">
					<h3 class="form__title">Login to Your Account</h3>
					<p>
						<input type="email" class="form__input"
							placeholder="Enter your email" name="email">
					</p>
					<p>
						<input type="password" class="form__input"
							placeholder="Enter your password" name="password">
					</p>
					<p>
						<c:if test="${param.error != null}">
							<span style="color: red; font-weight: bold">Invalid
								Credentials..!</span>
						</c:if>
						<span style="color: green; font-weight: bold">${redirectMsg}</span>
					</p>
					<p>
						<button class="form__btn">Login</button>
					</p>
					<p>
						<a href="${pageContext.request.contextPath}/forgot-password"
							class="form__forgot">Reset Password</a>
					</p>
				</form:form>

				<form:form action="${pageContext.request.contextPath}/register"
					class="form form_signup" method="post" modelAttribute="user">
					<h3 class="form__title">Register Yourself</h3>
					<p>
						<form:input type="text" path="firstName" class="form__input"
							placeholder="FirstName" />
					</p>
					<p>
						<form:input type="text" path="lastName" class="form__input"
							placeholder="LastName" />
					</p>
					<p>
						<form:input type="email" path="email" class="form__input"
							placeholder="Email" />
					</p>
					<p>
						<form:input type="text" path="contactNo" class="form__input"
							placeholder="Contact Number" />
					</p>
					<p>
						<form:input type="password" path="password" class="form__input"
							placeholder="Password" />
					</p>
					<p>
						<form:input type="password" path="confirmPassword"
							class="form__input" placeholder="Confirm Password" />
					</p>
					<div class="msgDesc">${regMsg}</div>
					<p>
						<button class="form__btn form__btn_signup">Signup</button>
					</p>
				</form:form>
			</div>
		</article>
	</div>

	<!-- Footer Setup -->
	<footer class="footer">
		<div class="icons">
			<a href="https://www.facebook.com/ankitkhatriofficial"
				target="_blank"><i class="fab fa-facebook"></i></a> <a
				href="https://www.linkedin.com/in/ankitkhatriofficial"
				target="_blank"><i class="fab fa-linkedin"></i></a> <a
				href="https://www.instagram.com/me_ankit_khatri" target="_blank"><i
				class="fab fa-instagram"></i></a> <a
				href="https://www.twitter.com/ankitkhatri0" target="_blank"><i
				class="fab fa-twitter"></i></a>
			<div class="company-name">
				&copy;<a href="#">ankitkhartriofficial </a>
				<p class="copyright">2021, ALL Rights Reserved</p>
			</div>
		</div>
	</footer>

	<script src="https://kit.fontawesome.com/66aa7c98b3.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/main.js"></script>

	<script>
		let a = "${regMsg}";
		if (a != "") {
			document.querySelector('.form-box').classList.add('active');
			document.body.classList.add('active');
		}
	</script>

</body>
</html>