<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete your question(s)</title>
</head>
<body>
<!-- If loggedIn session variable was not set to yes, user will be redirected to the login page -->
<c:set var="logged" value="${sessionScope.loggedIn }"/>
<c:if test="${sessionScope.loggedIn !='yes' }">
<c:redirect url="/Login.html"/>
</c:if>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
url="jdbc:mysql://localhost/questions"
user="root" password="skadoosh"/>
 <sql:query dataSource="${db}" var="rs">
 SELECT*from questions</sql:query>
 <!--variable that is incremented to create a dynamic number of checkboxes  -->
 <%int i=1; %>
 
 <form action="QuestionDeleter" method="get">
 <!-- Loops and displays all questions from the db aswell as a checkbox for them to be deleted -->
<c:forEach var="table" items="${rs.rows}">  
<c:set var="checkVal" value="check"></c:set>
<!-- the value of i is given to an arbitrary variable to facilitate concatenation -->
<c:set var="checkNumb" value="<%=i%>"></c:set>
${table.question}:<input type="checkbox" name="${checkVal}${checkNumb}" value="checked">
<%i++; %>
<br/></c:forEach>
<input type="submit" value="Delete">
</form>
</body>
</html>