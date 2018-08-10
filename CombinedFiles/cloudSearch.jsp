<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
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

	<h1>Search Results</h1>

	<c:choose>
		<c:when test="${files.size() > 0}">

			<table border="1"
				style="border-collapse: collapse; text-align: left;">
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
						<td><a href="DeleteController?id=${file.id}">Delete</a> <a
							href="DownloadController?id=${file.id}"> Download</a> <a
							href="RenameController?id=${file.id}"> Rename</a></td>
					</tr>
				</c:forEach>
			</table>
			<p>
				<a href="CloudMainPage">Back</a>
			</p>
		</c:when>
		<c:otherwise>
			<p>No matches found.</p>
			<p>
				<a href="CloudMainPage">Back</a>
			<p>
		</c:otherwise>
	</c:choose>
</body>
</html>