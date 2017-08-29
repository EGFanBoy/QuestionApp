<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QuestionApp-Interview</title>
</head>
<body>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
url="jdbc:mysql://localhost/questions"
user="root" password="skadoosh"/>
 <sql:query dataSource="${db}" var="rs">
 SELECT*from questions</sql:query>
 <form action="QuestionApp" method="post">
 <%int i=1; %>
 <c:if test="${sessionScope.err=='yes' }">

 <font color="red"> <c:out value="${'one of more field(s) were left empty. Please fill out every question.'}"></c:out></font>
 <br/>
 </c:if>


 <c:forEach var="table" items="${rs.rows}">  
 <c:set var="textVal" value="answer"></c:set>
 <c:set var="textNumb" value="<%=i%>"></c:set>
 <c:out value="${table.question}">
 </c:out>
  <br/>
   <input type="text" size="80" name="${textVal}${textNumb}">
   <br/>
  <%i++; %>
 </c:forEach>
 <button type="submit">Submit answers</button>
 </form>
</body>
</html>