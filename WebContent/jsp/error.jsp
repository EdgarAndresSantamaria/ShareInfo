<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>

	<head>
		<title>Error</title>
		<link href="/ShareInfo/css/styleSheet.css" rel="stylesheet" />
	</head>
	<body>
		<header>
			<h1>A webapp to share short messages</h1>
			<h2>Página de error</h2>
		</header>
		<section>
			<a>
				<font>Ocurrió un problema con la aplicación, ¿que desea hacer?</font>
			</a>
		</section>
		<section>
			<a href="/ShareInfo/jsp/singupForm.jsp">
				<font>Sign Up</font>
			</a>
		</section>
		<section>
			<a href="/ShareInfo/jsp/login.jsp">
				<font>Log in</font>
			</a>
		</section>
		<section>
			<a href="/ShareInfo/jsp/viewMessages.jsp">
				<font><%Object usuario = session.getAttribute("username");
			if (usuario != null) {%><%="view messages"%> <%}%></font>
			</a>
		</section>
		<footer>
		Web Systems - EUITI Bilbao
		Server Date: <%=new Date().toString() %>
		<br />
		<script languaje ="javascript">
		var fecha=new Date();
		document.write("Client Date:");
		document.write(fecha);
		</script>
		<br />
		</footer> 
	</body>
</html>