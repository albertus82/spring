<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Home Page</title>
	</head>
	<body>
		<h1>
			Benvenuto ${utente.nome} ${utente.cognome}!
		</h1>
		<h3>Data di nascita: <security:authentication property="principal.dataNascita" /></h3>
		
		<!-- Logout -->
		<sf:form action="logout" method="POST"><input type="submit" value="Logout" /></sf:form>
	</body>

</html>
