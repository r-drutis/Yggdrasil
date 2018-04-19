<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value="css/treedisplay.css" />" rel="stylesheet">
	<script type="text/javascript" src="js/raphael/raphael-min.js" ></script>
	<script type="text/javascript" src="js/jsphylosvg-min.js"></script>

	
	<script type="text/javascript">	
	window.onload = function(){
		
			var dataObject = { newick: '${sessionScope.newick}' };
			phylocanvas = new Smits.PhyloCanvas(
				dataObject,
				'svgCanvas',
				800, 800
			);	
	};
	</script>
	
		
<title>UPGMA Tree</title>
</head>
<body>
	<div class="container-1">
  
  <div class="tree-box">
    <h3><span style="font-family: monospace;">UPGMA Tree</span></h3>    
    <div id="svgCanvas"> </div> 
  </div>
  <div class="ui-box">
    <div class="container-2">
    
    <div class="sequencecontainer">
      <div class="name-box">
      <h3><span style="font-family: monospace;">Names</span></h3>
      
      <form name="mutate" action="${pageContext.request.contextPath}/FastaReader" method="post">
      
      <c:set var = "label" value="0" scope="page"/>      
      <c:forEach items="${sessionScope.sequence}" var="item">
      
      		<input type="checkbox" name="mutate" id="${label}" value="${label}"/>
      		<label for="${label}"><span style="font-family: monospace;font-size: 20px;"><c:out value="${item.header}"/></span></label><br/> 		
      		<c:set var="label" value="${label + 1}" scope="page"/>
      		
      </c:forEach>

      </div>
      <div class="seq-box">
        <h3><span style="font-family: monospace;">Sequences</span></h3>
        <c:forEach items="${sessionScope.sequence}" var="item">
        	<c:set var="str" value="${item.sequence}"/>        

        	<c:forEach var="i" begin="0" end="${fn:length(str)-1}" step="1">
        		<c:set var ="base" value="${str.charAt(i)}"/>
        		
	       			<c:if test ="${base eq 'A'.charAt(0)}">
 						<span style="background-color: #9ccc65;font-family: monospace;font-size: 20px;"><c:out value="${fn:trim(base)}"/></span>
  					</c:if>
  					<c:if test ="${base eq 'T'.charAt(0)}">
 						<span style="background-color: #ffeb3b; font-family: monospace; font-size: 20px"><c:out value="${fn:trim(base)}"/></span>
  					</c:if>
  					<c:if test ="${base eq 'C'.charAt(0)}">
 						<span style="background-color: #64b5f6; font-family: monospace; font-size: 20px"><c:out value="${fn:trim(base)}"/></span>
  					</c:if>
  					<c:if test ="${base eq 'G'.charAt(0)}">
 						<span style="background-color: #ef5350; font-family: monospace; font-size: 20px"><c:out value="${fn:trim(base)}"/></span>
  					</c:if>
 			
			</c:forEach><br/>

    	</c:forEach> 
      </div>
    </div>
    
    <div class ="tree-box">
      <h3><span style="font-family: monospace;">Mutation</span></h3>
    
      	<input type="reset" value="Clear" name="clear" />
      	<input type="submit" value="Mutate" name= "submit" />
      	
      	<div class="slidecontainer">
      		<p><span style="font-family: monospace;">Mutation Rate:</span></p>
  			<input type="range" min="1" max="100" value="50" class="slider" id="mutRate" name="mutRate"> 
  			<p><span style="font-family: monospace;">Transition/Transversion bias:</span></p>
  			<input type="range" min="1" max="100" value="50" class="slider" id="trBias" name="trBias">
  		</div>
      	
      </form>
      
    </div>
    
  </div>
 </div> 
</div>

	

</body>
</html>