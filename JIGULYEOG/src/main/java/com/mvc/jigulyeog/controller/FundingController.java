package com.mvc.jigulyeog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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

import com.mvc.jigulyeog.biz.FundingBiz;
import com.mvc.jigulyeog.biz.ProjectBiz;
import com.mvc.jigulyeog.model.dto.FundingListDto;
import com.mvc.jigulyeog.model.dto.ProjectDto;

@Controller
public class FundingController {
	Logger logger = LoggerFactory.getLogger(FundingController.class);
	
	@Autowired
	FundingBiz fb;
	
	@Autowired
	ProjectBiz pb;
	
	// go pay page
	@RequestMapping("/paypage.do")
	public String paypage(@RequestParam("pro_num") Integer pro_num,@RequestParam(value="overlap",required=false) String overlap, Model model) {
		logger.info("[ FundingController : paypage ]");
		logger.info("[ pro_num : "+pro_num+" ]");
		
		model.addAttribute("pro_num", pro_num);
		if(overlap!=null) {
			if(overlap.equals("true")) {model.addAttribute("overlap","true");}			
		}

		return "/project/paypage_certification";
	}
	
	@RequestMapping("/payment.do")
	public String payment(@RequestParam("pro_num") Integer pro_num,@RequestParam(value="overlap",required=false) String overlap, Model model) {
		logger.info("[ FundingController : payment ]");
		logger.info("[ pro_num : "+pro_num+" ]");
		
		ProjectDto project = pb.getProjectOne(pro_num);
		model.addAttribute("project",project);
		if(overlap!=null) {
			if(overlap.equals("true")) {model.addAttribute("overlap","true");}		
		}

		return "/project/paypage";
	}
	
	// funding process
	@RequestMapping(value="/fundingprocess.do",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Boolean> fundingProcess(@RequestBody FundingListDto sponser,@RequestParam(value="overlap",required=false) String overlap){
		logger.info("[ FundingController : fundingProcess ]");
		Map<String ,Boolean> map = new HashMap<String,Boolean>();
		logger.info("[ overlap :"+overlap+" ]");
		Boolean is = false;
		
		//처음 기부
		if(overlap==null||overlap==""||overlap.equals("false")) {
			logger.info("[ first donate ]");
			is = fb.fundingProcess(sponser);			
		}else {
			//중복기부
			logger.info("[ overlap donate ]");
			is = fb.fundingProcessOverlap(sponser);
		}
		
		if(is==true) {
			Boolean check = fb.checkAndUpdateSuccessStatus(sponser.getPro_num());
			if(check) {
				logger.info("[ project Success! ]");
			}else {
				logger.info("[ project Yet ..! ]");				
			}
		}

		map.put("check", is);
		return map;
	}
	
	@RequestMapping("payresult.do")
	public String payResult(@RequestParam("pro_num") Integer pro_num,Model model ) {
		logger.info("[ FundingController : payResult ]");
		
		model.addAttribute("pro_num",pro_num);
		
		return "/project/paypage_result";
	}
	
	private void jsResponse(String msg,String url,HttpServletResponse response) throws IOException {
		
		String s = "<script type='text/javascript' charset='utf-8'>"+
					"alert('"+msg+"');"+
					"location.href='"+url+"';"+
					"</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
		
	}
}
