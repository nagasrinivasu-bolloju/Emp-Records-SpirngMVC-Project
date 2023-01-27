<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>
	body{
	background-color:rgba(0,150,100,0.5);}
	
	.nav-bar{
	width:100%;
	height:80px;
	box-shadow:0px 4px 8px 0 rgba(0,0,0,0.2),0px 6px 20px 0 rgba(0,0,0,0.19);
	border-radius:5px;
	}
	.title{
	float:left;
	margin-top:0px;
	margin-left:350px;
	font-size:23px;
	font-family:Arial;
	}
	.form-group{
	border:0px;}
	
	.container{
	width:40%;
	height:400px;
	margin-top:40px;
	margin-left:360px;
	/* border:5px solid red; */
	/* background-color:rgba(0,0,0,0.5); */
	box-shadow:0px 4px 8px 0 rgba(0,0,0,0.2),0px 6px 20px 0 rgba(0,0,0,0.19);
	border-radius:5px;}
	 
	 label{
	 /* border:2px solid red; */
	  margin-top:10px;
	 margin-left:80px;
	 font-size:25px;
	 font-style:arial;
	 font-weight:600;
	 color:black;}
	 
	 .label-text{
	 margin-left:110px;}
	 
	 .input-div{
	 background-color:rgba(255,255,255, 0.73);
	 opacity:0.6;
	 float:right;
	 margin-right:80px;
	 margin-top:30px;
	border-radius:5px;
	width:300px;
	height:30px;
	border:0px;}
	
	 .label-div{
	 /* border:5px solid red; */
	 height:50px;}
	
	.add-emp{
	margin-top:30px;
	margin-left:35%;
width:150px;
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
.add-emp:hover{
background-color:rgba(0,150,0,0.7);
}

.button{
border:5px solid red;
margin-top:5px;
margin-left:10px;
}
</style>

</head>
<body>

	<%
	response.setHeader("Cache-control","no-cache,no-store,must-revalidate");
 	response.setHeader("pragma","no-cache");
 	response.setHeader("Expires","0");
	if(session.getAttribute("admin")==null)
		{
			response.sendRedirect("index.jsp");
		}
	%>
	<header>
		<nav class="nav-bar"
			style="background-color:orange">
			<div class="title">
				<h1>Employee Record Management  </h1>
			</div>
			 
		</nav>
	</header>.
	
	<!-- remove-employee  0
	read-employee    1
	update-employee  2 -->
	<div class="container">
				<form action="read-employees-with-similar-names" method="post">
				<input type="hidden" name="actionState" value="${action}"/>
				<fieldset class="form-group">
					<div class="label-div"><label ><div class="label-text">Employee First Name:</div></label></div>
					<br>
					<!-- <input class="input-div" type="Number" placeholder="enter employee id" name="id" min="100" max="10000" required="required"> -->
				 
					<input class="input-div" type="text" placeholder="                 enter employee First Name" name="name"
					onkeypress="return event.charCode>=65 && event.charCode<=90 || 
					event.charCode>=97 && event.charCode<=122 || event.charCode==32" required/>
 
				</fieldset>
	
				<input class="add-emp" type="submit" value="submit"  >
				</form>
				<div class="child2">
				<form action= "return-to-home" method="post">
					<input class="add-emp" type="submit" value="Back">
				</form>
	</div>
		</div>
	</div>
</body>
</html>