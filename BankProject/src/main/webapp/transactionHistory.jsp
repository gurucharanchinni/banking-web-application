<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql" %> 
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction History ${name}</title>
<style>
	*, *::after, *::before {
		margin: 0;
		padding: 0;
	}
	html, body {
		height: 100%;
		margin: 0;
		padding: 0;
		overflow-y: auto;
	}
	
	body {
		display: flex;
		align-items: center;
		flex-direction: column;
		gap: 10px;
	}
	form {
		font-size: 1.15rem;
		border: 2px solid black;
		padding: 20px;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
		gap: 20px;
		margin: 20px;
	}

	form > input {
		padding: 10px 20px;
		width: 60%;
		border: 2px solid black;
		border-radius: 0.75rem;
	}
	input[type="submit"] {
		background: skyblue;
		border-radius: 0.75rem;
		border: 0px;
		padding: 10px 20px;
		font-size: 1.10rem;
	}
	table {
		border-collapse: collapse;
		margin-bottom: 20px;
	}
	table, th, td, tr {
		padding: 20px;
		font-size: 1.15rem;
	}

	h1 {
		font-size: 2rem;
		margin-top: 10rem;
	}
	p{
		font-size: 1.15rem;
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
		margin-top: 10px;
	}
	
	.credit {
		color: green;
		font-weight: bold;
		font-size: 1.15rem;
	}
	.debit {
		color: red;
		font-weight: bold;
		font-size: 1.15rem;
	}
</style>
</head>
<body>
	<%
		if(session.getAttribute("name") == null) {
			response.sendRedirect("login.jsp");
		}
	%>
	<h1>Full Transaction History</h1>
	
	<form action="history" method="post">
		Enter Upi Pin: <input type="password" name="upi">
		<input type="submit" value="Continue">
	</form>
	
	<c:if test="${sessionScope.status4 == 'right'}">
		<sql:setDataSource var = "db" driver = "org.postgresql.Driver" url = "jdbc:postgresql://localhost:5432/bankDB" user = "postgres" password = "123456789"/>
		<sql:query var="res" dataSource="${db}">select * from histories where account_number='231004${mobile}'</sql:query>
			
		<table border="1" cellpadding="10" cellspacing="0">
		    <thead>
		        <tr>
		            <th>Description</th>
		            <th>Credit</th>
		            <th>Debit</th>
		            <th>Time</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach items="${res.rows}" var="user">
		            <tr>
		                <td>${user.description}</td>
		                <td class="credit">${user.credit}</td>
		                <td class="debit">${user.debit}</td>
		                <td>${user.timestamp}</td>
		            </tr>
		        </c:forEach>
		    </tbody>
		</table>
		<c:remove var="status4" scope="session"/>
	</c:if>
	
	<p style="color:red">
		<c:if test="${sessionScope.error13 == 'invalidUpi'}">
			Enter UPI pin with only four digits
			<c:remove var="error13" scope="session"/>
		</c:if>
		<c:if test="${sessionScope.status4 == 'wrong'}">
			You entered an incorrect UPI PIN. Please Re-enter
			<c:remove var="status4" scope="session"/>
		</c:if>
	</p>
	
	<a href="dashboard.jsp">Back To Dashboard</a>
	<a href="logout">Logout</a>
</body>
</html>