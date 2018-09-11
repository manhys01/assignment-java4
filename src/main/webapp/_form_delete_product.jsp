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
			<p class="form-control-plaintext">${Product.category.name }</p>
		</div>

		<div class="col-md-2">
			<label class="col-form-label">Nhà sản xuất</label>
		</div>
		<div class="col-md-4">
			<p class="form-control-plaintext">${Product.manufacturer.name }</p>
		</div>

	</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Tên Sản Phẩm</label>
		</div>
		<div class="col-md-10">
			<p class="form-control-plaintext">${Product.name}</p>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Đơn giá</label>
		</div>
		<div class="col-md-4">
			<fmt:formatNumber var="ProductPrice" type="number" pattern="###.#">${Product.price}</fmt:formatNumber>
			<p class="form-control-plaintext">${ProductPrice}</p>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Trong kho</label>
		</div>
		<div class="col-md-2">
			<p class="form-control-plaintext">${Product.inStock}</p>
		</div>
	</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Mô tả sản phẩm</label>
		</div>
		<div class="col-md-10">
			<p class="form-control-plaintext">${Product.description}</p>
		</div>
	</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Hình ảnh</label>
		</div>
		<div class="col-md-10">
			<img alt="${Product.name}" width="200px" src="${Product.image}">
		</div>
	</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Trạng thái</label>
		</div>
		<div class="col-md-4">
			<p class="form-control-plaintext">${!Product.discontinued?'Đang bán':'Ngừng kinh doanh'}</p>
		</div>
	</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Thời gian tạo</label>
		</div>
		<div class="col-md-4">
			<p class="form-control-plaintext">${Product.createAt}</p>
		</div>

		<div class="col-md-2">
			<label class="col-form-label">Lần sửa trước</label>
		</div>
		<div class="col-md-4">
			<p class="form-control-plaintext">${Product.latestUpdate}</p>
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
				<button type="submit" class="btn btn-danger mr-2" name="action"
					value="Delete">
					<i class="fas fa-pencil-alt"></i>
					Xóa sản phẩm này
				</button>
				
				<c:url var="myURL" value="/product.jsp">
					<c:if test="${param.page!=null}">
						<c:param name="page" value="${param.page}" />
					</c:if>
				</c:url>
				<a href="${myURL}" class="btn btn-success"> <i
					class="fas fa-arrow-left"></i> Quay Lại
				</a> 
			</div>
		</div>
	</div>
</form>
