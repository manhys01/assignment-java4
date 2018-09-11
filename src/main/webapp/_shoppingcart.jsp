<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="shopping-cart">
	<a href="cart.jsp" class="btn btn-danger"> <i class="fas fa-shopping-cart"></i> 
	<c:set var="totalProducts" value="${totalProducts}" scope="session" /> 
		<c:choose>
			<c:when test="${totalProducts!=null}">
				<span class="badge badge-pill badge-success text-light">${totalProducts}</span>
			</c:when>
			<c:otherwise>
				<span class="badge badge-pill badge-success">0</span>
			</c:otherwise>
		</c:choose>
	</a>
</div>
