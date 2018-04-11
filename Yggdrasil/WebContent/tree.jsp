<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="js/raphael/raphael-min.js" ></script>
	<script type="text/javascript" src="js/jsphylosvg-min.js"></script>

	
	<script type="text/javascript">	
	window.onload = function(){
		
			var dataObject = { newick: '${sessionScope.newick}' };
			phylocanvas = new Smits.PhyloCanvas(
				dataObject,
				'svgCanvas',
				500, 500
			);	
	};
	</script>
	
		
<title>UPGMA Tree</title>
</head>
<body>
	<div id="svgCanvas"> </div>
	
	<p>Your newick format is: <c:out value="${sessionScope.newick}" /></p>

	<p>Your sequences are: </p>

	<c:forEach items="${sessionScope.sequence}" var="item">

        <c:set var="str" value="${item.sequence}"/>
        
        <p>
        <c:forEach var="i" begin="0" end="${fn:length(str)-1}" step="1">
        	<c:set var = "base" value="${str.charAt(i)}"/>
	       	<c:if test = "${base eq 'A'.charAt(0)}">
 				<span style="background-color: #FFFF00"><c:out value="${base}" /></span>
  			</c:if>
  			<c:if test = "${base eq 'T'.charAt(0)}">
 				<span style="background-color: #00FF00"><c:out value="${base}" /></span>
  			</c:if>
  			<c:if test = "${base eq 'C'.charAt(0)}">
 				<span style="background-color: #6699FF"><c:out value="${base}" /></span>
  			</c:if>
  			<c:if test = "${base eq 'G'.charAt(0)}">
 				<span style="background-color: #FF3333"><c:out value="${base}" /></span>
  			</c:if>
 			
		</c:forEach>
		</p>



    </c:forEach>
</body>
</html>