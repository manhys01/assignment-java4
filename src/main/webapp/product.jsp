<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="manh-tags"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="_meta-css.jsp" />
<title>Sản phẩm</title>
</head>
<body>
	<jsp:include page="_menu.jsp" />
	<div class="container-fluid pt-5 pb-5 mainPage">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="_admin_sidebar.jsp" />
			</div>
			<div class="col-md-9">
				<c:choose>
					<c:when test="${param.action=='create'}">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item active" aria-current="page">Thêm
									sản phẩm</li>
							</ol>
						</nav>
						<div class="container">
							<jsp:include page="_form_create_product.jsp" />
						</div>
					</c:when>

					<c:when test="${param.action=='edit'}">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item active" aria-current="page">Sửa
									sản phẩm</li>
							</ol>
						</nav>
						<div class="container">
							<jsp:include page="_form_update_product.jsp" />
						</div>
					</c:when>

					<c:when test="${param.action=='delete'}">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item active" aria-current="page">Xóa
									sản phẩm</li>
							</ol>
						</nav>
						<div class="container">
							<jsp:include page="_form_delete_product.jsp" />
						</div>
					</c:when>

					<c:otherwise>
						<div class="container">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item active" aria-current="page">Danh
										sách sản phẩm</li>
								</ol>
							</nav>
						</div>
						<div class="container">
							<form class="form-inline mb-3">
								<c:url var="myURL" value="/product.jsp" >
									<c:param name="action" value="create"></c:param>
									<c:if test="${param.page!=null}">
										<c:param name="page" value="${param.page}" />
									</c:if>
								</c:url>
								<a href="${myURL}" class="btn btn-primary mr-3">
									<i class="fas fa-plus"></i>
									Thêm sản phẩm mới
								</a>
								<input type="text" name="find" class="form-control mr-2"
									placeholder="Tìm chủng loại, tên, nsx ...">
								<button name="action" value="search"
									class="btn btn-success">Tìm kiếm</button>
							</form>
						</div>
						<div class="container">
							<jsp:include page="_alert_message.jsp" />
							<jsp:include page="_product_table.jsp" />
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