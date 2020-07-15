<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/personal_css/login.css">
    <link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/images/ficon.png">
    <style>
      body{
        font-family: 'Poppins','Jeju Gothic', serif;
      }
    </style>
    
	
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script type="text/javascript">
		function login(){
			var user_id = $("#user_id").val().trim();
			var user_pw = $("#user_pw").val().trim();
			console.log(user_id+"/"+user_pw);
			var loginVal={
				"user_id":user_id,
				"user_pw":user_pw
			};
			if(user_id == null || user_id=="" || user_pw==null || user_pw==""){
				alert("ID&PW를 입력해주세요.");
			}else{
				$.ajax({
					type:"post",
					url:"login.do",
					data: JSON.stringify(loginVal),
					contentType:"application/json",
					dataType:"json",
					success:function(msg){
						if(msg.check == true){
							alert($("#user_id").val().trim()+" 님 환영합니다.");
							location.href="index.do";
						}else{
							alert("입력하신 ID 혹은 PW가 일치하지 않습니다.");
						}
					},
					error:function(){
						alert("통신실패");
					}
				});
			}
		}
	</script>
	
  </head>
  <body>
    
    <!----------------- START nav ----------------->  
 	<%@ include file="../header.jsp" %>
    <!-- END nav -->
  
  <div class="block-31" style="position: relative;">
    <div class="owl-carousel loop-block-31 ">
      <div class="block-30 block-30-sm item" style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_2.jpg');" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center justify-content-center">
            <div class="col-md-7 text-center">
              <h2 class="heading">Login</h2>
            </div>
          </div>
        </div>
      </div>
      
    </div>
  </div>

  <div class="site-section">
    <div class="container" style="text-align: center;">
      <div class="row block-9">
        <div class="col-md-6 pr-md-5" style="margin: 0 auto;">
          <div>
            <div class="form-group loginbox">
              <input type="text" class="form-control px-3 py-3" placeholder="ID" style="width: 500px;" name="user_id" id="user_id">
            </div>
            <div class="form-group loginbox">
              <input type="password" class="form-control px-3 py-3" placeholder="Password" style="width: 500px;" name="user_pw" id="user_pw">
            </div>
            <div class="form-group">
              <input type="button" value="Login" class="btn btn-success py-3 px-5 btn_success" style="width: 500px;" onclick="login();">
            </div>
            <div class="form-group">
              <input type="button" value="Join" class="btn py-3 px-5 joinbtn" style="width: 500px;" onclick="location.href='registForm.do'">
            </div>
            <div class="form-group">
              <a href="searchForm.do">ID/PW 찾기</a>
            </div>
          </div>

          <div class="sns" >
            <a href="https://kauth.kakao.com/oauth/authorize?client_id=132242b58385527b63dadb95d6f63f35&redirect_uri=http://sclass.iptime.org:8787/JIGULYEOG/kakaoLogin.do&response_type=code">
            	<img src="${pageContext.request.contextPath}/resources/images/kakao_login_large_narrow.png" style="width: 230px; height: 50px;">
            </a>
            <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
            <div id="naver_id_login">
            <a href="${url }"><img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"></a>
            <!-- <a href="${url }"><img src="${pageContext.request.contextPath}/resources/images/sns_naver.png"></a> -->
            </div>
            <!--<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
            <script type="text/javascript">
									var naverLogin = new naver.LoginWithNaverId(
										{
											clientId: "mAoH_4XKbJ8MMXqzYF59",
											callbackUrl: "http://localhost:8787/jigulyeog/member/callback.jsp",
											isPopup: false, 
											loginButton: {color: "green", type: 3, height: 50} /* 로그인 버튼의 타입을 지정 */
										}
									);
									
									naverLogin.init();
									
								</script>
            <script type="text/javascript">
 		var naver_id_login = new naver_id_login("mAoH_4XKbJ8MMXqzYF59", "http://localhost:8787/jigulyeog/callback.do");	// Client ID, CallBack URL 삽입
											// 단 'localhost'가 포함된 CallBack URL
 		var state = naver_id_login.getUniqState();
		
 		naver_id_login.setButton("green", 3, 50);
 		naver_id_login.setDomain("http://localhost:8787/jigulyeog/naverLogin.do");	//  URL
 		naver_id_login.setState(state);
 		naver_id_login.setPopup();
 		naver_id_login.init_naver_id_login();
	</script>-->
          </div>
        </div>

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