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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/personal_css/payment_page/paypage_style.css">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/images/ficon.png">
    <link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
  
     <style>
       body{
        font-family: 'Poppins','Jeju Gothic', serif;
       }

     </style>


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
              <h2 class="heading">Payment</h2>
            </div>
          </div>
        </div>
      </div>
      
    </div>
  </div>

    <div class="site-section">
        <div class="container">
            <div class="steps">
                <li class="fleft" id="stepslijs1"><b>01</b>본인 인증</li>
                <img src="${pageContext.request.contextPath}/resources/css/personal_css/payment_page/pay_icon_next.png">
                <li class="fleft" id="stepslijs1"><b>02</b>기부 신청서 작성</li>
                <img src="${pageContext.request.contextPath}/resources/css/personal_css/payment_page/pay_icon_next.png">
                <li class="on fleft" id="stepslijs2"><b>03</b>기부완료</li>
            </div>

            <div class="mt-3 rootForm" style="text-align: center; padding: 30px 10px 30px 10px;">
              <div class="resultMsg">
                <h3>귀하의 소중한 기부금이 전달되었습니다.</h3><br>
                <p style="text-align: center;">
                  기부해주셔서 감사합니다.<br>
                  귀하의 기부금은 해당 프로젝트에 많은 기여가 될 것 입니다.<br>
                  앞으로의 프로젝트 소식들은 해당 환경단체 커뮤니티에서 확인하실 수 있습니다.<br>

                  <input type="button" class="btn btn-success mt-3" style="width: 300px;" onclick="location.href='createcomm.do?pro_num=${pro_num}'" value="커뮤니티 입장하기">
                </p>
              </div>
            </div>


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