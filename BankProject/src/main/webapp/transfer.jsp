<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Money Transfer ${name}</title>
<style>
	h1 {
		margin-top: 60px;
		text-align: center;
	}
	.container {
		display: flex;
		justify-content: center;
		align-items: center;
		margin: 40px;
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
	
	a {
		text-decoration: none;
		color: black;
		padding: 15px;
		border-radius: 1rem;
		background: skyblue;
		font-size: 1.5rem;
		text-align: center;
		display: flex;
		width: 15%;
		justify-content: center;
		margin: 30px auto;
	}
</style>
</head>
<body>
	<%
		if(session.getAttribute("name") == null) {
			response.sendRedirect("login.jsp");
		}
	%>
	<h1>Money Transfer</h1>
	
	<div class="container">
		<form action="transfer" method="post">
			Enter Mobile No. of Reciever's Account: <input type="text" name="mobile">
			Enter UPI Pin of your account: <input type="password" name="upi">
			Enter Amount: <input type="text" name="amount" required>
			
			<input type="submit" value="Transfer">
		</form>
	</div>
	
	<p class="error">
		<c:if test="${sessionScope.error11 == 'invalidMobile'}">
			Enter Valid Mobile Number without country code
			<c:remove var="error11" scope="session"/>
		</c:if>
		<c:if test="${sessionScope.error12 == 'invalidUpi'}">
			Enter UPI pin with only four digits
			<c:remove var="error12" scope="session"/>
		</c:if>
		<c:if test="${sessionScope.status3 == 'no user'}">
			There is no Existing User with the Mobile No to Transfer
			<c:remove var="status3" scope="session"/>
		</c:if>
		<c:if test="${sessionScope.status3 == 'less balance'}">
			Insufficient Balance To Transfer
			<c:remove var="status3" scope="session"/>
		</c:if>
		<c:if test="${sessionScope.status3 == 'wrong upi'}">
			You entered an incorrect UPI PIN. Please Re-enter
			<c:remove var="status3" scope="session"/>
		</c:if>
	</p>
	
	<p class="success">
		<c:if test="${sessionScope.status3 == 'success'}">
			Money Transferred Successfully
			<c:remove var="status3" scope="session"/>
		</c:if>
	</p>
	
	<a href="dashboard.jsp">Back To Dashboard</a>
	<a href="logout">Logout</a>
</body>
</html>