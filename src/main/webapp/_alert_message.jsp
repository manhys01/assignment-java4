<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.Message!=null}">
	<c:choose>
		<c:when test="${sessionScope.Message.id==0}">
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
				${sessionScope.Message.message}
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:when>
		<c:otherwise>
			<div class="alert alert-success alert-dismissible fade show" role="alert">
				${sessionScope.Message.message}
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:otherwise>
	</c:choose>
	<c:remove var="Message" scope="session" />
</c:if>