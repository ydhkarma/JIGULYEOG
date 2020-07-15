<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
	$(function(){
		$('.popBtn').click(function(){

		    var org_num = $(this).closest("td").siblings(".org_num");
		    var org_name = $(this).closest("td").siblings(".org_name");
		    var org_addr = $(this).closest("td").siblings(".org_addr");
		    var org_nick = $(this).closest("td").siblings(".org_nick");
		    
		     $('#user_status',opener.document).val(org_num.text());
		     $('#user_name',opener.document).val(org_name.text());
		     $('#user_addr',opener.document).val(org_addr.text());
		     $('#user_nick',opener.document).val(org_nick.text());
		     
		    self.close();
		  });
	});

</script>
</head>
<body>

	<!-- START section -->
	<div class="site-section">
		<div class="container" style="text-align: center;">
			<div class="row block-9">
				<div class="col-md-6 pr-md-6" style="margin: 0 auto;">
					<form action="orgSearch.do" method="get" id="searchForm">
						<div class="form-group input-group">
							<input type="text" id="searchKeyword" name="keyword"
								class="form-control px-3 py-2" placeholder="단체명 또는 주소를 입력하세요."> <input
								type="button" class="btn btn-success py-2 px-5 ml-2"
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
					<br> <br>
				</div>
				<div class="row" style="text-align: center; margin: 0px auto;">
					<div class="col-md-12 pr-md-12" style="margin: 0 auto;">
						<table class="table table-hover">
							<thead>
								<tr>
									<th width="20%">단체번호</th>
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
											<tr id="org_list">
												<td class="org_num">${org.org_num }</td>
												<td class="org_name">${org.org_name }</td>
												<td class="org_addr">${org.org_addr }</td>
												<td class="org_nick">${org.org_ceo }</td>
												<c:if test="${empty org.org_pic }">
													<td><input type="button" value="선택" class="btn btn-success popBtn"></td>														
												</c:if>
												<c:if test="${!empty org.org_pic }">
													<td><input type="button" value="선택" class="btn btn-success popBtn" disabled="disabled"></td>														
												</c:if>												
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
										href="orgSearch.do?orgPage=${PageMaker.startPage - 1 }"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
									</a></li>
								</c:if>
								<c:forEach begin="${PageMaker.startPage }"
									end="${PageMaker.endPage }" var="idx">
									<c:choose>
										<c:when test="${idx eq orgPage }">
											<li class="page-item active"><a class="page-link"
												href="orgSearch.do?orgPage=${idx }">${idx }</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="orgSearch.do?orgPage=${idx }">${idx }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${PageMaker.next && PageMaker.endPage>0 }">
									<li class="page-item"><a class="page-link"
										href="orgSearch.do?orgPage=${PageMaker.endPage + 1 }"
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

	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>
</html>