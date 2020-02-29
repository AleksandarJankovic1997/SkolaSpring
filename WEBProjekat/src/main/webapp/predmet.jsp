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
	.logout{
		position:absolute;
    	bottom:10px;
    	right:10px;
	}

	a:link, a:visited {
 		background-color: white;
  		color: black;
  		border: 2px solid green;
  		padding: 10px 20px 20px 20px ;
  		text-align: center;
 		text-decoration: none;
 		display: inline-block;
	}
	fieldset{
		background-color:#DDDDDD;
		border-color: green;
		border-color: solid;
		border: 7px groove;
		margin-left:20 px;
		margin-right:70 px;
	}
	input[type=submit]:hover {
 		 background-color: #45a049;
	}
	input[type=submit] {
		background-color:  padding: 30px;;
		cursor: pointer;
	}
	input[type=text]:focus {
 		 background-color: lightblue;
	}
	input[type=submit]:focus{
		background-color:#AAAAAA;
	}

</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:out value="${porukatest}"></c:out>
	<center>
		<h2><c:out value="${predmet.naziv}" ></c:out></h2><br>
		<div>
			Profesor ovog predmeta: <c:out value="${profesor}"></c:out><br>
			Asistent ovog predmeta: <c:out value="${asistent}"></c:out>
		</div>
	</center>
	<fieldset>
		<legend>Obavestenja</legend>
			<c:if test="${empty obavestenja}">
				Trenutno nema obavestenja!
			</c:if>
			<c:if test="${!empty obavestenja}">
				<ul style="list-style-type:circle;">
					<c:forEach items="${obavestenja}" var="o">
						<li>${o.datum} &nbsp; ${o.tekst}</li>
					</c:forEach>
				</ul>
			</c:if>
			<sec:authorize access="hasRole('profesor')">
				<form action="/portal/korisnik/ubaciObavestenje" method="post" id="novoobavestenje">
				 Dodajte novo obavestenje!
				 <input type="text" name="tekst"> &nbsp;
				 <input type="hidden" name="predmet" value="${predmet.idPredmet}">
				 <input type="submit" name="submit" value="ubacite obavestenje">
				</form>
			</sec:authorize>
		</fieldset>
			
			<sec:authorize access="hasRole('profesor')">
				<fieldset>
					<legend>Napravite novi test</legend>
					<form id="novitest" action="/portal/korisnik/ubacitest" method="post">
						Unesite ime testa
						<input type="text"name="imetesta"><br>
						unesite 5 pitanja:<br>
						<div class="i">
							<pre></pre><input type="text" name="p1"><br>
							<pre></pre><input type="text" name="p2"><br>
							<pre></pre><input type="text" name="p3"><br>
							<pre></pre><input type="text" name="p4"><br>
							<pre></pre><input type="text" name="p5">
						</div>
						<input type="hidden" name="predmet" value="${predmet.idPredmet}">
						<input type="submit" name="submit" value="Napravi test"> 
					</form>
				</fieldset>
			</sec:authorize>
		<div style="margin: 10px 12px 10px 10px;">
			<sec:authorize access="hasRole('student')">
				<c:forEach items="${testovi}" var="t">
					<a href="/portal/korisnik/radiTest?pitanje=0&idSemaTest=${t.idSemaTest}">${t.imeTesta}</a>
				</c:forEach>
			</sec:authorize>
		</div>
		<sec:authorize access="hasRole('profesor')">
			<form action="/portal/korisnik/sviStudentiPredmet" method="post">
				<input type="submit" name="studenti" value="prikazistudenteutabeli!">
				<input type="hidden" name="predmet" value="${predmet.idPredmet}">
			</form>
			<pre></pre>
			<form action="/portal/reportsC/sviStudentiPredmet" method="get">
				<input type="submit" name="studenti" value="prikazistudenteureportu!">
				<input type="hidden" name="predmet" value="${predmet.idPredmet}">
			</form>
		</sec:authorize>
		<pre></pre>
		<form action="/portal/reportsC/sviTestoviPredmeta" method="get">
			<input type="submit" name="testovi" value="prikazi testove">
			<input type="hidden" name="predmet" value="${predmet.idPredmet}">	
		</form>
		
		<sec:authorize access="hasRole('student')">
			<a href="/portal/reportsC/pitanjaodgovori"><Button>Pitanja i odgovori studenta</Button></a>
		</sec:authorize>
		<a class="logout" href="<c:url value="/security/logout"/>">Logout</a>
</body>
</html>