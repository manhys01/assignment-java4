<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="manh-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="_meta-css.jsp" />
<title>Đăng nhập</title>
</head>
<body>
	<div class="container-fluid">
		<c:set var="username" value="" />
		<c:if test="${sessionScope.username!=null}">
			<c:set var="username" value="${sessionScope.username}" />
		</c:if>
		<c:set var="password" value="" />

		<c:forEach var="c" items="${pageContext.request.cookies}">
			<c:if test="${c.name=='username'}">
				<c:set var="username" value="${c.value}" />
			</c:if>
			<c:if test="${c.name=='password'}">
				<c:set var="password" value="${c.value}" />
			</c:if>
		</c:forEach>
		
		<form action="LoginServlet" method="post">
			<div class="row" style="margin-top: 100px;">
				<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3"
					style="margin: 0 auto;">
					<jsp:include page="_alert_message.jsp" />
					<div class="jumbotron">
						<div class="form-group">
							<h3 class="text-center">Đăng nhập</h3>
						</div>
						<div class="form-group">
							<input type="text" name="username" class="form-control"
								placeholder="Enter your username"
								value="${username}">
						</div>
						<div class="form-group">
							<input type="password" name="password" class="form-control"
								placeholder="Enter your password" value="${password}">
						</div>
						<div class="checkbox">
							<label>
								<input name="remember" type="checkbox" ${not empty password?'checked="checked"':''}>
								Ghi nhớ?
							</label>
						</div>
						<div class="form-group">
							<button name="action" value="Login"
								class="btn btn-primary btn-block">Đăng nhập</button>
						</div>
						<div class="form-group">
							<a href="register.jsp">Đăng ký tài khoản</a>
						</div>
						<div class="form-group">
							<a href="">Quên mật khẩu</a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<c:remove var="Message" scope="session" />
	<c:remove var="username" scope="session" />
	<jsp:include page="_js.jsp" />
</body>
</html>