<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="manh-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="_meta-css.jsp" />
<title>Sản phẩm</title>
</head>
<body>
	<m:setAttributeRecentPage
		currentPage="${pageContext.request.requestURI}" />
	<jsp:include page="_menu.jsp" />
	<div class="container-fluid bg-light pt-5 pb-5 mainPage">
		<div class=row>
			<div class="col-md-3">
				<jsp:include page="_sidebar.jsp" />
			</div>

			<div class="col-md-9">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item">Danh sách sản phẩm</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
				<c:choose>

					<c:when test="${param.category!=null}">
						<jsp:include page="_show_product_by_category.jsp" />
					</c:when>

					<c:when test="${param.manufacturer!=null}">
						<jsp:include page="_show_product_by_manufacturer.jsp" />
					</c:when>

					<c:otherwise>
						<jsp:include page="_show_product.jsp" />
					</c:otherwise>
					
				</c:choose>
			</div>
		</div>
	</div>
	<jsp:include page="_shoppingcart.jsp" />
	<jsp:include page="_footer.jsp" />
	<jsp:include page="_js.jsp" />
</body>
</html>