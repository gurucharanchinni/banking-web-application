<h1 align="center">Banking Project</h1>
A simple Java-based banking application built using <b>MVC Architecture</b>, <b>Servlets</b>, <b>JSP</b>, <b>JSTL</b>, and <b>PostgreSQL</b>. The application enables users to create and manage their bank accounts securely with features like login authentication, money transfers, transaction history, and session-based access control.

<h1 align="center">Tech Stack</h1>

- **Java** (Servlets, Filters, JDBC)
- **JSP** (for views)
- **JSTL** (for clean dynamic content rendering)
- **PostgreSQL** (real-time transaction storage)
- **SHA-256** (for secure encoding of MPIN and UPI PIN)
- **Session Management** (`HttpSession`)

<h1 align="center">MVC Architecture</h1>

The project follows the **Model-View-Controller (MVC)** design pattern:

- **Model (DAO Package):**  
  Java classes to interact with the database using JDBC.

- **View (JSP Pages):**  
  JSP and JSTL used for rendering UI and displaying responses.

- **Controller (Servlets Package):**  
  Handles routing, business logic, and interaction between model and view.
