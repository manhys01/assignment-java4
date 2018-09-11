<%@page
	import="poly.manhnd.assignment.daos.entities.implement.OrderDAOImp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="manh-tags"%>

<c:set var="totalOrder" value="<%=new OrderDAOImp().getTotalOrder()%>" />
<c:choose>
	<c:when test="${totalOrder>0}">
		<form class="row mb-3">
			<div class="col-sm-8 input-group">
				<input type="text" name="search" class="form-control mr-1"
					placeholder="Tìm theo tên, tài khoản, sđt, địa chỉ khách hàng">
				<button class="btn btn-success">Tìm kiếm</button>
			</div>
		</form>
		<m:getOrder maxResult="5">
			<tr>
				<c:set var="quantity" value="0" />
				<td class="text-truncate">${Order.id}</td>
				<td class="text-truncate">${Order.user.username}</td>
				<td class="text-truncate">${Order.customerName}</td>
				<td class="text-truncate"><c:forEach var="details"
						items="${OrderDetails}" varStatus="status">
						<span class="badge badge-pill badge-success">${status.count}.</span>
						<span>${details.product.name}</span>
						<span class="badge badge-danger">${details.quantity}/${details.product.inStock}
							chiếc</span>
						<br>
						<c:set var="quantity" value="${details.quantity + quantity}" />
					</c:forEach></td>
				<td class="text-truncate">${quantity}</td>
				<td class="text-truncate"><fmt:formatNumber var="totalPrice"
						type="number" maxFractionDigits="3" value="${Order.amount}" />
					${totalPrice} <sup>đ</sup></td>
				<td class="text-truncate">${Order.phoneNumber}</td>
				<td class="text-truncate">${Order.shippingAddress}</td>
				<td class="text-truncate">${Order.createAt}</td>
				<td class="text-truncate">${Order.orderState.name}</td>
				<td class="text-truncate">
					<div class="btn-group">
						<c:url var="myURL" value="/order.jsp">
							<c:param name="orderId" value="${Order.id}" />
							<c:if test="${param.page!=null}">
								<c:param name="page" value="${param.page}" />
							</c:if>
						</c:url>
						<a href="${myURL}&action=view" class="btn btn-success mr-1"
							type="submit" data-toggle="tooltip" data-placement="bottom"
							title="Chi tiết"> <i class="fas fa-eye"></i>
						</a> <a href="${myURL}&action=update" class="btn btn-warning"
							type="submit" data-toggle="tooltip" data-placement="bottom"
							title="Sửa"> <i class="fas fa-edit"></i>
						</a>
					</div>
				</td>
			</tr>
		</m:getOrder>
		<div class="container pt-3">
			<div class="row">
				<m:paginate max="5" uri="${pageContext.request.requestURI}"
					page="${param.page}" totalRecords="${totalRecords}" />
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<h3>Chưa có order nào!</h3>
	</c:otherwise>
</c:choose>