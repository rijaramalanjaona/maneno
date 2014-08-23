<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des categories</title>

<style type="text/css">
	h1 { 
  		font-family: serif;
		color: #996600;
		padding: 0.3em;
		text-align: center;
		letter-spacing: 0.3em;
	}
	#tab
	{
		margin: auto;
	    border: #DDEEFF 2px solid;
	    border-collapse: separate;
	    border-spacing: 2px;
	    empty-cells: hide;
	}
	
	#tab th, fieldset legend
	{
	    color: #996600;
	    background-color: #FFCC66;
	    border: #FFCC66 1px solid;
	    font-variant: small-caps;
	    font-size: 0.8em;
	    letter-spacing: 1px;
	}
	
	#tab td
	{
	    border: #DDEEFF 1px solid;
	    padding-left: 10px;
	    font-size: 0.8em;
	    letter-spacing: 1px;
	}
	
	fieldset
	{
    	border:2px solid #DDEEFF;
    	-moz-border-radius:8px;
    	-webkit-border-radius:8px;	
    	border-radius:8px;	
    	width:35%;
    	margin:20px auto;
    	font-size: 0.8em;
	    letter-spacing: 1px;
	}
	
</style>
</head>
<body>

	<h1>Liste des categories</h1>
	<table id="tab">
		<tr>
			<th></th>
			<th>Libelle</th>
		</tr>
		<c:if test="${listCategorie != null}">
			<c:forEach var="categorie" items="${listCategorie}">
		    <tr>
		       	<%-- <td><a href="<c:url value="/?action=supprimerCategorie&id=${categorie.id}"/>">Supprimer</a></td> --%>
		       	<td><a href="<c:url value="/?action=formModifierCategorie&id=${categorie.id}"/>">Editer</a></td>
		    	<td><c:out value="${categorie.libelle}" /></td>
		    </tr>	
			</c:forEach>
		</c:if>
	</table>
	
	<form action="<c:url value="/?action=insererCategorie" />" method="post">
		<fieldset>
			<legend>Insertion d'une categorie</legend>
			<table>
				<tr>
					<td>Libelle :</td>
					<td><input type="text" name="libelle" value="" id="libelle" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Envoyer"/></td>
				</tr>
			</table>
		</fieldset>
			
	</form>
</body>
</html>