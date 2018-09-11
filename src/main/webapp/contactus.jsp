<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="manh-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="_meta-css.jsp" />
<title>Liên hệ</title>
</head>
<body>
	<m:setAttributeRecentPage
		currentPage="${pageContext.request.requestURI}" />
	<jsp:include page="_menu.jsp" />
	<div class="container-fluid bg-light mainPage pt-5 pb-5">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="_sidebar.jsp" />
			</div>
			<div class="col-md-9">
				<div class="container">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">Liên hệ</li>
						</ol>
					</nav>
				</div>
				<div class="container">
					<form>
						<div class="form-group row">
							<div class="col-sm-6">
								<label class="col-form-label">Họ và tên (<span class="text-danger">*</span>)</label>
								<input type="text" name="fullname" class="form-control">
							</div>
						</div>

						<div class="form-group row">
							<div class="col-sm-6">
								<label class="col-form-label">Email (<span class="text-danger">*</span>)</label>
								<input type="text" name="email" class="form-control">
							</div>
						</div>
						
						<div class="form-group row">
							<div class="col-sm-6">
								<label class="col-form-label">Thông điệp (<span class="text-danger">*</span>)</label>
								<textarea rows="5" class="form-control" name="message"></textarea>
							</div>
						</div>
						
						<div class="form-group row">
							<div class="col-sm-2">
								<button type="button" name="action" value="send" class="btn btn-block btn-primary">Gửi</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="_footer.jsp" />
	<jsp:include page="_js.jsp" />
</body>
</html>