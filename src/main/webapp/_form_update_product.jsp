<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="poly.manhnd.assignment.daos.entities.implement.ManufacturerDAOImp"%>
<%@page
	import="poly.manhnd.assignment.daos.entities.implement.CategoryDAOImp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="manh-tags"%>

<m:getProductById productId="${param.id}" />
<form action="ProductManagerServlet" method="post"
	enctype="multipart/form-data">
	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Chủng loại</label>
		</div>
		<div class="col-md-4">
			<select name="categoryId" class="form-control">
				<c:set var="categories"
					value="<%=new CategoryDAOImp().getAllCategories()%>" />
				<c:if test="${categories!=null}">
					<c:forEach var="rows" items="${categories}">
						<c:choose>
							<c:when test="${rows.id==Product.category.id}">
								<option value="${rows.id}" selected="selected">${rows.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${rows.id}">${rows.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
			</select>
		</div>

		<div class="col-md-2">
			<label class="col-form-label">Nhà sản xuất</label>
		</div>
		<div class="col-md-4">
			<select name=manufacturerId class="form-control">
				<c:set var="manufacturer"
					value="<%=new ManufacturerDAOImp().getAllManufacturers()%>" />
				<c:if test="${manufacturer!=null}">
					<c:forEach var="rows" items="${manufacturer}">
						<c:choose>
							<c:when test="${rows.id==Product.manufacturer.id}">
								<option value="${rows.id}" selected="selected">${rows.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${rows.id}">${rows.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
			</select>
		</div>

	</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Tên Sản Phẩm</label>
		</div>
		<div class="col-md-10">
			<input type="text" name="ProductName" value="${Product.name}"
				class="form-control">
		</div>
	</div>
	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Đơn giá</label>
		</div>
		<div class="col-md-4">
			<fmt:formatNumber var="ProductPrice" type="number" pattern="###.#">${Product.price}</fmt:formatNumber>
			<input type="number" name="ProductPrice" value="${ProductPrice}"
				class="form-control">
		</div>
	</div>
	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Trong kho</label>
		</div>
		<div class="col-md-2">
			<input type="number" name="ProductInStock" value="${Product.inStock}"
				class="form-control">
		</div>
	</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Mô tả sản phẩm</label>
		</div>
		<div class="col-md-10">
			<textarea name="description" rows="5" class="form-control">${Product.description}</textarea>
		</div>
	</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Hình ảnh</label>
		</div>
		<div class="col-md-10">
			<img alt="${Product.name}" width="200px" src="${Product.image}">
			<input type="file" name="file-upload" class="form-control-plaintext"
				accept="image/*">
		</div>
	</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Trạng thái</label>
		</div>
		<div class="col-md-4">
			<select name="discontinued" class="form-control">
				<c:choose>
					<c:when test="${Product.discontinued}">
						<option value="false">Mở bán</option>
						<option value="true" selected="selected">Ngừng kinh doanh</option>
					</c:when>
					<c:otherwise>
						<option value="false" selected="selected">Mở bán</option>
						<option value="true">Ngừng kinh doanh</option>
					</c:otherwise>
				</c:choose>
			</select>
		</div>
	</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Thời gian tạo</label>
		</div>
		<div class="col-md-4">
			<input type="text" value="${Product.createAt}" readonly
				class="text-info form-control-plaintext">
		</div>

		<div class="col-md-2">
			<label class="col-form-label">Lần sửa trước</label>
		</div>
		<div class="col-md-4">
			<input type="text" value="${Product.latestUpdate}" readonly
				class="text-danger form-control-plaintext">
		</div>
	</div>

	<div class="form-group row">
		<div class="col-md-2"></div>
		<div class="col-md-3">
			<div class="btn-group">
				<input type="hidden" name="ProductImage" value="${Product.image}">
				<input type="hidden" name="id" value="${Product.id}">
				<c:if test="${param.page!=null}">
					<input type="hidden" name="page" value="${param.page}">
				</c:if>
				<button type="submit" class="btn btn-primary mr-2" name="action"
					value="Update">
					<i class="fas fa-pencil-alt"></i>
					Sửa
				</button>

				<c:url var="myURL" value="/product.jsp">
					<c:if test="${param.page!=null}">
						<c:param name="page" value="${param.page}" />
					</c:if>
				</c:url>
				<a href="${myURL}" class="btn btn-success">
					<i class="fas fa-arrow-left"></i>
					Quay Lại
				</a>
			</div>
		</div>
	</div>
</form>
