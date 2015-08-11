<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>login</title>
	</head>
	<body>
		<form action="auth" method="POST">
			Username: <input type="text" name="username" /> <br /> 
			Password: <input type="password" name="password" /> <br />
			<input type="submit" value="Accedi" /> <br />
		</form>
	</body>
</html>
