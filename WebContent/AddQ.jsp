<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add your question(s)</title>
</head>
<body>
<c:set var="logged" value="${sessionScope.loggedIn }"/>
<c:if test="${sessionScope.loggedIn !='yes' }">
<c:redirect url="/Login.html"/>
</c:if>
<form action="AddField" method="post">

<Input type="text" size="80" name="question"/>
<button type="submit">Add your question</button>


</form>
</body>
</html>