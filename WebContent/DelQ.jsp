<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="CSS/bootstrap.min.css" rel="stylesheet">
<link href="CSS/custom.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<title>Delete your question(s)</title>
</head>
<body ng-app="checkApp" ng-controller="validateCheck">
	<!-- If loggedIn session variable was not set to yes, user will be redirected to the login page -->
	<c:set var="logged" value="${sessionScope.loggedIn }" />
	<c:if test="${sessionScope.loggedIn !='yes' }">
		<c:redirect url="/Login.html" />
	</c:if>

	<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost/questionapp" user="root"
		password="skadoosh" />
	<sql:query dataSource="${db}" var="rs">SELECT*from interviewq</sql:query>
	<!--variable that is incremented to create a dynamic number of checkboxes  -->
	<%
		int i = 1;
	%>

	<form action="QuestionDeleter" method="get">

		<!-- Loops and displays all questions from the db aswell as a checkbox for them to be deleted -->
		<c:forEach var="table" items="${rs.rows}">
			<c:set var="checkVal" value="check"></c:set>
			<!-- the value of i is given to an arbitrary variable to facilitate concatenation -->
			<c:set var="checkNumb" value="<%=i%>"></c:set>
			<div class="checkbox">

			<label><input id="${checkVal}${checkNumb}" type="checkbox" name="${checkVal}${checkNumb}" value="checked" ng-model="${checkVal}${checkNumb}" />${table.question} </label>
			</div>
			<%
				i++;
			%>
			<br>
		</c:forEach>
		<!-- if there are no questions checkNumb will never be instantiated so if its null shows a message that there isnt any questions -->
		<c:if test="${checkNumb==null }">
		<p>There are currently no questions in the database.</p>
	</c:if>
		<!-- Hidden input field so that javascript can dynamically know how many checkboxes are present -->
		<input type="hidden" id="value" value="<%=i%>" />
		<button type="submit" ng-disabled="!checkChecked()" class="btn btn-custom">Delete Questions</button><br>
		 <a href="AddDel.jsp">Go back to main menu</a>
	</form>
	<script src="js/checkBox.js"></script>
</body>
</html>