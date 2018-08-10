<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>GuestBook</title>
</head>
<body>


<table border="1">
  <tr><th>Id</th><th>Name</th><th>Message</th><th>Operations</th></tr>
<c:forEach items="${entries}" var="entry">
  <tr>
    <td>${entry.id}</td>
    <td>${entry.name}</td>
    <td><c:out value="${entry.message}" escapeXml="true" /></td>
    <td><a href="EditComment?id=${entry.id}">Edit</a></td>
  </tr>
</c:forEach>
</table>

<p><a href="AddComment">Add Comment</a></p>

</body>
</html>