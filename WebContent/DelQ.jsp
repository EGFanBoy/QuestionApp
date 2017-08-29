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
<c:set var="logged" value="${sessionScope.loggedIn }"/>
<c:if test="${sessionScope.loggedIn !='yes' }">
<c:redirect url="/Login.html"/>
</c:if>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
url="jdbc:mysql://localhost/questions"
user="root" password="skadoosh"/>
 <sql:query dataSource="${db}" var="rs">
 SELECT*from questions</sql:query>
 <form action="QuestionDeleter" method="get">
 <%!int i=1; %>
<c:forEach var="table" items="${rs.rows}">  
<c:set var="checkVal" value="check"></c:set>
<c:set var="checkNumb" value="<%=i%>"></c:set>
${table.question}:<input type="checkbox" name="${checkVal}${checkNumb}" value="checked">
<%i++; %>
<br/></c:forEach>
<input type="submit" value="Delete">
</form>
</body>
</html>