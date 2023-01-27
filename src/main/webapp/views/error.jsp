<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>error page</title>
<style>
	.head
	{
	margin-left:40%;
	}
	
	.add-emp
	{
	margin-left:140px;
	width:200px;
	padding:7px;
	font-size:16px;
	font-family:helvetica;
	color:white;
	background-color:orange;
	border-radius:3px;
	box-shadow:0px 4px 8px 0 rgba(0,0,0,0.2),0px 6px 20px 0 rgba(0,0,0,0.19);
	border:2px;
	cursor:pointer;
	}
	.add-emp:hover
	{
	background-color:rgba(0,150,0,0.7);
	}
	.child2
	{
	margin-top:50px;
	margin-left:360px;
	}
</style>
</head>
<body bgcolor="red">\

	 <%
	 	response.setHeader("Cache-control","no-cache,no-store,must-revalidate");
	 	response.setHeader("pragma","no-cache");
	 	response.setHeader("Expires","0");
		if(session.getAttribute("admin")==null)
		{
			response.sendRedirect("index.jsp");
		}
	 %>
	<div>
	<h1 class="head">Error Occured!!!!</h1>
	<br>
	 <h3>${msg}</h3>
	</div>
	<div class="child2">
		<form action= "return-to-home" method="post">
			<input class="add-emp" type="submit" value="Back">
		</form>
	</div>
</body>
</html>