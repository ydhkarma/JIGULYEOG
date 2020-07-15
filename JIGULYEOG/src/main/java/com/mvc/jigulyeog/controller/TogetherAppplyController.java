package com.mvc.jigulyeog.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.jigulyeog.biz.TogetherApplyBiz;
import com.mvc.jigulyeog.model.dto.TogetherApplyDto;

@Controller
public class TogetherAppplyController {
	
	@Autowired
	private TogetherApplyBiz biz;
	
	private static final Logger logger = LoggerFactory.getLogger(TogetherAppplyController.class);
	 
	//함께해요 신청하기
	@RequestMapping("/together_apply.do")
	public void togetherApply(TogetherApplyDto dto,HttpServletResponse response) {
		logger.info("함께해요 신청");
		
		logger.info("========================tog_no : "+dto.getTog_no());
		logger.info("========================user_id : "+dto.getUser_id());
		int res = biz.insert(dto);
		
		/*******************솔지 수정 부분 ( 성공 및 실패시 해당 alert창과 함께 이동 )********************/
		response.setContentType("text/html; charset=utf-8");
		if(res>0) {
			logger.info("Success together apply");
			try {jsResponse("신청되었습니다.","together.do",response);}
			catch (IOException e) {e.printStackTrace();}		
		} else {
			logger.info("Failed together apply");
			try {jsResponse("신청 오류!","together.do",response);}
			catch (IOException e) {e.printStackTrace();}
		}
		/*********************************************************************************/
	}
	
	@RequestMapping("/together_applyCancel.do")
	public void togetherApplyCancel(TogetherApplyDto dto,HttpServletResponse response) {
		logger.info("함께해요 취소신청");
		logger.info("========================tog_no : "+dto.getTog_no());
		logger.info("========================user_id : "+dto.getUser_id());
		int res = biz.delete(dto);
		
		/*******************솔지 수정 부분 ( 성공 및 실패시 해당 alert창과 함께 이동 )********************/
		response.setContentType("text/html; charset=utf-8");
		if(res>0) {
			logger.info("Success together Cancel apply");
			try {jsResponse("신청이 취소 되었습니다.","together.do",response);}
			catch (IOException e) {e.printStackTrace();}		
		} else {
			logger.info("Failed together apply");
			try {jsResponse("신청 취소 오류!","together.do",response);}
			catch (IOException e) {e.printStackTrace();}
		}
		/*********************************************************************************/
	}
	
	
	/*******************솔지 수정 부분********************/
	private void jsResponse(String msg,String url,HttpServletResponse response) throws IOException {	
		String s = "<script type='text/javascript' charset='utf-8'>"+
					"alert('"+msg+"');"+
					"location.href='"+url+"';"+
					"</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}
	
}
