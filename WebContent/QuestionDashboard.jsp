<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<link href="CSS/bootstrap.min.css" rel="stylesheet">
<link href="CSS/custom.css" rel="stylesheet">
<title>QuestionApp-Interview</title>
</head>
<body ng-app="nameInfo">
	<!-- information to connect to the db -->
	<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost/questionapp" user="root" password="skadoosh" />
	<sql:query dataSource="${db}" var="rs">SELECT*from interviewq</sql:query>
<form name="myForm" action="QuestionApp"  method="post"  novalidate>
		<%
			int i = 1;
		%>
<div  class="container" ng-controller="validateInfo"  >
		<div class="form-group row justify-content-center">
			<div class="col-md-6 col-lg-3">
		<!-- Page will show an error if first or last name box are left empty -->
			<label class="label-clr">First name: </label>
			<!-- text boxes are given an Id so the other AngularJs controller can have access to the values -->
			<input class="form-control" id="firstName" type="text" name="firstName" ng-model="firstName"  style="width: 257px;" required/>
			<span ng-show="!myForm.firstName.$pristine&&myForm.firstName.$invalid"style="color:red">
			<span ng-show="myForm.firstName.$error.required">Please enter your first name.</span><br></span>
			<label class="label-clr">Last name: </label>
			<input class="form-control" id="lastName" type="text" name="lastName" ng-model="lastName" style="width: 257px;" required/>
			<span ng-show="!myForm.lastName.$pristine&&myForm.lastName.$invalid"style="color:red">
			<span ng-show="myForm.lastName.$error.required">Please enter your last name.</span></span>
			</div>
		</div>
</div><!-- Div that uses the angular app to verify first and last name -->

<div ng-controller="validateInterview">

				<!-- Displays the questions from the database followed by a textbox -->
				<c:forEach var="table" items="${rs.rows}">

					<c:set var="textVal" value="answer"></c:set>
					<!-- the value of i is given to an arbitrary variable to facilitate concatenation -->
					<c:set var="textNumb"  value="<%=i%>"></c:set>
					<p>${table.question}</p>
					<input class="form-control" type="text" name="${textVal}${textNumb}" ng-model="${textVal}${textNumb}" id="${textVal}${textNumb}" required>
					<br />
					<%
						i++;
					%>

				</c:forEach>
				<!-- if there are no questions textNumb will never be instantiated so if its null shows a message that there isnt any questions -->
		<c:if test="${textNumb==null }">
		<p>Sorry we do not have questions for you at this point!!</p>
	</c:if>
				<br />
				<input type="hidden" id="value" value="<%=i-1 %>"/> <!-- hidden input that send number of questions to the
				controller -->
			
				<button type="submit" ng-disabled="answered()" class="btn btn-custom">Submit answers</button>
				
		</div><!-- div that uses the validateInterview controller to verify if all questions have been answered -->

	</form>
	<form action="Index.html">
	<button type="submit" class="btn btn-custom">Exit interview</button>
	</form>
</body>
<script src="js/interviewChecker.js"></script>
</html>