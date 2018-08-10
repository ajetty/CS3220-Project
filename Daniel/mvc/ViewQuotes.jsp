<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Quotes</title>
</head>
<body>
<h1>Today's Quotes</h1>
	<c:if test="${quotePassed!=null}">
   	  <p>Quote: ${quotePassed.quote}</p>
 	  <p>By: ${quotePassed.author}</p>
  	  <a href="QuotesV?id=${quotePassed.id}&ld=1">Like</a>
  	  <a href="QuotesV?id=${quotePassed.id}&ld=0">DisLike</a>
    </c:if>

	<c:if test="${quotePassed==null}">
		<h1>No more Quotes</h1>
	</c:if>
	
<a href="Quotes" action="post">admin</a>
</body>
</html>