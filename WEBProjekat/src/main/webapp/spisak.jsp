<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
	body{
		background-image: url("https://www.how-to-study.com/images/study-skills-assessments.jpg");
		background-repeat: no-repeat;
 		background-size: cover;
	}
	table,th,td{
		border=2px solid black
	
	}
	th,td{
		text-align: left;
	}
	tr:hover {
		background-color: #f5f5f5;
	}
	
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h3>
		<c:out value="${predmet}"></c:out>
		</h3>	
	</center>
	<center>
		<table border="2">
			<tr>
				<th>Ime</th>
				<th>Prezime</th>
				<th>Broj indeksa</th>
				<th>Testovi studenta </th>
			</tr>
			<c:forEach items="${studenti}" var="s">
				<tr>
					<td>${s.ime}</td>
					<td>${s.prezime}</td>
					<td>${s.brojIndeksa}</td>
					<td><a href="/portal/korisnik/pregledajtest?ids=${s.idStudent}&imep=${predmet}">pregledajte testove</a>  </td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>