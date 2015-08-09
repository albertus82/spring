<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Pagina di accesso</title>
	</head>
	<body style="font-family: verdana, sans-serif;">
		<h1 style="text-align: center;">Accesso</h1>
	
		<!-- Eventuale messaggio -->
		<c:if test="${not empty requestScope.messaggio ne not empty param.messaggio}"> <%-- In EL non esiste XOR --%>
			<div style="color:red; text-align:center; margin:1em;"><strong>${requestScope.messaggio}${param.messaggio}</strong></div>
		</c:if>
		
		<form:form action="login" method="POST">
			<table style="margin: auto;" cellpadding="4">
				<tr>
					<td>Username</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="submit" value="Accedi" style="width: 40%;" />
						o
						<button style="width: 40%;" onclick="location.href='register';return false;">Registrati</button>
					</td>
				</tr>
			</table>
		</form:form>
		
	</body>
</html>