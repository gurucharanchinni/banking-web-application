<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard ${name}</title>
<style>
	h1 {
		text-align: center;
	}
	
	.container {
		margin-top: 100px;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
		gap: 10px;
		text-align: center;

	}

	a {
		text-decoration: none;
		color: black;
		padding: 10px;
		border-radius: 1rem;
		background: skyblue;
		font-size: 1.5rem;
		width: 20%;
		
	}
</style>
</head>
<body>
	<%
		if(session.getAttribute("name") == null){
			response.sendRedirect("login.jsp");
		}
	%>
	
	<h1>Welcome ${name}</h1>
	
	<div class="container">
		<a href="details.jsp">Bank Details</a><br>
		<a href="withdrawOrDeposit.jsp">Withdraw Or Deposit Money</a><br>
		<a href="transfer.jsp">Money Transfer</a><br>
		<a href="transactionHistory.jsp">Transaction History</a><br>
		<a href="logout">Logout</a>
	</div>
</body>
</html>