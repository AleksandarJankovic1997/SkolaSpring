<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	body{
		 background: url(https://www.simplilearn.com/ice9/free_resources_article_thumb/book-resources-to-read-for-cissp-certification-exam-article.jpg);
 		 background-repeat: no-repeat;
 		 background-size: cover;
 	}
	fieldset{ 
		 width: 500px;
		border-color: green;
		border-color: solid;
		border: 7px groove;
		 margin: 0 auto;
	}
	input[type=submit]{
	
	}
	input[type=submit]:hover {
 		 background-color: #AAAAAA;
	}
	input[type=submit] {
		background-color: #DDDDDD;;
		cursor: pointer;
	}
	input [type=text]{
		background-color:transparent;
	}
	input[type=password]:focus{
		 background-color: lightblue;
	}
	input[type=text]:focus {
 		 background-color: lightblue;
	}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<!--<c:out value="${error}"></c:out>
		<c:if test="${!empty greska}">
			<p> Invalid username or password</p>
		
		</c:if>
		-->
	 <c:if test="${! empty greska}" >
		<script> alert("Pogresan username ili password") </script>
		</c:if> 
		<fieldset>
		<legend>Dobro dosli na login stranicu!</legend>
		<c:url value="/login" var="loginurl"></c:url>
			<form action="${loginurl}" method="POST">
				<label  for="username">Unesite vas username</label>
				<input type="text" name="username" id="username"><br>
				<label for="password">Unesite vasu lozinku  </label>
				<input type="password" name="password" id="password">
				<input type="submit" value="uloguj se!">
			</form>
		</fieldset>
</body>
</html>