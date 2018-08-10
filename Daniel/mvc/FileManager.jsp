<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>File Manager</title>
</head>
<body>

	<table border="1">
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
					<a href="Rename?id=${file.id}"> Rename</a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<form action="uploader" method="post" enctype="multipart/form-data">
		<input type='file' name='fileUp' /> <br /> 
		File Name: <input type="text" name="name" /> <input type="submit" value="Upload" />
	</form>

</body>
</html>