<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="_meta-css.jsp"/>
<title>Trang quản trị</title>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.User!=null}">
			<c:choose>
				<c:when test="${!sessionScope.User.admin}">
					<div class="alert alert-warning" role="alert">
						Bạn không có quyền truy cập vào trang này! <br> 
						<a href="login.jsp" class="alert-link">Click vào đây</a> để tới trang chủ
					</div>
				</c:when>
				<c:otherwise>
					<h3>Admin, ${sessionScope.User.username}</h3>
					<form action="LoginServlet" method="post">
						<button class="btn btn-success" name="action" value="Logout">Đăng xuất</button>
					</form>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<div class="alert alert-info" role="alert">
				Bạn cần phải <a href="login.jsp" class="alert-link">đăng nhập</a> trước để tiếp tục truy cập
				trang...
			</div>
		</c:otherwise>
	</c:choose>
	<jsp:include page="_js.jsp"/>
</body>
</html>