<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp" isELIgnored="false"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>list of employees</title>

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
.list{
width:300px;
 float:right;
margin-right:50px;
margin-top:0px;
}

.text-center{
margin-left:42%;
}
 
table{
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 90%;
  margin-left:50px;
  margin-top:20px;
}

td, th {
  border: 1px solid #dddddd;
  text-align:center;
  padding: 8px;
}
th{
 background-color:green; 
}

tr:nth-child(even) {
  background-color:rgba(0,200,200,0.5);;
}

.add-emp{
margin-left:50px;
width:350px;
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
background-color:rgba(0,0,0,0.7);
}
 
 
.child2{
margin-top:50px;
margin-left:360px;}
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
				<h1>Employee Record Management </h1>
			</div>
		</nav>
	</header>
	<br>
	
	<div class="row">
		<div class="container">
			<h3 class="text-center">Employees</h3>
			<hr>
			<%-- <div class="parent">
			<div class="child1">
				<form action="<%request.getContextPath();%>new" method="post">
					<input class="add-emp" type="submit" value="Add Employee">
				</form>
			</div>
			<div class="child2">
				<form action="<%request.getContextPath();%>list" method="post">
					<input class="add-emp" type="submit" value="Employees">
				</form>
			</div>
			</div> --%>
			
			<br>
			<table class="table">
				<thead>
					<tr>
						<th>Emp Id</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Salary</th>
						<th>Addr Id</th>
						<th>Address</th>
						<c:if test="${action!=null && action!=1}">
							<th>Action</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
				 
					<c:forEach var="emp" items="${employees}">
						<tr>
							<td><c:out value="${emp.empid}" /></td>
							<td><c:out value="${emp.firstName}" /></td>
							<td><c:out value="${emp.lastName}" /></td>
							<td><c:out value="${emp.salary}" /></td>
							<td><c:out value="${emp.addrid}" /></td>
							<td><c:out value="${emp.address}" /></td>
							<c:if test="${action!=null && action!=1}">
							<td>
								<c:if test="${action == 0}">
									<form action="delete" method="post">
										<input type="hidden" name="id" value="${emp.empid}" />
										<input type="submit" value="Delete"/>
									</form>
								</c:if>
								<c:if test="${action == 2}">
									<form action="edit" method="post">
										<input type="hidden" name="id" value="${emp.empid}" />
										<input type="submit" value="Update"/>
									</form>
								</c:if>
								 <!-- can be written in another way -->
							</td>
							</c:if>
						</tr>
					</c:forEach>

				</tbody>

			</table>
			<div class="msg">
			<c:if test="${length ==0 }">
				<h3 style="margin-left:40%;">!!!!No Records Found!!!!</h3>
			</c:if>
			</div>
		</div>
	</div>
	<div class="child2">
				<form action= "return-to-home" method="post">
					<input class="add-emp" type="submit" value="Back">
				</form>
	</div>
</body>
</html>