<!DOCTYPE html >
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ page import="java.util.*,helper.info.*"%>
<%
 	HttpSession sesion = request.getSession();
%>
<html>
<head>
<title>View Messages</title>
<link href="/ShareInfo/css/styleSheet.css" rel="stylesheet" />
</head>
<script type="text/javascript">
	
	function getActiveUser() {
		//nocache = "nocache=" + Math.random() * 1000000;
		var request = new XMLHttpRequest();
		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				if (request.status == 200) {
					if (request.responseText != null) {
						console.log(request.responseText);
						var jsonObj = JSON.parse(request.responseText);
						var font="Active users:";
							for (i=0;i<jsonObj.length;i++){
								font+=" "+jsonObj[i]+" ";
							}
							
						document.getElementById("activeusers").innerHTML = font;
					}
				}
			}
		};	
		request.open("POST", "/ShareInfo/servlet/refreshUsers", true);
		request.send(null);
		setTimeout("getActiveUser()",1000);
	}
	
	function getMsg() {
		//nocache = "nocache=" + Math.random() * 1000000;
		var request = new XMLHttpRequest();
		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				if (request.status == 200) {
					if (request.responseText != null) {
						console.log(request.responseText);
						var jsonObj = JSON.parse(request.responseText);
				
	var table = "<tr><th>Username</th><th>Message</th></tr>";
						for (i = 0; i < jsonObj.length; i++) {
							table += "<tr><td>" + jsonObj[i].username + "</td>";
							table += "<td>" + jsonObj[i].message + "</td></tr>";
						}
						document.getElementById("msgtable").innerHTML = table;
					}
				}
			}
		}
		request.open("POST", "/ShareInfo/servlet/refreshMsg", true);
		request.send(null);
		setTimeout("getMsg()", 1000);
	}

	function getMyUser() {
		var request = new XMLHttpRequest();
		
		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				if (request.status == 200) {
					if (request.responseText != null) {
						console.log(request.responseText);
						var jsonObj = JSON.parse(request.responseText);
						var font = "You are logged in as:";
						for (i = 0; i < jsonObj.length; i++) {
							font += " " + jsonObj[i] + " ";
						}

						document.getElementById("myUser").innerHTML = font;
					}
				}
			}
		}
		request.open("POST", "/ShareInfo/servlet/myUser", true);
		request.send(null);
	}
</script>


<body onload="getActiveUser();getMsg();getMyUser()">
	<header>
		<h1>A webapp to share short messages</h1>
		<h3>View Messages</h3>
	</header>
	<section>
		<a href="/ShareInfo/servlet/logout"> <font>logout</font>
		</a>
	</section>
	<section>
		<a href="/ShareInfo/jsp/singupForm.jsp"> <font>change password</font>
		</a>
	</section>
<section>
		<a href="/ShareInfo/servlet/delMsg"> <font>remove messages</font>
		</a>
	</section>
	<section >
	<font id="activeusers">
	
	</font>
		
	</section>
 
	<section>
		<font id="myUser">
		
		</font>
	</section>
	<section>
		<form method="POST" action="/ShareInfo/servlet/MainServlet">
			<table>
				<tr>
					<td>Message:</td>
					<td><textarea name="message" id="message"></textarea></td>
				</tr>
			</table>
			<button>Send</button>
		</form>
	</section>
	<section>
	<table  id="msgtable">	
	</table>
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