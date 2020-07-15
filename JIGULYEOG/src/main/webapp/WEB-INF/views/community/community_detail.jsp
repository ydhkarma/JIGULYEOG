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
    	$(function(){
    		
    		
    		$("#writeGestbookBtn").click(function(){
    			// 방명록 내용
    			var content = $("#cation_content").val();
    			var user_id = '<c:out value="${user.user_id }"/>';
    			var com_num = '<c:out value="${commNo}"/>';
    			
    			if(content==""){
    				alert("방명록 내용을 입력해주세요.");
    				return;
    			}
    			
    			var param = {
    				"com_num":com_num,
    				"user_id":user_id,
    				"cation_content":content
    			};
    			
    			// write
    			$.ajax({
    				type:"post",
    				url:"writeguestbook.do",
    				data:JSON.stringify(param),
    				contentType:"application/json",
    				dataType:"json",
    				success:function(msg){
    					if(msg.check==true){
    						alert("방명록 작성을 성공하였습니다.");
    						location.reload();
    					}else{
    						alert("방명록 작성 실패");
    					}
    					
    				},
    				error:function(){
    					alert("AJAX : 통신실패");
    				}
    			});
    		});
    		
    		
    	});
    	
    	function showUpdateGuestbookForm(cation_num){
    		var formId = '#guestbookForm'+cation_num;
    		var updateFormId = '#updateGuestbookForm'+cation_num;
    		$(formId).css("display","none");
    		$(updateFormId).css("display","block");
    	}
    	
    	function rollbackGuestbook(cation_num){
    		var formId = '#guestbookForm'+cation_num;
    		var updateFormId = '#updateGuestbookForm'+cation_num;
    		$(formId).css("display","block");
    		$(updateFormId).css("display","none");
    	}
    	
    	function updateGuestbook(cation_num){
    		var user_id = $("#user_id"+cation_num).val();
    		var content = $("#content"+cation_num).val();
    		if(content==""){alert("내용을 입력해주세요.");return;}
    		
    		var param = {
    				"user_id":user_id,
    				"cation_content":content,
    				"cation_num":cation_num
    		};
    		
			$.ajax({
				type:"post",
				url:"updateguestbook.do",
				data:JSON.stringify(param),
				contentType:"application/json",
				dataType:"json",
				success:function(msg){
					if(msg.check==true){
						alert("방명록 수정을 성공하였습니다.");
						location.reload();
					}else{
						alert("방명록 수정 실패");
					}
					
				},
				error:function(){
					alert("AJAX : 통신실패");
				}
			});
    		
    		
    	}
    	
    	function deleteGuestbook(cation_num){
    		var check = confirm("해당 방명록을 삭제하시겠습니까?");
    		if(check==false){return;}
    		else{
    			$.ajax({
    				type:"post",
    				url:"deleteguestbook.do",
    				data:JSON.stringify({"cation_num":cation_num}),
    				contentType:"application/json",
    				dataType:"json",
    				success:function(msg){
    					if(msg.check==true){
    						alert("삭제되었습니다.");
    						location.reload();
    					}else{
    						alert("방명록 삭제 실패");
    					}
    					
    				},
    				error:function(){
    					alert("AJAX : 통신실패");
    				}
    			});
    		}
    	}
    
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
              <h2 class="heading">Community</h2>
              
                <input type="button" class="btn btn-success" onclick="location.href='commchat.do?commNo=${commNo }'" style="background-color: #28a745; color: white; border-radius: 5px; padding: 10px; width: 250px;" value="채팅방 입장하기">
            </div>
          </div>
        </div>
      </div>
      
    </div>
  </div>
  <!----------------- END header ----------------->  

  <div class="site-section">
    <div class="featured-donate overlay-color" style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_2.jpg');">
      <div class="container">
        <div class="row">
        <div class="col-lg-8 order-lg-2 mb-3 mb-lg-0">
            <img src="${pageContext.request.contextPath}/resources/upload/images/project/${project.pro_image}" alt="Image placeholder" class="img-fluid">
        </div>
        <div class="col-lg-4 pr-lg-5">
            <span class="featured-text mb-3 d-block">현재 진행중인 프로젝트</span>
            <h2 style="overflow: hidden; white-space: pre-wrap; word-wrap: break-word;">${project.pro_title }</h2>
            <span class="donation-time mb-3"><fmt:formatDate value="${project.pro_write_date}" pattern="yyyy/MM/dd"/></span>
            <div class="progress custom-progress-success">
			   <fmt:parseNumber var="percent" integerOnly="true" value="${project.pro_nowmoney*100/project.pro_goalmoney }"/>
			   <div class="progress-bar bg-success" role="progressbar" style="width: ${percent }%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
			</div>
            <span class="fund-raised d-block mb-5">${project.pro_nowmoney} of ${project.pro_goalmoney }</span>
            <p><a href="projectdetail.do?pro_num=${project.pro_num }" class="btn btn-primary btn-hover-white py-3 px-5">상세 정보</a></p>
        </div>
        </div>

    </div>
    
    
    </div>

  </div>

  <div class="site-section">
    <!--================Blog Area =================-->
           <div class="container">
                   <h2>Notice</h2>
                       <table class="table table-hover table-sm" id="freeboard_table2">
                       <thead>
                           <tr>
                               <th style="width: 100px;">NO</th>
                               <th >Title</th>
                               <th style="width: 200px;">Date</th>
                               <th style="width: 50px;">View</th>
                           </tr>
                           </thead>
                       <tbody>
                       <c:choose>
                       		<c:when test="${!empty NList }">
                       			<c:forEach var="notice" items="${NList }" varStatus="status">
	                       			<tr>
	                               		<td>${nNum-status.index }</td>
	                               		<td><a href="noticedetail.do?notice_num=${notice.notice_num }&pro_num=${project.pro_num }" style="cursor: pointer;">${notice.notice_title }</a></td>
	                               		<td><fmt:formatDate value="${notice.notice_date}" pattern="yyyy/MM/dd"/></td>
	                               		<td>${notice.notice_view }</td>
	                           		</tr>
                       			</c:forEach>
                       		</c:when>
                       		<c:otherwise>
                       			<tr>
                        			<td colspan="4" style="text-align: center" ><strong>작성된 글이 없습니다.</strong></td>   
                     			</tr>
                       		</c:otherwise>
                       </c:choose>
                           
   
                           </tbody>
                   </table>
               <br><br>
               <c:if test="${user.user_id eq project.user_id }">
	               <div class="text-right">
	                   <button type="button" class="btn btn-primary"  onclick="location.href='commnoticeform.do?commNo=${commNo}&pro_num=${project.pro_num }'" style="margin-right:0px;">글쓰기</button>
	               </div>           
               </c:if>
               
   				<!-- noticePageMaker -->
               <div style="width: 100%; height:100%;  text-align: center;">
                   <div style="margin: 0 auto; display: inline-block;">
                   <ul class="pagination" style="width:100%; text-align: center; ">
                   	<c:if test="${noticePageMaker.prev }">
                   		<li class="page-item">
	              			<a class="page-link" href="community.do?pro_num=${project.pro_num}&nPage=${noticePageMaker.startPage - 1 }" aria-label="Previous">
	                		<span aria-hidden="true">&laquo;</span>
	              			</a>
	            		</li>  
                   	</c:if>
                   	<c:forEach begin="${noticePageMaker.startPage }" end="${noticePageMaker.endPage }" var="idx">
          				<c:choose>
          					<c:when test="${idx eq nPage }">
			            		<li class="page-item active"><a class="page-link" href="community.do?pro_num=${project.pro_num}&nPage=${idx }">${idx }</a></li>
          					</c:when>
          					<c:otherwise>          				
			            		<li class="page-item"><a class="page-link" href="community.do?pro_num=${project.pro_num}&nPage=${idx }">${idx }</a></li>
          					</c:otherwise>
          				</c:choose>          		
          			</c:forEach>
                       <c:if test="${noticePageMaker.next && noticePageMaker.endPage>0 }">
	            			<li class="page-item">
		              			<a class="page-link" href="community.do?pro_num=${project.pro_num}&nPage=${noticePageMaker.endPage + 1 }" aria-label="Next">
		                		<span aria-hidden="true">&raquo;</span>
		              			</a>
	            			</li>           
            			</c:if>    
                   </ul>
                   </div>
               </div>
           </div>
   <!--================Blog Area =================-->
     </div>

     <div class="site-section">
        <div class="container">
                <table class="table">                    
                    <tr>
                        <td>
                                  <textarea class="form-control" style="width: 1100px; resize: none;" rows="2" cols="30" id="cation_content" placeholder="방명록을 작성해주세요." ></textarea>
                                  <div class="mt-2" style="text-align: right;">
                                    <input type="button" class="btn pull-right btn-success" id="writeGestbookBtn" value="등록">
                                  </div>  
                        </td>
                    </tr>
                </table>
        <div>
        <c:choose>
        	<c:when test="${!empty GList }">
        		<c:forEach items="${GList }" var="guestbook">
			         <div class="card fundraise-item mb-5 p-2" id="guestbookForm${guestbook.cation_num }">
			              <div class="card-body">
			                  <span class="donation-time d-block">${guestbook.user_id } <fmt:formatDate value="${guestbook.cation_date }" pattern="yyyy/MM/dd"/></span>
			                <div class="p-3">
			                  <span class="fund-raised d-block">${guestbook.cation_content }</span>
			                </div>
			                <c:if test="${user.user_id eq guestbook.user_id }">
				                <div style="text-align: right;">
				                 <input type="button" class="btn btn-primary" onclick="showUpdateGuestbookForm(${guestbook.cation_num});" value="수정"/>	
				                 <input type="button" class="btn btn-primary" onclick="deleteGuestbook(${guestbook.cation_num});" value="삭제"/>		                
				                </div>			                
			                </c:if>
			              </div>
			          </div>
			          
			         <div class="card fundraise-item mb-5 p-2" id="updateGuestbookForm${guestbook.cation_num }" style="display: none;">
			              <div class="card-body">
			              		<input type="hidden" value="${guestbook.user_id }" id="user_id${guestbook.cation_num }"/>
			                  <span class="donation-time d-block">${guestbook.user_id } <fmt:formatDate value="${guestbook.cation_date }" pattern="yyyy/MM/dd"/></span>
			                <div class="p-3">
			                	<textarea rows="3" cols="10" style="width:98%; resize: none;" id="content${guestbook.cation_num }">${guestbook.cation_content }</textarea>
			                </div>
			                <div style="text-align: right;">
			                 <input type="button" class="btn btn-primary" onclick="updateGuestbook(${guestbook.cation_num});" value="완료"/>	
			                 <input type="button" class="btn btn-primary" onclick="rollbackGuestbook(${guestbook.cation_num});" value="취소"/>		                
			                </div>
			              </div>
			          </div>			          
			                 		
        		</c:forEach>
        	</c:when>
        	<c:otherwise>
        		<p style="text-align: center;">방명록이 없습니다.</p>
        	</c:otherwise>
        </c:choose>

        </div>
        
        <div class="container">
        	   <!-- guestbookPageMaker -->
               <div style="width: 100%; height:100%;  text-align: center;">
                   <div style="margin: 0 auto; display: inline-block;">
                   <ul class="pagination" style="width:100%; text-align: center; ">
                   	<c:if test="${guestbookMaker.prev }">
                   		<li class="page-item">
	              			<a class="page-link" href="community.do?pro_num=${project.pro_num}&gPage=${guestbookMaker.startPage - 1 }" aria-label="Previous">
	                		<span aria-hidden="true">&laquo;</span>
	              			</a>
	            		</li>  
                   	</c:if>
                   	<c:forEach begin="${guestbookMaker.startPage }" end="${guestbookMaker.endPage }" var="idx">
          				<c:choose>
          					<c:when test="${idx eq gPage }">
			            		<li class="page-item active"><a class="page-link" href="community.do?pro_num=${project.pro_num}&gPage=${idx }">${idx }</a></li>
          					</c:when>
          					<c:otherwise>          				
			            		<li class="page-item"><a class="page-link" href="community.do?pro_num=${project.pro_num}&gPage=${idx }">${idx }</a></li>
          					</c:otherwise>
          				</c:choose>          		
          			</c:forEach>
                       <c:if test="${guestbookMaker.next && guestbookMaker.endPage>0 }">
	            			<li class="page-item">
		              			<a class="page-link" href="community.do?pro_num=${project.pro_num}&gPage=${guestbookMaker.endPage + 1 }" aria-label="Next">
		                		<span aria-hidden="true">&raquo;</span>
		              			</a>
	            			</li>           
            			</c:if>    
                   </ul>
                   </div>
               </div>
        </div>
    </div>

    


