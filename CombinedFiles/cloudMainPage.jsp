<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Cloud</title>
<style>
p, h1 {
	font-family: arial, sans-serif;
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>

	<h1>My Uploads</h1>

	<form action="SearchController" method="post">
		<p>
			Search: <input type="text" name="searchName" /> <input type="submit"
				value="Search" />
		</p>
	</form>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>File Name</th>
			<th>File Path</th>
			<th>Last Modified</th>
			<th>Options</th>
		</tr>
		<c:forEach items="${files}" var="file">
			<tr>
				<td>${file.id}</td>
				<td>${file.fileName}</td>
				<td style="width: 100px;">${file.filePath}</td>
				<td>${file.lastModified}</td>
				<td><a href="DeleteController?id=${file.id}">Delete</a> | <a
					href="DownloadController?id=${file.id}"> Download</a> |<a
					href="RenameController?id=${file.id}"> Rename</a></td>
			</tr>
		</c:forEach>
	</table>

	<form action="UploadController" method="post"
		enctype="multipart/form-data">
		<p>
			<input type='file' name='fileUp' />
		</p>
		<p>
			File Name: <input type="text" name="name" /> <input type="submit"
				value="Upload" />
		</p>
	</form>

</body>
</html>