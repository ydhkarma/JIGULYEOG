<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mvc.jigulyeog.model.dto.UserDto"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link
	href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=East+Sea+Dokdo&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
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
<script type="text/javascript" async> 
    //링크 공유용 코드
    var url_default_ks = "https://story.kakao.com/share?url="; 
    var url_default_fb = "https://www.facebook.com/sharer/sharer.php?u="; 
    var url_default_tw_txt = "https://twitter.com/intent/tweet?text="; 
    var url_default_tw_url = "&url="; 
    var url_default_band = "http://band.us/plugin/share?body="; 
    var url_route_band = "&route="; 
    var url_default_naver = "http://share.naver.com/web/shareView.nhn?url=";
    var title_default_naver = "&title="; 
    var url_this_page = location.href; 
    var title_this_page = document.title; 
    var url_combine_ks = url_default_ks + url_this_page; 
    var url_combine_fb = url_default_fb + url_this_page; 
    var url_combine_tw = url_default_tw_txt + document.title + url_default_tw_url + url_this_page; 
    var url_combine_band = url_default_band + encodeURI(url_this_page)+ '%0A' + encodeURI(title_this_page)+'%0A' + '&route=tistory.com'; 
    var url_combine_naver = url_default_naver + encodeURI(url_this_page) + title_default_naver + encodeURI(title_this_page);
    
    //댓글 insert ajax
    function insertcomment() {
        var sig_content = $('#sig_content').val();
        var pet_no = $('#pet_no').val();
        var sig_id = $('#sig_id').val();
        
        
        
        var commentVal = {
           "sig_content" : sig_content,
           "pet_no" : pet_no
        };
        
        	      	
        if ((sig_content == null || sig_content == "")) {
           alert("글을 작성해 주세요");
        } else {
           $.ajax({
              type : "POST",
              url : "signupadd.do",
              data : JSON.stringify(commentVal),
              contentType : "application/json",
              dataType : "json",
              success : function(msg) {
                 if (msg.code > 0) {
                    location.reload();
                 } else if(msg.code=-1) {
                    alert("이미 청원에 참가하셨습니다.");
                 }else{
                	alert("댓글 실패");
                 }
              },
              error : function() {
                 alert("로그인해야 작성 가능합니다.");
                 location.href="loginForm.do"
              }
           })
        }
      
     }
    
    //댓글 수정시 popup창
    function updateBtn(sig_no){
    	window.open("sigupdate.do?sig_no="+sig_no,"sugUpdate","width=500,height=380");
    }
    
    //댓글 삭제 ajax
    function deleteBtn(sig_no){
    	if(confirm("댓글을 정말 삭제하시겠습니까?") == true){
    		var commentVal = {
    				"sig_no" : sig_no
    				};
    				$.ajax({
    					type : "POST",
    					url : "signdelete.do",
    					data : JSON.stringify(commentVal),
    					contentType : "application/json",
    					dataType : "json",
    					success : function(msg) {
    					  	location.reload();
    					  	},
    					error : function() {
    					  	alert("통신 실패");
    					  	 }
    					   })	
			}		            		
		
				  				        
			}
    //게시물 삭제
    function button(pet_no){
  			if(confirm("게시물을 정말 삭제하시겠습니까?") == true){
  				location.href="chungdelete.do?pet_no="+pet_no;
  			}
  		}
    
    </script>

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
								<h2 class="heading">청원 상세 페이지</h2>
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
					<hr>
					<br>

					<!--이미지 및 타이틀-->
					<div>
						<h1 style="font-family: 'Jeju Gothic', serif;">${dto.pet_title }</h1>
					</div>
					<br>
					<div>
						<img
							src="${pageContext.request.contextPath}/resources/upload/images/chungwon/${dto.pet_photo}"
							width="900px">
					</div>
					<hr>

					<!--참여 인원-->
					<div class="form-control px-3 py-3" style="width: 900px">
						<div class="font"></div>
						<br>
						<div class="form-control px-3 py-3"
							style="width: 800px; font-family: 'Jeju Gothic', serif;">
							청원시작 : ${dto.pet_date }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 청원마감
							: ${dto.pet_dead }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 청원인
							: ${dto.user_id }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<hr>

						<!-- 그래프 -->
						<h4>
							청원 마감 인원 : <label style="color: #79ccab">${dto.pet_person }</label>명
						</h4>
						<h5>
							참여 인원 : <label style="color: #79ccab">${list.size() }</label>명
						</h5>
						<br>
						<h6>참여 현황 그래프</h6>
						<h4>
							<label style="color: #ff9f40;">${dto.pet_person } /
								${list.size() }</label>
						</h4>
						<p>
						<fmt:parseNumber var="percent" integerOnly="true" value="${list.size()*100/dto.pet_person }"/>
						<div class="progress position-relative" style="height: 30px;">
							<div class="progress-bar" role="progressbar"
								style="width: ${percent}%; " aria-valuenow="50"
								aria-valuemin="0" aria-valuemax="${dto.pet_person }"></div>
							<small
								class="justify-content-center d-flex position-absolute w-100"></small>
						</div>
						<hr>

						<!--공유 하기 기능-->
						<h6>공유하기</h6>
						<a href="https://twitter.com/share"> <i class="fa fa-twitter"></i>
							<img
							src="${pageContext.request.contextPath}/resources/images/personal_img/twitter.png"
							width="30px"></a> &nbsp; <a
							href="https://facebook.com/sharer.php"> <i
							class="fa fa-facebook"></i> <img
							src="${pageContext.request.contextPath}/resources/images/personal_img/facebook.png"
							width="30px"></a> &nbsp;
						<!-- 카카오 스토리 공유 버튼 -->
						<a href=""
							onclick="window.open(url_combine_ks, 'http://naver.com', 'scrollbars=no, width=600, height=600'); return false;">
							<img
							src="${pageContext.request.contextPath}/resources/images/personal_img/kakaostory.png"
							class="sharebtn_custom" style="width: 32px;">
						</a>&nbsp;

						<!-- 네이버 공유 버튼 -->
						<a href=""
							onclick="window.open(url_combine_naver, 'http://naver.com', 'scrollbars=no, width=600, height=600'); return false;">
							<img
							src="${pageContext.request.contextPath}/resources/images/personal_img/naver.png"
							class="sharebtn_custom" style="width: 32px;">
						</a>&nbsp;

						<!-- 밴드 공유 버튼 -->
						<a href=""
							onclick="window.open(url_combine_band, '', 'scrollbars=no, width=584, height=635'); return false;">
							<img
							src="${pageContext.request.contextPath}/resources/images/personal_img/band.png"
							class="sharebtn_custom" style="width: 32px;">
						</a>

					</div>
					<br>

					<!--청원 메인 내용-->

					<div style="width: 900px;">
						<h4>청원 내용</h4>
						<hr>
						<pre
							style="overflow: auto; white-space: pre-wrap; word-wrap: break-word;">${dto.pet_content }</pre>
						<!--해쉬태그-->
						<div>
							<a href="${dto.pet_link }">${dto.pet_link }</a>
						</div>
						<hr>
						<br> <br>
					</div>
					<br>

					<!--청원 댓글-->
					<form action="signupadd.do" method="post">
						<input type="hidden" value="${dto.pet_no }" name="pet_no">
						<div>
							<div>
								<h4>
									청원 동의 : [ <label style="color: #79ccab">${list.size()}</label>명]
								</h4>
							</div>
						</div>
						<input type="hidden" value="${dto.pet_no}" name="pet_no"
							id="pet_no">
						<c:if test="${dto.user_id != user.user_id }">
							<div class="form-control px-3 py-3"
								style="width: 900px; text-align: right;">
								<textarea rows="3" cols="90" placeholder="청원글을 입력하세요"
									name="sig_content" id="sig_content" class="form-control"></textarea>
								<input type="button" value="동의"
									class="btn btn-primary py-3 px-5" onclick="insertcomment();"
									style="margin-top: 10px;">
							</div>
							<br>
						</c:if>
					</form>

					<c:choose>
						<c:when test="${empty list }">
							<div>
								<h1>서명이 없습니다.</h1>
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${list }" var="sig">
								<div style="width: 900px;">
									<div>
										<hr>
										<p id="sig_id">${sig.user_id }</p>
										<p>${sig.sig_content }</p>
										<c:if test="${ sig.user_id == user.user_id}">
											<div style="text-align: right;">
												<input class="btn btn-success py-2 px-5" type="button"
													value="수정" onclick="updateBtn(${sig.sig_no});"> <input
													type="button" value="삭제" class="btn btn-success py-2 px-5"
													onclick="deleteBtn(${sig.sig_no});">

											</div>
										</c:if>

									</div>
									<hr>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>



		<div class="container">
			<div>
				<c:if test="${ dto.user_id == user.user_id}">
					<input type="button" value="청원 수정"
						class="btn btn-primary py-3 px-5"
						onclick="location.href='chungupdateform.do?pet_no=${dto.pet_no}'">
					<input type="button" value="청원 삭제"
						class="btn btn-primary py-3 px-5" onclick="button(${dto.pet_no});">
				</c:if>
				<input type="button" value="목록으로" class="btn btn-success py-2 px-5"
					onclick="location.href='chunglist.do'">

			</div>
		</div>
		<br> <br>



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