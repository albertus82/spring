<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostra parametri in query</title>
</head>
<body>

	<h1 style="text-align: center;">Registrazione</h1>

	<form:form method="PUT">
		Marca: <input type="text" name="make" maxlength="50" /><br />
		Modello: <input type="text" name="model" maxlength="50" /><br />
		Prezzo: <input type="text" name="price" maxlength="10" /><br />
		<input type="submit" value="Conferma" />
	</form:form>

</body>
</html>
