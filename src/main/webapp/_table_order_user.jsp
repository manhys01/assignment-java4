<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="manh-tags"%>

<m:getOrderUser user="${sessionScope.User}" maxResult="5">
	<c:if test="${Order!=null }">
		<tr>
			<td>${count}</td>
			<td>${Order.id}</td>
			<td>
				<c:set var="count" value="0" />
				<c:forEach var="rows" items="${OrderDetails}" varStatus="status">
					<span class="badge badge-pill badge-danger">${status.count}.</span>
					<small>${rows.product.name}</small>
					<br>
					<c:set var="count" value="${count + rows.quantity}" />
				</c:forEach>
			</td>
			<td>${count}</td>
			<td>
				<span class="badge badge-pill badge-danger">
					<fmt:formatNumber type="number">${Order.amount}</fmt:formatNumber>
					<sup> đ</sup>
				</span>

			</td>
			<td>
				<fmt:formatDate  value="${Order.createAt}" pattern="dd/MM/YYYY hh:mm" />
			</td>
			<td>${Order.orderState.name}</td>
		</tr>
	</c:if>
</m:getOrderUser>
<m:paginate max="5" uri="${pageContext.request.requestURI}"
	page="${param.page}" totalRecords="${totalRecords}" />