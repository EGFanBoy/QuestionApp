<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/bootstrap.min.css" rel="stylesheet">
<link href="CSS/custom.css" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Corben:bold"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googlepis.com/css?family=Nobile"
	rel="stylesheet" type="text/css">
<title>QuestionApp-Interview</title>
</head>
<body>
	<!-- information to connect to the db -->
	<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost/questionapp" user="root" password="skadoosh" />
	<sql:query dataSource="${db}" var="rs">
 SELECT*from interviewq</sql:query>
	<form action="QuestionApp" method="post">
		<%
			int i = 1;
		%>
		<!-- if the session variable is set to yes the page will display an error message -->
		<c:if test="${sessionScope.err=='yes' }">

			<font color="red"> one of more field(s) were left empty.
				Please fill out every question.</font>
			<br />
		</c:if>
		



				<!-- Displays the questions from the database followed by a textbox -->
				<c:forEach var="table" items="${rs.rows}">

					<c:set var="textVal" value="answer"></c:set>
					<!-- the value of i is given to an arbitrary variable to facilitate concatenation -->
					<c:set var="textNumb" value="<%=i%>"></c:set>

					<p>${table.question}</p>
					<input type="text" size="80" name="${textVal}${textNumb}">
					<br />
					<%
						i++;
					%>

				</c:forEach>
				<br />
			
				<button type="submit" class="btn btn-custom">Submit answers</button>
		

	</form>
</body>
</html>