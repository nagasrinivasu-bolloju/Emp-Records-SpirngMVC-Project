<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp" session="true" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>home</title>
<style>

body{
background-color:rgba(0,150,100,0.5);
}
.nav-bar{
width:100%;
height:80px;
box-shadow:0px 4px 8px 0 rgba(0,0,0,0.2),0px 6px 20px 0 rgba(0,0,0,0.19);
}
.title{
float:left;
margin-top:0px;
margin-left:350px;
font-size:23px;
font-family:Arial;
}

.text-center{
margin-left:45%;	
}
.add-emp{
margin-left:0px;
width:350px;
height:60px;
padding:7px;
font-size:20px;
font-family:helvetica;
color:white;
background-color:orange;
border-radius:10px;
box-shadow:0px 4px 8px 0 rgba(0,0,0,0.2),0px 6px 20px 0 rgba(0,0,0,0.19);
border:2px;
cursor:pointer;
}
.add-emp:hover{
background-color:rgba(0,0,0,0.7);
color:orange;
}
 
 .parent{
 height:100px;
 /* border:5px solid red; */
 }
.child1 {
/* border:5px solid green; */
   display: inline-block;
  margin-top:20px;
  margin-left:200px;
  /* vertical-align: middle; */
}
.child2{
/* border:5px solid green; */
display: inline-block;
float:right;
margin-top:20px;
margin-right:200px;
}

.child3{
/* border:5px solid green; */
 
margin-top:20px;
margin-left:200px;}

.add-emp-temp{
margin-left:100px;
width:600px;
height:60px;
padding:7px;
font-size:20px;
font-family:helvetica;
color:white;
background-color:orange;
border-radius:10px;
box-shadow:0px 4px 8px 0 rgba(0,0,0,0.2),0px 6px 20px 0 rgba(0,0,0,0.19);
border:2px;
cursor:pointer;
}


.add-emp-temp:hover{
background-color:rgba(0,0,0,0.7);
color:orange;
box-shadow:0px 4px 80px 0 rgba(0,0,0,0.2),0px 6px 40px 0 rgba(0,0,0,0.19);
}


.logout{
float:right;
margin-top:20px;
margin-right:10px;
}

.add-emp-logout{
margin-left:100px;
width:130px;
height:50px;
padding:2px;
font-size:20px;
font-weight:400;
font-family:helvetica;
color:white;
background-color:rgba(0,150,150,0.6);
border-radius:5px;
box-shadow:0px 4px 8px 0 rgba(0,0,0,0.2),0px 6px 20px 0 rgba(0,0,0,0.19);
border:2px;
cursor:pointer;
}


.parent{
/* border:3px solid red; */
/* margin-top:10px; */
}
.add-emp-logout:hover{
background-color:rgba(0,0,0,0.7);
color:orange;
box-shadow:0px 4px 80px 0 rgba(0,0,0,0.2),0px 6px 40px 0 rgba(0,0,0,0.19);
}
</style>

</head>
<body>
	
	 <%
	 	response.setHeader("Cache-control","no-cache,no-store,must-revalidate");
	 	response.setHeader("pragma","no-cache");
	 	response.setHeader("Expires","0");
		 if(session.getAttribute("admin")==null || (!(session.getAttribute("admin")).equals("activeState")))
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
			 <div class="logout">
			 	<form action="<%request.getContextPath();%>logout" method="post">
						<input class="add-emp-logout" type="submit" value="Log Out">
				</form>
			 </div>
		</nav>
	</header>
	
	 <!-- remove-employee  0
	read-employee    1
	update-employee  2 -->
			<h3 class="text-center">List of Actions</h3>
			<hr>
			<div>
			<div class="parent">
				<div class="child1">
					<form action="<%request.getContextPath();%>new" method="post">
						<input class="add-emp" type="submit" value="Add Employee">
					</form>
				</div>
				<div class="child2">
					<form action="<%request.getContextPath();%>temp" method="post">
						<input type="hidden" name="action" value="0">
						<input class="add-emp" type="submit" value="remove-employee">
					</form>
				</div>
			</div>
			</div>
			
			<div>
			<div class="parent">
				<div class="child1">
					<form action="<%request.getContextPath();%>temp" method="post">
						<input type="hidden" name="action" value="1">
						<input class="add-emp" type="submit" value="Read Employee">
					</form>
				</div>
				<div class="child2">
					<form action="<%request.getContextPath();%>temp" method="post">
						<input type="hidden" name="action" value="2">
						<input class="add-emp" type="submit" value="Update Employee">
					</form>
				</div>
			</div>
			</div>
			
			<div>
			<div class="parent"">
				<div class="child3">
					<form action="<%request.getContextPath();%>list" method="post">
						<input class="add-emp-temp" type="submit" value="Read All Employees Records">
					</form>
				 </div>
			</div>
			</div>
	 
</body>
</html>