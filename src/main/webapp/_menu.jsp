<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="manh-tags"%>
<!-- Menu top -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="index.jsp">MANHND</a>

	<!-- Button navbar-toggler -->
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target=".navbarTop" aria-controls="navbarTop"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<!-- navbar-collapse menu -->
	<div class="collapse navbar-collapse navbarTop">
		<ul class="navbar-nav mr-auto">
			<li
				class="nav-item <%=request.getServletPath().contains("index.jsp") ? "active" : ""%>">
				<a class="nav-link" href="index.jsp">
					Trang chủ
					<%=request.getServletPath().contains("index.jsp") ? "<span class='sr-only'>(current)</span>" : ""%>
				</a>
			</li>
			<li
				class="nav-item <%=request.getServletPath().contains("about.jsp") ? "active" : ""%>">
				<a class="nav-link" href="about.jsp">
					Giới thiệu
					<%=request.getServletPath().contains("about.jsp") ? "<span class='sr-only'>(current)</span>" : ""%></a>
			</li>
			<li
				class="nav-item <%=request.getServletPath().contains("showproduct.jsp") ? "active" : ""%>">
				<a class="nav-link" href="showproduct.jsp">
					Sản phẩm
					<%=request.getServletPath().contains("showproduct.jsp") ? "<span class='sr-only'>(current)</span>"
					: ""%>
				</a>
			</li>
			<li
				class="nav-item <%=request.getServletPath().contains("contactus.jsp") ? "active" : ""%>">
				<a class="nav-link" href="contactus.jsp">
					Liên hệ
					<%=request.getServletPath().contains("contactus.jsp") ? "<span class='sr-only'>(current)</span>" : ""%>
				</a>
			</li>
			<c:if test="${sessionScope.User.admin}">
				<li
					class="nav-item <%=request.getServletPath().contains("order.jsp") ? "active" : ""%>">
					<a class="nav-link" href="order.jsp">
						Quản lý đơn hàng
						<%=request.getServletPath().contains("order.jsp") ? "<span class='sr-only'>(current)</span>" : ""%>
						<m:getNumberOfWaitingOrder />
						<c:if test="${totalNumberOfWaitingOrder>0}">
							<span class="badge badge-danger">${totalNumberOfWaitingOrder}</span>
						</c:if>
					</a>
				</li>
				<li
					class="nav-item <%=request.getServletPath().equalsIgnoreCase("/product.jsp") ? "active" : ""%>">
					<a class="nav-link" href="product.jsp">
						Quản lý sản phẩm
						<%=request.getServletPath().equalsIgnoreCase("/product.jsp") ? "<span class='sr-only'>(current)</span>" : ""%>
					</a>
				</li>
			</c:if>
		</ul>
	</div>

	<div class="collapse navbar-collapse navbarTop">
		<form class="form-inline" action="showproduct.jsp">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Tìm kiếm sản phẩm" name="search" aria-label="Search">
			<button class="btn btn-success my-2 my-sm-0" type="submit">
				<i class="fas fa-search"></i>
				Tìm
			</button>
		</form>
	</div>

	<!-- Button login logout logic -->
	<div class="collapse show form-inline navbarTop">
		<c:choose>
			<c:when test="${sessionScope.User!=null}">
				<form action="LogoutServlet" method="post">
					<div class="btn-group">
						<button type="button" class="btn btn-info dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<i class="fas fa-user text-light"></i>
							${sessionScope.User.username}
						</button>
						<div class="dropdown-menu dropdown-menu-right">
							<a href="profile.jsp" class="dropdown-item">
								<i class="fas fa-eye text-primary"></i>
								Hồ sơ
							</a>
							<div class="dropdown-divider"></div>
							<button class="dropdown-item" type="submit">
								<i class="fas fa-sign-out-alt text-danger"></i>
								Đăng xuất
							</button>
						</div>
					</div>
				</form>
			</c:when>
			<c:otherwise>
				<form>
					<a class="btn btn-success" href="register.jsp">Đăng ký</a>
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#loginModal">Đăng nhập</button>
				</form>
			</c:otherwise>
		</c:choose>
	</div>

	<!-- Modal -->
	<c:set var="username" value="" />
	<c:set var="password" value="" />

	<c:forEach var="c" items="${pageContext.request.cookies}">
		<c:if test="${c.name=='username'}">
			<c:set var="username" value="${c.value}" />
		</c:if>
		<c:if test="${c.name=='password'}">
			<c:set var="password" value="${c.value}" />
		</c:if>
	</c:forEach>
	
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="#loginModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<form action="LoginServlet" method="post" class="btn-block">
					<div class="modal-header">
						<h5 class="modal-title" id="loginModalLabel">Đăng nhập</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<input type="text" name="username" class="form-control"
								placeholder="Nhập vào tài khoản" value="${username}">
						</div>
						<div class="form-group">
							<input type="password" name="password" class="form-control"
								placeholder="Nhập vào mật khẩu" value="${password}">
						</div>
						<div class="checkbox">
							<label>
								<input name="remember" type="checkbox"
									${not empty password?'checked="checked"':''}>
								Ghi nhớ?
							</label>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary btn-block">Đăng
							nhập</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</nav>
