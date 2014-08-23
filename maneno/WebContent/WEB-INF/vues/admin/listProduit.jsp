<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des produits</title>

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

	<h1>Liste des produits</h1>
	<table id="tab">
		<tr>
			<th></th>
			<th>Categorie</th>
			<th>Libelle</th>
			<th>Prix</th>
			<th>Stock</th>
		</tr>
		<c:if test="${listProduit != null}">
			<c:forEach var="produit" items="${listProduit}">
		    <tr>
		       	<td><a href="<c:url value="/?action=formModifierProduit&id=${produit.id}"/>">Editer</a></td>
		    	<td><c:out value="${produit.categorie.libelle}" /></td>
		    	<td><c:out value="${produit.libelle}" /></td>
		    	<td><c:out value="${produit.prix}" /></td>
		    	<td><c:out value="${produit.stock}" /></td>
		    </tr>	
			</c:forEach>
		</c:if>
	</table>
	
	<form action="<c:url value="/?action=insererProduit" />" method="post">
		<fieldset>
			<legend>Insertion d'un produit</legend>
			<table>
				<tr>
					<td>Categorie :</td>
					<td>
						<select name="id_categorie" id="id_categorie">
							<c:forEach var="categorie" items="${listCategorie}">
							<option value="<c:out value="${categorie.id}" />"><c:out value="${categorie.libelle}" /></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Libelle :</td>
					<td><input type="text" name="libelle" id="libelle" value="" /></td>
				</tr>
				<tr>
					<td>Description :</td>
					<td><textarea name="description" id="description"></textarea></td>
				</tr>
				<tr>
					<td>Prix :</td>
					<td><input type="text" name="prix" id="prix" value="" /></td>
				</tr>
				<tr>
					<td>Stock :</td>
					<td><input type="text" id="stock" name="stock" value="" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Envoyer"/></td>
				</tr>
			</table>
		</fieldset>
			
	</form>
</body>
</html>