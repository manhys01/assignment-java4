<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="manh-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="_meta-css.jsp" />
<title>Giới thiệu trang web</title>
</head>
<body>
	<m:setAttributeRecentPage
		currentPage="${pageContext.request.requestURI}" />
	<jsp:include page="_menu.jsp" />
	<div class="container-fluid bg-light mainPage pt-5 pb-5">
		<div class=row>
			<div class="col-md-3">
				<jsp:include page="_sidebar.jsp" />
			</div>

			<div class="col-md-9">
				<div class="container">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">Giới thiệu</li>
						</ol>
					</nav>

					<div id="accordion">
						<div class="card">
							<div class="card-header" id="headingOne">
								<h5 class="mb-0">
									<button class="btn btn-link" data-toggle="collapse"
										data-target="#collapseOne" aria-expanded="true"
										aria-controls="collapseOne">Viết và thiết kế</button>
								</h5>
							</div>

							<div id="collapseOne" class="collapse show"
								aria-labelledby="headingOne" data-parent="#accordion">
								<div class="card-body">Nguyễn Đức Mạnh</div>
							</div>
						</div>
						<div class="card">
							<div class="card-header" id="headingTwo">
								<h5 class="mb-0">
									<button class="btn btn-link collapsed" data-toggle="collapse"
										data-target="#collapseTwo" aria-expanded="false"
										aria-controls="collapseTwo">Ngôn ngữ lập trình</button>
								</h5>
							</div>
							<div id="collapseTwo" class="collapse"
								aria-labelledby="headingTwo" data-parent="#accordion">
								<div class="card-body">Java, html, css, javascript</div>
							</div>
						</div>
						<div class="card">
							<div class="card-header" id="headingThree">
								<h5 class="mb-0">
									<button class="btn btn-link collapsed" data-toggle="collapse"
										data-target="#collapseThree" aria-expanded="false"
										aria-controls="collapseThree">Framework đã sử dụng</button>
								</h5>
							</div>
							<div id="collapseThree" class="collapse"
								aria-labelledby="headingThree" data-parent="#accordion">
								<div class="card-body">Hibernate, bootstrap 4</div>
							</div>
						</div>
						<div class="card">
							<div class="card-header" id="headingFour">
								<h5 class="mb-0">
									<button class="btn btn-link collapsed" data-toggle="collapse"
										data-target="#collapseFour" aria-expanded="false"
										aria-controls="collapseFour">Thành phần sử dụng</button>
								</h5>
							</div>
							<div id="collapseFour" class="collapse"
								aria-labelledby="headingFour" data-parent="#accordion">
								<div class="card-body">JSP, Servlet, Servlet Filter,
									Custom Tags, JSTL, EL, Scriptlet, Expression...</div>
							</div>
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