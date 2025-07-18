<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Account</title>
<style>
    *, *::after, *::before {
        margin: 0;
    }
    .create-container {
        display: flex;
        justify-content: space-evenly;
        align-items: center;
        flex-direction: column;
        margin-top: 60px;
    }

    .create-container>form {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        gap: 10px;
        border: 2px solid black;
        width: 400px;
        margin: 20px;
        font-size: 1.25rem;
        padding: 20px;
    }

    .create-container>form input[type="text"] {
        padding: 10px 20px;
        width: 60%;
        border: 2px solid black;
        border-radius: 0.75rem;
    }

    input[type="submit"] {
        margin-bottom: 15px;
        padding: 10px 20px;
        font-size: 1.10rem;
        border: 0px;
        background: skyblue;
        border-radius: 1rem;
    }

    select {
        padding: 10px 20px;
        font-size: 1.05rem;
        background: white;
        border: 2px solid black;
        border-radius: 0.5rem;
    }

    .error {
    	margin-top: 60px;
        font-size: 1.5rem;
        text-align: center;
        color: red;
        margin-bottom: -30px;
    }
</style>
</head>
<body>
	<p class="error">
		<c:if test="${sessionScope.error7 == 'No Account'}">
			You Don't Have an account with the number you entered in Login
			<c:remove var="error7" scope="session"/>
		</c:if>
	</p>

	<div class="create-container">
		<h1>Create Account</h1>
		<form action="create" method="post">
			Enter Name: <input type="text" name="acchold" required>
			Enter Email ID: <input type="text" name="email" required>
			Enter Mobile No.: <input type="text" name="mobile">
			<select name="acctype">
				<option value="">Select Type of Account</option>
				<option value="Savings">Savings</option>
				<option value="Current">Current</option>
			</select>
			Set MPin: <input type="text" name="mpin">
			Set UPI Pin: <input type="text" name="upi">
			<input type="submit" value="Create">
		</form>
	</div>
	
	<p class="error">
        <c:if test="${sessionScope.error1 == 'invalidMobile'}">
            Enter Valid Mobile Number without country code
            <c:remove var="error1" scope="session"/>
        </c:if>
        <c:if test="${sessionScope.error2 == 'invalidAccount'}">
            Select Valid Account Type<br>
            <c:remove var="error2" scope="session"/>
        </c:if>
        <c:if test="${sessionScope.error3 == 'invalidMPin'}">
            Enter M-PIN with only five digits
            <c:remove var="error3" scope="session"/>
        </c:if>
        <c:if test="${sessionScope.error4 == 'invalidUpi'}">
            Enter UPI pin with only four digits
            <c:remove var="error4" scope="session"/>
        </c:if>
        <c:if test="${sessionScope.status1 == 'exist'}">
            Account Existing with the Mobile No. Enter Another Mobile No.
            <c:remove var="status1" scope="session"/>
        </c:if>
    </p>
</body>
</html>