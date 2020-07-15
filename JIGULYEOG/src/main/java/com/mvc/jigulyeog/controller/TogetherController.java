package com.mvc.jigulyeog.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.mvc.jigulyeog.biz.TogetherApplyBiz;
import com.mvc.jigulyeog.biz.TogetherBiz;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.TogetherApplyDto;
import com.mvc.jigulyeog.model.dto.TogetherDto;
import com.mvc.jigulyeog.model.dto.UserDto;

@Controller
public class TogetherController {
	
	@Autowired
	private TogetherBiz biz;
	
	@Autowired
	private TogetherApplyBiz AppBiz;
	
	HttpSession session;
	
	private static final Logger logger = LoggerFactory.getLogger(TogetherController.class);
	
	/*솔지 수정부분 */
	
	// Date type error : typeMisMatch
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        DateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
    }
	 
	// 함께해요 페이지가기(=목록_list 보기)
		@RequestMapping("/together.do")
		public String together(Model model,@RequestParam(value="page",required=false) Integer page,@RequestParam(value="category",required=false) Integer category,@RequestParam(value="keyword",required=false) String keyword) {
			logger.info("함께해요 페이지 요청(list포함)");
			logger.info("page: "+page+","+"category:"+category+"keyword: "+keyword);
			boolean search = (keyword==null||keyword=="")?true:false;
			if(search) {
				if(category==null) {
					if(page==null) {
						page=1; // 첫 페이지를 보여준다.
					}
					logger.info("page : "+page);
					Paging paging = biz.togetherPaging(page); // paging 설정
					List<TogetherDto> list = biz.selectList(paging); // 게시글 리스트불러오기f
					PageMaker maker = biz.getPageMaker(paging);
					
					model.addAttribute("list",list);
					model.addAttribute("page",page);
					model.addAttribute("pageMaker",maker);
				} else if(category==1) {
					if(page==null) {
						page=1; // 첫 페이지를 보여준다.
					}
					Paging paging = biz.togetherFundingPaging(page);
					List<TogetherDto> list = biz.selectFundingList(paging);
					PageMaker maker = biz.getPageMaker(paging);
					
					model.addAttribute("list",list);
					model.addAttribute("page",page);
					model.addAttribute("pageMaker",maker);
					model.addAttribute("category",1);
					
				} else if(category==2) {
					if(page==null) {
						page=1; // 첫 페이지를 보여준다.
					}
					Paging paging = biz.togetherSharePaging(page);
					List<TogetherDto> list = biz.selectShareList(paging);
					PageMaker maker = biz.getPageMaker(paging);
					logger.info("22222222");
					model.addAttribute("list",list);
					model.addAttribute("page",page);
					model.addAttribute("pageMaker",maker);
					model.addAttribute("category",2);
					
				} else if(category==3) {
					if(page==null) {
						page=1; // 첫 페이지를 보여준다.
					}
					Paging paging = biz.togetherVolunteerPaging(page);
					List<TogetherDto> list = biz.selectVolunteerList(paging);
					PageMaker maker = biz.getPageMaker(paging);
					
					model.addAttribute("list",list);
					model.addAttribute("page",page);
					model.addAttribute("pageMaker",maker);
					model.addAttribute("category",3);
					
				}
				
				
			} else {
				if(page==null) {
					page=1;
				}
				keyword=keyword.trim();
				Paging paging = biz.togetherSearchPaging(page, keyword);
				List<TogetherDto> list = biz.togetherSearch(paging, keyword);
				PageMaker maker = biz.getPageMaker(paging);
				
				model.addAttribute("list",list);
				model.addAttribute("page",page);
				model.addAttribute("pageMaker",maker);
				model.addAttribute("keyword",keyword);
				
			}
			return "/together/together";
		}
		
		// 함께해요 작성폼가기
		@RequestMapping("/together_writeform.do")
		public String together_writeform() {
			logger.info("함께해요 작성하기 요청");
			return "/together/together_writeform";
		}
		
		
		// 함께해요 작성완료 or 취소
		@RequestMapping(value="/together_write.do",method=RequestMethod.POST)
		   public String together_write(TogetherDto dto,HttpServletRequest request,@RequestParam("file") MultipartFile file) {
			   logger.info("함께해요 작성완료 요청");
			   String tog_image = biz.TogetherfileUpload(file, request);
			   
			   
			   if(tog_image!="") {
				   dto.setTog_image(tog_image);
				   int res = biz.insert(dto);
				   if(res>0) {
					   return "redirect:together.do";
				   } else {
					   return "redirect:together_writeform.do";
				   }
			   } else {
				   return "redirect:together_writeform.do";
			   }
			
		   }
		
		// 함께해요 자세히보기
		@RequestMapping(value="/together_detail.do")
		public String together_detail(TogetherApplyDto AppDto,Model model, @RequestParam("tog_no") int tog_no, HttpServletRequest request) {
			
			UserDto user;
			try {
				
				user = (UserDto) request.getSession().getAttribute("user");
				String user_id = user.getUser_id();
				
				logger.info("tog_no : "+tog_no);
				logger.info("user_id : "+user_id);
				
				model.addAttribute("res",AppBiz.select(tog_no, user_id));
				logger.info("check -- res "+model.containsAttribute("res"));
				
				/***************솔지 수정 부분*****************/
				TogetherDto together = biz.selectOne(tog_no); 
				/*이미지 출력을 위해 user_id정보를 가져오는 로직 추가*/
				UserDto writeUser = biz.selectWriteUser(together.getUser_id());
				model.addAttribute("dto",together);
				model.addAttribute("writeUser",writeUser);
				
			} catch (NullPointerException e) {
				logger.info("예외확인");
				logger.info("tog_no : "+tog_no);
				e.printStackTrace();
				TogetherDto together = biz.selectOne(tog_no); 
				UserDto writeUser = biz.selectWriteUser(together.getUser_id());
				model.addAttribute("dto",together);
				model.addAttribute("writeUser",writeUser);
				/*******************************************/
				
			}
			
			return "/together/together_detail";
		}
		
		
		//함께해요 수정요청
		@RequestMapping("/together_updateform.do")
		public String together_updateform(Model model, int tog_no) {
			logger.info("함께해요 수정폼요청");
			model.addAttribute("dto",biz.selectOne(tog_no));
			return "/together/together_updateform";
		}
		
		//함께해요 수정완료 or 취소
		@RequestMapping("together_update")
		public String together_updatecomplete(TogetherDto dto, HttpServletRequest request,@RequestParam(value="file",required=false) MultipartFile file) {
			logger.info("함께해요 수정완료");
			
			
			// 대표이미지 설정 X ==> 그대로 유지
			if(!file.isEmpty()) {
				String tog_image = biz.TogetherfileUpload(file, request);
				if(tog_image!="") {
					dto.setTog_image(tog_image);				
				}else {
					try {
						logger.info("이미지등록에러");
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}
			
			int res = biz.update(dto);
			
			if(res>0) {
				return "redirect:together_detail.do?tog_no="+dto.getTog_no();
			}else {
				return "redriect:together_updateform.do?tog_no="+dto.getTog_no();
			}
		}
		
		//함께해요 삭제
		@RequestMapping("/together_delete.do")
		public String together_delete(int tog_no) {
			logger.info("delete");
			int res = biz.delete(tog_no);
			if(res>0) {
				return "redirect:together.do";
			} else {
				return "redirect:together_detail.do?tog_no="+tog_no;
			}
			
		}
		
		// ckeditor을 이용한 사진 업로드
		@RequestMapping(value="/togetherDetailFile.do",method=RequestMethod.POST)
		public String fileUpload(HttpServletRequest req,HttpServletResponse res,@RequestParam MultipartFile upload) throws IOException {
			logger.info("[ ProjectController : fileUpload ]");
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

							// upload path : /resources/upload/images/together/together_detail
							String uploadPath = req.getSession().getServletContext().getRealPath("/resources/upload/images/together/together_detail"); // 업로드 경로
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
							
							String fileUrl = req.getContextPath()+"/resources/upload/images/together/together_detail/"+fileName;
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
	
}
