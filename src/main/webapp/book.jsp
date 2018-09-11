<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="manh-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="_meta-css.jsp" />
<title>Đặt hàng</title>
</head>
<body>
	<m:setAttributeRecentPage
		currentPage="${pageContext.request.requestURI}" />
	<jsp:include page="_menu.jsp" />


	<div class="container-fluid bg-light mainPage pt-5 pb-5">
		<div class="row">
			<c:set var="cart" value="${Cart}" scope="session" />

			<div class="container">
				<c:choose>
					<c:when test="${sessionScope.Message.id==0}">
						<div class="alert alert-danger" role="alert">
							<h4 class="alert-heading">Đặt hàng thất bại!</h4>
							<p>Có lỗi xảy ra</p>
							<hr>
							<p class="mb-0">${sessionScope.Message.message}</p>
						</div>
						<c:remove var="Message" scope="session" />
					</c:when>
				</c:choose>
			</div>

			<div class="container">
				<h3>Chi tiết sản phẩm</h3>
				<table class="table table-bordered">
					<tr>
						<td>#</td>
						<td>Hình</td>
						<td>Mã SP</td>
						<td>Tên SP</td>
						<td>Số lượng</td>
						<td>Đơn giá</td>
						<td>Tổng</td>
					</tr>
					<c:set var="total" value="0" />
					<c:forEach var="cart" items="${sessionScope.Cart}"
						varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>
								<img alt="${cart.value.product.name}"
									src="${cart.value.product.image}" width="60px">
							</td>
							<td>${cart.value.product.id}</td>
							<td>${cart.value.product.name}</td>
							<td>${cart.value.quantity}</td>
							<td>
								<fmt:formatNumber type="number" maxFractionDigits="3"
									value="${cart.value.product.price}" />
							</td>
							<td>
								<c:set var="sum"
									value="${cart.value.product.price * cart.value.quantity}" />
								<fmt:formatNumber type="number" maxFractionDigits="3"
									value="${sum}" />
							</td>
							<c:set var="total" value="${total+sum}" />
						</tr>
					</c:forEach>
					<tr>
						<td colspan="3">
							<form action="showproduct.jsp">
								<button type="submit" name="action" value="continue"
									class="btn btn-block btn-success">Tiếp tục mua hàng</button>
							</form>
						</td>
						<td colspan="3" class="text-right">Thành tiền</td>
						<td>
							<fmt:formatNumber type="number" maxFractionDigits="3"
								value="${total}" />
						</td>
					</tr>
				</table>
			</div>


			<div class="container">
				<c:set var="user" value="${User}" scope="session" />
				<h3>Thông tin khách hàng</h3>

				<c:choose>

					<c:when test="${User!=null}">
						<div class="jumbotron">
							<form action="BookServlet" method="post">
								<div class="form-group">
									<div class="row">
										<label for="FullName"
											class="col-form-label col-xs-12 col-sm-4 col-md-3 col-lg-2">Tên
											khách hàng</label>
										<div class="col-xs-12 col-sm-8 col-md-6 col-lg-6">
											<input type="text" value="${user.fullname}"
												name="CustomerName" id="FullName" class="form-control">
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<label for="ShippingAddress"
											class="col-form-label col-xs-12 col-sm-4 col-md-3 col-lg-2">Địa
											chỉ nhận hàng</label>
										<div class="col-xs-12 col-sm-8 col-md-6 col-lg-6">
											<textarea rows="5" name="Shipping Address"
												id="ShippingAddress" class="form-control">${user.address}</textarea>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<label for="PhoneNumber"
											class="col-form-label col-xs-12 col-sm-4 col-md-3 col-lg-2">Điện
											thoại liên hệ</label>
										<div class="col-xs-12 col-sm-8 col-md-6 col-lg-6">
											<input type="text" value="${user.phone}" name="Phone Number"
												id="PhoneNumber" class="form-control">
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<label for="fullname"
											class="col-form-label col-xs-12 col-sm-4 col-md-3 col-lg-2"></label>
										<div class="col-xs-12 col-sm-8 col-md-6 col-lg-6">
											<input type="hidden" name="amount" value="${total}">
											<button name="action" value="Pay" class="btn btn-info">Đặt
												hàng</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</c:when>

					<c:otherwise>

						<div class="jumbotron">
							<form action="BookServlet" method="post">
								<div class="form-group">
									<div class="row">
										<label for="FullName"
											class="col-form-label col-xs-12 col-sm-4 col-md-3 col-lg-2">Tên
											khách hàng</label>
										<div class="col-xs-12 col-sm-8 col-md-6 col-lg-6">
											<input type="text" value="${sessionScope.customerName}"
												name="CustomerName" id="FullName" class="form-control">
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<label for="ShippingAddress"
											class="col-form-label col-xs-12 col-sm-4 col-md-3 col-lg-2">Địa
											chỉ nhận hàng</label>
										<div class="col-xs-12 col-sm-8 col-md-6 col-lg-6">
											<textarea rows="5" name="Shipping Address"
												id="ShippingAddress" class="form-control">${sessionScope.shippingAddress}</textarea>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<label for="PhoneNumber"
											class="col-form-label col-xs-12 col-sm-4 col-md-3 col-lg-2">Điện
											thoại liên hệ</label>
										<div class="col-xs-12 col-sm-8 col-md-6 col-lg-6">
											<input type="text" value="${sessionScope.phoneNumber}"
												name="Phone Number" id="PhoneNumber" class="form-control">
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<label for="fullname"
											class="col-form-label col-xs-12 col-sm-4 col-md-3 col-lg-2"></label>
										<div class="col-xs-12 col-sm-8 col-md-6 col-lg-6">
											<input type="hidden" name="amount" value="${total}">
											<button name="action" value="Pay" class="btn btn-info">Đặt
												hàng</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</c:otherwise>

				</c:choose>

			</div>
		</div>
	</div>

	<jsp:include page="_footer.jsp" />
	<jsp:include page="_js.jsp" />
</body>
</html>