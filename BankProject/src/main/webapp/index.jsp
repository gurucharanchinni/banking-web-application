<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome To Chinni Bank</title>
<style>
	*,
	*::after,
	*::before {
		margin: 0;
	}

	header {
		display: flex;
		align-items: center;
		background: orangered;
		color: white;
		padding: 20px;
		margin-bottom: 20px;
		
		h1{
			margin-left: 580px;
		}
	}


	.hero-section {
		display: flex;
		justify-content: center;
		align-items: center;

	}

	.container {
		margin-top: 200px;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
		gap: 10px;
		width: 50%;
	}

	a {
		text-decoration: none;
		color: black;
		padding: 15px;
		border-radius: 1rem;
		background: skyblue;
		font-size: 1.5rem;
	}
</style>
</head>
<body>
	<header>
		<h1>Welcome to Chinni's Bank</h1>
	</header>
	<div class="hero-section">
		<div class="container">
			<a href="create.jsp">Create Account</a>
			<a href="login.jsp">Login</a>
		</div>
	</div>
</body>
</html>