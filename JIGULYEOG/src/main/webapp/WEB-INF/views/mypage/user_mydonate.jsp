<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>지구력</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500|Gaegu:700"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=East+Sea+Dokdo&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/animate.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/aos.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jquery.timepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaticon.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/icomoon.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/personal_css/mypage.css">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/images/ficon.png">
<style>
body {
	font-family: 'Poppins', 'Jeju Gothic', serif;
}
</style>
</head>
<body>

	<!----------------- START nav ----------------->
	<%@ include file="../header.jsp"%>
	<!----------------- END nav ----------------->

	<!----------------- START header ----------------->
	<div class="block-31" style="position: relative;">
		<div class="owl-carousel loop-block-31 ">
			<div class="block-30 block-30-sm item"
				style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_2.jpg');"
				data-stellar-background-ratio="0.5">
				<div class="container">
					<div class="row align-items-center justify-content-center">
						<div class="col-md-7 text-center">
							<h2 class="heading">나의 후원</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!----------------- END header ----------------->


	<!-- START section -->
	<div class="site-section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>후원한 프로젝트</h1>
					<div class="row">
						<div class="col-md-12 block-11">
							<div class="nonloop-block-11 owl-carousel">
								<c:choose>
									<c:when test="${empty projectList}">
										<div class="card fundraise-item">
											<div class="card-body">
												<h3 class="card-title">
													<a href="projectlist.do">후원한 프로젝트가 없습니다.<br>프로젝트에
														후원해볼까요?
													</a>
												</h3>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<c:forEach var="p" items="${projectList}">
											<c:choose>
												<c:when test="${p.pro_nowmoney ==0 }">
													<c:set var="percent" value="0"></c:set>
												</c:when>
												<c:otherwise>
													<fmt:parseNumber var="percent" integerOnly="true"
														value="${p.pro_nowmoney*100/p.pro_goalmoney }"></fmt:parseNumber>
												</c:otherwise>
											</c:choose>
											<div class="card fundraise-item">
												<a href="projectdetail.do?pro_num=${p.pro_num }"><img
													class="card-img-top"
													src="${pageContext.request.contextPath}/resources/upload/images/project/${p.pro_image}"
													alt="Image placeholder"></a>
												<div class="card-body">
													<h3 class="card-title">
														<a href="projectdetail.do?pro_num=${p.pro_num }">${p.pro_title }</a>
													</h3>
													<div class="progress custom-progress-success">
														<div class="progress-bar bg-success" role="progressbar"
															style="width: ${percent}%" aria-valuenow="25"
															aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="fund-raised d-block">후원된 금액:
														${p.pro_nowmoney }, 목표금액: ${p.pro_goalmoney }</span>
												</div>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br> <br>
			<div class="row">
				<div class="col-md-12">
					<h1>구독한 단체</h1>
					<div class="row">
						<div class="col-md-12 block-11">
							<div class="nonloop-block-11 owl-carousel">
								<c:choose>
									<c:when test="${empty orgList}">
										<div class="card fundraise-item">
											<div class="card-body">
												<h3 class="card-title">
													<a href="org.do">구독한 단체가 없습니다.<br>단체를 구독해볼까요?
													</a>
												</h3>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<c:forEach var="org" items="${orgList }">
											<div class="card fundraise-item">
												<c:choose>
													<c:when test="${org.org_pic eq null }">
														<a href="orgDetail.do?org_num=${org.org_num}">
														<img class="card-img-top"
															src="${pageContext.request.contextPath}/resources/images/personal_img/no-image-icon.PNG"
															alt="Image placeholder" height="300px"></a>
													</c:when>
													<c:otherwise>
														<a href="orgDetail.do?org_num=${org.org_num}"><img
															class="card-img-top"
															src="${pageContext.request.contextPath}/resources/upload/images/user/${org.org_pic}"
															alt="Image placeholder" height="300px"> </a>

													</c:otherwise>
												</c:choose>
												<div class="card-body">
													<h3 class="card-title">
														<a href="orgDetail.do?org_num=${org.org_num}">${org.org_name }</a>
													</h3>
												</div>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br> <br>

			<!--
			<div class="row">
				<h1>새소식</h1>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>내용</th>
							<th>작성자</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>환경단체에서 새 프로젝트를 게시하였습니다.</td>
							<td>단체?시스템?</td>
							<td><input type="button" value="이동" class="btn btn-primary"
								onclick="location.href='#'"> <input type="button"
								value="삭제" class="btn cancelbtn" onclick="location.href='#'">
							</td>
						</tr>
						<tr>
							<td>2</td>
							<td>환경단체에서 새 프로젝트를 게시하였습니다.</td>
							<td>단체?시스템?</td>
							<td><input type="button" value="이동" class="btn btn-primary"
								onclick="location.href='#'"> <input type="button"
								value="삭제" class="btn cancelbtn" onclick="location.href='#'">
							</td>
						</tr>
						<tr>
							<td>3</td>
							<td>환경단체에서 새 프로젝트를 게시하였습니다.</td>
							<td>단체?시스템?</td>
							<td><input type="button" value="이동" class="btn btn-primary"
								onclick="location.href='#'"> <input type="button"
								value="삭제" class="btn cancelbtn" onclick="location.href='#'">
							</td>
						</tr>
						<tr>
							<td>4</td>
							<td>환경단체에서 새 프로젝트를 게시하였습니다.</td>
							<td>단체?시스템?</td>
							<td><input type="button" value="이동" class="btn btn-primary"
								onclick="location.href='#'"> <input type="button"
								value="삭제" class="btn cancelbtn" onclick="location.href='#'">
							</td>
						</tr>
						<tr>
							<td>5</td>
							<td>환경단체에서 새 프로젝트를 게시하였습니다.</td>
							<td>단체?시스템?</td>
							<td><input type="button" value="이동" class="btn btn-primary"
								onclick="location.href='#'"> <input type="button"
								value="삭제" class="btn cancelbtn" onclick="location.href='#'">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
-->
			<!-- 
			페이징
			<div style="width: 200px; margin: 0px auto;">
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
						<li class="page-item active"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#">4</a></li>
						<li class="page-item"><a class="page-link" href="#">5</a></li>
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
				</nav>

			</div>
-->
		</div>
	</div>

	<!-- END section -->


	<!----------------- START footer ----------------->
	<%@ include file="../footer.jsp"%>
	<!----------------- END footer ----------------->

	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>


	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery-migrate-3.0.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.waypoints.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.stellar.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"></script>

	<script src="${pageContext.request.contextPath}/resources/js/aos.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.animateNumber.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/google-map.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>
</html>