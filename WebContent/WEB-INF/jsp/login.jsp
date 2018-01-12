<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Registro de Expedientes - Demuna 2013</title>

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/ext-all.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/grid-examples.css">
	
 	<script type="text/javascript" src="${pageContext.request.contextPath}/extjs/ext-base.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/extjs/ext-all.js"></script>	

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frmlogin.js"></script>
	
</head>
<body style="color:#006600">

<%
	if( session.getAttribute("iddem") == null ){
%>
			
		<script type="text/javascript">
				abrirLogin();
		</script>
<%
	}else{
%>
	<jsp:forward page="index.htm"/>		
<%
	}
%>
</body>
</html>