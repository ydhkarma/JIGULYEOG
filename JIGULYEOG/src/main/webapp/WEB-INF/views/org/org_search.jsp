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
<link rel="icon" href="images/ficon.png">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">

<style>
body {
	font-family: 'Poppins', 'Jeju Gothic', serif;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	// search check
	$(function() {
		$("#searchBtn").click(function() {
			if ($("#searchKeyword").val() == "") {
				alert("검색할 키워드를 입력해주세요.");
				return false;
			}
			$("#searchForm").submit();
		});
	});
</script>
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
							<h2 class="heading">환경단체 검색</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!----------------- END header ----------------->

	<!-- START section -->
	<div class="site-section">
		<div class="container" style="text-align: center;">
			<div class="row block-9">
				<div class="col-md-6 pr-md-6" style="margin: 0 auto;">
					<form action="org.do" method="get" id="searchForm">
						<div class="form-group input-group">
							<input type="text" id="searchKeyword" name="keyword"
								class="form-control px-3 py-2" placeholder="단체명 또는 주소를 입력하세요.">
							<input type="button" class="btn btn-success py-2 px-5 ml-2"
								id="searchBtn" value="검색">
						</div>
					</form>
				</div>
			</div>
			<!-- 아래 div는 검색전=hide 검색후=show -->
			<hr>
			<div class="row">
				<div class="col-md-6 pr-md-5" style="margin: 0 auto;">
					<h3>검색결과</h3>
					<br>

					<!-- img태그위치에 지도api 적용해야함 -->
					<div id="map" style="width: 500px; height: 400px;"></div>
					<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0770d1c109b361eee880a8893aba7aa0&libraries=services"></script>
					<script type="text/javascript">
						$(function(){
							var orgList=new Array();
							<c:forEach items="${orgList}" var="org">
								var json = new Object();
								json.org_name = "${org.org_name}";
								json.org_addr = "${org.org_addr}"
								orgList.push(json);
							</c:forEach>
							//alert("json="+JSON.stringify(result))
							
							//카카오맵 코드
							var mapContainer = document.getElementById('map'); // 지도를 표시할 div 
							var mapOption = {
								center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
								level : 3	// 지도의 확대 레벨
							};

							// 지도를 생성합니다    
							var map = new kakao.maps.Map(mapContainer, mapOption);

							// 주소-좌표 변환 객체를 생성합니다
							var geocoder = new kakao.maps.services.Geocoder();
							
							var bounds = new kakao.maps.LatLngBounds();
							
							// 주소로 좌표를 검색합니다.
							for(var i =0; i<orgList.length; i++){
								geocoder.addressSearch(orgList[i].org_addr, function(result, status){
									//정상적으로 검색이 완료됐으면
									if(status === kakao.maps.services.Status.OK){
										var coords = new kakao.maps.LatLng(
											result[0].y,
											result[0].x
										);
										
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
							}
						});
					</script>

					<br> <br>
				</div>
				<div class="row" style="text-align: center; margin: 0px auto;">
					<div class="col-md-12 pr-md-12" style="margin: 0 auto;">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>단체명</th>
									<th>주소</th>
									<th>대표</th>
									<th>상세정보</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty orgList }">
										---------- 검색 결과가 없습니다. ----------
									</c:when>
									<c:otherwise>
										<c:forEach var="org" items="${orgList }">
											<tr>
												<td>${org.org_name }</td>
												<td>${org.org_addr }</td>
												<td>${org.org_ceo }</td>
												<td><input type="button" value="상세"
													class="btn btn-success"
													onclick="location.href='orgDetail.do?org_num=${org.org_num}'"></td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
					<!-- CW PAGE MAKER PART -->
					<div style="width: 200px; margin: 0px auto;">
						<nav aria-label="Page navigation">
							<ul class="pagination">
								<c:if test="${PageMaker.prev }">
									<li class="page-item"><a class="page-link"
										href="org.do?orgPage=${PageMaker.startPage - 1 }"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
									</a></li>
								</c:if>
								<c:forEach begin="${PageMaker.startPage }"
									end="${PageMaker.endPage }" var="idx">
									<c:choose>
										<c:when test="${idx eq orgPage }">
											<li class="page-item active"><a class="page-link"
												href="org.do?orgPage=${idx }">${idx }</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="org.do?orgPage=${idx }">${idx }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${PageMaker.next && PageMaker.endPage>0 }">
									<li class="page-item"><a class="page-link"
										href="org.do?orgPage=${PageMaker.endPage + 1 }"
										aria-label="Next"> <span aria-hidden="true">&raquo;</span>
									</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
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