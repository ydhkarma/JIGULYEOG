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
	<script type="text/javascript">
		// search check
		$(function(){
			$("#searchBtn").click(function(){
				if($("#searchKeyword").val()==""){
					alert("검색할 키워드를 입력해주세요."); return false;
				}
				$("#searchForm").submit();
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
      <div class="block-30 block-30-sm item" style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_3.jpg');" data-stellar-background-ratio="0.5">
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
  <!----------------- END header ----------------->  


  <!----------------- START Project ----------------->   
  <div class="site-section fund-raisers">
    <div class="container">
      <div class="mb-3">
        <div>
          	<form action="projectlist.do" method="get" id="searchForm" style="display:inline-block;">
	          <input type="text" class="form-control px-3 py-1" id="searchKeyword" name="keyword" style="width: 300px; display: inline-block;" placeholder="이름으로 검색하세요.">
	          <input type="button" class="btn btn-success btn-hover-white" id="searchBtn" value="search"/>
			</form>
          <div class="dropdown" style="float: right;">
            <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              	<c:if test="${category eq 1 || empty category }">
              		최신순
              	</c:if>
              	<c:if test="${category eq 2 }">
              		오래된 순
              	</c:if>
              	 <c:if test="${category eq 3 }">
              		최다 후원순
              	</c:if>
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <a class="dropdown-item" href="projectlist.do">최신순</a>
              <a class="dropdown-item" href="projectlist.do?category=2">오래된 순</a>
              <a class="dropdown-item" href="projectlist.do?category=3">최다 후원순</a>
            </div>
          </div>
        </div>
        
      </div>

        <div class="row">

				<c:choose>
					<c:when test="${!empty PList }">
						<c:forEach var="project" items="${PList }">
								<div class="col-12 col-sm-6 col-md-6 col-lg-4 mb-4 mb-lg-0">
			                        <div class="card fundraise-item mb-5">
			                            <a href="projectdetail.do?pro_num=${project.pro_num }" style="margin: 0px auto; margin-top: 10px;"><img class="card-img-top" src="${pageContext.request.contextPath}/resources/upload/images/project/${project.pro_image}" style="width: 330px; height: 300px;" alt="Image placeholder"></a>
			                            <div class="card-body">
			                              <h3 class="card-title"><a href="projectdetail.do?pro_num=${project.pro_num }">${project.pro_title }</a></h3>
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
						</c:forEach>
					
					</c:when>
				</c:choose>
		
      </div>
      <div class="container">
      	 <c:if test="${!empty user }">
      	<c:if test="${user.user_status ne 0 }">
	      <div style="text-align: right;">
	        <input type="button" class="btn btn-success" onclick="location.href='projectwriteform.do'" value="글쓰기">
	      </div>      	
      	</c:if>
      </c:if>
      </div>

	<!-- PAGE MAKER PART -->
	  <div style="width: 200px; margin: 0px auto;"> 
        <nav aria-label="Page navigation">
          <ul class="pagination">
          	<c:if test="${pageMaker.prev }">
	            <li class="page-item">
	              <a class="page-link" href="projectlist.do?page=${pageMaker.startPage - 1 }&category=${category}&keyword=${keyword }" aria-label="Previous">
	                <span aria-hidden="true">&laquo;</span>
	              </a>
	            </li>          	          	
          	</c:if>
          	<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
          		<c:choose>
          			<c:when test="${idx eq page }">
			            <li class="page-item active"><a class="page-link" href="projectlist.do?page=${idx }&category=${category}&keyword=${keyword }">${idx }</a></li>
          			</c:when>
          			<c:otherwise>          				
			            <li class="page-item"><a class="page-link" href="projectlist.do?page=${idx }&category=${category}&keyword=${keyword }">${idx }</a></li>
          			</c:otherwise>
          		</c:choose>          		
          	</c:forEach>
          	
            <c:if test="${pageMaker.next && pageMaker.endPage>0 }">
	            <li class="page-item">
	              <a class="page-link" href="projectlist.do?page=${pageMaker.endPage + 1 }&category=${category}&keyword=${keyword }" aria-label="Next">
	                <span aria-hidden="true">&raquo;</span>
	              </a>
	            </li>           
            </c:if>
          </ul>
        </nav>
      </div>
      
     

    </div>
    <!-- ------------------------- -->
	<!-- PAGE MAKER PART -->

  </div> <!-- .section -->
  <!----------------- End Project ----------------->   
  
  


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