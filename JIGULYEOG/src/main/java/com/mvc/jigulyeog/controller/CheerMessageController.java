package com.mvc.jigulyeog.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.jigulyeog.biz.CheerMessageBiz;
import com.mvc.jigulyeog.model.dto.CheerMessageDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;

@Controller
public class CheerMessageController {
	private static Logger logger = LoggerFactory.getLogger(CheerMessageController.class);
	
	@Autowired
	CheerMessageBiz cmb;
	
	// Date type error : typeMisMatch
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        DateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
    }
	
    // chherMsg List
	@RequestMapping(value="/cheerMsgList.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getCheerMsgList(HttpSession session, @RequestBody Map param, @RequestParam(value="page",required=false) Integer page) {
		logger.info("[ CheerMessageController : getCheerMsgList ]");
		boolean chk = false;
		
		if(page==null) {
			page=1;
		}
		
		logger.info("[ page : "+page+" ]");
		
		int pro_num = Integer.parseInt(param.get("pro_num").toString());
		
		logger.info("[ pro_num :"+pro_num+" ]");
		
		int msgCnt = cmb.totalArticle(pro_num); // 댓글 count
		Map<String ,Object> model = new HashMap<String,Object>();	
		
		model.put("msgCnt", msgCnt);
		
		
		// 댓글 존재
		if(msgCnt>0) {
			chk = true;
			Paging paging = cmb.cheerMsgPaging(page,pro_num); // Paging설정			
			List<CheerMessageDto> CMList = cmb.cheerMsgList(paging,pro_num); // 댓글 리스트 불러오기
			
			List<String> userImgList = cmb.getUserImgList(CMList);
			
			PageMaker maker = cmb.getPageMaker(paging);

			model.put("CMList", CMList);
			model.put("imgList", userImgList);
			model.put("page", page);
			model.put("pageMaker", maker);
		}
		
		
		model.put("check", chk);
		

		return model;
	}
	
	
	// 응원 메세지 쓰기 (AJAX)
	@RequestMapping("/cheerMsgWrite.do")
	@ResponseBody
	public Map<String, Boolean> cheerMsgWrite(HttpSession session, @RequestBody CheerMessageDto msg){
		logger.info("[ CheerMessageController : cheerMsgWrite ]");
		
		Boolean is = true;
		Map<String ,Boolean> map = new HashMap<String,Boolean>();
		
		// 혹시 모르니 검사.
		if(msg.getCheer_content()==null||msg.getCheer_content()=="") {
			is = false;
		}else {
			is = cmb.cheerMsgWrite(msg); // success : true , fail : false		
		}
		
		map.put("check", is);
		
		return map;
		
	}
	
	// 댓글 수정 (AJAX)
	@RequestMapping("/cheerMsgUpdate.do")
	@ResponseBody
	public Map<String, Boolean> cheerMsgUpdate(HttpSession session, @RequestBody CheerMessageDto msg){
		logger.info("[ CheerMessageController : cheerMsgUpdate ]");
		
		Boolean is = false;
		
		Map<String ,Boolean> map = new HashMap<String,Boolean>();
		
		is = cmb.cheerMsgUpdate(msg); // success : true , fail : false	
		
		map.put("check", is);
		return map;
		
	}
	
	// 댓글 삭제 (AJAX)
	@RequestMapping("/cheerMsgDelete.do")
	@ResponseBody
	public Map<String, Boolean> cheerMsgDelete(HttpSession session, @RequestBody CheerMessageDto msg){
		logger.info("[ CheerMessageController : cheerMsgDelete ]");
		
		Boolean is = false;
		
		Map<String ,Boolean> map = new HashMap<String,Boolean>();
		
		is = cmb.cheerMsgDelete(msg.getCheer_num()); // success : true , fail : false	
		
		map.put("check", is);
		return map;
		
	}
	
	

	
	
}
