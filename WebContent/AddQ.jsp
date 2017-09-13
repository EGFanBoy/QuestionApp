<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="CSS/bootstrap.min.css" rel="stylesheet">
<link href="CSS/custom.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<title>Add your question(s)</title>
</head>
<body ng-app="addApp" ng-controller="validateAdd">
	<!-- If loggedIn session variable was not set to yes, user will be redirected to the login page -->
	<c:set var="logged" value="${sessionScope.loggedIn }" />
	<c:if test="${sessionScope.loggedIn !='yes' }">
		<c:redirect url="/Login.html" />
	</c:if>
	<!-- Message box and submit button -->
	<form name="myForm" action="AddField" method="post" novalidate>
		<span style="color: red" ng-show="!myForm.question.$pristine&&myForm.question.$invalid">
		<span ng-show="myForm.question.$error.required"> Please enter a question</span><br></span>
		 <Input class="form-control" type="text" name="question" ng-model="question" required />
		<button ng-disabled="myForm.question.$pristine||myForm.question.$invalid" type="submit" class="btn btn-custom">Add your question</button>

	</form>
	<br>
	<a href="AddDel.jsp">Go back to main menu</a>
	<script src="js/addChecker.js"></script>
</body>
</html>