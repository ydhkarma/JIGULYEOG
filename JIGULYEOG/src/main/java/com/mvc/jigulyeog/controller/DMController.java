package com.mvc.jigulyeog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.jigulyeog.biz.DMBiz;
import com.mvc.jigulyeog.model.dto.DMChatDto;
import com.mvc.jigulyeog.model.dto.DMListDto;
import com.mvc.jigulyeog.model.dto.UserDto;

@Controller
public class DMController {
	Logger logger = LoggerFactory.getLogger(DMController.class);
	@Autowired
	DMBiz dmb;
	
	
	// dmList
	@RequestMapping("/dmlist.do")
	public String dmList(HttpSession session, Model model) {
		logger.info("[ DMController : dmList ]");
		UserDto user = (UserDto) session.getAttribute("user");
		
		//login chk
		if(user == null) {
			logger.info("[ 잘못된 경로 : 로그인 필수 ]");
			return "redirect:index.do";
		}
		
		List<DMListDto> DMList = dmb.getDMList(user.getUser_id()); // DM List를 가져온다.
		// {'user01','user02'}
		List<DMListDto> unreadDMList = dmb.checkUnreadDM(user.getUser_id());
		// {'user01','user02','user03'}
		logger.info("[ unreadDMList : "+unreadDMList.toString()+" ]");
		
		// 안읽은 메세지가 존재
		if(!unreadDMList.isEmpty()) {
			for(DMListDto dto1:unreadDMList) {
				if(!DMList.contains(dto1)) {
					// 가지고 있지 않으면,
					// dmlist에 해당 친구를 추가해줘야 한다.
					dmb.addDM(dto1.getUser_id(), dto1.getDm_id());
					
					logger.info("[ add DMList : "+dto1.getDm_key()+" ]");
					DMList = dmb.getDMList(user.getUser_id()); // DM List를 가져온다.
					//DMList.add(dto1);
				}
			}
		}
		
		model.addAttribute("DMList",DMList);
		
		return "/dm/dm_list";
	}
	
	// add DM
	@RequestMapping("/dmlistadd.do")
	public String dmListAdd(HttpSession session, @RequestParam("add") String dm_id) {
		logger.info("[ DMController : dmListAdd ]");
		UserDto user = (UserDto) session.getAttribute("user");
		//login chk
		if(user == null) {
			logger.info("[ 잘못된 경로 : 로그인 필수 ]");
			return "redirect:index.do";
		}
		
		// [1] 일단 DM리스트에 있는지 확인!
		DMListDto meDm = dmb.checkDMList(user.getUser_id(),dm_id);
		Boolean meDmChk = (meDm != null)?true:false; 
		// 있으면 true(전에 DM햇었음.), 없으면 false(처음 이거나 삭제)
		
		// [2] 상대방 DM리스트에 있는지 확인!
		
		DMListDto youDm = dmb.checkDMList(dm_id,user.getUser_id()); 
		Boolean youDmChk = (youDm != null)?true:false;
		// 있으면 true(전에 DM했었음.), 없으면 false (처음 이거나 삭제)
		
		
		// [1] + [2] ==> (false,false) 나도 처음(삭제 했거나..), 상대방도 처음(삭제 했거나..)
		
		if(!meDmChk&&!youDmChk) {
			Boolean addMeDm = dmb.addDM(user.getUser_id(),dm_id);
			Boolean addYouDm = dmb.addDM(dm_id,user.getUser_id());	
			
			return "redirect:dmlist.do";
		}
		
		// [1] + [2] ==> (true,true) 둘다 서로 DM List에 남아있음
		if(meDmChk&&youDmChk) {	
			
			return "redirect:dmlist.do";
		}
		
		// [1] + [2] ==> (false,true) or (true,false) 둘 중 하나 삭제.

		// 내가 삭제 ==> 나만 추가
		if(meDmChk == false&&youDmChk == true) {
			logger.info("[ meDmChk == false ]");
			dmb.addDM(user.getUser_id(),dm_id);
			return "redirect:dmlist.do";
		}
			
		// 상대방이 삭제 ==> 상대방만 추가
		if(meDmChk == true&&youDmChk == false) {
			logger.info("[ youDmChk == false ]");
			dmb.addDM(dm_id,user.getUser_id());
			return "redirect:dmlist.do";
		}
		
		return "";

	}
	
	// GET DM Chat log
	@RequestMapping(value="/getDmChatLog.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getDmChatLog(@RequestBody DMListDto dto){
		logger.info("[ DMController : getDmChatLog ]");
		
		List<DMChatDto> chatList = dmb.getDMCHatLog(dto);
		logger.info(chatList.toString());
		boolean is = false;
		
		if(!chatList.isEmpty()) {
			is = true;
		}
		
		Map<String ,Object> model = new HashMap<String,Object>();
		
		model.put("chatList", chatList);
		model.put("check", is);
		
		return model;
	}
	
	@RequestMapping("/deleteDM.do")
	public String deleteDM(@RequestParam("user_id") String user_id,@RequestParam("dm_id") String dm_id,@RequestParam("dm_key") String dm_key) {
		logger.info("[ DMController : deleteDM ]");
		Boolean is = dmb.deleteDM(user_id,dm_id,dm_key);
		
		if(is) {
			logger.info("[ deleteDM : 대화 삭제 완료. ]");
			return "redirect:dmlist.do";
		}else {
			logger.info("[ deleteDM : 삭제할 대화가 없습니다. ]");
			return "redirect:dmlist.do";
		}
		
	}
	
	@RequestMapping(value="insertDMChat.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Boolean> insertDMChat(@RequestBody DMChatDto chat){
		logger.info("[ DMController : insertDMChat ]");
		Boolean is = dmb.insertDMChat(chat);
		
		Map<String,Boolean> map = new HashMap<String, Boolean>();
		
		map.put("check",is);
		
		return map;
	}
	
}
