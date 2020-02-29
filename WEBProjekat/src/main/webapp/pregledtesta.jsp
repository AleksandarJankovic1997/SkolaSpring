<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/portal/korisnik/sledecitest" metod="post">
		<c:out value="${test.imeTesta}"></c:out>
		
		<c:forEach items="${pitanja} var="p">
			<c:out value="${p.pitanje}"></c:out>
		</c:forEach>
		<c:forEach items="${odgovori}" var="o">
			<c:out value="${o.odgovor}"></c:out>
		</c:forEach>
		unesite tacnost odgovora redom!
		<input type="text" name="odg1">
		<input type="text"name="odg2">
		<input type="text" name="odg3">
		<input type="text" name="odg4">
		<input type="text" name="odg5">
		<input type="hidden" name="broj" value="${broj+1}">
		<input type="submit" value="posalji">	
	</form>
</body>
</html>