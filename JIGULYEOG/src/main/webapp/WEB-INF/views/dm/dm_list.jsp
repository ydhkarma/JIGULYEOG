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
    <link rel="icon" href="${pageContext.request.contextPath}/resources/images/ficon.png">

     <!-- fontawesome CSS -->
     <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/personal_css/fontawesome/css/all.min.css">
     <link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/personal_css/chat_style/bubble_default.css" />
     <style>
       body{
        font-family: 'Poppins','Jeju Gothic', serif;
       }
       .dropbtn{
            background-color: white;
            color: black;
            }
 
     </style>
  </head>
  <body>
   <!----------------- START nav ----------------->
	<%@ include file="../header.jsp"%>
	<!----------------- END nav ----------------->
  
  <div class="block-31" style="position: relative;">
    <div class="owl-carousel loop-block-31 ">
      <div class="block-30 block-30-sm item" style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_2.jpg');" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center justify-content-center">
            <div class="col-md-7 text-center">
              <h2 class="heading">DM List</h2>
            </div>
          </div>
        </div>
      </div>
      
    </div>
  </div>
  
  
  <div class="site-section">
    <div class="common_style" style="padding-bottom:30px;">
        <!-- why section -->
        <div id="contentWrap">
            <div id="contentCover">
                <div class="row">
                    <div class="col-md-3">
                        <div id="roomWrap">
                            <div id="roomList">
                                <div id="roomHeader">DM 리스트</div>
                                <input type="hidden" value="${user.user_id }" name="user_id" id="user_id"/>
                                <div id="roomSelect">
                                
                                	<c:choose>
                                		<c:when test="${!empty DMList }">
                                			<c:forEach items="${DMList }" var="DM">
				                                    <div class="roomEl">
				                                        <div class="dropdown">
				                                            <button class="btn dropdown-toggle dropbtn"  style="background-color: #00000000; color: black; border: none; "data-toggle="dropdown">
				                                                ${DM.dm_id }
				                                            </button>
				                                            <div class="dropdown-menu">
				                                                <a class="dropdown-item chatStart" onclick="viewChat('${user.user_id }','${DM.dm_id}','${DM.dm_key }');" name="${DM.dm_id}" id="${DM.dm_key }">대화하기</a>
				                                                <a class="dropdown-item" href="deleteDM.do?user_id=${user.user_id }&dm_id=${DM.dm_id }&dm_key=${DM.dm_key }">삭제하기</a>
				                                            </div>
				                                        </div>
				                                    </div>
                                			</c:forEach>
                                		</c:when>
                                		<c:otherwise>
                                			<div class="roomEl">
                                				친구가 없습니다.
                                			</div>
                                		</c:otherwise>
                                	</c:choose>
                                    
                                  
                                                                       
                                </div>
                            </div>
                        </div>


                    </div>
                    <div class="col-md-9">
                        <div id="noWrap" style="height: 500px; width:700px; text-align: center; padding-top: 50px; border: none;">
                            		채팅방을 선택해주세요.
                        </div>

						<!-- DM창-->
                        <div id="chatWrap" style="display:none;">
                            <div id="chatHeader">chatHeader</div>
                            <div id="chatLog">
                              
                            </div>
                            <form id="chatForm" onsubmit="return false">
                                <input type="text" autocomplete="off" size="30" id="messageContent" placeholder="메시지를 입력하세요">
                                <input type="button" id="sendMessage" value="보내기">
                            </form>
                        </div>
                        
                    </div>

                </div>
             
            </div>
        </div>
      <!-- end why section -->
    </div>

  </div>

  
  	<!----------------- START footer ----------------->
	<%@ include file="../footer.jsp"%>
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
  <!-- jQuery -->
  <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
 <script src="https://hidden-fjord-85200.herokuapp.com/socket.io/socket.io.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/personal_js/dmChat.js" ></script>
  
    
  </body>
</html>