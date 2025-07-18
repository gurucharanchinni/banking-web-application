<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	*, *:after, *:before {
		margin: 0;
		padding: 0;
	}
	html {
		width: 100%;
		height: 100%;
		overflow: hidden;
	}
	body {
		height: inherit;
		border: 2px solid black;
	}
	body {
		display: flex;
		justify-content: space-evenly;
		align-items: center;
		flex-direction: column;
		font-size: 1.2rem;
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
		margin-top: -10px;
	}
	span {
		font-weight: bold;
		font-size: 1.2rem;
	}
</style>
</head>
<body>
	<%
		if(session.getAttribute("name") == null)
			response.sendRedirect("login.jsp");
	%>
	<h1>${name}'s Bank Details</h1>
	
	<sql:setDataSource var = "db" driver = "org.postgresql.Driver" url = "jdbc:postgresql://localhost:5432/bankDB" user = "postgres" password = "123456789"/>
	<sql:query var="res" dataSource="${db}">select * from accounts where mobile='${mobile}'</sql:query>
		
	<c:forEach items="${res.rows}" var="user">
		<p><span>Account Number:</span> ${user.account_number}</p>
		<p><span>IFSC Code:</span> ${user.ifsc_code}</p>
    	<p><span>Name:</span> ${user.name}</p>
    	<p><span>Email:</span> ${user.email}</p>
    	<p><span>Mobile:</span> ${user.mobile}</p>
    	<p><span>Account Type:</span> ${user.account_type}</p>
	</c:forEach><br>
	
	<a href="dashboard.jsp">Return To Dashboard</a>
	<a href="logout">Logout</a>
</body>
</html>