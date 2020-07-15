<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>지구력</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500|Gaegu:700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=East+Sea+Dokdo&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/aos.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.timepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/flaticon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/icomoon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/images/ficon.png">
    <style tpye="text/css">
    .font{
      font-family: 'Poppins','Jeju Gothic', serif;
    }
    </style>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
       
    function getFormatDate(date){
        var year = date.getFullYear();              //yyyy
        var month = (1 + date.getMonth());          //M
        month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
        var day = date.getDate();                   //d
        day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
        return  year + '-' + month + '-' + day;     //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
    }
    
    $(function(){
		$("#submitBtn").click(function(){
			var pet_title = $("#pet_title").val();
			var pet_content =$("#pet_content").val();
			var pet_person = $("#pet_person").val();
			var pet_dead = $("#pet_dead").val();
			var file = $("#file").val();
			
			var today = new Date();
			today = getFormatDate(today);
			
			if(pet_title == "" || pet_title == null){
				alert('청원 제목이 입력되지않았습니다.');
				return false;
			}
			
			if (pet_content==""){
			 	alert('청원 내용이 입력되지 않았습니다.');
			 	return false;
			}
			
			if(file == "" || file == null){
				alert('대표 이미지를 설정해주세요.');
				return false;
			}
			
			if(pet_person == ""){
				alert("마감 인원을 설정하지 않았습니다.");
				return false;
			}
					
			if(today == pet_dead || today > pet_dead){
				alert('마감기한은 오늘 이후여야합니다.');
				return false;
			}
	
			$("#submitForm").submit();	
			
		});
			
	});
    </script>
    
  </head>
 <body>
    <div class="font">
    
  <!----------------- START nav ----------------->  
 	  <%@ include file="../header.jsp" %>
  <!----------------- END nav ----------------->

  <!----------------- START header ----------------->    
  <div class="block-31" style="position: relative;">
    <div class="owl-carousel loop-block-31 ">
      <div class="block-30 block-30-sm item" style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_3.jpg');" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center justify-content-center">
            <div class="col-md-7 text-center">
              <h2 class="heading">청원하기</h2>
            </div>
          </div>
        </div>
      </div>
      
    </div>
  </div>
  <!----------------- END header ----------------->  


  <!----------------- START Project ----------------->
  <div class="site-section">
    <div class="container">
      <!--유의 사항-->
      <h5 style="color:red;">▶ 청원 작성시 유의 사항</h5>
      <div class="form-control px-3 py-3" style="width:1000px;"><br>
        <ul>
          <li>청원 마감일은 시작일로부터 30일입니다.</li><br>
          <li>청원 요건에 맞지 않는 청원은 관리지 판단하 게시판에 공개되지 않습니다.</li><br>
          
          <li>청원 내용의 주요 키워드를 링크란에 입력해주시면 다른 참여자들이 여러분들의 청원을 쉽게 찾는 데 도움이 됩니다.</li>
        </ul>
      </div><br><hr>

      <div class="row block-9">
        <div class="col-md-6 pr-md-5">
          <form action="chungwriteres.do" method="post" enctype="multipart/form-data" id="submitForm">
            <div class="form-group">
              <!--청원 타이틀-->
              <h4 class="font">청원 제목</h4>
              <input type="text" class="form-control px-3 py-3" placeholder="청원 제목을 입력하세요" 
              style="width:1000px;font-family: 'Jeju Gothic', serif;" name="pet_title" id="pet_title" value="${dto.pet_title }">
            </div><br>


            <!--<div class="mb-3">
              <h4 class="font"></h4>
              <input type="text" class="form-control px-3 py-1" style="width:1000px;  display: inline-block;" ><br>
              <input type="button" value="동의" class="btn btn-primary py-3 px-5">
            </div>-->


            <!--청원 상세 내용-->
            <div class="form-group">
              <h4 class="font">청원 내용</h4>
              <textarea name="pet_content" id="pet_content" cols="30" rows="17" class="form-control px-3 py-3" placeholder="청원 내용을 입력하세요"
              style="width:1000px; font-family: 'Jeju Gothic', serif;"></textarea>
            </div><br>

            <!--검색 태그-->
            <div class="form-group">
              <h4 class="font">참조 링크</h4>
              <span><input type="text"  class="form-control px-3 py-3" style="width:1000px; font-family: 'Jeju Gothic', serif;"
                placeholder="관련있는 링크를 참조하세요" name="pet_link" id="pet_link"></span><br>
            </div>

            <!--사진-->
            
            <div>
              <h4 class="font">사진 첨부</h4>
              <input type="file"  style="width:400px" placeholder="사진을 첨부하세요" name="file" id="file">
            </div><br>
            
            <!-- 청원 마감일 -->
            <div>
            	<h4>청원 마감일</h4>
            	<input type="date" style="width:400px" name="pet_dead" id="pet_dead">
            </div><br>
            
            <!-- 청원 인원 -->
            <div>
            	<h4>청원 인원</h4>
            	<input type="text" style="width:400px" name="pet_person" id="pet_person">
            </div><br><br>

            <!--완료 , 취소-->
            <div class="form-group">
              <input type="submit" value="작성 완료" class="btn btn-success py-2 px-5"  style="font-family: 'Jeju Gothic', serif;" id="submitBtn" > &nbsp;
              <input type="button" value="작성 취소" class="btn btn-primary py-2 px-5" style="font-family: 'Jeju Gothic', serif;" onclick="location.href='chunglist.do'">
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <!----------------- End Project ----------------->   
  
  


 <!----------------- START footer ----------------->    
	<%@ include file="../footer.jsp" %>
  <!----------------- END footer ----------------->  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>
</div>

  <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/jquery-migrate-3.0.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/jquery.waypoints.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/jquery.stellar.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"></script>
  
  <script src="${pageContext.request.contextPath}/resources/js/aos.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/jquery.animateNumber.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="${pageContext.request.contextPath}/resources/js/google-map.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    
  </body>
</html>