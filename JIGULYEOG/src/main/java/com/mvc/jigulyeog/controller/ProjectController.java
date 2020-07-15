package com.mvc.jigulyeog.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import com.mvc.jigulyeog.biz.FundingBiz;
import com.mvc.jigulyeog.biz.ProjectBiz;
import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.ProjectDto;
import com.mvc.jigulyeog.model.dto.UserDto;

@Controller
public class ProjectController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	ProjectBiz pb;
	
	@Autowired
	FundingBiz fb;
	
	// Date type error : typeMisMatch
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        DateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
    }

	
	// ## 프로젝트 리스트 불러오기 ## //
	@RequestMapping(value="/projectlist.do",method=RequestMethod.GET)
	public String projectList(Model model,@RequestParam(value="page",required=false) Integer page,@RequestParam(value="category",required=false) Integer category,@RequestParam(value="keyword",required=false) String keyword) {
		logger.info("[ ProjectController : projectList ]");
		logger.info("[ page : "+page+" ]");
		
		// << 만약, 검색을 했으면 false, 하지 않았으면 true >>
		Boolean searchIs = (keyword==null||keyword=="")?true:false;
		
		// 검색 X
		if(searchIs) {
			// 최신 순 (category : 1)
			if(category==null||category==1) {
				// 만약 page가 넘어오지 않았다면,
				if(page==null) {
					page=1; // 첫 페이지를 보여준다.
				}
				
				Paging paging = pb.projectPaging(page); // Paging 설정
				
				List<ProjectDto> PList = pb.projectList(paging); // 게시글 리스트 불러오기
				
				PageMaker maker = pb.getPageMaker(paging);
				
				model.addAttribute("PList",PList);
				model.addAttribute("page",page);
				model.addAttribute("pageMaker",maker);
				model.addAttribute("category",1);
				
			}else if(category == 2) {
				// 오래된 순 (category : 2)
				
				// 만약 page가 넘어오지 않았다면,
				if(page==null) {
					page=1; // 첫 페이지를 보여준다.
				}
				
				Paging paging = pb.projectPaging(page); // Paging 설정
				
				List<ProjectDto> PList = pb.getArticleOldList(paging); // 게시글 리스트 불러오기
				
				PageMaker maker = pb.getPageMaker(paging);
				
				model.addAttribute("PList",PList);
				model.addAttribute("page",page);
				model.addAttribute("pageMaker",maker);
				model.addAttribute("category",2);
						
			}else if(category == 3) {
				// 모금 순 (category : 3)
				
				// 만약 page가 넘어오지 않았다면,
				if(page==null) {
					page=1; // 첫 페이지를 보여준다.
				}
				
				Paging paging = pb.projectPaging(page); // Paging 설정
				
				List<ProjectDto> PList = pb.getArticleManyList(paging); // 게시글 리스트 불러오기
				
				PageMaker maker = pb.getPageMaker(paging);
				
				model.addAttribute("PList",PList);
				model.addAttribute("page",page);
				model.addAttribute("pageMaker",maker);
				model.addAttribute("category",3);		
			}
		}else {
			// 검색 O
			
			// 만약 page가 넘어오지 않았다면,
			if(page==null) {
				page=1; // 첫 페이지를 보여준다.
				}
			
			keyword=keyword.trim(); // 혹시 모를 공백 제거
			
			Paging paging = pb.projectPagingSearch(page,keyword); // Paging 설정
			
			List<ProjectDto> PList = pb.projectSearch(paging,keyword); // 게시글 리스트 불러오기
			
			PageMaker maker = pb.getPageMaker(paging);
			
			model.addAttribute("PList",PList);
			model.addAttribute("page",page);
			model.addAttribute("pageMaker",maker);
			model.addAttribute("keyword",keyword);
		}

		return "/project/project_list";
	}
	
	// ## 프로젝트 작성 폼 보여주기 ## //
	@RequestMapping("/projectwriteform.do")
	public String projectWriteForm() {
		logger.info("[ ProjectController : projectWriteForm ]");
		
		return "/project/project_writeform";
	}
	
	// ## ckeditor을 이용한 사진 업로드 ## //
	@RequestMapping(value="/ckeditorProjectFileupload.do",method=RequestMethod.POST)
	public String ckeditorProjectFileUpload(HttpServletRequest req,HttpServletResponse res,@RequestParam MultipartFile upload) throws IOException {
		logger.info("[ ProjectController : ckeditorFileUpload ]");
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
						String uploadPath = req.getSession().getServletContext().getRealPath("/resources/upload/images/project/pro_detail"); // 업로드 경로
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
						
						String fileUrl = req.getContextPath()+"/resources/upload/images/project/pro_detail/"+fileName;
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
	

	// ## 프로젝트 작성 기능 ## //
	@RequestMapping(value="/projectwrite.do",method=RequestMethod.POST)
	public void projectWrite(ProjectDto project,HttpServletRequest request,HttpServletResponse response,@RequestParam("file") MultipartFile file) {
		response.setContentType("text/html; charset=utf-8");
		
		logger.info("[ ProjectController : projectWrite ]");
		
		String pro_image = pb.projectfileUpload(file, request); // 대표이미지 설정
		
		if(pro_image!="") {
			logger.info("[ ProjectController : Success setting Image ]");
			project.setPro_image(pro_image);
			boolean is = pb.projectWrite(project);
			if(is==true) {
				try {jsResponse("프로젝트가 등록 되었습니다.","projectlist.do",response);}
				catch (IOException e) {e.printStackTrace();}		
			}else {				
				try {jsResponse("프로젝트가 등록 에러.","projectlist.do",response);}
				catch (IOException e) {e.printStackTrace();}						
			}
			
		}else {
			try {jsResponse("이미지 등록 에러","projectlist.do",response);}
			catch (IOException e) {e.printStackTrace();}
		}
	}
	
	// ## 프로젝트 상세 폼 ## //
	@RequestMapping("/projectdetail.do")
	public String projectDetail(@RequestParam("pro_num") Integer pro_num,Model model,HttpSession session) {
		logger.info("[ ProjectController : projectDetail ]");

		ProjectDto project = pb.getProjectOne(pro_num);
		OrgDto org = pb.getProjectOneOrg(project.getUser_id());

		model.addAttribute("project",project); // project info
		model.addAttribute("org",org); // org info
		
		Boolean fundingChk = false; // 기부 여부
		
		// User 기부 여부 확인 (추 후, 중복 기부를 위함)
		if(session.getAttribute("user")!=null) {
			UserDto user = (UserDto) session.getAttribute("user");
			logger.info("[ login user : "+user.getUser_id()+" ]");
			fundingChk = fb.checkFundingUser(user.getUser_id(),pro_num);
			logger.info("[ funding Check :"+fundingChk+" ]");
		}
		
		model.addAttribute("fundingChk",fundingChk);
		
		// D-Day 구하기
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
	
		String currentTime = mSimpleDateFormat.format(new Date());
		String endTime = mSimpleDateFormat.format(project.getPro_due_date());
		
        // 두 날짜를 계산한 뒤 그 리턴값으로 long type 변수를 초기화 하고 있다.
        // 연산결과 -950400000. long type 으로 return 된다.
		long calDate = project.getPro_due_date().getTime() - (new Date()).getTime();
		
		 // Date.getTime() 은 해당날짜를 기준으로1970년 00:00:00 부터 몇 초가 흘렀는지를 반환해준다. 
        // 이제 24*60*60*1000(각 시간값에 따른 차이점) 을 나눠주면 일수가 나온다.
        long calDateDays = calDate / ( 24*60*60*1000); 

        //logger.info("calDateDays"+calDateDays);
        //calDateDays = Math.abs(calDateDays);
        logger.info("calDateDays"+Math.abs(calDateDays));
        
        logger.info("D-Day : "+(int)calDateDays);
        model.addAttribute("dday", (int)calDateDays);
		
		
		return "/project/project_detail";
	}
	
	
	// ## 프로젝트 수정 폼 보여주기 ## //
	@RequestMapping("/projectupdateform.do")
	public String projectUpdateForm(@RequestParam("pro_num") int pro_num,Model model) {
		logger.info("[ ProjectController : projectUpdateForm ]");
		logger.info("[ pro_num : "+pro_num+" ]");
		
		ProjectDto project = pb.getProjectOne(pro_num); // 해당 project info
		model.addAttribute("project", project);
		
		return "/project/project_updateform";
	} 
	
	// ## 프로젝트 수정 기능 ## //
	@RequestMapping("/projectupdate.do")
	public void projectUpdate(ProjectDto project,HttpServletRequest request,HttpServletResponse response,@RequestParam(value="file",required=false) MultipartFile file) {
		response.setContentType("text/html; charset=utf-8");
		logger.info("[ ProjectController : projectUpdate ]");
		
		logger.info("file not changed? : "+file.isEmpty()); // 대표이미지 수정 여부를 알기 위함
		
		// file.isEmpty() ==> true : 대표이미지 수정 X & file.isEmpty() ==> false : 대표이미지 수정
		if(!file.isEmpty()) {
			// 대표이미지 수정 O
			String pro_image = pb.projectfileUpload(file, request); // 다시 설정
			if(pro_image!="") {
				logger.info("[ ProjectController : Success setting Image ]");
				project.setPro_image(pro_image);				
			}else {
				try {jsResponse("이미지 등록 에러","projectlist.do",response);}
				catch (IOException e) {e.printStackTrace();}
			}
		}
		
	
	boolean is = pb.projectUpdate(project);
	
	if(is==true) {
			try {jsResponse("프로젝트가 수정되었습니다.","projectlist.do",response);}
			catch (IOException e) {e.printStackTrace();}		
			
		}else {
			try {jsResponse("프로젝트 수정 에러.","projectlist.do",response);}
			catch (IOException e) {e.printStackTrace();}		
		}
		
	}
	
	// ## 프로젝트 삭제 기능 ## //
	@RequestMapping("/projectdelete.do")
	public void projectDelete(@RequestParam int pro_num,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		logger.info("[ ProjectController : projectDelete ]");
		
		boolean is = pb.projectDelete(pro_num);
		if(is==true) {
			try {jsResponse("프로젝트가 삭제되었습니다.","projectlist.do",response);}
			catch (IOException e) {e.printStackTrace();}		
			
		}else {
			try {jsResponse("프로젝트 삭제 에러.","projectlist.do",response);}
			catch (IOException e) {e.printStackTrace();}		
		}
		
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
