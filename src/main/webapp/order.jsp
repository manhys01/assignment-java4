<%@page
	import="poly.manhnd.assignment.daos.entities.implement.OrderStateDAOImp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="manh-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="_meta-css.jsp" />
<title>Quản lý đơn hàng</title>
</head>
<body>
	<jsp:include page="_menu.jsp" />
	<div class="container-fluid bg-light mainPage pt-5 pb-5">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="_admin_sidebar.jsp" />
			</div>
			<div class="col-md-9">

				<c:choose>
					<c:when test="${param.action=='view'}">
						<div class="container">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item active" aria-current="page">Chi
										tiết đơn hàng</li>
								</ol>
							</nav>
						</div>
						<div class="container">
							<jsp:include page="_form_view_order.jsp" />
						</div>
					</c:when>

					<c:when test="${param.action=='update'}">
						<div class="container">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item active" aria-current="page">Chi
										tiết đơn hàng</li>
								</ol>
							</nav>
						</div>

						<div class="container"><jsp:include
								page="_alert_message.jsp" /></div>

						<div class="container">
							<jsp:include page="_form_update_order.jsp" />
						</div>

					</c:when>

					<c:when test="${param.action=='updateOrderDetail'}">

						<div class="container">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item active" aria-current="page">Chi
										tiết sản phẩm đơn hàng</li>
								</ol>
							</nav>
						</div>

						<div class="container"><jsp:include
								page="_alert_message.jsp" /></div>

						<div class="container">
							<jsp:include page="_form_update_orderdetail.jsp" />
						</div>
					</c:when>

					<c:when test="${param.action=='removeOrderDetail'}">

						<div class="container">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item active" aria-current="page">Chi
										tiết sản phẩm đơn hàng</li>
								</ol>
							</nav>
						</div>

						<div class="container"><jsp:include
								page="_alert_message.jsp" /></div>

						<div class="container">
							<jsp:include page="_form_delete_orderdetail.jsp" />
						</div>
					</c:when>

					<c:otherwise>
						<div class="container">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item active" aria-current="page">Bảng
										danh sách các đơn đặt hàng</li>
								</ol>
							</nav>
						</div>

						<div class="container"><jsp:include
								page="_alert_message.jsp" /></div>

						<div class="container">
							<jsp:include page="_table_order.jsp" />
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