<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modification d'une categorie</title>
<style type="text/css">
	h1 { 
  		font-family: serif;
		color: #996600;
		padding: 0.3em;
		text-align: center;
		letter-spacing: 0.3em;
	}
	#frm
	{
    	margin:20px auto;
    	font-size: 0.8em;
	    letter-spacing: 1px;
	}
	
</style>
</head>
<body>
	<c:import url="/WEB-INF/vues/admin/menu.jsp"></c:import>
	<h1>Modification d'une categorie</h1>
	<form action="<c:url value="/?action=modifierCategorie" />" method="post">
		<input type="hidden" name="id" value="<c:out value="${categorie.id}" />" />
		<table id="frm">
			<tr>
				<td>Libelle :</td>
				<td><input type="text" name="libelle" id="libelle" value="<c:out value="${categorie.libelle}" />" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Envoyer"/></td>
			</tr>
		</table>
	</form>

</body>
</html>