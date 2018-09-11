<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="m" uri="manh-tags"%>
<div class="container">
	<m:getProductsByManufacturerId manufacturerId="${param.manufacturer}"
		maxResult="8" />
	<c:forEach var="row" items="${rows}">
		<c:set var="products" value="${row.value}" />
		<c:choose>
			<c:when test="${fn:length(products)==1}">
				<div class="row">
					<div class="col-md-3">
						<div class="card-deck">
							<c:forEach var="p" items="${products}">
								<div class="card">
									<div class="card-header">
										<a href="#ProdcutDetail${p.id}" data-toggle="modal"
											data-placement="bottom">
											<img class="card-img-top product-image" src="${p.image}"
												alt="${p.name}">
										</a>
									</div>
									<div class="card-body">
										<p class="card-title text-truncate font-weight-bold">
											<a href="#ProdcutDetail${p.id}" data-toggle="modal"
												data-placement="bottom" title="${p.name}">${p.name}</a>
										</p>
										<!-- modal -->
										<div class="modal fade" id="ProdcutDetail${p.id}"
											tabindex="-1" role="dialog"
											aria-labelledby="ProdcutDetailLabel${p.id}"
											aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered modal-lg"
												role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title text-uppercase"
															id="ProdcutDetailLabel${p.id}">Chi tiết sản phẩm</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">
														<div class="container-fluid">
															<div class="row">
																<div class="col-sm-3 align-middle">
																	<img src="${p.image}" alt="${p.name}"
																		class="img-thumbnail border-0 ">
																</div>
																<div class="col-sm-9">
																	<div class="container align-middle">
																		<div class="row pb-3">
																			<h5 class="text-danger">${p.name}</h5>
																		</div>
																		<div class="row">
																			<table class="table table-bordered">
																				<tr>
																					<td>Mã sản phẩm</td>
																					<td>${p.id}</td>
																				</tr>
																				<tr>
																					<td>Tên sản phẩm</td>
																					<td>${p.name}</td>
																				</tr>
																				<tr>
																					<td>Mô tả</td>
																					<td>${p.description}</td>
																				</tr>
																				<tr>
																					<td>Đơn giá</td>
																					<td>
																						<fmt:formatNumber type="number"
																							maxFractionDigits="3" value="${p.price}" />
																						<sup>đ</sup>
																					</td>
																				</tr>
																				<tr>
																					<td>Trạng thái</td>
																					<td>
																						<c:choose>
																							<c:when test="${p.discontinued}">
																								<span>Ngừng kinh doanh</span>
																							</c:when>
																							<c:otherwise>
																								<span>${p.inStock>0?'Còn hàng':'Hết hàng'}</span>
																							</c:otherwise>
																						</c:choose>
																					</td>
																				</tr>
																			</table>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="modal-footer">
														<c:choose>
															<c:when test="${!p.discontinued && p.inStock >0}">
																<form action="CartServlet">
																	<input type="hidden" name="id" value="${p.id}">
																	<button type="submit" name="action" value="Add To Cart"
																		class="btn btn-success btn-block">
																		<i class="fas fa-cart-plus"></i>
																		Thêm vào giỏ hàng
																	</button>
																</form>
															</c:when>
															<c:otherwise>
																<button type="submit" name="action" value="Add To Cart"
																	class="btn btn-secondary btn-block disabled">
																	<i class="fas fa-phone"></i>
																	Liên hệ
																</button>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
										</div>

										<p class="card-text text-truncate font-weight-normal"
											data-toggle="tooltip" data-placement="bottom"
											title="${p.description}">${p.description}</p>

										<c:choose>
											<c:when test="${!p.discontinued}">
												<p class="text-danger font-weight-normal text-right">
													<span class="badge badge-danger">
														<fmt:formatNumber type="number" maxFractionDigits="3"
															value="${p.price}" />
														<sup>đ</sup>
													</span>
												</p>
												<c:choose>
													<c:when test="${p.inStock>0}">
														<p class="text-success font-weight-normal text-right">
															<small>Còn hàng</small>
														</p>
													</c:when>
													<c:otherwise>
														<p class="text-secondary font-weight-normal text-right">
															<small>Hết hàng</small>
														</p>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<p class="text-danger font-weight-normal text-right">
													<span class="badge badge-secondary">
														<small>Ngừng kinh doanh</small>
													</span>
												</p>
											</c:otherwise>
										</c:choose>
									</div>

									<div class="card-footer">
										<c:choose>
											<c:when test="${!p.discontinued && p.inStock >0}">
												<form action="CartServlet">
													<input type="hidden" name="id" value="${p.id}">
													<button type="submit" name="action" value="Add To Cart"
														class="btn btn-success btn-block">
														<i class="fas fa-cart-plus"></i>
														Thêm vào giỏ
													</button>
												</form>
											</c:when>
											<c:otherwise>
												<button type="submit" name="action" value="Add To Cart"
													class="btn btn-secondary btn-block disabled">
													<i class="fas fa-phone"></i>
													Liên hệ
												</button>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:when>
			<c:when test="${fn:length(products)==2}">
				<div class="row">
					<div class="col-md-6">
						<div class="card-deck">
							<c:forEach var="p" items="${products}">
								<div class="card">
									<div class="card-header">
										<a href="#ProdcutDetail${p.id}" data-toggle="modal"
											data-placement="bottom">
											<img class="card-img-top product-image" src="${p.image}"
												alt="${p.name}">
										</a>
									</div>
									<div class="card-body">
										<p class="card-title text-truncate font-weight-bold">
											<a href="#ProdcutDetail${p.id}" data-toggle="modal"
												data-placement="bottom" title="${p.name}">${p.name}</a>
										</p>
										<!-- modal -->
										<div class="modal fade" id="ProdcutDetail${p.id}"
											tabindex="-1" role="dialog"
											aria-labelledby="ProdcutDetailLabel${p.id}"
											aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered modal-lg"
												role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title text-uppercase"
															id="ProdcutDetailLabel${p.id}">Chi tiết sản phẩm</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">
														<div class="container-fluid">
															<div class="row">
																<div class="col-sm-3 align-middle">
																	<img src="${p.image}" alt="${p.name}"
																		class="img-thumbnail border-0 ">
																</div>
																<div class="col-sm-9">
																	<div class="container align-middle">
																		<div class="row pb-3">
																			<h5 class="text-danger">${p.name}</h5>
																		</div>
																		<div class="row">
																			<table class="table table-bordered">
																				<tr>
																					<td>Mã sản phẩm</td>
																					<td>${p.id}</td>
																				</tr>
																				<tr>
																					<td>Tên sản phẩm</td>
																					<td>${p.name}</td>
																				</tr>
																				<tr>
																					<td>Mô tả</td>
																					<td>${p.description}</td>
																				</tr>
																				<tr>
																					<td>Đơn giá</td>
																					<td>
																						<fmt:formatNumber type="number"
																							maxFractionDigits="3" value="${p.price}" />
																						<sup>đ</sup>
																					</td>
																				</tr>
																				<tr>
																					<td>Trạng thái</td>
																					<td>
																						<c:choose>
																							<c:when test="${p.discontinued}">
																								<span>Ngừng kinh doanh</span>
																							</c:when>
																							<c:otherwise>
																								<span>${p.inStock>0?'Còn hàng':'Hết hàng'}</span>
																							</c:otherwise>
																						</c:choose>
																					</td>
																				</tr>
																			</table>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="modal-footer">
														<c:choose>
															<c:when test="${!p.discontinued && p.inStock >0}">
																<form action="CartServlet">
																	<input type="hidden" name="id" value="${p.id}">
																	<button type="submit" name="action" value="Add To Cart"
																		class="btn btn-success btn-block">
																		<i class="fas fa-cart-plus"></i>
																		Thêm vào giỏ hàng
																	</button>
																</form>
															</c:when>
															<c:otherwise>
																<button type="submit" name="action" value="Add To Cart"
																	class="btn btn-secondary btn-block disabled">
																	<i class="fas fa-phone"></i>
																	Liên hệ
																</button>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
										</div>

										<p class="card-text text-truncate font-weight-normal"
											data-toggle="tooltip" data-placement="bottom"
											title="${p.description}">${p.description}</p>

										<c:choose>
											<c:when test="${!p.discontinued}">
												<p class="text-danger font-weight-normal text-right">
													<span class="badge badge-danger">
														<fmt:formatNumber type="number" maxFractionDigits="3"
															value="${p.price}" />
														<sup>đ</sup>
													</span>
												</p>
												<c:choose>
													<c:when test="${p.inStock>0}">
														<p class="text-success font-weight-normal text-right">
															<small>Còn hàng</small>
														</p>
													</c:when>
													<c:otherwise>
														<p class="text-secondary font-weight-normal text-right">
															<small>Hết hàng</small>
														</p>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<p class="text-danger font-weight-normal text-right">
													<span class="badge badge-secondary">
														<small>Ngừng kinh doanh</small>
													</span>
												</p>
											</c:otherwise>
										</c:choose>
									</div>

									<div class="card-footer">
										<c:choose>
											<c:when test="${!p.discontinued && p.inStock >0}">
												<form action="CartServlet">
													<input type="hidden" name="id" value="${p.id}">
													<button type="submit" name="action" value="Add To Cart"
														class="btn btn-success btn-block">
														<i class="fas fa-cart-plus"></i>
														Thêm vào giỏ
													</button>
												</form>
											</c:when>
											<c:otherwise>
												<button type="submit" name="action" value="Add To Cart"
													class="btn btn-secondary btn-block disabled">
													<i class="fas fa-phone"></i>
													Liên hệ
												</button>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:when>
			<c:when test="${fn:length(products)==3}">
				<div class="row">
					<div class="col-md-9">
						<div class="card-deck">
							<c:forEach var="p" items="${products}">
								<div class="card">
									<div class="card-header">
										<a href="#ProdcutDetail${p.id}" data-toggle="modal"
											data-placement="bottom">
											<img class="card-img-top product-image" src="${p.image}"
												alt="${p.name}">
										</a>
									</div>
									<div class="card-body">
										<p class="card-title text-truncate font-weight-bold">
											<a href="#ProdcutDetail${p.id}" data-toggle="modal"
												data-placement="bottom" title="${p.name}">${p.name}</a>
										</p>
										<!-- modal -->
										<div class="modal fade" id="ProdcutDetail${p.id}"
											tabindex="-1" role="dialog"
											aria-labelledby="ProdcutDetailLabel${p.id}"
											aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered modal-lg"
												role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title text-uppercase"
															id="ProdcutDetailLabel${p.id}">Chi tiết sản phẩm</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">
														<div class="container-fluid">
															<div class="row">
																<div class="col-sm-3 align-middle">
																	<img src="${p.image}" alt="${p.name}"
																		class="img-thumbnail border-0 ">
																</div>
																<div class="col-sm-9">
																	<div class="container align-middle">
																		<div class="row pb-3">
																			<h5 class="text-danger">${p.name}</h5>
																		</div>
																		<div class="row">
																			<table class="table table-bordered">
																				<tr>
																					<td>Mã sản phẩm</td>
																					<td>${p.id}</td>
																				</tr>
																				<tr>
																					<td>Tên sản phẩm</td>
																					<td>${p.name}</td>
																				</tr>
																				<tr>
																					<td>Mô tả</td>
																					<td>${p.description}</td>
																				</tr>
																				<tr>
																					<td>Đơn giá</td>
																					<td>
																						<fmt:formatNumber type="number"
																							maxFractionDigits="3" value="${p.price}" />
																						<sup>đ</sup>
																					</td>
																				</tr>
																				<tr>
																					<td>Trạng thái</td>
																					<td>
																						<c:choose>
																							<c:when test="${p.discontinued}">
																								<span>Ngừng kinh doanh</span>
																							</c:when>
																							<c:otherwise>
																								<span>${p.inStock>0?'Còn hàng':'Hết hàng'}</span>
																							</c:otherwise>
																						</c:choose>
																					</td>
																				</tr>
																			</table>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="modal-footer">
														<c:choose>
															<c:when test="${!p.discontinued && p.inStock >0}">
																<form action="CartServlet">
																	<input type="hidden" name="id" value="${p.id}">
																	<button type="submit" name="action" value="Add To Cart"
																		class="btn btn-success btn-block">
																		<i class="fas fa-cart-plus"></i>
																		Thêm vào giỏ hàng
																	</button>
																</form>
															</c:when>
															<c:otherwise>
																<button type="submit" name="action" value="Add To Cart"
																	class="btn btn-secondary btn-block disabled">
																	<i class="fas fa-phone"></i>
																	Liên hệ
																</button>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
										</div>

										<p class="card-text text-truncate font-weight-normal"
											data-toggle="tooltip" data-placement="bottom"
											title="${p.description}">${p.description}</p>

										<c:choose>
											<c:when test="${!p.discontinued}">
												<p class="text-danger font-weight-normal text-right">
													<span class="badge badge-danger">
														<fmt:formatNumber type="number" maxFractionDigits="3"
															value="${p.price}" />
														<sup>đ</sup>
													</span>
												</p>
												<c:choose>
													<c:when test="${p.inStock>0}">
														<p class="text-success font-weight-normal text-right">
															<small>Còn hàng</small>
														</p>
													</c:when>
													<c:otherwise>
														<p class="text-secondary font-weight-normal text-right">
															<small>Hết hàng</small>
														</p>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<p class="text-danger font-weight-normal text-right">
													<span class="badge badge-secondary">
														<small>Ngừng kinh doanh</small>
													</span>
												</p>
											</c:otherwise>
										</c:choose>
									</div>

									<div class="card-footer">
										<c:choose>
											<c:when test="${!p.discontinued && p.inStock >0}">
												<form action="CartServlet">
													<input type="hidden" name="id" value="${p.id}">
													<button type="submit" name="action" value="Add To Cart"
														class="btn btn-success btn-block">
														<i class="fas fa-cart-plus"></i>
														Thêm vào giỏ
													</button>
												</form>
											</c:when>
											<c:otherwise>
												<button type="submit" name="action" value="Add To Cart"
													class="btn btn-secondary btn-block disabled">
													<i class="fas fa-phone"></i>
													Liên hệ
												</button>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="card-deck mb-4">
					<c:forEach var="p" items="${products}">
						<div class="card">
							<div class="card-header">
								<a href="#ProdcutDetail${p.id}" data-toggle="modal"
									data-placement="bottom">
									<img class="card-img-top product-image" src="${p.image}"
										alt="${p.name}">
								</a>
							</div>
							<div class="card-body">
								<p class="card-title text-truncate font-weight-bold">
									<a href="#ProdcutDetail${p.id}" data-toggle="modal"
										data-placement="bottom" title="${p.name}">${p.name}</a>
								</p>
								<!-- modal -->
								<div class="modal fade" id="ProdcutDetail${p.id}" tabindex="-1"
									role="dialog" aria-labelledby="ProdcutDetailLabel${p.id}"
									aria-hidden="true">
									<div class="modal-dialog modal-dialog-centered modal-lg"
										role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title text-uppercase"
													id="ProdcutDetailLabel${p.id}">Chi tiết sản phẩm</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<div class="container-fluid">
													<div class="row">
														<div class="col-sm-3 align-middle">
															<img src="${p.image}" alt="${p.name}"
																class="img-thumbnail border-0 ">
														</div>
														<div class="col-sm-9">
															<div class="container align-middle">
																<div class="row pb-3">
																	<h5 class="text-danger">${p.name}</h5>
																</div>
																<div class="row">
																	<table class="table table-bordered">
																		<tr>
																			<td>Mã sản phẩm</td>
																			<td>${p.id}</td>
																		</tr>
																		<tr>
																			<td>Tên sản phẩm</td>
																			<td>${p.name}</td>
																		</tr>
																		<tr>
																			<td>Mô tả</td>
																			<td>${p.description}</td>
																		</tr>
																		<tr>
																			<td>Đơn giá</td>
																			<td>
																				<fmt:formatNumber type="number"
																					maxFractionDigits="3" value="${p.price}" />
																				<sup>đ</sup>
																			</td>
																		</tr>
																		<tr>
																			<td>Trạng thái</td>
																			<td>
																				<c:choose>
																					<c:when test="${p.discontinued}">
																						<span>Ngừng kinh doanh</span>
																					</c:when>
																					<c:otherwise>
																						<span>${p.inStock>0?'Còn hàng':'Hết hàng'}</span>
																					</c:otherwise>
																				</c:choose>
																			</td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<c:choose>
													<c:when test="${!p.discontinued && p.inStock >0}">
														<form action="CartServlet">
															<input type="hidden" name="id" value="${p.id}">
															<button type="submit" name="action" value="Add To Cart"
																class="btn btn-success btn-block">
																<i class="fas fa-cart-plus"></i>
																Thêm vào giỏ hàng
															</button>
														</form>
													</c:when>
													<c:otherwise>
														<button type="submit" name="action" value="Add To Cart"
															class="btn btn-secondary btn-block disabled">
															<i class="fas fa-phone"></i>
															Liên hệ
														</button>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
								</div>

								<p class="card-text text-truncate font-weight-normal"
									data-toggle="tooltip" data-placement="bottom"
									title="${p.description}">${p.description}</p>

								<c:choose>
									<c:when test="${!p.discontinued}">
										<p class="text-danger font-weight-normal text-right">
											<span class="badge badge-danger">
												<fmt:formatNumber type="number" maxFractionDigits="3"
													value="${p.price}" />
												<sup>đ</sup>
											</span>
										</p>
										<c:choose>
											<c:when test="${p.inStock>0}">
												<p class="text-success font-weight-normal text-right">
													<small>Còn hàng</small>
												</p>
											</c:when>
											<c:otherwise>
												<p class="text-secondary font-weight-normal text-right">
													<small>Hết hàng</small>
												</p>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<p class="text-danger font-weight-normal text-right">
											<span class="badge badge-secondary">
												<small>Ngừng kinh doanh</small>
											</span>
										</p>
									</c:otherwise>
								</c:choose>
							</div>

							<div class="card-footer">
								<c:choose>
									<c:when test="${!p.discontinued && p.inStock >0}">
										<form action="CartServlet">
											<input type="hidden" name="id" value="${p.id}">
											<button type="submit" name="action" value="Add To Cart"
												class="btn btn-success btn-block">
												<i class="fas fa-cart-plus"></i>
												Thêm vào giỏ
											</button>
										</form>
									</c:when>
									<c:otherwise>
										<button type="submit" name="action" value="Add To Cart"
											class="btn btn-secondary btn-block disabled">
											<i class="fas fa-phone"></i>
											Liên hệ
										</button>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</div>
<div class="container">
	<hr>
	<div class="row">
		<div class="col-md-10">
			<m:paginate max="8" totalRecords="${totalRecords}"
				page="${param.page}" uri="${pageContext.request.requestURI}" />
		</div>
	</div>
</div>