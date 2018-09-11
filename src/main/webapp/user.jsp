<%@page
	import="poly.manhnd.assignment.daos.entities.implement.UserDAOImp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="manh-tags"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="_meta-css.jsp" />
<title>Quản lý tài khoản</title>
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
					<c:when test="${param.action eq 'update'}">
						<div class="container">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item active" aria-current="page">Thay
										đổi trạng thái tài khoản</li>
								</ol>
							</nav>
						</div>
						<div class="container">
							<m:getUser />
							<form action="UserManagerServlet" method="post">
								<div class="form-group row">
									<div class="col-sm-2">
										<label class="col-form-label">Mã tài khoản</label>
									</div>
									<div class="col-sm-10">
										<p class="form-control-plaintext">${u.id}</p>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-sm-2">
										<label class="col-form-label">Họ và tên</label>
									</div>
									<div class="col-sm-10">
										<p class="form-control-plaintext">${u.fullname}</p>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-sm-2">
										<label class="col-form-label">Tài khoản</label>
									</div>
									<div class="col-sm-10">
										<p class="form-control-plaintext">${u.username}</p>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-sm-2">
										<label class="col-form-label">Mật khẩu</label>
									</div>
									<div class="col-sm-10">
										<p class="form-control-plaintext">${u.password}</p>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-sm-2">
										<label class="col-form-label">Email</label>
									</div>
									<div class="col-sm-10">
										<p class="form-control-plaintext">${u.email}</p>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-sm-2">
										<label class="col-form-label">Điện thoại</label>
									</div>
									<div class="col-sm-10">
										<p class="form-control-plaintext">${u.phone}</p>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-sm-2">
										<label class="col-form-label">Địa chỉ</label>
									</div>
									<div class="col-sm-10">
										<p class="form-control-plaintext">${u.address}</p>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-sm-2">
										<label class="col-form-label">Quyền</label>
									</div>
									<div class="col-sm-3">
										<%-- <select name="admin" class="form-control">
											<option value="false" ${u.admin?'':'selected="selected"'}>User</option>
											<option value="true" ${u.admin?'selected="selected"':''}>Administrator</option>
										</select> --%>
										<p class="form-control-plaintext">${u.admin?'Admistrator':'User' }</p>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-sm-2">
										<label class="col-form-label">Trạng thái</label>
									</div>
									<div class="col-sm-3">
										<select name="blocked" class="form-control">
											<option value="true" ${u.blocked?'selected="selected"':''}>Khóa</option>
											<option value="false" ${u.blocked?'':'selected="selected"'}>Hoạt
												động</option>
										</select>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-sm-2"></div>
									<div class="col-sm-3">
										<div class="btn-group">
											<input type="hidden" name="userId" value="${u.id}">
											<c:if test="${param.page!=null}">
												<input type="hidden" name="page" value="${param.page}">
											</c:if>
											<button type="submit" class="btn btn-primary mr-2"
												name="action" value="update user">
												<i class="fas fa-pencil-alt"></i> Sửa
											</button>
											<c:url var="myURL" value="/user.jsp">
												<c:if test="${param.page!=null}">
													<c:param name="page" value="${param.page}" />
												</c:if>
											</c:url>
											<a href="${myURL}" class="btn btn-success"> <i
												class="fas fa-arrow-left"></i> Quay Lại
											</a>
										</div>
									</div>
								</div>

							</form>
						</div>
					</c:when>
					<c:otherwise>

						<div class="container">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item active" aria-current="page">Danh
										sách tài khoản</li>
								</ol>
							</nav>
						</div>
						<div class="container">
							<jsp:include page="_alert_message.jsp" />
						</div>
						<div class="container">
							<m:getListUser maxResult="5" />
							<c:if test="${users!=null}">
								<form class="form-inline mb-3">
									<input type="text" name="search" class="form-control mr-1"
										placeholder="Tìm user">
									<button class="btn btn-success">Tìm kiếm</button>
								</form>
								<c:choose>
									<c:when test="${empty users }">
										<h3>Không tìm thấy user có từ khóa trong tìm kiếm là
											${param.search}</h3>
									</c:when>
									<c:otherwise>
										<div class="table-responsive">
											<table class="table table-striped table-bordered">
												<thead>
													<tr>
														<th>ID</th>
														<th>Tài khoản</th>
														<th>Mật khẩu</th>
														<th>Họ và tên</th>
														<th>Email</th>
														<th>Quyền</th>
														<th>Điện thoại</th>
														<th>Địa chỉ</th>
														<th>Trạng thái</th>
														<th>Lựa chọn</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="rows" items="${users}">
														<tr>
															<td nowrap="nowrap">${rows.id}</td>
															<td nowrap="nowrap">${rows.username}</td>
															<td nowrap="nowrap">${rows.password}</td>
															<td nowrap="nowrap">${rows.fullname}</td>
															<td nowrap="nowrap">${rows.email}</td>
															<td nowrap="nowrap">${rows.admin?'admin':'user'}</td>
															<td nowrap="nowrap">${rows.phone}</td>
															<td nowrap="nowrap">${rows.address}</td>
															<td nowrap="nowrap">${!rows.blocked?'Đang hoạt động':'Khóa'}</td>
															<td><c:if test="${!rows.admin}">
																	<c:url var="myURL" value="/user.jsp">
																		<c:param name="userId" value="${rows.id}" />
																		<c:if test="${param.page!=null}">
																			<c:param name="page" value="${param.page}" />
																		</c:if>
																	</c:url>
																	<a href="${myURL}&action=update"
																		class="btn btn-primary" type="submit"> <i
																		class="fas fa-pencil-alt"></i>
																	</a>
																</c:if></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</c:otherwise>
								</c:choose>
							</c:if>
						</div>
						<div class="container mt-3">
							<m:paginate max="5" uri="${pageContext.request.requestURI}"
								page="${param.page}" totalRecords="${totalRecords}" />
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