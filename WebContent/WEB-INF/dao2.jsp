<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>  
<!DOCTYPE html>
<html>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link href="${contextPath}/ressources/css/bootstrap.min.css" rel="stylesheet">
<head>
<meta charset="ISO-8859-1">
<title>Dao 2</title>
</head>
<body>
<h2>Recherche</h2>

<form action="dao2" method="post"> <!-- Mettre un form et un methode post pour acceder a la methode post de la servlet -->
<label for="recherche" >Entrer votre recherche :</label>
<input type="text" name="recherche" id="recherche">
<button type="submit">Envoyer</button>
</form>




<c:out value="---Resultat---"></c:out>
<c:forEach items="${resultat}" var="etud">
<ul>
<li>	
	<c:out value="${etud.identifiant} : "></c:out>
	<c:out value="${etud.nom}"></c:out>
	<c:out value="${etud.prenom}"></c:out> 
	
	</li>

</ul>
</c:forEach>

</body>
</html>