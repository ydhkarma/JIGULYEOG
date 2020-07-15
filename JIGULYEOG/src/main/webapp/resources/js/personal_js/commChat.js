$(function(){
	 //var socket = io.connect("http://localhost:82");
	 
	 var commNo ='<c:out value="${commNo}"/>';
	 var userId ='<c:out value="${user.user_id}"/>';
	 var userNick = '<c:out value="${user.user_nick}"/>';
	 
	 alert(commNo+","+userId+","+userNick);
	 
	 
	
	
});