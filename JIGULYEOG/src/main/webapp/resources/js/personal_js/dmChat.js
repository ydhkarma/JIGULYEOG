$(function(){
	
   var socket = io.connect("https://hidden-fjord-85200.herokuapp.com");
    var chatkey ="";
    var receiveId ="";
    var sendId = "";
    
    $(".chatStart").click(function(){
    	// chatKey : chat key , id : user_id
    	chatkey = $(this).attr('id');
    	sendId = $('#user_id').val();
    	receiveId = $(this).attr('name');
    	socket.emit('join DM', {chatKey:chatkey,id:sendId});
    	
        
    });
    
    // 메세지 보내기 클릭
    $("#sendMessage").on("click",function(){
    	var content = $("#messageContent").val();
    	
    	if(!content){
            alert("대화내용을 입력해주세요");
            return ;
        }
    	
    	 var str = '<div class="myMsg">';
         str += '<span class="msg">';
         str += content;
         str += '</span>';
         str += '</div>';
         
         // 메세지를 보냄
         socket.emit("sendDM",{chatKey:chatkey, data:content,receive:receiveId,send:sendId});
         
         var param = {
        		 "dm_key":chatkey,
        		 "dm_host":sendId,
        		 "send_id":sendId,
        		 "receive_id":receiveId,
        		 "dm_content":content
         };
         
         $.ajax({
        	 type:"post",
        	 url:"insertDMChat.do",
        	 data: JSON.stringify(param),
        	 contentType:"application/json",
			 dataType:"json",
			 success:function(msg){
				 if(msg.check==true){
					 
				 }else{
					 alert("메세지 전송 오류 ! [해당 페이지를 새로고침 합니다.]");
					  location.reload();
				 }
			 },
			 error:function(){
					alert("AJAX: 통신오류! [해당 페이지를 새로고침 합니다.]");
					location.reload();
					
				}
         
         });
    	
         $("#messageContent").val("");
         $("#chatLog").append(str);
         
    });
    
    // 메세지 보내기 엔터
    $("#messageContent").keydown(function(key){
    	if (key.keyCode == 13) {
    	var content = $("#messageContent").val();
    	
    	if(!content){
            alert("대화내용을 입력해주세요");
            return ;
        }
    	
    	 var str = '<div class="myMsg">';
         str += '<span class="msg">';
         str += content;
         str += '</span>';
         str += '</div>';
         
         // 메세지를 보냄
         socket.emit("sendDM",{chatKey:chatkey, data:content,receive:receiveId,send:sendId});
         
         var param = {
        		 "dm_key":chatkey,
        		 "dm_host":sendId,
        		 "send_id":sendId,
        		 "receive_id":receiveId,
        		 "dm_content":content
         };
         
         $.ajax({
        	 type:"post",
        	 url:"insertDMChat.do",
        	 data: JSON.stringify(param),
        	 contentType:"application/json",
			 dataType:"json",
			 success:function(msg){
				 if(msg.check==true){
					 
				 }else{
					 alert("메세지 전송 오류 ! [해당 페이지를 새로고침 합니다.]");
					  location.reload();
				 }
			 },
			 error:function(){
					alert("AJAX: 통신오류! [해당 페이지를 새로고침 합니다.]");
					location.reload();
					
				}
         
         });
    	
         $("#messageContent").val("");
         $("#chatLog").append(str);
         
         return false;
    	}
    });
    
    socket.on("sendDM",function(data){
        console.log("받음:"+data.content);
        
        if(!data.onlineCheck){
            $("#chatLog").append('<div id="offlineMsg">오프라인 상태 입니다.</div>');
        }

        if(data.sendId!=sendId){
            var str = '<div class="anotherMsg">';
            str += '<span class="anotherName">';
            str += data.sendId;
            str += '</span>';
            str += '<span class="msg">';
            str += data.content;
            str += '</span>';
            str += '</div>';
            
            $("#chatLog").append(str);
        }

        $("#chatLog").scrollTop($("#chatLog")[0].scrollHeight);
    });
    
});

function viewChat(user_id,dm_id,dm_key){
	var dmKey = dm_key;
	var receiveId = dm_id;
	var sendId = user_id;
	
	$("#chatHeader").html(receiveId);
	$("#noWrap").css("display","none");
	$("#chatWrap").css("display","block");
	$("#chatLog").html("");

	$.ajax({
    	type:'POST',
		url:"getDmChatLog.do",
		data: JSON.stringify({"user_id":sendId, "dm_id":dm_id, "dm_key":dm_key}),
		contentType:"application/json",
		dataType:"json",
		success : function(data){
			
			if(data.check==true){
				var chatList = data.chatList;
				
				for(var i =0;i<chatList.length;i++){
					
					// 상대방이 보낸 message
					if(chatList[i].send_id!=sendId){
						var str = '<div class="anotherMsg">';
						str += '<span class="anotherName">';
						str += chatList[i].send_id;
						str += '</span>';
						str += '<span class="msg">';
						str += chatList[i].dm_content;
						str += '</span>';
						str += '</div>';
						$("#chatLog").append(str);						
					}else{
						// 내가 보낸 message
				        var str = '<div class="myMsg">';
				        str += '<span class="msg">';
				        str += chatList[i].dm_content;
				        str += '</span>';
				        str += '</div>';
				        $("#chatLog").append(str);			
					}
				}
				
			}
			
			
		},
		error:function(){
			alert("ajax : 통신 실패 ");
		}
    });
    
	
	 
}