<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>employee form</title>

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
	/* background-color:rgba(0,150,100,0.5); */
	box-shadow:0px 4px 8px 0 rgba(0,0,0,0.2),0px 6px 20px 0 rgba(0,0,0,0.19);
	border-radius:5px;}
	 
	 label{
	 /* border:2px solid red; */
	  
	 margin-left:50px;
	 font-size:25px;
	 font-style:arial;
	 font-weight:600;
	 color:black;}
	 
	 .emplabel{
	 margin-left:55px;}
	.empid{
	margin-left:10px;
	height:50px;
	width:95%;}
	 
	 input{
	 background-color:rgba(255,255,255, 0.73);
	 opacity:0.6;
	 float:right;
	 margin-right:50px;
	border-radius:5px;
	width:200px;
	height:30px;
	border:0px;}
	
	.div-caption{
	border:0px;
	}
	
	.add-emp{
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
			response.sendRedirect("login.jsp");
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
	
	<div class="container">
		<div class="form-div">
			<c:if test="${emp != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${emp == null}">
					<form action="insert" method="post">
				</c:if>
				<div class="div-caption">
				<caption>
					<h2>
						<c:if test="${emp != null}">
            				<h2 style="margin-left:100px;"\>Updating Employee Record</h2>
            			</c:if>
						<c:if test="${emp == null}">
            				<h2 style="margin-left:100px;">Adding New Employee Record</h2>
            			</c:if>
					</h2>
					<hr>
				</caption>
				</div>
				<c:if test="${emp != null}">
					<input class="inpu1" type="hidden" name="empid" value="<c:out value='${emp.empid}' />" />
				</c:if>
				
				<c:if test="${emp==null }">
					<input class="inpu1" type="hidden" name="empid" value="0" />
				</c:if>
				
				<fieldset class="form-group">
					<label>FirstName:</label>
					<input type="text" value="<c:out value='${emp.firstName}' />" class="form-control"
						name="firstName" onkeypress="return event.charCode>=65 && event.charCode<=90 || 
					event.charCode>=97 && event.charCode<=122 || event.charCode==32"required="required">
				</fieldset>

				<fieldset class="form-group">
					 <label>LastName:</label>  
					 <input type="text" value="<c:out value='${emp.lastName}' />" onkeypress="return event.charCode>=65 && event.charCode<=90 
					 || event.charCode>=97 && event.charCode<=122 || event.charCode==32" class="form-control" name="lastName">
				</fieldset>

				<fieldset class="form-group">
					<label>Salary:</label>
					<input type="Number" name="salary" value="<c:out value='${emp.salary}'/>" min="0" step="0.01" class="form-control"
					  required/>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Addr Id:</label> 
					<input type="Number"  value="<c:out value='${emp.addrid}' />" class="form-control"
						name="addrid" min="500" onkeypress="return event.charCode>=48 && event.charCode<=57" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Address:</label> <input type="text"
						value="<c:out value='${emp.address}' />" class="form-control"
						name="address">
				</fieldset>
				
				<div class="button2">
					<input class="add-emp" type="submit" value="save"  >
				</div>
				</form>
				<div class="button1">
					<form action="return-to-home">
					<input class="add-emp" type="submit" value="Back"  >
					</form>
				</div>
		</div>
	</div>
</body>
</html>