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
  <!-- iamport.payment.js -->
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

        <script type="text/javascript">

            $(function(){	
                var IMP = window.IMP;
    	        IMP.init('imp59305349');
    	        var msg;
    	        var pub_pay_uid;

    	        // ## 기부버튼 클릭시 ## //
                $(".donatebtn").click(function(){  	
                	// 유효성 체크
                    var d_name = $('#donateName').val();
                    var d_nick = $('#donateNick').val();
                    var d_addr = $('#donateAddr').val();
                    var d_tel = $('#donateTel').val();
                    var project_title = $('#project_title').html();
                    
                        if(d_name==""||d_nick==""||d_addr==""||d_tel==""){
                            alert("기부자 정보를 모두 입력해주세요.");
                            return false;
                        }

                        var payVal = $('input[name="rdo_pay"]:checked').val();
                    
                    if(payVal==null){
                        alert("결제수단을 입력해주세요.");
                        return false;
                    }
                    
                    var money = $('input[name="doMoney"]').val();
                    if(money==""){
                        alert("기부 금액을 입력하세요.");
                        return false;                     
                    }

                    if($("input:checkbox[name=complete_yn]").is(":checked") == false){
                        alert("약관에 동의해주세요.");
                        return false;   
                    }
                    
					var pro_num = '<c:out value="${project.pro_num}"/>';
					var user_id = '<c:out value="${user.user_id}"/>';
					var overlap = '<c:out value="${overlap}"/>';
					
					var funded_money = money;      
                    
                    var param = {
                    		"pro_num":pro_num,
                    		"user_id":user_id,
                    		"funded_money":funded_money
                    };

                    // ## 결제 API                  
                    IMP.request_pay({
                        pg : 'html5_inicis', // version 1.1.0부터 지원.
                        pay_method : payVal,
                        merchant_uid : 'merchant_' + new Date().getTime(),
                        name : project_title,
                        amount : money, //판매 가격
                        buyer_email : "",
                        buyer_name : d_name,
                        buyer_tel : d_addr,
                        buyer_addr : d_tel
                    }, function(rsp) {
                        if ( rsp.success ) {
                            var result = '결제가 완료되었습니다.';
                            result += '고유ID : ' + rsp.imp_uid;
                            result += '상점 거래ID : ' + rsp.merchant_uid;
                            result += '결제 금액 : ' + rsp.paid_amount;
                            result += '카드 승인번호 : ' + rsp.apply_num;
                            
                            // 결제 완료 시 update 와 insert
                            $.ajax({
                				type:"post",
                				url:"fundingprocess.do?overlap="+overlap,
                				data:JSON.stringify(param),
                				contentType:"application/json",
                				dataType:"json",
                				success:function(msg){
                					if(msg.check==true){
                						//insert update 성공시
                						alert(result);
                						location.href='payresult.do?pro_num='+pro_num;
                						
                					}else{
                						alert("fundingprocess.do error");
                						location.href='paypage.do?pro_num='+pro_num;
                						
                					}
                					
                				},
                				error:function(){
                					alert("AJAX : 통신실패");
                					location.href='paypage.do?pro_num='+pro_num;
                				}
                				
                			});
                            
                            
                            
                        } else {
                            var msg = '결제에 실패하였습니다.';
                            msg += '에러내용 : ' + rsp.error_msg;
	                       alert(msg);
	                       location.href='paypage.do?pro_num='+pro_num;
                        }
                    });
                    
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
                <li class="on fleft" id="stepslijs1"><b>02</b>기부 신청서 작성</li>
                <img src="${pageContext.request.contextPath}/resources/css/personal_css/payment_page/pay_icon_next.png">
                <li class="fleft" id="stepslijs2"><b>03</b>기부완료</li>
            </div>

            <div class="row mt-3 rootForm">
                <!--projectinfo-->
                <div class="col-md-4">
                    <div class="projectinfo">
                        <div class="card fundraise-item mb-5">
                            <a href="" style="margin: 0px auto; margin-top: 10px;" ><img class="card-img-top" src="${pageContext.request.contextPath}/resources/upload/images/project/${project.pro_image}" style="width: 270px; height: 250px;" alt="Image placeholder"></a>
                            <div class="card-body">
                              <h3 class="card-title"><a href="#" id="project_title">${project.pro_title }</a></h3>
                              <span class="donation-time mb-3 d-block">
                              			    <b>${project.user_id }</b> 
       										<fmt:formatDate value="${project.pro_write_date}" pattern="yyyy/MM/dd"/>
       						</span>
                              <div class="progress custom-progress-success">
			                     <fmt:parseNumber var="percent" integerOnly="true" value="${project.pro_nowmoney*100/project.pro_goalmoney }"/>
			                     <div class="progress-bar bg-success" role="progressbar" style="width: ${percent }%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
			                  </div>
			                  <span class="fund-raised d-block">${project.pro_nowmoney} of ${project.pro_goalmoney }</span>
                            </div>
                        </div>
                    </div>                
                </div>


                <div class="col-md-8">
                    <div class="donateForm">
                        <h4>기부자 정보</h4>
                        <form>
                            <input type="text" class="form-control" id="donateName" readonly="readonly" value="${user.user_name }"><br>
                            <input type="text" class="form-control" id="donateNick" readonly="readonly" value="${user.user_nick }"><br>
                            <input type="text" class="form-control" id="donateAddr"  value="${user.user_addr }"><br>
                            <input type="tel" class="form-control" id="donateTel" value="${user.user_phone }"><br>
                        </form>
        
                        <div class="mt-4">
                            <h4>결제수단</h4>
                        </div>
        
                        <div class="conchk">
                            <div class="con-row">
                                <label><input type="radio" name="rdo_pay" value="card">신용카드</label>
                            </div>
                            <div class="con-row">
                                <label><input type="radio" name="rdo_pay" value="trans">실시간 계좌이체</label>
                            </div>
                            <div class="con-row">
                                <label><input type="radio" name="rdo_pay" value="phone">휴대폰 소액결제</label>
                            </div>
                            <div class="con-row">
                                <label><input type="radio" name="rdo_pay" value="vbank">무통장 입금</label>
                            </div> 
                        </div>

                        <div class="donatemoney mt-3">
                            <h4>기부 금액</h4>
                            <input type="number" name="doMoney" min="100" placeholder="기부금액을 입력하세요">
                        </div>
                        <hr>
                        <div class="donatechk">
                            <input type="checkbox" name="complete_yn"> 알맞은 기부자 정보와 결제수단을 입력하셨습니까? <br>
                            <span style="color: #999999;"> 확인 후, 밑의 [기부하기] 버튼을 눌러주시면, 바로 결제 기부하실 수 있습니다. </span>
                        </div>
                        <div class="donatebtnform">
                            <input type="button" class="btn btn-success donatebtn"  value="기부하기">
                        </div>
        
                    <div>
        
        
            </div>
        </div>

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