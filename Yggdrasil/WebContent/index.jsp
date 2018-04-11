<link rel="stylesheet" type="text/css" href="css/style.css"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
h.a {
    font-family: Verdana, sans-serif;
}
</style>
<title>Yggdrasil</title>
</head>
<body>

	<center>
	<img src="https://i.imgur.com/EWonCr5.jpg" width="449.1" height="459" />
	<h1 class="a">Yggdrasil Phylogenetic Tool</h1>
	

		<p>Select a FASTA format file to upload</p>

		<form method="post" action="${pageContext.request.contextPath}/FastaReader" encType="multipart/form-data">
			<input type="file" name="file" value="Select fasta file" />
			<input type="submit" value="Start upload" />	
		</form>
	</center>
	

</body>
</html>