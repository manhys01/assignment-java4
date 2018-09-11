<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="poly.manhnd.assignment.daos.entities.implement.OrderStateDAOImp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="manh-tags"%>

<m:getOrderDetail />
<form action="OrderManagerServlet" method="post">
	<div class="form-group row">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
			<label for="orderId">Mã đơn hàng</label>
		</div>
		<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
			<input name="orderId" readonly class="form-control-plaintext"
				type="text" value="${detail.order.id}" id="orderId">
		</div>
	</div>

	<div class="form-group row">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
			<label for="productId">Mã sản phẩm</label>
		</div>
		<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
			<input name="productId" readonly class="form-control-plaintext"
				type="text" value="${detail.product.id}" id="productId">
		</div>
	</div>

	<div class="form-group row">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
			<label for="name">Tên sản phẩm</label>
		</div>
		<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
			<input name="name" readonly class="form-control-plaintext"
				type="text" value="${detail.product.name}" id="name">
		</div>
	</div>

	<div class="form-group row">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
			<label for="quantity">Số lượng</label>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<c:choose>
				<c:when test="${detail.quantity > detail.product.inStock }">
					<input name="quantity" class="form-control" type="number"
						value="${detail.quantity}" id="quantity">
				</c:when>
				<c:when test="${detail.quantity < detail.product.inStock }">
					<input name="quantity" class="form-control" type="number"
						value="${detail.quantity}" id="quantity">
				</c:when>
				<c:otherwise>
					<input name="quantity" class="form-control" type="number"
						value="${detail.quantity}" id="quantity">
				</c:otherwise>
			</c:choose>

		</div>
	</div>

	<div class="form-group row">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
			<label for="inStock">Trong kho</label>
		</div>
		<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
			<input name="inStock" readonly class="form-control-plaintext"
				type="text" value="${detail.product.inStock}" id="inStock">
		</div>
	</div>

	<div class="form-group row">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
		</div>
		<div class="btn-group">
			<input type="hidden" name="orderDetailId" value="${detail.id}">
			<button type="submit" class="btn btn-danger mr-2" name="action"
				value="update order detail">
				<i class="fas fa-pencil-alt"></i>
				Sửa
			</button>
			<a href="order.jsp?action=update&orderId=${detail.order.id}"
				class="btn btn-success">
				<i class="fas fa-arrow-left"></i>
				Quay Lại
			</a>
		</div>
	</div>
</form>