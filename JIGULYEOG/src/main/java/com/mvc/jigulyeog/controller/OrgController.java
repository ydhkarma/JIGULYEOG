package com.mvc.jigulyeog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mvc.jigulyeog.biz.MemberBiz;
import com.mvc.jigulyeog.biz.MyPageBiz;
import com.mvc.jigulyeog.biz.OrgBiz;
import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.ProjectDto;
import com.mvc.jigulyeog.model.dto.UserDto;

@Controller
public class OrgController {
	private static final Logger logger = LoggerFactory.getLogger(OrgController.class);
	
	@Autowired
	private MyPageBiz mpb;
	
	@Autowired
	private OrgBiz ob;
	
	@Autowired
	private MemberBiz mb;

	@RequestMapping("/org.do")
	public String orgSearchForm(Model model, @RequestParam(value = "orgPage", required = false) Integer orgPage,
			@RequestParam(value = "keyword", required = false) String keyword) {
		logger.info("[org_search Page]");

		Boolean searchIs = (keyword == null) ? true : false;

		if (searchIs) {
			if (orgPage == null) {
				orgPage = 1;
			}
			Paging paging = ob.orgPaging(orgPage);
			List<OrgDto> orgList = ob.orgList(paging);
			PageMaker maker = ob.getPageMaker(paging);

			model.addAttribute("orgList", orgList);
			model.addAttribute("orgPage", orgPage);
			model.addAttribute("PageMaker", maker);
		} else {

			if (orgPage == null) {
				orgPage = 1;
			}

			keyword = keyword.trim();

			Paging paging = ob.orgPagingSearch(orgPage, keyword);
			List<OrgDto> orgList = ob.orgListSearch(paging, keyword);
			PageMaker maker = ob.getPageMaker(paging);

			model.addAttribute("orgList", orgList);
			model.addAttribute("orgPage", orgPage);
			model.addAttribute("PageMaker", maker);
			model.addAttribute("keyword", keyword);
		}

		return "/org/org_search";
	}

	@RequestMapping(value = "/orgDetail.do")
	public String orgDetail(Model model, @RequestParam(value = "org_num", required = true) Integer org_num,
			HttpSession session) {
		logger.info("[ OrgController : orgDetail ]");
		logger.info("[ org_num : " + org_num + " ]");
		
		//사용자가 단체를 구독했는지 판단
		UserDto dto = (UserDto)session.getAttribute("user");
		String user_id = "";
		boolean subChk = false;
		
		//로그인 되있을때만 체크.
		if(dto != null) {
			user_id = dto.getUser_id();
			subChk = ob.subChk(org_num,user_id);
		}
		model.addAttribute("subChk",subChk);
		
		OrgDto org = ob.selectOne(org_num);
		model.addAttribute("org", org);
		
		Date date = org.getOrg_est_date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateEx = transFormat.format(date);
		model.addAttribute("dateEx", dateEx);
		logger.info("[ dateEx : " + dateEx + " ]");
		List<ProjectDto> pList = ob.getPList(org_num);
		model.addAttribute("pList", pList);
		logger.info("[ pList : " + pList.size() + " ]");
		
		return "/org/org_detail";
	}

	@RequestMapping("org_myPage.do")
	public String org_myPage(HttpSession session, Model model) {
		UserDto user = (UserDto) session.getAttribute("user");
		if (user != null) {
			// 로그인이 된 상태일때
			return "/org/org_mypage";
		} else {
			// 로그인이 안된 상태일때
			return "redirect:login.do";
		}
	}

	@RequestMapping("org_myAction.do")
	public String org_myAcion(HttpSession session, Model model) {
		UserDto user = (UserDto) session.getAttribute("user");
		if (user != null) {
			//로그인이 된 상태일때
			//진행중인 프로젝트 리스트, 종료된 프로젝트 리스트 불러오기
			int org_num = user.getUser_status();
			System.out.println(org_num);
			List<ProjectDto> nowPList = ob.getNowPList(org_num);
			List<ProjectDto> endPList = ob.getEndPList(org_num);

			int nowP = nowPList.size();
			int endP = endPList.size();
			System.out.println("nowp: " + nowP + ", endP: " + endP);
			model.addAttribute("nowPList", nowPList);
			model.addAttribute("endPList", endPList);
			
			//구독중인 회원 리스트 불러오기
			List<Object> subList = ob.getSubList(org_num);
			model.addAttribute("subList",subList);
			
			return "/org/org_myaction";
		} else {
			// 로그인이 안된 상태일때
			return "redirect:login.do";
		}
	}

