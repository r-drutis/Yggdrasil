<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Yggdrasil</title>
</head>
<body>
	Select a FASTA format file to upload
	
	<form method="post" action="${pageContext.request.contextPath}/FastaReader" encType="multipart/form-data">
		<input type="file" name="file" value="Select fasta file" />
		<input type="submit" value="Start upload" />
	
	</form>

</body>
</html>