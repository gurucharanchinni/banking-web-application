<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Withdraw Or Deposit Money ${name}</title>
</head>
<style>
	h1 {
		margin: 60px;
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
	
	select {
        padding: 10px 20px;
        font-size: 1.05rem;
        background: white;
        border: 2px solid black;
        border-radius: 0.5rem;
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
<body>
	<%
		if(session.getAttribute("name") == null) {
			response.sendRedirect("login.jsp");
		}
	%>
	<h1>Withdraw or Deposit Money</h1>
	
	<div class="container">
		<form action="withdrawOrDeposit" method="post">
			Enter UPI Pin: <input type="password" name="upi">
			<select name="option">
				<option value="">Select Option</option>
				<option value="Deposit">Deposit</option>
				<option value="Withdraw">Withdraw</option>
			</select>
			Enter Amount: <input type="text" name="amount" required>
			
			<input type="submit" value="Process">
	</form>
	</div>
	
	<p class="error">
	    <c:if test="${sessionScope.error9 == 'invalidUpi'}">
	        Enter UPI pin with only four digits
	        <c:remove var="error9" scope="session"/>
	    </c:if>
	    
	    <c:if test="${sessionScope.error10 == 'invalidOption'}">
	        Select Valid Option
	        <c:remove var="error10" scope="session"/>
	    </c:if>
	
	    <c:if test="${sessionScope.status2 == 'invalidUpi'}">
	        You entered an incorrect UPI PIN. Please Re-enter
	        <c:remove var="status2" scope="session"/>
	    </c:if>
	    
	    <c:if test="${sessionScope.status2 == 'lessBalance'}">
	        Insufficient Balance To Withdraw
	        <c:remove var="status2" scope="session"/>
	    </c:if>
	</p>
	
	<p class="success">
	    <c:if test="${sessionScope.status2 == 'Deposit'}">
	        Successful Deposit
	        <c:remove var="status2" scope="session"/>
	    </c:if>
	    
	    <c:if test="${sessionScope.status2 == 'Withdraw'}">
	        Successful Withdraw
	        <c:remove var="status2" scope="session"/>
	    </c:if>
	</p>
	
	
	<a href="dashboard.jsp">Back To Dashboard</a>
	<a href="logout">Logout</a>
</body>
</html>