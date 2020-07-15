<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


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
      .project-detail img{
      	max-width:700px;
      }

      
      
    </style>
    
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script type="text/javascript">
	
	// ## donate page 로 이동 하기 전 체크 ## //
		function donateForm(){
			var chk = '<c:out value="${fundingChk }"/>';
			if(chk == 'true'){
				var is = confirm("기부 내역이 존재합니다. 중복 기부 하시겠습니까?");
				if(is){
					// 예
					var pro_num = '<c:out value="${project.pro_num}"/>';
					location.href='paypage.do?pro_num='+pro_num+'&overlap=true';
				}
			}else{
				var pro_num = '<c:out value="${project.pro_num}"/>';
				location.href='paypage.do?pro_num='+pro_num;
			}
		}
	
	// ## show cheerMsg update Form ## //
		function updateForm(i){
			$(".default"+i+"").css({"display":"none"});
			$(".update"+i+"").css({"display":"block"});
		}
		
	// ## rollback cheerMsg update Form ## //
		function rollbackUpdate(i){
			$(".update"+i+"").css({"display":"none"});			
			$(".default"+i+"").css({"display":"block"});
		}
		
	// ## cheerMsg update ## //
		function updateMsg(i){	
			var formName = "upform"+i;
			var cheer_content = document.forms[formName].elements["cheer_content"].value;
			var param = {
					"cheer_num":i,
					"cheer_content":cheer_content	
			};
			
			$.ajax({
				type:"post",
				url:"cheerMsgUpdate.do",
				data:JSON.stringify(param),
				contentType:"application/json",
				dataType:"json",
				success:function(msg){
					if(msg.check==true){
						alert("응원메세지가 수정되었습니다.");
						pagingCheerMsg(1);s
						
					}else{
						alert("응원메세지 수정 실패.");
					}
					
				},
				error:function(){
					alert("AJAX : 통신실패");
				}
				
			});
			
			
		}
		
	// ## cheerMsg delete ## //
		function delCheerMsg(i){
			var param = {"cheer_num":i};
			$.ajax({
				type:"post",
				url:"cheerMsgDelete.do",
				data:JSON.stringify(param),
				contentType:"application/json",
				dataType:"json",
				success:function(msg){
					if(msg.check==true){
						alert("응원메세지가 삭제되었습니다.");
						pagingCheerMsg(1);
						
					}else{
						alert("응원메세지 삭제 실패.");
					}
					
				},
				error:function(){
					alert("AJAX : 통신실패");
				}
				
			});
		}
	

	// ## cheerMsg Paging ## //
		function pagingCheerMsg(page){
			// 초기화
			$("#cheerMsgForm").html("");
			$("#msgPaging").html("");
			
			var pro_num = '<c:out value="${project.pro_num}"/>';
			var currUser = '<c:out value="${user.user_id}"/>';
			var param = {"pro_num":pro_num};
			
			// cheerMsg 가져오기
			$.ajax({
				type:"post",
				url:"cheerMsgList.do?page="+page,
				data:JSON.stringify(param),
				contentType:"application/json",
				dataType:"json",
				success:function(msg){
					$("#msgCnt").html(msg.msgCnt);
					
					// 댓글이 있다면,
					if(msg.check==true){
						
						var CMList = []; var imgList=[]; var page; var pageMaker;
						CMList = msg.CMList; imgList=msg.imgList; page = msg.page; pageMaker = msg.pageMaker;
						page = msg.page;
						pageMaker = msg.pageMaker;
						
						var str ="";
						
						// 댓글 리스트
						for(var i = 0;i<CMList.length;i++){
							
							// default form
							str += '<li class="comment default'+CMList[i].cheer_num+'">';
		                     str +=  '<div class="vcard bio">';
		                     if(imgList[i]==""){
		                    	 str +=  '<img src="${pageContext.request.contextPath}/resources/images/person_1.jpg" alt="Image placeholder">';
		                     }else{
		                      str +=  '<img src="${pageContext.request.contextPath}/resources/upload/images/user/'+imgList[i]+'" alt="Image placeholder">';		                    	 
		                     }
		                      str+= '</div>';
		                      str += '<div class="comment-body">';
		                        str += '<h3 style="width:100%;">'+CMList[i].user_id+'</h3>';
		                        var now = new Date(CMList[i].cheer_date);
		                        var date = now.getFullYear() + "-" + (now.getMonth()+1) + "-" + (now.getDate());
		                         str += '<div class="meta">'+date+'</div>';
		                        str += '<p>'+CMList[i].cheer_content+'</p>';   
		                       if(currUser == CMList[i].user_id){
		                        	str += '<div style="width:100%; text-align:right; "> <input type="button" value="수정" onclick="updateForm('+CMList[i].cheer_num+');" class="btn" style="display:inline-block; text-align:right;"/> <input type="button" value="삭제" class="btn btn-primary" onclick="delCheerMsg('+CMList[i].cheer_num+');" style="display:inline-block;"/></div>';
		                       }
		                        str+='</div>';
		                   str+= '</li>';
		                   
		                   
							// update form
							str += '<li class="comment up update'+CMList[i].cheer_num+'" style="display:none;">';
							str +='<form action="cheerMsgUpdate.do" method="post" name="upform'+CMList[i].cheer_num+'">';
							str += '<input type="hidden" name="cheer_num" value="'+CMList[i].cheer_num+'"/>';
		                     str +=  '<div class="vcard bio">';
		                      str +=  '<img src="${pageContext.request.contextPath}/resources/upload/images/user/${user.getUser_pic()}" alt="Image placeholder">';
		                      str+= '</div>';
		                      str += '<div class="comment-body">';
		                        str += '<h3 style="width:100%;">'+CMList[i].user_id+'</h3>';
		                        var now = new Date(CMList[i].cheer_date);
		                        var date = now.getFullYear() + "-" + (now.getMonth()+1) + "-" + (now.getDate());
		                         str += '<div class="meta">'+date+'</div>';
		                        str += '<textarea rows="2" cols="30" style="width:90%;resize: none;" name="cheer_content" class="update_content update_content'+CMList[i].cheer_num+'">'+CMList[i].cheer_content+'</textarea>';   
		                       if(currUser == CMList[i].user_id){
		                        	str += '<div style="width:100%; text-align:right; "> <input type="button" value="완료" class="btn" style="display:inline-block; text-align:right;" onclick="updateMsg('+CMList[i].cheer_num+');"/> <input type="button" value="취소" class="btn btn-primary" onclick="rollbackUpdate('+CMList[i].cheer_num+');" style="display:inline-block;"/></div>';
		                       }
		                        str+='</div>';
		                        str+='</form>';
		                   str+= '</li>';		
		                   
		                   
						}
						
						$("#cheerMsgForm").append(str);
						
						// Page Maker
						var msgPaging = "";
		                msgPaging += '<nav aria-label="Page navigation">';
		                msgPaging += '<ul class="pagination">';
		                
		                if(pageMaker.prev==true){
			                msgPaging += '<li class="page-item">';
			                msgPaging += '<a class="page-link" href="#" aria-label="Previous" onclick="pagingCheerMsg('+(pageMaker.startPage - 1)+');">';
			                msgPaging += '<span aria-hidden="true">&laquo;</span>';
			                msgPaging += '</a>';
			                msgPaging += '</li>';	
		                }
		                
		                for(var idx = pageMaker.startPage;idx<=pageMaker.endPage;idx++){
		                	if(idx == page){
				                msgPaging += '<li class="page-item active"><a class="page-link" onclick="pagingCheerMsg('+idx+')">'+idx+'</a></li>';	
		                	}else{
				                msgPaging += '<li class="page-item"><a class="page-link" onclick="pagingCheerMsg('+idx+')">'+idx+'</a></li>';	
		                	}
		                }
						
		                if(pageMaker.next && pageMaker.endPage>0){
			                msgPaging += '<li class="page-item">';
			                msgPaging += '<a class="page-link" href="#" aria-label="Next" onclick="pagingCheerMsg('+(pageMaker.endPage + 1)+');">';
			                msgPaging += '<span aria-hidden="true">&raquo;</span>';
			                msgPaging += '</a>';
			                msgPaging += '</li>';
		                }
		                msgPaging += '</ul>';
		                msgPaging += '</nav>';
						$("#msgPaging").append(msgPaging);
					}
				},
				error:function(){
					alert("AJAX : 통신실패");
				}
				
			});
	
		}
		
		$(function(){
			
			// ## 처음 로딩시 응원메세지 페이징해서 가져온다.
			pagingCheerMsg(1);
			
			// ## cheerMsg 작성 버튼 클릭 시 ## //
			$("#submitMsg").click(function(){
				var pro_num = '<c:out value="${project.pro_num}"/>';
				var user_id = '<c:out value="${user.user_id}"/>';
				
				var msgContent = $("#msgContent").val();
				
				if(msgContent==""){
					alert("응원 메세지 내용을 입력해주세요.");
					return false;
					
				}
				
				if($("#msgContent").val().length<0){
						alert("내용을 입력해주세요.");	
						return false;
				}
				
				var param = {
						"pro_num":pro_num,
						"user_id":user_id,
						"cheer_content":msgContent			
				};
				
				$.ajax({
					type:"post",
					url:"cheerMsgWrite.do",
					data:JSON.stringify(param),
					contentType:"application/json",
					dataType:"json",
					success:function(msg){
						if(msg.check==true){
							alert("응원메세지가 작성되었습니다.");
							 $("#msgContent").val("");
							pagingCheerMsg(1);
							
						}else{
							alert("응원메세지 작성 실패.");
						}
						
					},
					error:function(){
						alert("AJAX : 통신실패");
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
  
  
  <div id="blog" class="site-section">
    <div class="container">
            
            <div class="row">

              <div class="col-md-8">
              	<div style="color:#28a745;">환경 프로젝트</div>
                <h2 style="overflow: hidden; white-space: pre-wrap; word-wrap: break-word;">${project.pro_title }</h2>
                <br>
                <p class="mb-4"><img src="${pageContext.request.contextPath}/resources/upload/images/project/${project.pro_image}" alt="" class="img-fluid" style="width: 500px; height: 460px; "></p>

                <div class="project-detail" style="word-break:break-all;">
                	${project.pro_detail }
                </div>
                <div class="pt-5 mt-5">
                <c:if test="${user.user_id eq project.user_id }">
	            	<div style="text-align:right;" class="mb-3">
	            		<input type="button" value="삭제" class="btn btn-primary" onclick="location.href='projectdelete.do?pro_num=${project.pro_num}'">
	            		<input type="button" value="수정" class="btn btn-primary" onclick="location.href='projectupdateform.do?pro_num=${project.pro_num}'">
	            	</div>                
                </c:if>
             
             <!-- Comment 등록 버튼 Start -->
             <div class="container">
                <div>
                    <div>
                        <table class="table">                    
                            <tr>
                                <td>
                                	<c:choose>
                                		<c:when test="${empty user }">
                                          <textarea class="form-control" style="width: 98%; resize: none;" rows="2" cols="30"  placeholder="회원만 댓글작성이 가능합니다." readonly="readonly"></textarea>
                                          <div class="mt-2" style="text-align: right;">
                                            <input type="submit" class="btn pull-right btn-success" value="등록" disabled="disabled">
                                          </div>                                                                      		
                                		</c:when>
                                		<c:otherwise>
                                          <textarea class="form-control" style="width: 700px; resize: none;" rows="2" cols="30" id="msgContent" placeholder="응원의 메세지를 남겨주세요!" ></textarea>
                                          <div class="mt-2" style="text-align: right;">
                                            <input type="button" class="btn pull-right btn-success" id="submitMsg" value="등록">
                                          </div>                                  		
                                		</c:otherwise>
                                	</c:choose>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>    
            </div>
                  <!-- 응원 메세지 -->
                  <h3><span id="msgCnt"><b>0</b></span>개의 응원메세지</h3>
                  <div class="mb-4" style="color:#b3b3b3;">해당 프로젝트에 따뜻한 응원 메세지를 남겨주세요.</div>
                  
                  	<!-- START comment-list -->
	                  <ul class="comment-list" id="cheerMsgForm">
	                
	                  </ul>
	                <!-- END comment-list -->
	             
   				<!-- PAGE MAKER PART -->
				<div style="display:inline-block; padding-left:200px;" class="container" id="msgPaging">
				
                </div>

                </div>
                  
              </div> <!-- .col-md-8 -->


              
              <div class="col-md-4 sidebar" style="border: 1.5px #eeebec solid;">
                <!--모금 정보 부분-->
                <div class="sidebar-box" style="text-align: left;">
                    <h1 style="color: #28a745; font-weight: 900; font-size:3.5em;"><fmt:parseNumber var="percent" integerOnly="true" value="${project.pro_nowmoney*100/project.pro_goalmoney }"/>${percent }<span style="font-weight: 300; font-size: 0.6em;">%</span></h1>   

                    <div class="progress custom-progress-success" style="height: 10px;">
                        <div class="progress-bar bg-success" role="progressbar" style="width: ${percent }%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                      </div>
                      <span class="fund-raised d-block" style="font-size:1.2em;"><fmt:formatDate value="${project.pro_start_date}" pattern="yyyy.MM.dd"/> ~ <fmt:formatDate value="${project.pro_due_date}" pattern="yyyy.MM.dd"/>까지</span>                     
 
                      <h4 style="font-weight: 600; text-align: center;" class="m-3">
                      <span style="color: #f86f46;"><fmt:formatNumber value="${project.pro_nowmoney }" pattern="#,###.##"/> 원
                        <c:if test="${fn:contains(project.pro_success, 'S')}">성공 ! </c:if>
                       </span>
                      </h4>
					  
					  <h4 style="text-align:center; font-size:1em; color:#b3b3b3;">
					  <fmt:formatNumber value="${project.pro_goalmoney }" pattern="#,###.##"/> 원 목표
					  </h4>
                    <div class="mt-3 mb-2" style="background-color: #f86f46; color: white; width: 50px; text-align: center;">
                        <span style="font-weight: 500;">
                        		D- 
                        		<c:choose>
                        			<c:when test="${dday <= 0 }">
                        				0
                        			</c:when>
                        			<c:otherwise>
	                        			${dday}                        			
                        			</c:otherwise>
                        		</c:choose>
                         </span>
                    </div>

                    <div class="person-donate text-center bg-light pt-4">
                        <img src="${pageContext.request.contextPath}/resources/upload/images/user/${org.org_pic}" alt="Image placeholder" class="img-fluid">
                        <div class="donate-info">
                          <h2><a href="orgDetail.do?org_num=${org.org_num }">${org.org_name }</a></h2>
                          <span class="time d-block mb-3">${org.org_role }</span>
                          
                          <c:if test="${(user.user_id ne project.user_id) && dday >= 0 }">
		                       <div class="donate-amount d-flex m-1" style="width: 100%;">
		                          <input type="button" class="btn btn-success" style="width: 100%;" onclick="donateForm();" value="기부하기">
		                       </div>                           
                          </c:if>
		                                  				
            				<c:if test="${ (user.user_id eq project.user_id) || fundingChk eq true }">
	                           <div class="donate-amount d-flex m-1" style="width: 100%;">
	                            	<input type="button" class="btn btn-warning" onclick="location.href='createcomm.do?pro_num=${project.pro_num}'" style="width: 100%; color: white;" value="커뮤니티 입장">
	                          </div>           				
            				</c:if>

                          <c:if test="${project.user_id ne user.user_id }">
	                          <div class="donate-amount d-flex m-1" style="width: 100%;">
	                            <input type="button" class="btn btn-outline-primary" style="width: 100%;" onclick="location.href='dmlistadd.do?add=${project.user_id}'" value="DM 보내기">
	                          </div>                         
                          </c:if>

                          
                        </div>
                      </div>
                </div>
                <!----------->
               
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
  <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    
  </body>
</html>