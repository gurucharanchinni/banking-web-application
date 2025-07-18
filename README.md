<h1 align="center">Banking Project</h1>
A simple Java-based banking application built using <b>MVC Architecture</b>, <b>Servlets</b>, <b>JSP</b>, <b>JSTL</b>, and <b>PostgreSQL</b>. The application enables users to create and manage their bank accounts securely with features like login authentication, money transfers, transaction history, and session-based access control.

<h1 align="center">Tech Stack</h1>

- **Java** (Servlets, Filters, JDBC)
- **JSP** (for views)
- **JSTL** (for clean dynamic content rendering)
- **PostgreSQL** (real-time transaction storage)
- **SHA-256** (for secure encoding of MPIN and UPI PIN)
- **Session Management** (`HttpSession`)

<h1 align="center">Features</h1>

✅ User Registration
Allows users to create a bank account by entering their personal details along with a unique MPIN and UPI ID.
The MPIN and UPI ID are securely encoded using SHA-256 before storing in the database.

✅ Login Authentication using Mobile Number and MPIN
Users can log in using their registered mobile number and MPIN.
Authentication is handled securely by comparing encoded values stored in the database.

✅ Form Validation using Servlet Filters
Filters are used to validate user inputs like mobile number format, UPI ID, amount limits, etc.
Invalid data is blocked at the filter level before reaching the business logic.

✅ View Account Details
Once logged in, users can view their basic account details such as name, balance, UPI ID, and account number.
This page is only accessible if the session is active.

✅ Deposit & Withdraw Funds
Users can deposit or withdraw money securely from their account.
Transactions update the real-time balance stored in the database.

✅ Fund Transfer
Enables users to transfer money to another account using UPI ID and mobile number.
Both sender and receiver balances are updated, and the transfer is logged.

✅ Transaction History
Displays a complete list of the user’s past transactions including deposits, withdrawals, and transfers.
Sorted chronologically and retrieved in real-time from the database.

✅ Session Management to restrict page access post-login
Uses HttpSession to ensure that sensitive pages are accessible only when a valid session exists.
If the session is inactive, the user is redirected to the login page.

✅ Session Timeout Handling via Filters
Filters are used to automatically detect and handle session expiration.
If a session has timed out, the user is logged out and prompted to log in again.

✅ Logout with session invalidation
Users can safely log out of their account, which removes their session data.
After logout, all secure pages are blocked until the user logs in again.

<h1 align="center">MVC Architecture</h1>

The project follows the **Model-View-Controller (MVC)** design pattern:

- **Model (DAO Package):**  
  Java classes to interact with the database using JDBC.

- **View (WebApp Package):**  
  JSP and JSTL used for rendering UI and displaying responses.

- **Controller (Servlets Package):**  
  Handles routing, business logic, and interaction between model and view.
