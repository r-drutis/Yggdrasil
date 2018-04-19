<link rel="stylesheet" type="text/css" href="css/style.css"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>

	body {
    	background-color: #e5e8d7;
	}

</style>
<title>Yggdrasil</title>
</head>
<body>
	
	<center>
	
	<c:set var="str" value="CTGAGCTAGCGTCTGCTCTCGAGCTAGCGTACTTAGAGCCTGAGCTGACGTACTATCATCAGCCTCCTAGTACTACACAGCTGACTACTAAGCAGCACTAGCGAGTGTCTAACAAGCATCTGTGTACTAATCGACAGCACTTGAAGCACTAGCTAGCACCTACTAACGAGCAGCAGCACTAGCTAGCACCTACTAAGCATGCGTTGTTGACTAAGCCGTGATCTCGTCCACGACAGCACATGTGATTAGCGTAGCCTGTGAAGCGAGCACCTATGATAGAGCAGCACTGTCACTCTGCTCTGATAGAGCTAGCGTCTAAGCCTAACTCACTAGCGTTGAAGCTGAATGCTACTATAGAGCGCTATCTGTATGCTGCTCGTCAGCCATCACCTAACTTGATAGAGCAGCAGCACTAGCTAGCACCTACTAAGCTAGCGTACTTAGAGCATCTGTTGTTCGTGAAGCACTTAGAGCGTCTGTTACAGCACTATCATCAGCTACACTGACAGCAGCACTCTCTACAGCATCCTGGCTTAGTGAAGCCGTCTACACAGCATCCTAACTGCTGACAGCACTCACACATGAAGCTAGTGTAGCGAGCACACTGACAGCAGCAGCACTAGCTAGCACCTACTAAGCTAGCGTACTTAGAGCACAACTGACAGCCTGCTCAGCTGAGATACAACACTACACAGCATGCTAACTCACAGCAGCACTAGCCTCCTATGATAGAGCTGTGCTAGCCACTGTCATCTGCTCTGAAGCCTGCTCAGCCGTCTACACAGCCGTACTCTGCACAGCAGCAGCGATGAGTGTCTCAGCATGCGTTGTTGACTAAGCCATTGTTGATGTACAAGCTGACTCTGTATGAGCCGTACTTGAAGCATCACTCTGCTCAGCAGCATGCGTTGTAGCCTGCTCTAGCTGACAACTTAGCTAATCGACAGCATCCTGGTACTATGAAGCATGCTGTAGCGTAGCCACACTCTGCTCACGAGC"/>        

        	<c:forEach var="i" begin="0" end="${fn:length(str)-1}" step="1">
        		<c:set var ="base" value="${str.charAt(i)}"/>
        		
	       			<c:if test ="${base eq 'A'.charAt(0)}">
 						<span style="background-color: #9ccc65;font-family: monospace;font-size: 10px;"><c:out value="${fn:trim(base)}"/></span>
  					</c:if>
  					<c:if test ="${base eq 'T'.charAt(0)}">
 						<span style="background-color: #ffeb3b; font-family: monospace; font-size: 10px"><c:out value="${fn:trim(base)}"/></span>
  					</c:if>
  					<c:if test ="${base eq 'C'.charAt(0)}">
 						<span style="background-color: #64b5f6; font-family: monospace; font-size: 10px"><c:out value="${fn:trim(base)}"/></span>
  					</c:if>
  					<c:if test ="${base eq 'G'.charAt(0)}">
 						<span style="background-color: #ef5350; font-family: monospace; font-size: 10px"><c:out value="${fn:trim(base)}"/></span>
  					</c:if>
 			
			</c:forEach><br/>
			<img src="https://i.imgur.com/I8KDLB5.png" width="449.1" height="459" /><br/>
	</center>
	
	<center>		
		<h1><span style="font-family: monospace;">Yggdrasil Phylogenetics Tool</span></h1>
	

		<p>Select a FASTA format file to upload <a href="${pageContext.request.contextPath}/FastaReader?action=about">Usage Page</a></p>

		<form method="post" action="${pageContext.request.contextPath}/FastaReader" encType="multipart/form-data">
			<input type="file" name="file" value="Select fasta file" accept=".txt, .fa, .fasta" />
			<input type="submit" value="Start upload" />	
		</form>
	</center>


</body>
</html>