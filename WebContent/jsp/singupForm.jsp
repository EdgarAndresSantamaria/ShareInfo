<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ page import="java.util.*,helper.info.*"%>
</html><!DOCTYPE html>
<html>
	<head>
		<title>SignupForm</title>
		<link href="/ShareInfo/css/styleSheet.css" rel="stylesheet" />
	</head>
	<script type="text/javascript">
	function validarEmail(){
		var valor=document.getElementById("email").value;
		if(!(/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/.test(valor))){
			alert("El email introducido no es valido");
			document.getElementById("email").focus();
			return false;
		}else{
			return true;
		}
	}

	function validarPass(){
		var valor=document.getElementById("pass").value;
		if(!(/(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8})$/.test(valor)))
		{
			alert("la longitud debe ser de 8, contener al menos un número y una letra,y no se permiten caracteres especiales");
			document.getElementById("pass").focus();
			return false;
		}else{
			return true;
		}
	}
	</script>
	<body>
		<header>
			<h1>A webapp to share short messages</h1>
			<h3>Sign up form</h3>
		</header>
		<section>
			<form method="POST" action="/ShareInfo/servlet/SignupServlet">
				<table>
				<tr>
					<td>Email:</td>
					<td><input id="email" name="email" 
						value="<%Object email = session.getAttribute("email");
			if (email != null) {%><%=email%> <%}%>"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input id="pass" type="password"
						name="password" /></td>
				</tr>
				<tr>
	   					<td>Nickname:</td>
	   					<td><input  name="nickname"   value="<%Object usuario = session.getAttribute("username");
			if (usuario != null) {%><%=usuario%> <%}%>"/></td>
	   				</tr>
	 			</table>
				<button>Send</button>
			</form>
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