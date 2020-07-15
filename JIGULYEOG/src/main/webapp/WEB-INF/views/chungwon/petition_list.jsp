<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mvc.jigulyeog.model.dto.UserDto"%>

<!DOCTYPE html>
<html>
<head>
<title>지구력</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500|Gaegu:700"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
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
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/images/ficon.png">

<style type="text/css">
.font {
	font-family: 'Poppins', 'Jeju Gothic', serif;
}
</style>
</head>
<body>
	<%
		UserDto user = (UserDto) session.getAttribute("user");
	%>
	<div class="font">
		<!----------------- START nav ----------------->
		<%@ include file="../header.jsp"%>
		<!----------------- END nav ----------------->

		<!----------------- START header ----------------->
		<div class="block-31" style="position: relative;">
			<div class="owl-carousel loop-block-31 ">
				<div class="block-30 block-30-sm item"
					style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_3.jpg');"
					data-stellar-background-ratio="0.5">
					<div class="container">
						<div class="row align-items-center justify-content-center">
							<div class="col-md-7 text-center">
								<h2 class="heading">청원 목록</h2>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
		<!----------------- END header ----------------->


		<!----------------- START Project ----------------->
		<div class="site-section fund-raisers">
			<div class="container">
				<div class="mb-3">
					<!--검색창밑 카테고리 선택-->
					<div>
						<form action="chunglist.do" method="get"
							style="display: inline-block;">
							<input type="hidden" value="${navi.currentPage }"> <input
								type="text" name="searchWord" class="form-control px-3 py-1"
								style="width: 300px; display: inline-block;"
								placeholder="관심있는 청원을 검색하세요" value="${searchWord}"> <input
								type="submit" class="btn btn-success btn-hover-white"
								value="search" />
						</form>
						<div class="dropdown" style="float: right;">
							<button class="btn btn-success dropdown-toggle" type="button"
								id="dropdownMenuButton" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">
								<c:if test="${category eq 1 || empty category }">
              		최신 게시물순
              	</c:if>
								<c:if test="${category eq 2 }">
              		오래된 게시물순
              	</c:if>
							</button>
							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">

								<a class="dropdown-item" href="chunglist.do?category=1">최신
									게시물순</a>
								<!-- <a class="dropdown-item" href="chungnewest.do">오래된 게시물순</a> -->
								<a class="dropdown-item" href="chunglist.do?category=2">오래된
									게시물순</a>
								<!--  <a class="dropdown-item" href="#">인기</a>-->
							</div>
						</div>
					</div>
				</div>
				<br>

				<!--청원 프로젝트-->
				<div class="row">
					<c:choose>
						<c:when test="${empty list }">
							<div>
								<h1>청원 게시글이 없습니다.</h1>
							</div>
						</c:when>

						<c:otherwise>

							<c:forEach items="${list }" var="dto">

								<div class="col-12 col-sm-6 col-md-6 col-lg-4 mb-4 mb-lg-0">
									<div class="card fundraise-item mb-5">
										<a href="#"><img class="card-img-top"
											src="${pageContext.request.contextPath}/resources/upload/images/chungwon/${dto.pet_photo}"
											alt="Image placeholder" height="210px"
											onclick="location.href='chungdetail.do?pet_no=${dto.pet_no }'"></a>
										<div class="card-body">
											<h3 class="card-title">
												<a href="chungdetail.do?pet_no=${dto.pet_no }">${dto.pet_title }</a>
											</h3>
											<div>작성자 : ${dto.user_id }</div>
											<div>청원 시작일 : ${dto.pet_date }</div>

											<span class="fund-raised d-block">청원 마감일 :
												${dto.pet_dead }</span>
										</div>
									</div>
								</div>

							</c:forEach>

						</c:otherwise>
					</c:choose>
				</div>
				<!--작성하기-->
				<div class="container">
					<c:if test="${null ne user.user_id}">
						<input type="button" class="btn btn-success btn-hover-white"
							value="작성하기" onclick="location.href='chungwriteform.do'"
							style="float: right;" />
					</c:if>
				</div>
				<br> <br>
				<!--페이징-->
				<div style="width: 200px; margin: 0px auto;">
					<nav aria-label="Page navigation">
						<ul class="pagination">
							<li class="page-item"><a class="page-link"
								href="chunglist.do?page=1" aria-label="Previous"> <span
									aria-hidden="true">&lt;&lt;</span>
							</a></li>
							<li class="page-item"><a class="page-link"
								href="chunglist.do?page=${navi.startPageGroup-1 }&category=${category }&searchWord=${searchWord}">&lt;</a>
							</li>
							<!-- <li class="page-item active"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">4</a></li>
            <li class="page-item"><a class="page-link" href="#">5</a></li> -->
							<c:forEach var="counter" begin="${navi.startPageGroup }"
								end="${navi.endPageGroup }">

								<li class="page-item"><a class="page-link"
									href="chunglist.do?page=${counter }&category=${category }&searchWord=${searchWord}">${counter }</a></li>
							</c:forEach>
							<li class="page-item"><a class="page-link"
								href="chunglist.do?page=${navi.endPageGroup+1 }&category=${category }&searchWord=${searchWord}">&gt;</a>
							</li>

							<li class="page-item"><a class="page-link"
								href="chunglist.do?page=${navi.totalRecordsCount}&category=${category }&searchWord=${searchWord}"
								aria-label="Next"> <span aria-hidden="true">&gt;&gt;</span>
							</a></li>
						</ul>
					</nav>
					<!-- 
        <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
          		<c:choose>
          			<c:when test="${idx eq page }">
			            <li class="page-item active"><a class="page-link" href="projectlist.do?page=${idx }&category=${category}&keyword=${keyword }">${idx }</a></li>
          			</c:when>
          			<c:otherwise>          				
			            <li class="page-item"><a class="page-link" href="projectlist.do?page=${idx }&category=${category}&keyword=${keyword }">${idx }</a></li>
          			</c:otherwise>
          		</c:choose>          		
          	</c:forEach>
         -->




				</div>
			</div>
			<!-- .section -->
		</div>
		<!----------------- End Project ----------------->




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