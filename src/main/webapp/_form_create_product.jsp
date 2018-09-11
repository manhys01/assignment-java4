<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="poly.manhnd.assignment.daos.entities.implement.ManufacturerDAOImp"%>
<%@page
	import="poly.manhnd.assignment.daos.entities.implement.CategoryDAOImp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="manh-tags"%>

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
						<option value="${rows.id}">${rows.name}</option>
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
						<option value="${rows.id}">${rows.name}</option>
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
			<input type="text" name="ProductName" class="form-control">
		</div>
	</div>
	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Đơn giá</label>
		</div>
		<div class="col-md-4">
			<input type="number" name="ProductPrice" class="form-control">
		</div>
	</div>
	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Trong kho</label>
		</div>
		<div class="col-md-2">
			<input type="number" name="ProductInStock" class="form-control">
		</div>
	</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Mô tả sản phẩm</label>
		</div>
		<div class="col-md-10">
			<textarea name="description" rows="5" class="form-control"></textarea>
		</div>
	</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label class="col-form-label">Hình ảnh</label>
		</div>
		<div class="col-md-10">
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
				<option value="false" selected="selected">Mở bán</option>
				<option value="true">Ngừng kinh doanh</option>
			</select>
		</div>
	</div>

	<div class="form-group row">
		<div class="col-md-2"></div>
		<div class="col-md-3">
			<div class="btn-group">
				<c:if test="${param.page!=null}">
					<input type="hidden" name="page" value="${param.page}">
				</c:if>
				<button type="submit" class="btn btn-primary mr-2" name="action"
					value="Create">
					<i class="fas fa-plus"></i>
					Thêm mới
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
