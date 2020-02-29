<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/portal/path/saveUser" method="get">
		Unesite username:
		<input type="text" name="username">
		Unesite sifru:
		<input type="text" name="password">
		izaberite ulogu:	
		<select name="uloga">
			<option value="profesor">profesor</option>
			<option value="student">student</option>
			<option value="asistent">asistent</option>
		</select>
		Unesite ime:
		<input type="text" name="ime">
		Unesite prezime
		<input type="text" name="prezime">
		Unesite brIndeksa
		<input type="text" name="brindeksa">
		<input type="submit" value="registruj">
	</form>
</body>
</html>