<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Givig - Non-profit Free Bootstrap 4 Template by Colorlib</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500|Gaegu:700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=East+Sea+Dokdo&display=swap" rel="stylesheet">
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
    <link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/images/ficon.png">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/personal_js/ckeditor/ckeditor.js"></script>
    <style>
      body{
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
    	return  year + '-' + month + '-' + day;
	}
	
	$(function(){
		$("#submitBtn").click(function(){
			var tog_title = $("#tog_title").val();
			var tog_dead = $("#tog_dead").val();
			var tog_category = $("#tog_category").val();
			var file = $("#file").val();
			
			var today = new Date();
			today = getFormatDate(today);
			
			
			if(tog_title == "" || tog_title == null){
				alert('프로젝트 제목을 입력해주세요.');
				return false;
			}
			
					
			if(today == tog_dead){
				alert('마감기한은 오늘 이후여야합니다.');
				return false;
			}
			
			if(today > tog_dead){
				alert('마감기한은 오늘 이후여야합니다.');
				return false;
			}
			
			var ckeditor = CKEDITOR.instances['tog_content']; 
			if (ckeditor.getData()==""){
			 	alert('내용을 입력 하세요');
			 	ckeditor.focus();
			 	return false;
			}
			
			/*
			if(tog_category == null){
				alert('카테고리를 선택해주세요.');
				return false;
			}*/
			
			
			$("#submitForm").submit();	
			
		});
		
		
	});
	</script>
    
  </head>
  <body>
    
          <!----------------- START nav ----------------->  
 <%@ include file="../header.jsp" %>
  <!----------------- END nav ----------------->
  
  <div class="block-31" style="position: relative;">
    <div class="owl-carousel loop-block-31 ">
      <div class="block-30 block-30-sm item" style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_3.jpg');"data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center justify-content-center">
            <div class="col-md-7 text-center">
              <h2 class="heading">함께해요</h2>
            </div>
          </div>
        </div>
      </div>
      
    </div>
  </div>
  
  
  <div id="blog" class="site-section">
    <div class="container">
            
            <div class="row">

              <div class="col-md-10">
                  
                  <!-- END comment-list -->
                  
                  <div class="comment-form-wrap pt-5">
                    <h3 >함께해요 수정하기</h3>
                    <form action="together_update.do" class="p-5 bg-light" method="post" enctype="multipart/form-data"`>
                    	<input type="hidden" name="tog_no" value="${dto.tog_no}">
                      <table class="table">
                  		<tr>
                  			<th style="width:200px;">제목</th>
                  			<td><input type="text" class="form-control" name="tog_title" id="tog_title" value="${dto.tog_title }"></td>
                  		</tr>
                  		<tr>
                  			<th style="width:200px">카테고리</th>
                  			<td>
	                  			<select name="tog_category" required style="width:100px;" >
	                      			<option value="모금">모금</option>
					                <option value="나눔">나눔</option>
					                <option value="봉사">봉사</option>
								</select>
							</td>
                  		</tr>
                  		<tr>
                  			<th style="width:200px;">현재 이미지</th>
                  			<td>
                  				<input type="hidden" name="tog_image" value="${dto.tog_image}"/>
                  				<img src="${pageContext.request.contextPath}/resources/upload/images/together/${dto.tog_image}" style="width: 50px; height: 50px;" alt="Image placeholder">
                  			</td>
                  		</tr>
                  		<tr>
                  			<th style="width:200px;">이미지</th>
                  			<td><input type="file" name="file" id="file" class="form-control" style="width: 250px; display: inline-block;"></td>
                  		</tr>
                  		<tr>
                  			<th style="width:200px;">기한</th>
                  			<td><input type="date" class="form-control" id="tog_dead" name="tog_dead" style="width: 200px; display: inline-block;" value="<fmt:formatDate value='${dto.tog_dead}' pattern='yyyy-MM-dd'/>"></td>
                  		</tr>
                  		<tr>
                  			<th style="width:200px;">내용</th>
                  			<td>
                  				<textarea class="form-control" id="tog_content" name="tog_content">${dto.tog_content }</textarea>
					            <script type="text/javascript">
					                  CKEDITOR.replace('tog_content'
					                                  , {
					                	  height: 500,
					                	  filebrowserUploadUrl:'togetherDetailFile.do'
					                                  });
					            </script>
			             	</td>
                  		</tr>
                  		<tr>
                  			<th style="width:200px;">한마디</th>
                  			<td><textarea name="tog_ps" id="tog_ps" cols="5" rows="5" class="form-control">${dto.tog_ps }</textarea></td>
                  		</tr>
                  		<tr>
                  			<td colspan="2" style="text-align: right;">
                  				<a href="together_detail.do?tog_no=${dto.tog_no }"><input type="button"  value="취소" class="btn py-3 px-5  btn-primary"></a>
                        		<button id="submitBtn" class="btn py-3 px-5  btn-primary">작성</button>
                  			</td>
                  		</tr>
                  	</table>

                    </form>
                    
                  </div>
                  
              </div> <!-- .col-md-8 -->
              

            </div>

            
          </div>
  </div>
  
  <!-- START footer -->
 <%@ include file="../footer.jsp" %>
  <!-- END footer -->

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


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