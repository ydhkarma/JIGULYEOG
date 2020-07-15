package com.mvc.jigulyeog.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.mvc.jigulyeog.biz.CommunityBiz;
import com.mvc.jigulyeog.biz.ProjectBiz;
import com.mvc.jigulyeog.model.dto.CommunityGuestbookDto;
import com.mvc.jigulyeog.model.dto.CommunityNoticeDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.ProjectDto;
import com.mvc.jigulyeog.model.dto.UserDto;

@Controller
public class CommnunityController {
	Logger logger = LoggerFactory.getLogger(CommnunityController.class);
	
	@Autowired
	CommunityBiz cb;
	
	@Autowired
	ProjectBiz pb;
	
	// << 커뮤니티 생성 >>
	@RequestMapping("/createcomm.do")
	public String createCommunity(@RequestParam("pro_num") Integer pro_num) {
		logger.info("[ CommnunityController : createCommunity ]");
		
		// [1] 커뮤니티가 있는지 체크	
		Integer com_num = cb.checkCommnunity(pro_num);
		
		if(com_num==null) {
			// [2] 없으면 생성
			Boolean is = cb.createCommunity(pro_num);
			if(is) {
				logger.info("[ CommnunityController : createCommunity ]");
			}
			
		}

		return "redirect:community.do?pro_num="+pro_num;
	}
	
	@RequestMapping("/community.do")
	public String goCommunity(@RequestParam("pro_num") Integer pro_num, Model model,@RequestParam(value="nPage",required = false)Integer nPage,@RequestParam(value="gPage",required = false)Integer gPage ) {
		logger.info("[ CommnunityController : goCommunity ]");
		logger.info("pro_num : "+pro_num);
		
		// [1] 해당 project info
		ProjectDto project = pb.getProjectOne(pro_num);
		model.addAttribute("project",project);
		
		// [2] 해당 commNo 정보 가져오기
		int commNo = cb.getCommunityNo(pro_num);
		model.addAttribute("commNo",commNo);
		
		// [3] 해당 project 후원자
		List<UserDto> fundingUserList = cb.fundingUserList(pro_num);
		model.addAttribute("fundUserList",fundingUserList);
		
		// [4] 공지사항 리스트 가져오기
		// 만약, nPage가 넘어오지 않았다면,
		if(nPage==null) {
			nPage = 1;
		}
		
		Paging nPaging = cb.noticePaging(nPage,commNo);
		
		List<CommunityNoticeDto> NList = cb.noticeList(nPaging,commNo);
		PageMaker noticeMaker = cb.getPageMaker(nPaging);
		
		
		// this.number = this.totalPost -(this.pageNum-1)*this.pageSize;
		int nNum = nPaging.getTotalArticle() - (nPage-1)*nPaging.getPageSize();
		model.addAttribute("nNum",nNum);
		model.addAttribute("NList",NList);
		model.addAttribute("noticePageMaker",noticeMaker);
		model.addAttribute("nPage",nPage);
		
		
		// [5] 방명록 리스트 가져오기
		// 만약, nPage가 넘어오지 않았다면,
		if(gPage==null) {
			gPage = 1;
		}
		
		Paging gPaging = cb.guestbookPaging(gPage,commNo);
		List<CommunityGuestbookDto> GList = cb.guestbookList(gPaging,commNo);		
		PageMaker guestbookMaker = cb.getPageMaker(gPaging);
		
		model.addAttribute("GList",GList);
		model.addAttribute("guestbookMaker",guestbookMaker);
		model.addAttribute("gPage",gPage);
		
		return "/community/community_detail";
	}
	
	@RequestMapping("/commchat.do")
	public String getCommunityChat(@RequestParam("commNo") Integer commNo,Model model) {
		logger.info("[ CommnunityController : getCommunityChat ]");
		
		model.addAttribute("commNo",commNo);
		
		return "/community/community_chat";
	}
	
	@RequestMapping("/commnoticeform.do")
	public String commNoticeForm(@RequestParam("commNo") Integer commNo,@RequestParam("pro_num") Integer pro_num,Model model) {
		logger.info("[ CommnunityController : commNoticeForm ]");
		
		model.addAttribute("commNo",commNo);
		model.addAttribute("pro_num",pro_num);
		
		return "/community/community_notice_writeform";
	}
	
	//ckeditor 를 이용한 file 업로드
	@RequestMapping("/ckeditorNoticeFileupload.do")
	public String ckeditorNoticeFileUpload(HttpServletRequest req,HttpServletResponse res,@RequestParam MultipartFile upload) throws IOException{
		logger.info("[ CommnunityController : ckeditorFileUpload ]");
		res.setContentType("text/html; charset=utf-8");
		
		JsonObject json = new JsonObject();
		PrintWriter pw = res.getWriter();
		OutputStream out = null;
		
		MultipartFile file = upload; //업로드한 파일
		
		if(file != null) {
			if(file.getSize() > 0 && StringUtils.isNoneBlank(file.getName())) {
				// file의 type이 image라면
				
				if(file.getContentType().toLowerCase().startsWith("image/")) {
					try {
						String fileName = file.getName();
						byte[] bytes = file.getBytes();

						// upload path : /resources/upload/images/project/pro_detail
						String uploadPath = req.getSession().getServletContext().getRealPath("/resources/upload/images/community"); // 업로드 경로
						logger.info("[uploadPath :"+uploadPath+"]");
						
						File uploadFile = new File(uploadPath);
						
						// 경로 존재 여부
						if(!uploadFile.exists()) {
							uploadFile.mkdirs();
						}
						
						fileName = UUID.randomUUID().toString(); // 랜덤글자
						
						uploadPath = uploadPath+"/"+fileName;
						
						File newfile = new File(uploadPath);
				
						out = new FileOutputStream(newfile);
						
						out.write(bytes);
						
						pw = res.getWriter();
						res.setContentType("text/html");
						
						String fileUrl = req.getContextPath()+"/resources/upload/images/community/"+fileName;
						logger.info("[fileUrl : "+fileUrl+"]");
						
						// json 데이터로 등록
						// {"uploaded":1,"fileName":"test.jpg","url":"/resources/upload/test.jpg"}
						// 이런 형태로 리턴이 나가면 된다.
						
						json.addProperty("uploaded", 1);
						json.addProperty("fileName", fileName);
						json.addProperty("url", fileUrl);
						
						pw.println(json);
							
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						if(out != null) {
							out.close();
						}
						if(pw != null) {
							pw.close();
						}
						
					}
				}
				
			}
		} 
		
		return null;
	}
	
