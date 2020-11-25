<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>   

<!DOCTYPE html>
<html>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link href="${contextPath}/ressources/css/bootstrap.min.css" rel="stylesheet">
<head>
<meta charset="ISO-8859-1">
<title>Dao 1</title>
</head>

<header>
<a href="${contextPath}/Controller2"> Moteur de Recherche</a>
</header>




<body>
<form action="dao" method="post">
<p>
<label for="id" >Identifiant</label>
<input type="text" name="id" id="id">
</p>
<p>
<label for="nom" >Nom</label>
<input type="text" name="nom" id="nom">
</p>
<p>
<label for="prenom">Prenom</label>
<input type="text" name="prenom" id="prenom">
</p>
<button type="submit" name="action" value="Send">Envoyer</button>
<button type="submit" name="action" value="Delete" >Supprimer</button>
<button type="submit" name="action" value="Edit" >Modifier</button>

</form>



<c:out value="---Base de données---"></c:out>
<c:forEach items="${res }" var="etud">
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