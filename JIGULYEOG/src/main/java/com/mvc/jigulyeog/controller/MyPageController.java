package com.mvc.jigulyeog.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.mvc.jigulyeog.biz.MemberBiz;
import com.mvc.jigulyeog.biz.MyPageBiz;
import com.mvc.jigulyeog.model.dto.ChungwonDto;
import com.mvc.jigulyeog.model.dto.MyPagePageMaker;
import com.mvc.jigulyeog.model.dto.MyPagePaging;
import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.ProjectDto;
import com.mvc.jigulyeog.model.dto.SignUpDto;
import com.mvc.jigulyeog.model.dto.TogetherApplyDto;
import com.mvc.jigulyeog.model.dto.TogetherDto;
import com.mvc.jigulyeog.model.dto.UserDto;

@Controller
public class MyPageController {
	private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);

	@Autowired
	private MyPageBiz mpb;

	@Autowired
	private MemberBiz mb;

	// 마이 페이지 정보 뿌리기용 임시 작업용.
	// 로그인 버튼 누르면 session에 정보 담고 index 페이지로 이동.
	@RequestMapping("/login.do")
	public String login(HttpSession session) {
		logger.info("[login 임시]");
//		UserDto user = new UserDto("admin", "admin", "관리자", "관리자별칭", "01011111111", "서울시 강남구 역삼동", "test_person1.jpg",
//				0);

		UserDto user = new UserDto("ORG01", "1111", "(사)무지개세상", "장승진", "01012341234", "서울특별시 서초구 매헌로 54 (양재동)", "", 1);

		session.setAttribute("user", user);

		return "redirect:index.do";
	}

	@RequestMapping("/myPage.do")
	public String user_mypage(HttpServletRequest request,Model model) {
		logger.info("[user_mypage Page]");
		
		if(request.getSession().getAttribute("snsChk")!=null) {
			model.addAttribute("snsCheck","true");
		}
		return "/mypage/user_mypage";
	}

	@RequestMapping("/myDonate.do")
	public String user_mydonate(HttpSession session, Model model) {
		logger.info("[user_mydonate Page]");

		UserDto user = (UserDto) session.getAttribute("user");
		String user_id = user.getUser_id();

		List<ProjectDto> projectList = mpb.getMyDonateProjectList(user_id);
		List<OrgDto> orgList = mpb.getMySubscribeOrgList(user_id);

		for (ProjectDto dto : projectList) {
			System.out.println(dto);
		}
		;
		for (OrgDto dto : orgList) {
			System.out.println(dto);
		}

		model.addAttribute("projectList", projectList);
		model.addAttribute("orgList", orgList);

		return "/mypage/user_mydonate";
	}

	@RequestMapping(value = "/myAction.do", method = RequestMethod.GET)
	public String user_myaction(HttpSession session, Model model,
			@RequestParam(value = "CWPage", required = false) Integer CWPage,
			@RequestParam(value = "TGPage", required = false) Integer TGPage) {
		logger.info("[user_myaction Page]");
		logger.info("[page : " + CWPage + "]");

		UserDto user = (UserDto) session.getAttribute("user");
		String user_id = user.getUser_id();

		// **** 내가 서명한 청원 리스트 불러오기 ***
		List<ChungwonDto> signUpList = mpb.getMySignUpList(user_id);
		logger.info("[signUpList size: " + signUpList.size() + "]");
		model.addAttribute("signUpList", signUpList);

		// *** 내가 작성한 청원 리스트 불러오기 & paging 처리 ***
		// 최신 순으로
		if (CWPage == null) { // page 값이 넘어오지 않았다면
			CWPage = 1; // 첫 페이지를 보여준다.
		}

		MyPagePaging CWPaging = mpb.myChungwonPaging(CWPage, user_id); // 나의 청원 Paging 설정
		List<ChungwonDto> CWList = mpb.getMyChungwonList(CWPaging, user_id); // 내가 작성한 청원 게시글 불러오기
		logger.info("[chungwonList size: " + CWList.size() + "]");
		MyPagePageMaker CWMaker = mpb.getPageMaker(CWPaging);

		model.addAttribute("CWList", CWList);
		model.addAttribute("CWPage", CWPage);
		model.addAttribute("CWPageMaker", CWMaker);

		// **** 내가 요청한 함께해요 리스트 불러오기 ***
		List<TogetherDto> togetherApplyList = mpb.getMyTogetherApplyList(user_id);
		logger.info("[togetherApplyList size: " + togetherApplyList.size() + "]");
		model.addAttribute("togetherApplyList", togetherApplyList);

		// *** 내가 작성한 함께해요 리스트 불러오기 & paging 처리 ***
//		List<TogetherDto> togetherList = mpb.getMyTogetherList(user_id);
//		logger.info("[togetherList size: " + togetherList.size() + "]");
		// 최신 순으로
		if (TGPage == null) { // page 값이 넘어오지 않았다면
			TGPage = 1; // 첫 페이지를 보여준다.
		}

		MyPagePaging TGPaging = mpb.myTogetherPaging(TGPage, user_id); // 나의 청원 Paging 설정
		List<TogetherDto> TGList = mpb.getMyTogetherList(TGPaging, user_id); // 내가 작성한 청원 게시글 불러오기
		System.out.println(TGList.size());
		logger.info("[TGList size: " + TGList.size() + "]");
		MyPagePageMaker TGMaker = mpb.getPageMaker(TGPaging);

		model.addAttribute("TGList", TGList);
		model.addAttribute("TGPage", TGPage);
		model.addAttribute("TGPageMaker", TGMaker);

		return "/mypage/user_myaction";
	}

	@RequestMapping("/SUExcelDown.do")
	@ResponseBody
	public byte[] SUExcelDown(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pet_no", required = false) Integer pet_no,
			@RequestParam(value = "pet_title", required = false) String pet_title) {
		System.out.println(pet_no);
		System.out.println(pet_title);

		// 청원 글에 대한 서명리스트틀 불러온다.
		List<SignUpDto> SUList = mpb.getSignUpList(pet_no);
		System.out.println(SUList.size());

		String filename = "/" + pet_title + " 서명자 엑셀 추출.xls";
		// 서명 리스트를 엑셀로 생성하여 저장한다.
		mpb.SUExcelCreate(request, SUList, filename);
		byte[] bytes = null;
		try {
			// 엑셀 경로경로
			String path = WebUtils.getRealPath(request.getSession().getServletContext(), "/resources/excel");
			File file = new File(path + filename);

			bytes = FileCopyUtils.copyToByteArray(file);
			String fn = new String(file.getName().getBytes(), "8859_1");

			response.setHeader("Content-Disposition", "attachment;filename=\"" + fn + "\"");
			response.setHeader("Content-Description", "JSP Generated Data");
			response.setContentLength(bytes.length);
			response.setContentType("application/vnd.ms-excel");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	@RequestMapping("/TGAExcelDown.do")
	@ResponseBody
	public byte[] TGAExcelDown(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "tog_no", required = false) Integer tog_no,
			@RequestParam(value = "tog_title", required = false) String tog_title) {
		System.out.println(tog_no);
		System.out.println(tog_title);

		// 청원 글에 대한 서명리스트틀 불러온다.
		List<TogetherApplyDto> TGAList = mpb.getTogetherApplyList(tog_no);
		System.out.println(TGAList.size());

		String filename = "/" + tog_title + " 신청자 엑셀 추출.xls";
		// 서명 리스트를 엑셀로 생성하여 저장한다.
		mpb.TGAExcelCreate(request, TGAList, filename);
		byte[] bytes = null;
		try {
			// 엑셀 경로경로
			String path = WebUtils.getRealPath(request.getSession().getServletContext(), "/resources/excel");
			File file = new File(path + filename);

			bytes = FileCopyUtils.copyToByteArray(file);
			String fn = new String(file.getName().getBytes(), "8859_1");

			response.setHeader("Content-Disposition", "attachment;filename=\"" + fn + "\"");
			response.setHeader("Content-Description", "JSP Generated Data");
			response.setContentLength(bytes.length);
			response.setContentType("application/vnd.ms-excel");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	@RequestMapping("/secession.do")
	public String secession() {
		logger.info("[move to secession Page]");

		return "/mypage/secession";
	}

	@RequestMapping("secessionRes.do")
	public void secession(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user_pw", required = true) String user_pw) throws IOException {
		UserDto user = (UserDto) session.getAttribute("user");
		String user_pw_session = user.getUser_pw();

		// session의 user_pw와 입력된 user_pw 비교
		if (user_pw.equals(user_pw_session)) {
			//비밀번호가 일치 할 때
			//환경단체인지 일반단체인지 확인
			int org_num = user.getUser_status();
			String user_id = user.getUser_id();
			
			if(org_num>0) {
				//환경단체 회원 일 때, org_pic을 null로 변경 및 user db삭제
				int update_res = mpb.secession_org_update(org_num);
				int delete_res = mpb.secession(user_id);
				if (delete_res > 0 && update_res > 0) {
					session.invalidate();
					jsResponse("회원탈퇴가 완료되었습니다.", "index.do", response);
				} else {
					jsResponse("오류가 발생하여 회원탈퇴를 실패하였습니다.", "index.do", response);
				}
			} else {
				//일반 회원 일 때, user db삭제
				int delete_res = mpb.secession(user_id);
				if (delete_res > 0) {
					session.invalidate();
					jsResponse("회원탈퇴가 완료되었습니다.", "index.do", response);
				} else {
					jsResponse("오류가 발생하여 회원탈퇴를 실패하였습니다.", "index.do", response);
				}
			}
		} else {
			//비밀번호가 일치하지 않을 때
			jsResponse("비밀번호가 일치하지 않습니다.", "secession.do", response);
		}
	}

	@RequestMapping("/user_pw_chkform.do")
	public String user_pw_chkform() {
		logger.info("[user_pw_chkform Page]");

		return "/mypage/user_pw_chkform";
	}

	@RequestMapping("/user_pw_chk.do")
	public String user_pw_chk(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user_pw", required = true) String user_pw){
		logger.info("[user_pw_chk Page]");

		UserDto user = (UserDto) session.getAttribute("user");
		String user_pw_session = user.getUser_pw();

		// session의 user_pw와 입력된 user_pw 비교
		if (user_pw.equals(user_pw_session)) {
			// true면 user_updateform으로
			return "redirect:user_updateform.do";
		} else {
			// false면 경고창 출력
			return "redirect:user_pw_chkform.do";
		}
	}

	@RequestMapping("/user_updateform.do")
	public String user_updateform( HttpServletRequest request,Model model) {
		logger.info("[user_updateform Page]");
		
		if(request.getSession().getAttribute("snsChk")!=null) {
			model.addAttribute("snsCheck","true");
		}

		return "/mypage/user_updateform";
	}

	@RequestMapping("/user_updateRes.do")
	public void user_updateRes(UserDto user, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file, HttpSession session) {
		response.setContentType("text/html; charset=utf-8");
		logger.info("[user_updateRes]");
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
					jsResponse("회원정보 수정 실패", "user_updateform.do", response);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// nickCheckForUpdate
	@RequestMapping("/nickCheckForUserUpdate.do")
	@ResponseBody
	public Map nickCheck(@RequestBody Map param, HttpSession session) {
		String user_nick = (String) param.get("user_nick");

		UserDto user = (UserDto) session.getAttribute("user");
		String session_nick = user.getUser_nick();
		logger.info("user_nick: " + user_nick);
		logger.info("session_nick: " + session_nick);
		int res = 0;

		// session의 nick과 입력된 nick이 같으면 사용가능으로 표시
		if (user_nick.equals(session_nick)) {
			// session의 nick과 입력된 nick이 같으면
			// res=0으로 놔둬서 사용가능으로 표시
		} else {
			// session의 nick과 입력된 nick이 다르면 nickCheck 실시
			if (mb.nickCheck(user_nick) != 0) {
				res = 1;
			}
		}
		// id 중복되면 res : 1, 없거나 session과 같으면 0

		Boolean is = (res == 0) ? true : false;

		Map<String, Boolean> map = new HashMap<String, Boolean>();

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