</div>
<div class="site-section">
  <div class="container">
    <div class="m-3">
      <h2>최근 후원자</h2>
    </div>

      <div class="row">
  
  		<c:choose>
  			<c:when test="${empty fundUserList }">
  			<div style="text-align:center; width:98%;">
  				<p>------------아직 후원자가 없습니다.------------</p>  			
  			</div>
  			</c:when>
  			<c:otherwise>
  				<c:forEach items="${fundUserList }" var="fundUser">
  					<div class="col-md-2"> 
			          <div class="block-38 text-center">
			            <div class="block-38-img">
			              <div class="block-38-header">
			              <c:if test="${empty fundUser.user_pic }">
			              	<img src="${pageContext.request.contextPath}/resources/images/person_1.jpg" alt="Image placeholder">
			              </c:if>
			              <c:if test="${!empty fundUser.user_pic }">
			                <img src="${pageContext.request.contextPath}/resources/upload/images/user/${fundUser.user_pic}" alt="Image placeholder">			              
			              </c:if>
			                <h3 class="block-38-heading">${fundUser.user_nick }</h3>
			                <p class="block-38-subheading">${fundUser.user_id }</p>
			              </div>
			            </div>  
			          </div>
			        </div>
  				</c:forEach>
  			</c:otherwise>
  			
  		</c:choose>

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
  <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    
  </body>
</html>