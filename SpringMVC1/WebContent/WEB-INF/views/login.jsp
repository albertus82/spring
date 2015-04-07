<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login Page</title>
	</head>
	<body style="font-family: verdana, sans-serif;">
		<h1 style="text-align: center;">Login</h1>
	
		<form:form action="login" method="POST">
			<table style="margin: auto;" cellpadding="4">
				<tr>
					<td>Nome</td>
					<td><input type="text" name="nome" /></td>
				</tr>
				<tr>
					<td>Cognome</td>
					<td><input type="text" name="cognome" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="submit" value="Accedi" style="width: 100%;" />
					</td>
				</tr>
			</table>
		</form:form>
	</body>
</html>