<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="_meta-css.jsp"/>
<link rel="stylesheet" href="css/bootstrap.css">

<title>Lỗi Java</title>
</head>
<body>
	<h1>Java ERROR</h1>
	<p>Sorry, Java has thrown an exception.</p>
	<p>To continue, click the back button.</p>
	
	<h2>Details</h2>
	<p>Type: ${pageContext.exception["class"]}</p>
	<p>Message: ${pageContext.exception.message}</p>
	<jsp:include page="_js.jsp"/>
</body>
</html>