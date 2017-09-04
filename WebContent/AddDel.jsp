<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/bootstrap.min.css" rel="stylesheet">
<link href="CSS/custom.css" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Corben:bold" rel="stylesheet" type="text/css">
 <link href="http://fonts.googlepis.com/css?family=Nobile" rel="stylesheet" type="text/css">
<title>Add or delete questions</title>
</head>
<body>
<div class="container">
<div class="text-center">
<!-- If loggedIn session variable was not set to yes, user will be redirected to the login page -->
<c:set var="logged" value="${sessionScope.loggedIn }"/>
<c:if test="${sessionScope.loggedIn !='yes' }">
<c:redirect url="/Login.html"/>
</c:if>
<a href="AddQ.jsp">Add Questions</a>
<br/>
<a href="DelQ.jsp">Delete Questions</a>
</div>
</div>
</body>
</html>