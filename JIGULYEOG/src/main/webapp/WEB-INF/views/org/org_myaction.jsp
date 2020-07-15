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
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/images/ficon.png">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
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
							<h2 class="heading">환경단체 내활동</h2>
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
					<h1>진행중인 프로젝트</h1>
					<div class="row">
						<div class="col-md-12 block-11">
							<div class="nonloop-block-11 owl-carousel">
								<c:choose>
									<c:when test="${empty nowPList}">
										<div class="card fundraise-item">
											<div class="card-body">
												<h3 class="card-title">
													<a href="projectlist.do">진행한 프로젝트가 없습니다.<br>프로젝트를
														진행해보세요.
													</a>
												</h3>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<c:forEach var="nowP" items="${nowPList}">
											<c:choose>
												<c:when test="${nowP.pro_nowmoney ==0 }">
													<c:set var="percent" value="0"></c:set>
												</c:when>
												<c:otherwise>
													<fmt:parseNumber var="percent" integerOnly="true"
														value="${nowP.pro_nowmoney*100/nowP.pro_goalmoney }"></fmt:parseNumber>
												</c:otherwise>
											</c:choose>
											<div class="card fundraise-item">
												<a href="projectdetail.do?pro_num=${nowP.pro_num }"><img
													class="card-img-top"
													src="${pageContext.request.contextPath}/resources/upload/images/project/${nowP.pro_image}"
													alt="Image placeholder"></a>
												<div class="card-body">
													<h3 class="card-title">
														<a href="projectdetail.do?pro_num=${nowP.pro_num }">${nowP.pro_title }</a>
													</h3>
													<div class="progress custom-progress-success">
														<div class="progress-bar bg-success" role="progressbar"
															style="width: ${percent}%" aria-valuenow="25"
															aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="fund-raised d-block">후원된 금액:
														${nowP.pro_nowmoney }, 목표금액: ${nowP.pro_goalmoney }</span>
												</div>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<br> <br> <br>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<h1>종료된 프로젝트</h1>
					<div class="row">
						<div class="col-md-12 block-11">
							<div class="nonloop-block-11 owl-carousel">
								<c:choose>
									<c:when test="${empty endPList}">
										<div class="card fundraise-item">
											<div class="card-body">
												<h3 class="card-title">종료된 프로젝트가 없습니다.</h3>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<c:forEach var="endP" items="${endPList}">
											<c:choose>
												<c:when test="${endP.pro_nowmoney ==0 }">
													<c:set var="percent" value="0"></c:set>
												</c:when>
												<c:otherwise>
													<fmt:parseNumber var="percent" integerOnly="true"
														value="${endP.pro_nowmoney*100/endP.pro_goalmoney }"></fmt:parseNumber>
												</c:otherwise>
											</c:choose>
											<div class="card fundraise-item">
												<a href="projectdetail.do?pro_num=${endP.pro_num }"><img
													class="card-img-top"
													src="${pageContext.request.contextPath}/resources/upload/images/project/${endP.pro_image}"
													alt="Image placeholder"></a>
												<div class="card-body">
													<h3 class="card-title">
														<a href="projectdetail.do?pro_num=${endP.pro_num }">${endP.pro_title }</a>
													</h3>
													<div class="progress custom-progress-success">
														<div class="progress-bar bg-success" role="progressbar"
															style="width: ${percent}%" aria-valuenow="25"
															aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="fund-raised d-block">후원된 금액:
														${endP.pro_nowmoney }, 목표금액: ${endP.pro_goalmoney }</span>
												</div>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<br> <br> <br>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<h1>구독자</h1>
					<div class="row">
						<div class="col-md-12 block-11">
							<div class="nonloop-block-11 owl-carousel">
								<c:choose>
									<c:when test="${empty subList}">
										<div class="card fundraise-item">
											<div class="card-body">
												<h3 class="card-title">구독자가 없습니다.</h3>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<c:forEach var="sub" items="${subList}">
											<div class="card fundraise-item">
												<img class="card-img-top"
													src="${pageContext.request.contextPath}/resources/upload/images/user/${sub.user_pic}"
													alt="Image placeholder" height="100px">
												<div class="card-body">
													<h3 class="card-title" style="text-align: center;">${sub.user_id }</h3>
												</div>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<br> <br> <br>
				</div>
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