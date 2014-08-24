<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<style type="text/css">
	.menu {
    	font-size: 0.8em;
		text-align:right;
		margin:20px;
	}
</style>

<div class="menu">
	<c:out value="${client.prenom}" />&nbsp;<c:out value="${client.nom}" />&nbsp;|&nbsp;
	<a href="<c:url value="/?action=achat" />">Achat</a>&nbsp;|&nbsp;
	<a href="<c:url value="/?action=historiqueAchat" />">Historique achat</a>&nbsp;|&nbsp;
	<a href="<c:url value="/?action=deconnexion" />">D&eacute;connexion</a>&nbsp;|&nbsp;
</div>