<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
</head>
<body>

<c:choose>
	<c:when test="${files.size() > 0}">
	
		<table border="1" style="border-collapse: collapse;text-align: left;">
		<tr>
			<th>ID</th>
			<th>File Name</th>
			<th>File Path</th>
			<th>Options</th>
		</tr>
		
		<c:forEach items="${files}" var="file">
		<tr>
			<td>${file.id}</td>
			<td>${file.fileName}</td>
			<td>${file.filePath}</td>
			<td>
				<a href="Delete?id=${file.id}">Delete</a>
				<a href="Download?id=${file.id}"> Download</a> 
				<a href="Update?id=${file.id}"> Update</a>
				<a href="History?id=${file.id}"> History</a>
			</td>
		</tr>
		</c:forEach>
		</table>
		<a href="FileManagerDB">Go back to Main.</a>
	</c:when>
	<c:otherwise>
		<h3>There are no search results!</h3>
		<a href="FileManagerDB">Go back to Main.</a>
	</c:otherwise>
</c:choose>
</body>
</html>