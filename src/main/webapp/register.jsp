<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" style="height: 100%">
<head>
<jsp:include page="_meta-css.jsp" />
<title>Đăng ký tài khoản</title>
</head>
<body class="body-register-page jumbotron">
	<div class="container-fluid">
		<form class="row" method="post" action="RegisterServlet">
			<div class="col-md-6" style="margin: 0 auto;">
				<jsp:include page="_alert_message.jsp" />
				<div class="form-group row">
					<div class="col text-center">
						<h3 style="padding-bottom: 20px;">Đăng ký tài khoản</h3>
					</div>
				</div>

				<div class="form-group row">
					<div class="col-xs-12 col-sm-6 col-md-5 col-lg-4">
						<label for="fullname" class="col-form-label">Họ và tên (<span class="text-danger">*</span>)
						</label>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-7 col-lg-8">
						<input type="text" name="fullname" id="fullname" class="form-control"
							value="${sessionScope.fullname}">
						<span class="text-danger">${sessionScope.error_fullname }</span>
						<c:remove var="error_fullname" scope="session" />
					</div>
				</div>

				<div class="form-group row">
					<div class="col-xs-12 col-sm-6 col-md-5 col-lg-4">
						<label for="username" class="col-form-label">Tài khoản (<span class="text-danger">*</span>)
						</label>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-7 col-lg-8">
						<input type="text" name="username" id="username" class="form-control"
							value="${sessionScope.username}">
						<span class="text-danger">${sessionScope.error_username}</span>
						<c:remove var="error_username" scope="session" />
					</div>
				</div>

				<div class="form-group row">
					<div class="col-xs-12 col-sm-6 col-md-5 col-lg-4">
						<label for="password" class="col-form-label">Mật khẩu (<span class="text-danger">*</span>)
						</label>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-7 col-lg-8">
						<input type="password" name="password" id="password" class="form-control">
						<span class="text-danger">${sessionScope.error_password }</span>
						<c:remove var="error_password" scope="session" />
					</div>
				</div>

				<div class="form-group row">
					<div class="col-xs-12 col-sm-6 col-md-5 col-lg-4">
						<label for="re-password" class="col-form-label">Xác nhận mật khẩu (<span
								class="text-danger">*</span>)
						</label>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-7 col-lg-8">
						<input type="password" name="re-password" id="re-password" class="form-control">
						<span class="text-danger">${sessionScope.error_re_password }</span>
						<c:remove var="error_re_password" scope="session" />
					</div>
				</div>

				<div class="form-group row">
					<div class="col-xs-12 col-sm-6 col-md-5 col-lg-4">
						<label for="email" class="col-form-label">Email (<span class="text-danger">*</span>)
						</label>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-7 col-lg-8">
						<input type="text" name="email" id="email" class="form-control" value="${sessionScope.email}">
						<span class="text-danger">${sessionScope.error_email }</span>
						<c:remove var="error_email" scope="session" />
					</div>
				</div>

				<div class="form-group row">
					<div class="col-xs-12 col-sm-6 col-md-5 col-lg-4">
						<label for="phone" class="col-form-label">Điện thoại (<span class="text-danger">*</span>)
						</label>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-7 col-lg-8">
						<input type="text" name="phone" id="phone" class="form-control" value="${sessionScope.phone}">
						<span class="text-danger">${sessionScope.error_phone }</span>
						<c:remove var="error_phone" scope="session" />
					</div>
				</div>

				<div class="form-group row">
					<div class="col-xs-12 col-sm-6 col-md-5 col-lg-4">
						<label for="address" class="col-form-label">Địa chỉ (<span class="text-danger">*</span>)
						</label>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-7 col-lg-8">
						<input type="text" name="address" id="address" class="form-control"
							value="${sessionScope.address}">
						<span class="text-danger">${sessionScope.error_address }</span>
						<c:remove var="error_address" scope="session" />
					</div>
				</div>

				<div class="form-group row">
					<div class="col-xs-12 col-sm-6 col-md-5 col-lg-4"></div>
					<div class="col-xs-12 col-sm-6 col-md-7 col-lg-8">
						<div class="form-inline">
							<button type="submit" class="btn btn-primary mr-2">Đăng ký</button>
							<button type="reset" class="btn btn-secondary">Nhập lại</button>
						</div>
					</div>
				</div>

			</div>
		</form>
	</div>
	<jsp:include page="_js.jsp" />
</body>
</html>