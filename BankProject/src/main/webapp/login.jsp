<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style>
	h1 {
		text-align: center;
	}
	.container {
		display: flex;
		justify-content: center;
		align-items: center;
	}
	form {
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
		gap: 10px;
		font-size: 1.25rem;
		border: 2px solid black;
		padding: 20px;
	}
	form > input {
		padding: 10px 20px;
		width: 60%;
		border: 2px solid black;
		border-radius: 0.75rem;
	}
	input[type="submit"] {
		font-size: 1.15rem;
		padding: 8px;
		background: skyblue;
		border: 0px;
		outline: 0px;
		border-radius: 0.5rem;
	}
	.success {
		color: green;
	}
	.error {
		color: red;
	}
	.success, .error {
		text-align: center;
		font-size: 1.25rem;
	}
</style>
</head>
<body>
	<p class="success">
		<c:if test="${sessionScope.status1 == 'success'}">
			Account created
			<c:remove var="status1" scope="session"/>
		</c:if>
	</p>
	
	<h1>Login</h1>
	<div class="container">
		<form action="login" method="post">
			Enter Mobile Number Linked To Your Account: <input type="text" name="mobile">
			Enter MPIN: <input type="password" name="mpin">
			<input type="submit" value="Login">
		</form>
	</div>
	
	<p class="error">
		<c:if test="${sessionScope.error5 == 'invalidMPin'}">
			Enter M-PIN with only five digits
			<c:remove var="error5" scope="session"/>
		</c:if>
		<c:if test="${sessionScope.error6 == 'invalidMobile'}">
			Enter Valid Mobile Number without country code
			<c:remove var="error6" scope="session"/>
		</c:if>
		<c:if test="${sessionScope.error8 == 'invalidMPin'}">
			You entered an incorrect M-PIN. Please Re-enter
			<c:remove var="error8" scope="session"/>
		</c:if>
	</p>
</body>
</html>