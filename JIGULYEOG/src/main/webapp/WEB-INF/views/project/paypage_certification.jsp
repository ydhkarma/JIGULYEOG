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

  <!-- jQuery -->
  	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<script type="text/javascript">
		$(function(){
			// ## sms 전송을 위해 User Default Phone Number Setting (필수) ## //
			var userTel = '<c:out value="${user.user_phone}"/>';	
			var code;
			$("#rphone").val(userTel);
			
			// ## 문자 전송 버튼 클릭 ## //
			$("#sendBtn").click(function(){
				// ## 번호 유효성 검사
				var rphone = $("#rphone").val();
				if(rphone==""){alert("번호를 입력해주세요.");return;}
				if(!isMobile(rphone)){alert("알맞은 핸드폰번호 형식이 아닙니다.");return;}
				else{
					rphone = formatMobile(rphone);
					var param = {
							"rphone":rphone,
							"sphone1":"010",
							"sphone2":"4854",
							"sphone3":"7484"
					};
					
					$.ajax({
						type:"POST",
						url:"sendSMS.do",
						data:JSON.stringify(param),
						contentType:"application/json",
						dataType:"json",
						success:function(msg){
							if(msg.check==true){
								alert("인증번호를 발송했습니다.");
								code = msg.code;
								$("#certifi_form").css("display","block");
							}
							
						},
						error:function(){
							alert("AJAX : 통신실패");
						}
						
					});
					
				}
			});
			
			
			// ## 인증 버튼 클릭 ## //
			$("#certifiBtn").click(function(){
				var inputCode = $("#certifiInput").val();
				if(inputCode==""){alert("내용을 입력해주세요.");return;}
				
				// ## 사용자가 입력한 코드와 인증코드 비교 ##//
				if(inputCode==code){
					var str = "인증되었습니다.";
					$("#certifi_result").html(str);
					$("#certifi_result").css({"display":"block","color":"#0030DB"});
					$("#nextBtn").css("display","block");
					$("#sendBtn").prop("disabled", true);
					
				}else{
					var str = "인증코드가 올바르지 않습니다.";
					$("#certifi_result").html(str);
					$("#certifi_result").css({"display":"block","color":"#DB0000"});
				}
				
			});
			
			
		});
		
		// ## 핸드폰번호 형식 체크 ## //
		function isMobile(phoneNum) {
			var regExp =/(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/;
			var myArray;
			if(regExp.test(phoneNum)){
				myArray = regExp.exec(phoneNum);
				return true;
			} else {
				return false; }
			}

		
		// ## 핸드폰 번호 포맷으로 변환 ## //
		function formatMobile(phoneNum) {
			if(isMobile(phoneNum)) {
				var rtnNum;
				var regExp =/(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/;
				var myArray;
				if(regExp.test(phoneNum)){
					myArray = regExp.exec(phoneNum);
					rtnNum = myArray[1]+'-'+myArray[2]+'-'+myArray[3];
					return rtnNum; }
				else {
					return phoneNum; }
				} else {
					return phoneNum; }
			}

	
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
                <li class="on fleft" id="stepslijs1"><b>01</b>본인 인증</li>
                <img src="${pageContext.request.contextPath}/resources/css/personal_css/payment_page/pay_icon_next.png">
                <li class="fleft" id="stepslijs1"><b>02</b>기부 신청서 작성</li>
                <img src="${pageContext.request.contextPath}/resources/css/personal_css/payment_page/pay_icon_next.png">
                <li class="fleft" id="stepslijs2"><b>03</b>기부완료</li>
            </div>

            <div class="row mt-3 rootForm">
                <!--projectinfo-->
                <div class="p-5" style="width: 98%; text-align: center;">
                    <img src="${pageContext.request.contextPath}/resources/images/personal_img/security_ico.png" width="170px;"/><br>
                    <div class="p-3">
                      <h3 style="color: #28a745;">잠깐!</h3>
                      <p>기부를 진행하기 위해서는 <span style="font-weight: bold;">핸드폰 인증</span>이 필요합니다!<br> 아래 핸드폰 번호를 확인하시고, 문자인증을 진행 해주세요.<br>
                      	<i style="color: #b3b3b3; font-size: 1em;">단, 핸드폰 형식은 하이픈(-)빼고 입력해주세요.<br>(예를 들어, 010-1234-1234라면, 01012341234 라고 작성.)</i></p>
                      	  <input type="hidden" name="smsType" id ="smsType" value="S">
	                      <input type="tel" id="rphone" class="form-control" style="width: 250px; display: inline-block;"/>
	                      <input type="button" value="전송" id="sendBtn" class="btn btn-success" style="display: inline-block;"/>
                      <p style="margin-top:10px;">
                      <div id="certifi_form" style="display: none;">
                      	<input type="text" placeholder="인증번호 입력" id="certifiInput" class="form-control" style="width: 250px; display: inline-block;"/>
                      	<input type="button" value="인증" id="certifiBtn" class="btn btn-primary" style="display: inline-block;"/><br>                      
                      </div>
                      	<span style="color: #DB0000;display: none;" id="certifi_result"></span>
                      </p>
                    </div>
                    <div id="nextBtn" style="display:none;">
	                    <input type="button" class="btn btn-primary" onclick="location.href='payment.do?pro_num=${pro_num}&overlap=${overlap }'" value="다음 단계">                    
                    </div>

                </div>
        

            </div>

        </div>

    </div>

    <!-- 
	    <form method="post" name="smsForm" id="smsForm" action="sendSMS.do">
	        <span>
	          <input type="text" name="smsType" value="S">단문(SMS)</span>
	        <span>
	        <br />
	        <br /> 전송메세지
	        <textarea name="msg" cols="30" rows="10" style="width:455px;">내용입력</textarea>
	        <br />
	        <br />
	        <p>단문(SMS) : 최대 90byte까지 전송할 수 있으며, 잔여건수 1건이 차감됩니다.
	            <br /> 장문(LMS) : 한번에 최대 2,000byte까지 전송할 수 있으며 1회 발송당 잔여건수 3건이 차감됩니다.
	        </p>
	        <br />받는 번호
	        <input type="text" name="rphone" value="011-111-1111"> 예) 011-011-111 , '-' 포함해서 입력.
	        <br /> 보내는 번호
	        <input type="hidden" name="sphone1" value="010">
	        <input type="hidden" name="sphone2" value="4854">
	        <input type="hidden" name="sphone3" value="7484">
	        <span id="sendPhoneList"></span>
	        <br />return url
	        <input type="text" name="returnurl" maxlength="64" value="">
	        <br /> test flag
	        <input type="text" name="testflag" maxlength="1"> 예) 테스트시: Y
	        <br>
	        <input type="button" id="sendBtn" value="전송">
	        <br/>이통사 정책에 따라 발신번호와 수신번호가 같은 경우 발송되지 않습니다.
	    </form>
-->
 

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