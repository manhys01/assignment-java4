<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="manh-tags"%>

<m:getCategories />
<div class="container">
	<div class="list-group">
		<div
			class="list-group-item d-flex justify-content-between align-items-center active"
			data-toggle="collapse" data-target="#Category" aria-expanded="true"
			aria-controls="#Category">
			Danh mục sản phẩm
			<span class="badge badge-secondary badge-pill">${totalCategory}</span>
		</div>
		<div class="collapse show" id="Category">
			<c:forEach var="category" items="${categories}">
				<div
					class="list-group-item d-flex justify-content-between align-items-center">
					<m:getProductsByCategoryId categoryId="${category.id}" />
					<c:choose>
						<c:when test="${sizeProducts>0}">
							<a href="showproduct.jsp?category=${category.id}">${category.name}</a>
						</c:when>
						<c:otherwise>
							<a class="disabled">${category.name}</a>
						</c:otherwise>
					</c:choose>
					<span class="badge badge-secondary badge-pill">${sizeProducts}</span>
				</div>
			</c:forEach>
		</div>
	</div>

	<m:getManufacturers />
	<div class="list-group mt-5 mb-5">
		<div
			class="list-group-item d-flex justify-content-between align-items-center active"
			data-toggle="collapse" data-target="#Manufacturer"
			aria-expanded="true" aria-controls="#Manufacturer">
			Hãng sản xuất
			<span class="badge badge-secondary badge-pill">${totalManufacturer}</span>
		</div>
		<div class="collapse show" id="Manufacturer">
			<c:forEach var="manufacturer" items="${manufacturers}">
				<div
					class="list-group-item d-flex justify-content-between align-items-center">
					<m:getProductsByManufacturerId manufacturerId="${manufacturer.id}" />
					<c:choose>
						<c:when test="${sizeProducts>0}">
							<a href="showproduct.jsp?manufacturer=${manufacturer.id}">${manufacturer.name}</a>
						</c:when>
						<c:otherwise>
							<a class="disabled">${manufacturer.name}</a>
						</c:otherwise>
					</c:choose>
					<span class="badge badge-secondary badge-pill">${sizeProducts}</span>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
