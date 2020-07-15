<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html lang="en">
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>


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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/personal_css/togather.css">
    <link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/images/ficon.png">
    <style>
      body{
        font-family: 'Poppins','Jeju Gothic', serif;
      }
    </style>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script type="text/javascript">
	
	
	</script>
  </head>
  <body>
	


	
	
	
	
	
    
     <!----------------- START nav ----------------->  
 <%@ include file="../header.jsp" %>
  <!----------------- END nav ----------------->
  
  <div class="block-31" style="position: relative;">
    <div class="owl-carousel loop-block-31 ">
      <div class="block-30 block-30-sm item" style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_3.jpg');" data-stellar-background-ratio="0.5">
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
            
            <!------------------------------------------ 솔지 수정 부분----------------------------->
            <!-- 
            		수정사항 
            		1. 디테일 폼 테이블 형식으로 출력
            		2. 중앙정렬
            		3. 신청하기 및 취소하기 버튼 위치 변경
            		4. 유저 이미지 연동 (user_pic 연동)
            		5. dm하기 연동
             -->
            <div style="text-align: center;">
                <h3 style="margin-bottom: 40px;">${dto.tog_title} </h3>
                <p class="mb-2"><img src="${pageContext.request.contextPath}/resources/upload/images/together/${dto.tog_image}" alt="" style="width:300px;" class="img-fluid"></p>                                       
                    <div class="mb-3">      
                           <c:if test="${dto.user_id ne user.user_id }">
								<c:if test="${empty res }">
	                                 <input type="button" value="신청하기"   class="btn btn-success" style="width: 300px;"
	                                    onclick="location.href='together_apply.do?tog_no=${dto.tog_no}&user_id=${user.user_id }'">
	                              </c:if>
	                              <c:if test="${!empty res }">
	                                 <input type="button" value="신청취소"   class="btn btn-danger" style="width: 300px; color:white;"
	                                    onclick="location.href='together_applyCancel.do?tog_no=${dto.tog_no}&user_id=${user.user_id }'">
	                              </c:if>
	                     	</c:if>	
      				</div>
                 
                  <div class="comment-form-wrap">
                    <form class="p-5 bg-light">
	                    <table class="table" style="width:700px; margin:0px auto; text-align: left;">
	             
	                    	<tr>
	                    		<th style="width:150px;">카테고리</th>
	                    		<td><b>${dto.tog_category }</b></td>
	                    	</tr>
	                     	<tr>
	                    		<th>마감기한 :</th>
	                    		<td><b><fmt:formatDate value="${dto.tog_dead}" pattern='yyyy/MM/dd'/></b></td>
	                    	</tr>        
	                       	<tr>
	                    		<th>작성자 :</th>
	                    		<td><b>${dto.user_id }</b></td>
	                    	</tr> 
	                    	
	                    	<tr>
	                    		<th>내용</th>   
	                    		<td>${dto.tog_content }</td>           		
	                    	</tr>  
	                    </table>
                      </form>
                  </div>
           
                      
	              <div class="form-group mt-2" style="text-align: right;">
			          <c:if test="${!empty user }">	
					              <c:if test="${dto.user_id eq user.user_id }">
											<input type="button" value="수정" class="btn btn-primary" onclick="location.href='together_updateform.do?tog_no=${dto.tog_no}'">
											<input type="button" value="삭제" class="btn btn-primary" onclick="location.href='together_delete.do?tog_no=${dto.tog_no}'">					
								</c:if>
				       </c:if>
	                		<input type="button" value="목록" class="btn btn-primary" onclick="location.href='together.do'">		
		           </div>
               </div>
                
              <div class="comment-form-wrap pt-5" >
                    <form action="#" class="p-5 bg-light">
                    <div class="row">
                    	<div class="col-md-4">
		                      <div class="form-group" style="text-align:center;">
		                        <div class="vcard bio person-donate" style="text-align: center;">
		                          <img src="${pageContext.request.contextPath}/resources/upload/images/user/${writeUser.user_pic}" alt="Image placeholder" class="img-fluid">
		                        </div>
		                        <c:if test="${!empty user }">
			                      	<c:if test="${user.user_id ne writeUser.user_id}">
			                        <input type="button" onclick="location.href='dmlistadd.do?add=${writeUser.user_id}'" value="DM하기" class="btn btn-primary" >
			                        </c:if>
		                        </c:if>
		                      </div>
	                 	</div> 
	                 	<div class="col-md-8">
	                      <div class="form-group"  style="width: 85%; display: inline-block;">
	                        <textarea name="" id="message" cols="30" rows="5" class="form-control" readonly>${dto.tog_ps}</textarea>
	                      </div>	                 	             	
	                 	</div>   
                      </div>
                    </form>
                  </div>
                  
                
              </div> <!-- .col-md-8 -->
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