<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="manh-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="_meta-css.jsp" />
<title>Hồ sơ</title>
</head>
<body>
	<!-- remove recent page -->
	<c:remove var="recentPage" scope="session" />
	<jsp:include page="_menu.jsp" />
	<div class="container-fluid bg-light mainPage pt-5 pb-5">
		<div class="row">
			<!-- slide bar -->
			<div class="col-md-3 mb-3">
				<ul class="list-group">
					<li
						class="list-group-item d-flex justify-content-between align-items-center">
						<a href="profile.jsp">Thông tin cá nhân</a>
					</li>
					<li
						class="list-group-item d-flex justify-content-between align-items-center">
						<a href="profile.jsp?change=password">Đổi mật khẩu</a>
					</li>
					<li
						class="list-group-item d-flex justify-content-between align-items-center">
						<a href="profile.jsp?view=order">Lịch sử đơn hàng</a> <m:getTotalOrderUser
							user="${sessionScope.User}" /> <c:if test="${totalOrder>0}">
							<span class="badge badge-pill badge-secondary">
								${totalOrder}</span>
						</c:if>
					</li>
				</ul>
			</div>

			<!-- content -->
			<div class="col-md-9">

				<c:choose>
					<c:when test="${param.view!=null}">
						<c:if test="${param.view=='order'}">
							<div class="container">
								<nav aria-label="breadcrumb">
									<ol class="breadcrumb">
										<li class="breadcrumb-item active" aria-current="page">Lịch
											sử mua hàng</li>
									</ol>
								</nav>
								<jsp:include page="_table_order_user.jsp" />
							</div>
						</c:if>
					</c:when>

					<c:when test="${param.edit!=null}">
						<c:choose>
							<c:when test="${param.edit=='fullname'}">
								<div class="container">
									<nav aria-label="breadcrumb">
										<ol class="breadcrumb">
											<li class="breadcrumb-item active" aria-current="page">Sửa
												họ và tên</li>
										</ol>
									</nav>
								</div>
								<div class="container">
									<jsp:include page="_alert_message.jsp"/>
								</div>
								<form action="UserServlet" method="post" class="container">
									<div class="form-group row">
										<div class="col-md-2">
											<label class="col-form-label">Họ và tên hiện tại</label>
										</div>

										<div class="col-md-5">
											<input type="text" readonly class="form-control-plaintext" value="${User.fullname}">
										</div>
									</div>
									<div class="form-group row">
										<div class="col-md-2">
											<label class="col-form-label">Họ và tên mới</label>
										</div>

										<div class="col-md-5">
											<input type="text" name="fullname" class="form-control">
										</div>
									</div>
									<div class="form-group row">
										<div class="col-md-2">
										</div>

										<div class="col-md-5">
											<button name="action" value="update fullname" class="btn btn-primary">Sửa</button>
										</div>
									</div>
								</form>
							</c:when>
							
							<c:when test="${param.edit=='phone'}">
								<div class="container">
									<nav aria-label="breadcrumb">
										<ol class="breadcrumb">
											<li class="breadcrumb-item active" aria-current="page">Sửa số điện thoại</li>
										</ol>
									</nav>
								</div>
								<div class="container">
									<jsp:include page="_alert_message.jsp"/>
								</div>
								<form action="UserServlet" method="post" class="container">
									<div class="form-group row">
										<div class="col-md-2">
											<label class="col-form-label">SĐT hiện tại</label>
										</div>

										<div class="col-md-5">
											<input type="text" readonly class="form-control-plaintext" value="${User.phone}">
										</div>
									</div>
									<div class="form-group row">
										<div class="col-md-2">
											<label class="col-form-label">SĐT mới</label>
										</div>

										<div class="col-md-5">
											<input type="text" name="phone" class="form-control">
										</div>
									</div>
									<div class="form-group row">
										<div class="col-md-2">
										</div>

										<div class="col-md-5">
											<button name="action" value="update phone" class="btn btn-primary">Sửa</button>
										</div>
									</div>
								</form>
							</c:when>
							
							<c:when test="${param.edit=='address'}">
								<div class="container">
									<nav aria-label="breadcrumb">
										<ol class="breadcrumb">
											<li class="breadcrumb-item active" aria-current="page">Sửa địa chỉ</li>
										</ol>
									</nav>
								</div>
								<div class="container">
									<jsp:include page="_alert_message.jsp"/>
								</div>
								<form action="UserServlet" method="post" class="container">
									<div class="form-group row">
										<div class="col-md-2">
											<label class="col-form-label">Địa chỉ hiện tại</label>
										</div>

										<div class="col-md-5">
											<p class="form-control-plaintext">${User.address}</p>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-md-2">
											<label class="col-form-label">Địa chỉ mới</label>
										</div>

										<div class="col-md-5">
											<textarea rows="5" name="address" class="form-control"></textarea>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-md-2">
										</div>

										<div class="col-md-5">
											<button name="action" value="update address" class="btn btn-primary">Sửa</button>
										</div>
									</div>
								</form>
							</c:when>
							
						</c:choose>
					</c:when>

					<c:when test="${param.change!=null}">
						<c:if test="${param.change=='password'}">
							<div class="container">
								<nav aria-label="breadcrumb">
									<ol class="breadcrumb">
										<li class="breadcrumb-item active" aria-current="page">Đổi
											mật khẩu</li>
									</ol>
								</nav>
								<jsp:include page="_alert_message.jsp" />
							</div>

							<form action="UserServlet" method="post" class="container">
								<div class="form-group row">
									<div class="col-md-2">
										<label class="col-form-label">Mật khẩu cũ</label>
									</div>

									<div class="col-md-5">
										<input type="password" name="password" class="form-control">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-md-2">
										<label class="col-form-label">Mật khẩu mới</label>
									</div>
									<div class="col-md-5">
										<input type="password" name="new_password"
											class="form-control">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-md-2">
										<label class="col-form-label">Xác nhận lại</label>
									</div>
									<div class="col-md-5">
										<input type="password" name="confirm_new_password"
											class="form-control">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-md-2"></div>
									<div class="col-md-5">
										<button type="submit" name="action" value="Change Password"
											class="btn btn-success">OK</button>
										<button type="reset" class="btn btn-secondary">Reset</button>
									</div>
								</div>
							</form>
						</c:if>
					</c:when>
					<c:otherwise>
						<div class="container">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item active" aria-current="page">Thông
										tin cá nhân</li>
								</ol>
							</nav>
						</div>
						<div class="container">
							<table class="table table-striped">
								<tr>
									<td>Họ và tên</td>
									<td>${User.fullname}</td>
									<td><a href="profile.jsp?edit=fullname"
										class="btn btn-primary">Sửa</a></td>
								</tr>
								<tr>
									<td>Số điện thoại</td>
									<td>${User.phone}</td>
									<td><a href="profile.jsp?edit=phone"
										class="btn btn-primary">Sửa</a></td>
								</tr>
								<tr>
									<td>Địa chỉ</td>
									<td>${User.address}</td>
									<td><a href="profile.jsp?edit=address"
										class="btn btn-primary">Sửa</a></td>
								</tr>
								<tr>
									<td>Email</td>
									<td>${User.email}</td>
									<td></td>
								</tr>
								<tr>
									<td>Ngày tham gia</td>
									<td><fmt:formatDate  value="${User.createAt}" pattern="dd/MM/YYYY hh:mm" /></td>
									<td></td>
								</tr>
								<c:if test="${User.admin}">
									<tr>
										<td>Quyền</td>
										<td>Administrator</td>
										<td></td>
									</tr>
								</c:if>
							</table>
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