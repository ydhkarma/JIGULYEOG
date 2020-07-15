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
    <style>
      body{
        font-family: 'Poppins','Jeju Gothic', serif;
      }
    </style>
    
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <!-- CKEditor-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/personal_js/ckeditor/ckeditor.js"></script> 
    <script type="text/javascript">
    	$(function(){
    		$("#submitBtn").click(function(){
    			var notice_title = $("#notice_title").val();
    			
    			if(notice_title == "" || notice_title == null){
    				alert("제목을 입력해주세요.");
    				
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

  <!----------------- START header ----------------->    
  <div class="block-31" style="position: relative;">
    <div class="owl-carousel loop-block-31 ">
      <div class="block-30 block-30-sm item" style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_2.jpg');" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center justify-content-center">
            <div class="col-md-7 text-center">
              <h2 class="heading">Project Notice</h2>
            </div>
          </div>
        </div>
      </div>
      
    </div>
  </div>
  <!----------------- END header ----------------->  


  <div class="site-section">
    <div class="container">
    	<form action="commnoticewrite.do" method="post" id="submitForm" >
    		<input type="hidden" value="${commNo }" name="com_num"/>
    		<input type="hidden" value="${pro_num }" name="pro_num"/>
		      <table class="table">
		        <div style="text-align: center; padding-bottom: 10px;">
		          <h1>공지사항</h1>
		        </div>
		          <tr>
		            <th style="width: 170px;">제목</th>
		            <td><input type="text" class="form-control" id="notice_title" name="notice_title"></td>
		          </tr>
		          <tr>
		            <th style="width: 50px;" colspan="2">내용</th>
		          </tr>  
		          <tr>
		            <td colspan="2">
		              <textarea class="form-control" id="p_content" name="notice_content"></textarea>
		              <script type="text/javascript">
		              CKEDITOR.replace('p_content',
		            		  {
						    	  height: 500,
						    	  filebrowserUploadUrl:'ckeditorNoticeFileupload.do'
						                      });
		              </script>
		            </td>
		          </tr>
		          <tr>
		            <td colspan="2" style="text-align: right;">
		              <input type="button" class="btn btn-success" id="submitBtn" value="입력" style="display: inline-block;">
		              <input type="button" value="취소" class="btn btn-success" style="display: inline-block;" onclick="location.href='community.do?pro_num=${pro_num }'">
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
  <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    
  </body>
</html>