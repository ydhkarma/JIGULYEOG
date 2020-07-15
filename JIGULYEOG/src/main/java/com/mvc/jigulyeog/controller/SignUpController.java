package com.mvc.jigulyeog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.jigulyeog.biz.SignUpBiz;
import com.mvc.jigulyeog.model.dto.SignUpDto;
import com.mvc.jigulyeog.model.dto.UserDto;

@Controller
public class SignUpController {
	
	
	@Autowired
	private SignUpBiz biz;
	
	private static final Logger logger = LoggerFactory.getLogger(ChungwonController.class);
	
	//댓글 insert ajax
	@RequestMapping(value="/signupadd.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Integer> signUpAdd(@RequestBody SignUpDto sig_content,HttpServletRequest request){
/////////////////////////////(시작)login/회원가입 합칠 때 만든 코드 /새로 하려면지워두 됨
		
		//로그인 시 댓글 작성 가능// 비 로그인시 ajax에서 alert 띄운 후 loginForm.do로
		
		String user_id = ((UserDto)request.getSession().getAttribute("user")).getUser_id();
		System.out.println("user_id : " + user_id);
		int res;
		sig_content.setUser_id(user_id);
		
		boolean chk = biz.insertChk(sig_content);
		if(chk==true) {
			res=-1;
		}else {
			res = biz.insert(sig_content);
		}
		
		
/////////////////////////////(끝)login/회원가입 합칠 때 만든 코드 /새로 하려면지워두 됨 
		
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("code",res);
		return map;
		
	}
	
	//popup창에 필요한 selectone
	@RequestMapping("/sigupdate.do")
	public String signUpOne(Model model,int sig_no) {
		
		model.addAttribute("sig", biz.signUpOne(sig_no));
		
		return "/chungwon/petition_sigupdate";
		
	}
	
	//댓글 수정 popup
	@RequestMapping("/signupdateres.do")
	public void sigUpdate(SignUpDto dto, HttpServletResponse response,HttpServletRequest request) throws IOException {	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		logger.info("UPDATE SIG");
		int res = biz.sigUpdate(dto);
		if(res>0) {
			popupResponse("수정 완료되었습니다.","chungdetail.do?pet_no="+dto.getPet_no(),response);
		}else {
			jsResponse("수정 실패","chungdetail.do?pet_no="+dto.getPet_no(),response);
		}
		
	}
	
	//댓글 삭제 ajax
	@RequestMapping(value="/signdelete.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Integer> sigDelete(@RequestBody SignUpDto dto) {
		
		logger.info("DELETE SIG");
		int res = biz.sigDelete(dto);
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("code",res);
		return map;
		
	}
	
	private void jsResponse(String msg , String url , HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"+
						"alert('"+msg+"');"+
						"location.href='"+url+"';"+
						"self.close();"+
					"</script>";
		
		PrintWriter out = response.getWriter();
		out.print(s);
	}
	
	private void popupResponse(String msg , String url , HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"+
						"window.close();"+	
						"alert('"+msg+"');"+
						"location.href='"+url+"';"+
						"window.opener.location.reload();"+
					"</script>";
		
		PrintWriter out = response.getWriter();
		out.print(s);
	}


}
