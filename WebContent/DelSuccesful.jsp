<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deletion successful</title>
</head>
<body>


<c:out value="${'Question(s) successfully deleted '}"/>
<br/>
<a href="DelQ.jsp">Go back to deletion menu</a>
<br/>
<a href="AddDel.jsp">Go back to main question menu</a>
</body>
</html>