	// notice 작성
	@RequestMapping("/commnoticewrite.do")
	public void commNoticeWrite(HttpServletResponse response,CommunityNoticeDto notice,@RequestParam("pro_num") Integer pro_num) {
		logger.info("[ CommnunityController : commNoticeWrite ]");
		response.setContentType("text/html; charset=utf-8");
		
		Boolean is = cb.commNoticeWrite(notice);
		if(is==true) {
			try {jsResponse("공지사항이 작성되었습니다.","community.do?pro_num="+pro_num,response);}
			catch (IOException e) {e.printStackTrace();}
		}else {
			try {jsResponse("공지사항 작성 실패","community.do?pro_num="+pro_num,response);}
			catch (IOException e) {e.printStackTrace();}			
		}
		
	}
	
	@RequestMapping("noticedetail.do")
	public String notcieDetail(@RequestParam("notice_num") Integer notice_num,Model model,@RequestParam("pro_num") Integer pro_num) {
		logger.info("[ CommnunityController : notcieDetail ]");
		
		CommunityNoticeDto notice = cb.getNoticeOne(notice_num);
		model.addAttribute("notice",notice);
		
		ProjectDto project = pb.getProjectOne(pro_num);
		
		// 조회수 올리기
		cb.countViewNotice(notice_num);
		
		model.addAttribute("project",project);
		
		return "/community/community_notice_detail";
	}
	
	@RequestMapping("/noticeupdateform.do")
	public String noticeUpdateForm(@RequestParam("notice_num")Integer notice_num, @RequestParam("pro_num")Integer pro_num,Model model) {
		logger.info("[ CommnunityController : noticeUpdateForm ]");
		CommunityNoticeDto notice = cb.getNoticeOne(notice_num);
		model.addAttribute("notice",notice);
		model.addAttribute("pro_num",pro_num);
		
		return "/community/community_notice_updateform";
	}
	
	@RequestMapping(value="/commnoticeupdate.do",method=RequestMethod.POST)
	public void commNoticeUpdate(HttpServletResponse response,CommunityNoticeDto notice, @RequestParam("pro_num")Integer pro_num) {
		logger.info("[ CommnunityController : noticeUpdate ]");
		response.setContentType("text/html; charset=utf-8");
		
		Boolean is = cb.commNoticeUpdate(notice);
		
		if(is==true) {
			try {jsResponse("공지사항이 수정되었습니다.","noticedetail.do?notice_num="+notice.getNotice_num()+"&pro_num="+pro_num,response);}
			catch (IOException e) {e.printStackTrace();}
		}else {
			try {jsResponse("공지사항 수정 실패","noticedetail.do?notice_num="+notice.getNotice_num()+"&pro_num="+pro_num,response);}
			catch (IOException e) {e.printStackTrace();}			
		}
		
	}
	@RequestMapping(value="/noticedelete.do")
	public void commNoticeDelete(HttpServletResponse response, @RequestParam("pro_num")Integer pro_num,@RequestParam("notice_num")Integer notice_num) {
		logger.info("[ CommnunityController : noticeUpdate ]");
		response.setContentType("text/html; charset=utf-8");
		
		Boolean is = cb.commNoticeDelete(notice_num);
		
		if(is==true) {
			try {jsResponse("공지사항이 삭제되었습니다.","community.do?pro_num="+pro_num,response);}
			catch (IOException e) {e.printStackTrace();}
		}else {
			try {jsResponse("공지사항 삭제 실패","community.do?pro_num="+pro_num,response);}
			catch (IOException e) {e.printStackTrace();}			
		}
		
	}
	
	@RequestMapping(value="writeguestbook.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Boolean> writeCommGuestbook(@RequestBody CommunityGuestbookDto guestbook){
		logger.info("[ CommnunityController : writeCommGuestbook ]");
		
		Boolean is = cb.commGuestbookWrite(guestbook);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("check", is);
		return map;
	}
	
	@RequestMapping(value="updateguestbook.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Boolean> updateCommGuestbook(@RequestBody CommunityGuestbookDto guestbook){
		logger.info("[ CommnunityController : updateCommGuestbook ]");
		
		Boolean is = cb.commGuestbookUpdate(guestbook);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("check", is);
		return map;
	}
	
	@RequestMapping(value="deleteguestbook.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Boolean> deleteCommGuestbook(@RequestBody Map<String,Integer> param){
		logger.info("[ CommnunityController : updateCommGuestbook ]");
		int cation_num = param.get("cation_num");
		Boolean is = cb.commGuestbookDelete(cation_num);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("check", is);
		return map;
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
