<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="_meta-css.jsp" />
<title>Kết quả</title>
</head>
<body>
	<jsp:include page="_menu.jsp" />
	<div class="container-fluid pt-5 pb-5">
		<div class="container">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">
						<a href="#">Kết quả</a>
					</li>
				</ol>
			</nav>
			<c:choose>
				<c:when test="${sessionScope.Message.id==1}">
					<div class="alert alert-success" role="alert">
						<h4 class="alert-heading">Đặt hàng thành công!</h4>
						<p>Chúng tôi sẽ liên hệ với bạn để xác thực đơn hàng trong thời gian sớm nhất.</p>
						<hr>
						<p class="mb-0">Vui lòng chú ý điện thoại của bạn!</p>
					</div>
					
					<c:remove var="Cart" scope="session"/>
					<c:remove var="Message" scope="session"/>
					<c:remove var="totalProducts" scope="session"/>
				</c:when>
				
				<c:otherwise>
					<div class="alert alert-danger" role="alert">
						<h4 class="alert-heading">Đặt hàng thất bại!</h4>
						<p>Có lỗi xảy ra</p>
						<hr>
						<p class="mb-0">${sessionScope.Message.message}</p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<jsp:include page="_footer.jsp" />
	<jsp:include page="_js.jsp" />
</body>
</html>