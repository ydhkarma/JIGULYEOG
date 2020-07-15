<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mvc.jigulyeog.model.dto.OrgDto"%>
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
<link rel="icon" href="images/ficon.png">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

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
							<h2 class="heading">${org.org_name }</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!----------------- END header ----------------->


	<div class="site-section">
		<div class="container">
			<div class="row block-9" style="text-align: center;">
				<div class="col-md-5 pr-md-5" style="margin: 0 auto;">
					<div class="card fundraise-item">
						<c:choose>
							<c:when test="${org.org_pic eq null }">
								<img class="card-img-top"
									src="${pageContext.request.contextPath}/resources/images/personal_img/no-image-icon.PNG"
									alt="Image placeholder" height="300px">
							</c:when>
							<c:otherwise>
								<img class="card-img-top"
									src="${pageContext.request.contextPath}/resources/upload/images/user/${org.org_pic}"
									alt="Image placeholder" height="300px">
							</c:otherwise>
						</c:choose>
						<div class="card-body">
							<h3 class="card-title">${org.org_name }</h3>
							<c:choose>
								<c:when test="${subChk eq false }">
									<form action="subscribe.do" method="post" id="subscribe">
										<input type="hidden" name="org_num" value="${org.org_num }">
										<input type="submit" value="구독하기"
											class="btn btn-success py-2 px-5">
									</form>
								</c:when>
								<c:otherwise>
									<form action="subscribeCancle.do" method="post"
										id="subscribeCancle">
										<input type="hidden" name="org_num" value="${org.org_num }">
										<input type="submit" value="구독취소"
											class="btn btn-success py-2 px-5">
									</form>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>

			<br> <br> <br>

			<div class="row">
				<div class="col-md-6 pr-md-5" style="margin: 0 auto;">
					<!-- img태그위치에 지도api 적용해야함 -->
					<div id="map" style="width: 500px; height: 400px;"></div>
					<script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0770d1c109b361eee880a8893aba7aa0&libraries=services"></script>

					<script type="text/javascript">
						$(function() {
							var org_addr = "${org.org_addr}";
							//카카오맵 코드
							var mapContainer = document.getElementById('map'); // 지도를 표시할 div 
							var mapOption = {
								center : new kakao.maps.LatLng(33.450701,
										126.570667), // 지도의 중심좌표
								level : 3
							// 지도의 확대 레벨
							};

							// 지도를 생성합니다    
							var map = new kakao.maps.Map(mapContainer,
									mapOption);

							// 주소-좌표 변환 객체를 생성합니다
							var geocoder = new kakao.maps.services.Geocoder();

							var bounds = new kakao.maps.LatLngBounds();

							// 주소로 좌표를 검색합니다.
							geocoder.addressSearch(org_addr, function(result,
									status) {
								//정상적으로 검색이 완료됐으면
								if (status === kakao.maps.services.Status.OK) {
									var coords = new kakao.maps.LatLng(
											result[0].y, result[0].x);

									// 결과값으로 받은 위치를 마커로 표시합니다
									var marker = new kakao.maps.Marker({
										map : map,
										position : coords
									});

									// LatLngBounds 객체에 좌표를 추가합니다
									bounds.extend(coords);
									// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
									//map.setCenter(coords);
									// LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
									// 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
									map.setBounds(bounds);
								}
							})
						});
					</script>
				</div>
			</div>
			<br>
			<div class="row block-9">
				<div class="col-md-5 pr-md-5" style="margin: 0 auto;">
					<table class="">
						<col width="100">
						<col width="300">
						<tr>
							<th>주소</th>
							<td><input type="text" value="${org.org_addr }"
								class="form-control" readonly></td>
						</tr>
						<tr>
							<th>대표자</th>
							<td><input type="text" value="${org.org_ceo }"
								class="form-control" readonly></td>
						</tr>
						<tr>
							<th>설립일</th>
							<td><input type="date" value="${dateEx }"
								class="form-control" readonly></td>
						</tr>
						<tr>
							<th>부서</th>
							<td><input type="text" value="${org.org_dept }"
								class="form-control" readonly></td>
						</tr>
					</table>
				</div>
			</div>
			<br> <br>

			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-12" style="text-align: center;">
							<h3>진행한 프로젝트</h3>
						</div>
						<div class="col-md-12 block-11">
							<div class="nonloop-block-11 owl-carousel">
								<c:choose>
									<c:when test="${empty pList }">
										<div class="card fundraise-item">
											<div class="card-body">
												<h3 class="card-title">진행한 프로젝트가 없습니다.</h3>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<c:forEach var="p" items="${pList }">
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
					<br> <br> <br>
				</div>
			</div>
		</div>
	</div>

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
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>


</body>
</html>