<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	body{
	 	font-size: 20px;
		background-image: url("https://www.how-to-study.com/images/study-skills-assessments.jpg");
		background-repeat: no-repeat;
 		background-size: cover;
	}
	fieldset{
		background-color:#DDDDDD;
		border-color: green;
		border: 7px groove;
		 margin: 0 auto;
	}
	.logout{
		position:absolute;
    	bottom:10px;
    	right:10px;
	}
	.a{
		text-align: center;
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
<sec:authorize access="hasRole('profesor')">
	<p class="a">Dobro dosli Profesore!</p>
</sec:authorize>
<sec:authorize access="hasAnyRole('Student','Asistent')">
	<c:out value="Dobrodosao ${osoba.ime}"></c:out>
</sec:authorize>
<fieldset>
	<legend>Vasi podaci:</legend>
	
	Ime: <c:out value="${osoba.ime}"></c:out><br>
	Prezime: <c:out value="${osoba.prezime}"></c:out><br>
	<sec:authorize access="hasRole('student')">
	Broj indeksa: <c:out value="${osoba.brojIndeksa}"></c:out><br>
	Smer: <c:out value="${osoba.smer}"></c:out><br>
	Adresa: <c:out value="${osoba.adresa}"></c:out>
	</sec:authorize>
	<sec:authorize access="hasRole('profesor')">
	Zvanje: <c:out value="${osoba.zvanje}"></c:out>
	</sec:authorize>

</fieldset>

<fieldset>
	<legend>Vasi predmeti:</legend>
	<ul style="list-style-type:circle;">
		<c:forEach items="${predmeti}" var="p">
			<li> <a href="/portal/korisnik/prikazPredmeta?idPredmet='${p.idPredmet}'">"${p.naziv}"</a></li>
		</c:forEach>
	</ul>

</fieldset>
<a class="logout" href="<c:url value="/security/logout"/>">Logout</a>



</body>
</html>