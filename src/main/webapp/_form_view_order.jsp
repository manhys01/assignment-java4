<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="poly.manhnd.assignment.daos.entities.implement.OrderStateDAOImp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="manh-tags"%>

<m:getListOrderDetailByOrderID orderId="${param.orderId}" />
<form action="OrderManagerServlet" method="post">
	<div class="form-group row">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
			<label for="customerName">Tên khách hàng</label>
		</div>
		<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
			<input name="customerName" class="form-control-plaintext" readonly
				type="text" value="${Order.customerName}" id="customerName">
		</div>
	</div>

	<div class="form-group row">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
			<label for="phoneNumber">Số điện thoại</label>
		</div>
		<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
			<input name="phoneNumber" class="form-control-plaintext" readonly
				type="text" value="${Order.phoneNumber}" id="phoneNumber">
		</div>
	</div>

	<div class="form-group row">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
			<label for="shippingAddress">Địa chỉ giao hàng</label>
		</div>
		<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
			<p class="form-control-plaintext" id="shippingAddress">${Order.shippingAddress}</p>
		</div>
	</div>


	<div class="form-group row">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
			<label for="shippingAddress">Các sản phẩm</label>
		</div>
		<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
			<c:choose>
				<c:when test="${sizeOfOrderDetail>0}">
					<div class="table-responsive">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>#</th>
									<th>Tên sản phẩm</th>
									<th>Số lượng</th>
									<th>Trong kho</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="detail" items="${OrderDetails}"
									varStatus="status">
									<c:choose>
										<c:when test="${detail.quantity > detail.product.inStock }">
											<tr>
												<td>${status.count}</td>
												<td>${detail.product.name}</td>
												<td>${detail.quantity}
													<i class="fas fa-times-circle text-danger"></i>
												</td>
												<td>${detail.product.inStock}</td>
											</tr>
										</c:when>
										<c:when test="${detail.quantity < detail.product.inStock }">
											<tr>
												<td>${status.count}</td>
												<td>${detail.product.name}</td>
												<td>${detail.quantity}
													<i class="fas fa-check-circle text-success"></i>
												</td>
												<td>${detail.product.inStock}</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr>
												<td>${status.count}</td>
												<td>${detail.product.name}</td>
												<td>${detail.quantity}
													<i class="fas fa-exclamation-triangle text-warning"></i>
												</td>
												<td>${detail.product.inStock}</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>

				<c:otherwise>
					<p class="form-control-plaintext text-danger">Không có sản phẩm
						nào trong Order! Bạn nên xóa nó đi...</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="form-group row">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
			<label for="orderState">Trạng thái</label>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="form-control-plaintext">${Order.orderState.name}</p>
		</div>
	</div>

	<div class="form-group row">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
			<label for="createAt">Thời gian đặt hàng</label>
		</div>
		<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
			<p class="form-control-plaintext">
				<fmt:formatDate pattern="dd/MM/YYYY hh:mm:ss"
					value="${Order.createAt}" />
			</p>
		</div>
	</div>

	<c:if test="${Order.fixer!=null}">
		<div class="form-group row">
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
				<label for="latestUpdate">Thời gian sửa gần nhất</label>
			</div>

			<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
				<p class="form-control-plaintext">
					<fmt:formatDate pattern="dd/MM/YYYY hh:mm:ss"
						value="${Order.latestUpdate}" />
				</p>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label">
				<label for="fixer">Người sửa</label>
			</div>
			<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
				<p class="form-control-plaintext">${Order.fixer}</p>
			</div>
		</div>
	</c:if>

	<div class="form-group row">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-form-label"></div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
			<div class="btn-group">
				<a href="order.jsp?action=update&orderId=${Order.id }"
					class="btn btn-danger mr-1">
					<i class="fas fa-edit"></i>
					Sửa
				</a>
				<c:url var="myURL" value="/order.jsp">
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