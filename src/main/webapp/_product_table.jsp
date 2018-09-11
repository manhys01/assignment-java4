<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="manh-tags"%>

<m:getListProduct maxResult="5" />
<c:choose>
<c:when test="${not empty products}">
	<div class="table-responsive mb-3">
		<table class="table table-sm table-striped table-bordered">
			<thead>
				<tr>
					<th>Ảnh</th>
					<th>ID</th>
					<th>Chủng loại</th>
					<th>Nhà sản xuất</th>
					<th>Tên sản phẩm</th>
					<th>Đơn giá</th>
					<th>Trong kho</th>
					<th>Mô tả</th>
					<th>Trạng thái</th>
					<th>Ngày tạo</th>
					<th>Ngày sửa gần nhất</th>
					<th>Lựa chọn</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="rows" varStatus="status" items="${products}">
					<tr>
						<td>
							<img src="${rows.image }" width="50px">
						</td>
						<td>${rows.id}</td>
						<td>${rows.category.name}</td>
						<td>${rows.manufacturer.name}</td>
						<td>${rows.name}</td>
						<td>
							<span>
								<fmt:formatNumber type="number" maxFractionDigits="1">${rows.price}</fmt:formatNumber>
							</span>
						</td>
						<td>${rows.inStock}</td>
						<td>${rows.description}</td>
						<td>${rows.discontinued?'Ngừng kinh doanh':'Đang mở bán'}</td>
						<td>
							<fmt:formatDate value="${rows.createAt}"
								pattern="dd/MM/YYY hh:mm:ss" />
						</td>
						<td>
							<fmt:formatDate value="${rows.latestUpdate}"
								pattern="dd/MM/YYY hh:mm:ss" />
						</td>
						<td>
							<div class="btn-group">
								<c:url var="myURL" value="/product.jsp" >
									<c:param name="id" value="${rows.id}"/>
									<c:if test="${param.page!=null}">
										<c:param name="page" value="${param.page}" />
									</c:if>
								</c:url>
								<a href="${myURL}&action=edit" class="btn btn-warning mr-2">
									<i class="fas fa-pencil-alt"></i>
								</a>
								<a href="${myURL}&action=delete" class="btn btn-danger">
									<i class="fas fa-trash-alt"></i>
								</a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:when>
<c:otherwise>
	<h3>Không tìm thấy sản phẩm ${param.find}!</h3>
</c:otherwise>
</c:choose>
<m:paginate max="5" uri="${pageContext.request.requestURI}"
	page="${param.page}" totalRecords="${totalRecords}" />
