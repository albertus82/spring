<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Pagina di registrazione</title>
	</head>
	<body style="font-family: verdana, sans-serif;">
		<h1 style="text-align: center;">Registrazione</h1>
	
		<!-- Eventuale messaggio -->
		<c:if test="${not empty requestScope.messaggio}">
			<div style="color:red; text-align:center; margin:1em;"><strong>${requestScope.messaggio}</strong></div>
		</c:if>
		
		<form:form commandName="utente" action="register" method="POST">
			<table style="margin: auto;" cellpadding="4">
				<tr>
					<td>Username</td>
					<td><form:input type="text" path="username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:input type="text" path="password" /></td>
				</tr>
				<tr>
					<td>Nome</td>
					<td><form:input type="text" path="nome" /></td>
				</tr>
				<tr>
					<td>Cognome</td>
					<td><form:input type="text" path="cognome" /></td>
				</tr>
				<tr>
					<td>Data di nascita</td>
					<td><form:input type="text" path="dataNascita" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="submit" value="Conferma" style="width: 100%;" />
					</td>
				</tr>
			</table>
		</form:form>
		
	</body>
</html>