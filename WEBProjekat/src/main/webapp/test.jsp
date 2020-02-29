<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<style>

	body{
		background-image: url("https://www.how-to-study.com/images/study-skills-assessments.jpg");
		background-repeat: no-repeat;
 		background-size: cover;
	}
	input[type=submit]:hover {
 		 background-color: #45a049;
	}
	input[type=submit] {
		background-color: #4CAF50;
		cursor: pointer;
	}
	input[type=text]:focus {
 		 background-color: lightblue;
	}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h3><c:out value="${imetesta}"></c:out></h3>
	<form action="/portal/korisnik/sledecePitanje" method="GET">
		<c:out value="${pitanje.pitanje}"></c:out>
		<input type="text" name="odgovor">
		<input type="hidden" value="${broj+1}" name="broj">
		<input type="submit" value="sledece pitanje" name="submit">
		
	</form>


</body>
</html>