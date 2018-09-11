<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="manh-tags"%>
<div class="container mb-3">
	<div class="list-group">
		<a href="order.jsp"
			class="list-group-item list-group-item-action <%=request.getServletPath().contains("order.jsp") ? "active" : ""%>">
			Quản lý đơn hàng </a>
		<a href="user.jsp"
			class="list-group-item list-group-item-action <%=request.getServletPath().contains("user.jsp") ? "active" : ""%>">Quản
			lý tài khoản</a>
		<a href="product.jsp"
			class="list-group-item list-group-item-action <%=request.getServletPath().contains("product.jsp") ? "active" : ""%>">Quản
			lý sản phẩm</a>
	</div>
</div>