<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>지구력</title>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/personal_css/main.css">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/images/ficon.png">
    <link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
    
    <!-- CKEditor-->
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

		// ## 수정버튼 클릭시 ## //
		$("#submitBtn").click(function(){
			var pro_title = $("#pro_title").val();
			var pro_start_date = $("#pro_start_date").val();
			var pro_due_date = $("#pro_due_date").val();
			var pro_goalmoney = $("#pro_goalmoney").val();
			var file = $("#file").val();

			var today = new Date();
			today = getFormatDate(today);
			
			// ## 유효성 체크
			
			if(pro_title == "" || pro_title == null){
				alert('프로젝트 제목을 입력해주세요.');
				return false;
			}
			
			if(pro_start_date > pro_due_date){
				alert('모금 시작 날짜가 모금 마감 날짜보다 늦을 수 없습니다.');
				return false;
			}

			
			if(pro_start_date == pro_due_date){
				alert('모금 시작 날짜와 모금 마감 날짜가 같을 수 없습니다.');
				return false;
			}
			
			if(pro_goalmoney < 1000){
				alert('모금 금액의 최소 금액은 1000원 입니다.');
				return false;
			}
			
	
			var ckeditor = CKEDITOR.instances['p_content']; 
			if (ckeditor.getData()==""){
			 	alert('내용을 입력 하세요');
			 	ckeditor.focus();
			 	return false;
			}

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
      <div class="block-30 block-30-sm item" style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_2.jpg');" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center justify-content-center">
            <div class="col-md-7 text-center">
              <h2 class="heading">Project</h2>
              <p class="lead">
                Making someone happy is like spraying perfume.<br>
                 Because when you spray it, it leaves a few drops on me.
                 <br>ㅡ Talmud ㅡ</p>
            </div>
          </div>
        </div>
      </div>
      
    </div>
  </div>
  
  
  <div class="site-section">
    <div class="container">
    <form action="projectupdate.do" method="post" id="submitForm" enctype="multipart/form-data">
    	<input type="hidden" value="${project.pro_num }" name="pro_num">
    	<input type="hidden" value="${project.pro_nowmoney }" name="pro_nowmoney">
    	<input type="hidden" value="${project.pro_success}" name="pro_success">
    	<input type="hidden" value="${user.user_id }" name="user_id">
	      <table class="table">
	        <div style="text-align: center; padding-bottom: 10px;">
	          <h1>프로젝트 작성</h1>
	        </div>
	          <tr>
	            <th style="width: 230px;">제목</th>
	            <td><input type="text" class="form-control" name="pro_title" id="pro_title" value="${project.pro_title }"></td>
	          </tr>
	          <tr>
	            <th style="width: 230px;">모금기한</th>
	            
	            <td><input type="date" class="form-control" id="pro_start_date" value="<fmt:formatDate value='${project.pro_start_date}' pattern='yyyy-MM-dd'/>" name="pro_start_date" style="width: 200px; display: inline-block;" >~<input type="date" class="form-control" name="pro_due_date" value="<fmt:formatDate value='${project.pro_due_date}' pattern='yyyy-MM-dd'/>" id="pro_due_date" style="width: 200px; display: inline-block;">
	            </td>
	          </tr>  
	          <tr>
	            <th style="width: 230px;">모금 금액</th>
	            <td><input type="number" value="${project.pro_goalmoney }" min="1000" max="100000000"  class="form-control" name="pro_goalmoney" id="pro_goalmoney" style="width: 250px;"></td> 
	          </tr>
	          <tr>
	          	<th style="width: 230px;">현재 대표이미지</th>
	          	<td>
	          		<input type="hidden" name="pro_image" value="${project.pro_image}"/>
	          		<img src="${pageContext.request.contextPath}/resources/upload/images/project/${project.pro_image}" style="width: 330px; height: 300px;" alt="Image placeholder">
	          	</td>
	          </tr>
	          <tr>
	            <th style="width: 230px;">대표이미지</th>
	            <td><input type="file" name="file" id="file" class="form-control" style="width: 250px; display: inline-block;"></td> 
	          </tr>     
	          <tr>
	            <th style="width: 50px;" colspan="2">내용</th>
	          </tr>  
	          <tr>
	            <td colspan="2">
	              <textarea class="form-control" id="p_content" name="pro_detail">${project.pro_detail }</textarea>
	              <script type="text/javascript">
	                  CKEDITOR.replace('p_content'
	                                  , {
	                	  height: 500,
	                	  filebrowserUploadUrl:'ckeditorProjectFileupload.do'
	                                  });
	              </script>
	            </td>
	          </tr>
	          <tr>
	            <td colspan="2" style="text-align: right;">
	              <input type="button" class="btn btn-success" value="입력" id="submitBtn" style="display: inline-block;">
	              <input type="button" value="취소" class="btn btn-success" onclick="location.href='projectdetail.do?pro_num=${project.pro_num}'" style="display: inline-block;">
	            </td>
	          </tr> 
	      </table>
    </form>

            
    </div>
  </div>

 <!----------------- START footer ----------------->    
  <!-- START footer -->
 <%@ include file="../footer.jsp" %>
  <!-- END footer -->
  <!----------------- END footer ----------------->  

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