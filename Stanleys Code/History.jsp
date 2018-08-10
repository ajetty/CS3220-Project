<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>History</title>
</head>
<body>

<c:choose>
	<c:when test="${files.size() > 0}">
	
		<table border="1" style="border-collapse: collapse;text-align: left;">
		<tr>
			<th>File Name</th>
			<th>File Path</th>
			<th>Date</th>
			<th>Options</th>
		</tr>
		
		<c:forEach items="${files}" var="file">
		<tr>
			<td>${file.fileName}</td>
			<td>${file.filePath}</td>
			<td>${file.date}</td>
			<td>
				<a href="DownloadHistory?id=${file.id}">Download</a> 
			</td>
		</tr>
		</c:forEach>
		</table>
		<a href="FileManagerDB">Go back to Main.</a>
	</c:when>
	<c:otherwise>
		<h3>There is no history for this file!</h3>
		<a href="FileManagerDB">Go back to Main.</a>
	</c:otherwise>
</c:choose>
</body>
</html>