<% /* JSP: Java Server Page (1999) Une JSP débute par les directives */ %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% /* On fait appel à une bibliothèque de balises (taglib) : JSTL
    Java Standard Tag Library
    */ %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendrier Gif</title>
<link href="style/theme1.css" rel="stylesheet">
</head>
<body>
<h1>Calendrier Gif</h1>
<c:if test="${notification ne null}"><h2>${notification}</h2></c:if>
<c:if test="${param.notification ne null}"><h2>${param.notification}</h2></c:if>
<form action="connexion" method="post">
	<input type="email" name="EMAIL" placeHolder="Email" required><br>
	<input type="password" name="MOT_DE_PASSE" placeHolder="Mot de Passe" required><br>
	<input type="submit" value="Connexion">
</form>
<a href="inscription">S'inscrire</a>
<h2>Utilisateurs ayant réagi au moins 5 fois</h2>
<ul>
<c:forEach items="${utilisateurs}" var="utilisateur">
	<li>${utilisateur.prenom}</li>
</c:forEach>
</ul>

<h2>Nombre d'inscrits par année et par mois</h2>
<c:forEach items="${nbInscrits}" var="nbInscrits">
	<span>${nbInscrits.annee} / ${nbInscrits.mois} : ${nbInscrits.nbInscrits}</span><br />
</c:forEach>

<jsp:include page="piedDePage.jsp"></jsp:include>
</body>
</html>