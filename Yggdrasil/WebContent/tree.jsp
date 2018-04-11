<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<p>Your newick format is: <c:out value="${sessionScope.newick}" /></p>

	<p>Your sequences are: </p>

	<c:forEach items="${sessionScope.sequence}" var="item">

        <p><c:out value="${item.header}" /></p>
        <p><c:out value="${item.sequence}" /></p>



    </c:forEach>
</body>
</html>