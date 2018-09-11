<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="manh-tags" prefix="m"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="_meta-css.jsp" />
<title>Trang chủ</title>
</head>
<body>
	<m:setAttributeRecentPage
		currentPage="${pageContext.request.requestURI}" />
	<jsp:include page="_menu.jsp" />
	<div class="container-fluid bg-light mainPage pt-5 pb-5">
		<div class="row">

			<!-- slide bar -->
			<div class="col-md-3">
				<jsp:include page="_sidebar.jsp" />
			</div>

			<!-- content -->
			<div class="col-md-9">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">Sản phẩm trưng bày</li>
					</ol>
				</nav>
				<div class="container">
					<div class="row">
						<div id="carouselExampleIndicators"
							class="carousel slide slideshow" data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carouselExampleIndicators" data-slide-to="0"
									class="active"></li>
								<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
							</ol>
							<div class="carousel-inner">
								<div class="carousel-item active">
									<img class="d-block w-100" src="images/surface-book.png"
										alt="First slide">
									<div class="carousel-caption d-none d-md-block">
										<h5>Surface Book 2</h5>
									</div>
								</div>
								<div class="carousel-item">
									<img class="d-block w-100" src="images/surface-book.png"
										alt="Second slide">
									<div class="carousel-caption d-none d-md-block">
										<h5>Surface Book 2</h5>
									</div>
								</div>
							</div>
							<a class="carousel-control-prev"
								href="#carouselExampleIndicators" role="button"
								data-slide="prev">
								<span class="carousel-control-prev-icon" aria-hidden="true"></span>
								<span class="sr-only">Previous</span>
							</a>
							<a class="carousel-control-next"
								href="#carouselExampleIndicators" role="button"
								data-slide="next">
								<span class="carousel-control-next-icon" aria-hidden="true"></span>
								<span class="sr-only">Next</span>
							</a>
						</div>
					</div>
				</div>

				<nav aria-label="breadcrumb" class="mt-3">
					<ol class="breadcrumb ">
						<li class="breadcrumb-item">Ngẫu nhiên</li>
					</ol>
				</nav>

				<div class="container">
					<div class="row">
						<m:getRamdomProducts limit="3" />
						<div class="card-deck mb-3">
							<c:forEach var="rows" items="${randomProducts}">
								<div class="card">
									<div class="card-header">
										<img class="card-img-top" src="${rows.image}"
											alt="Card image cap">
									</div>
									<div class="card-body">
										<h5 class="card-title">${rows.name}</h5>
										<p class="card-text text-right">
											<small class="badge badge-danger badge-pill">
												<fmt:formatNumber type="number">${rows.price}</fmt:formatNumber>
												<sup>đ</sup>
											</small>
										</p>
										<p class="card-text text-right">
											<small class="text-success">Còn hàng</small>
										</p>
									</div>
									<div class="card-footer">
										<form action="CartServlet" method="post">
											<input type="hidden" name="id" value="${rows.id}">
											<button class="card-link btn btn-primary btn-block"
												name="action" value="Add To Cart">
												<i class="fas fa-cart-plus"></i>
												Thêm vào giỏ
											</button>
										</form>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="_footer.jsp" />
	<jsp:include page="_js.jsp" />
</body>
</html>