<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="manh-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="_meta-css.jsp" />
<title>Giỏ hàng</title>
</head>
<body>
	<m:setAttributeRecentPage currentPage="${pageContext.request.requestURI}" />
	<jsp:include page="_menu.jsp" />
	<div class="container-fluid bg-light mainPage pt-5 pb-5">
		<c:choose>
			<c:when test="${sessionScope.Cart!=null}">
				<m:cart>
					<tr>
						<td>${count}</td>
						<td>
							<img alt="${productName}" src="${productImage}" width="60px">
						</td>
						<td>${productID}</td>
						<td>${productName}</td>
						<td>
							<form action="CartServlet" method="post">
								<input type="hidden" name="id" value="${productID}">
								<div class="input-group mb-3">
									<input type="number" min=1 name="quantity" value="${quantity}" class="form-control">
									<div class="input-group-append">
										<button type="submit" name="action" value="Update Product" class="btn btn-warning">Sửa</button>
									</div>
								</div>
							</form>
						</td>
						<td>
							<fmt:formatNumber type="number" maxFractionDigits="3" value="${price}" />
						</td>
						<td>
							<fmt:formatNumber type="number" maxFractionDigits="3" value="${amount}" />
						</td>
						<td>
							<form action="CartServlet" method="post">
								<input type="hidden" name="id" value="${productID}">
								<button type="submit" name="action" value="Remove Product" class="btn btn-danger btn-block">Xóa</button>
							</form>
						</td>
					</tr>
				</m:cart>
			</c:when>
			<c:otherwise>
				<div class="alert alert-info" role="alert">
					Giỏ hàng trống! <br> <a href="showproduct.jsp" class="alert-link">Quay lại</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<jsp:include page="_footer.jsp" />
	<jsp:include page="_js.jsp" />
</body>
</html>