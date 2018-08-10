<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Quotes Admin</title>
</head>
<body>

<table border="1">
  <tr><th>Quote</th><th>Author</th><th>Likes</th><th>Dislikes</th><th>Action</th></tr>
<c:forEach items="${quotes}" var="quote">
  <tr>
    <td>${quote.quote}</td>
    <td>${quote.author}</td>
    <td>${quote.likes}</td>
    <td>${quote.dislikes}</td>
    <td><a href="QuoteDelete?action=delete&id=${quote.id}">Delete</a></td>
  </tr>
</c:forEach>
</table>

<form action="Quotes" method="post">
Author: <input type="text" name="author" /> <br />
Quote: <textarea name="quote"></textarea> <br />
<input type="submit" name="add" value="Add" />
</form>
<a href="Quotes?&sort=author">Author sort</a>
<a href="Quotes?&sort=quote">Quote sort</a>
<a href="Quotes?&sort=like">Like sort</a>
<a href="Quotes?&sort=dislike">Dislike sort</a>
<a href="QuotesV">Today's Quote</a>
</body>
</html>