	@RequestMapping("/org_pw_chkform.do")
	public String org_pw_chkform() {

		return "/org/org_pw_chkform";
	}

	@RequestMapping("/org_pw_chk.do")
	public String user_pw_chk(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user_pw", required = true) String user_pw) {
		logger.info("[org_pw_chk Page]");

		UserDto user = (UserDto) session.getAttribute("user");
		String user_pw_session = user.getUser_pw();

		// session의 user_pw와 입력된 user_pw 비교
		if (user_pw.equals(user_pw_session)) {
			// true면 user_updateform으로
			return "redirect:org_updateform.do";
		} else {
			// false면 경고창 출력
			return "redirect:org_pw_chkform.do";
		}
	}

	@RequestMapping("/org_updateform.do")
	public String org_updateform() {

		return "/org/org_updateform";
	}

	@RequestMapping("/org_updateRes.do")
	public void org_updateRes(UserDto user, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file, HttpSession session) {
		response.setContentType("text/html; charset=utf-8");
		logger.info("org_updateRes");
		UserDto dto = (UserDto) session.getAttribute("user");
		String user_pic = "";
		
		if (file.getSize() == 0) {
			// 사진 수정을 안할때, user_pic에 기존 pic만 담아줌
			user_pic = dto.getUser_pic();
		} else {
			// 사진 수정 할때, 업로드 처리 및 파일 이름 담아줌
			user_pic = mb.userfileUpload(file, request, user);
		}
		
		if (user_pic != "") {
			user.setUser_pic(user_pic);
			int res = mpb.update_member(user);

			if (res > 0) {
				try {
					session.invalidate();
					jsResponse("회원정보 수정 성공", "loginForm.do", response);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					jsResponse("회원정보 수정 실패", "org_updateform.do", response);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping("/subscribe.do")
	public void subscribe(HttpSession session, @RequestParam(value="org_num") int org_num,
			HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html; charset=utf-8");
		UserDto dto = (UserDto)session.getAttribute("user");
		String user_id = dto.getUser_id();
		
		int res=ob.subscribe(org_num, user_id);
		if(res>0) {
			try {
				jsResponse("구독 성공", "orgDetail.do?org_num="+org_num, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				jsResponse("구독 실패", "orgDetail.do?org_num="+org_num, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("/subscribeCancle.do")
	public void subscribeCancle(HttpSession session, @RequestParam(value="org_num") int org_num,
			HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html; charset=utf-8");
		UserDto dto = (UserDto)session.getAttribute("user");
		String user_id = dto.getUser_id();
		
		int res=ob.subscribeCancle(org_num, user_id);
		if(res>0) {
			try {
				jsResponse("구독취소 성공", "orgDetail.do?org_num="+org_num, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				jsResponse("구독취소 실패", "orgDetail.do?org_num="+org_num, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//phoneCheck
	   @RequestMapping("/updatePhoneCheck.do")
	   @ResponseBody
	   public Map updatePhoneCheck(@RequestBody Map param, HttpSession session) {
		   String user_phone = (String) param.get("user_phone");
		   logger.info("user_phone:"+user_phone);
		   UserDto dto = (UserDto)session.getAttribute("user");
		   String session_phone = dto.getUser_phone();
		   int res = 0;

		   if(user_phone.equals(session_phone)) {
			   //session의 전화번호와 입력된 전화번호가 일치하면 0
			   res=0;
		   }else {
			   // 전화번호 중복되면 res : 1, 없으면 0
			   if (mb.phoneCheck(user_phone) != 0) {
				   res = 1;
			   }
		   }
		   
		   Boolean is = (res==0)?true:false;
		   
		   Map<String,Boolean> map = new HashMap<String, Boolean>();
		   
		   map.put("check", is);
		   return map;
	   }
	
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript' charset='utf-8'>" + "alert('" + msg + "');" + "location.href='" + url
				+ "';" + "</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}
}
