package com.mvc.jigulyeog.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.mvc.jigulyeog.biz.ChungwonBiz;
import com.mvc.jigulyeog.biz.SignUpBiz;
import com.mvc.jigulyeog.model.dto.ChungwonDto;
import com.mvc.jigulyeog.model.dto.PageNavigator;
import com.mvc.jigulyeog.model.dto.UserDto;


@Controller
public class ChungwonController {
	
	@Autowired
	private ChungwonBiz biz;
	//댓글 리스트를 디테일 폼과 함께 가져오기 위해 @Autowired
	@Autowired
	private SignUpBiz biz2;
	
	
	//페이지당 글 목록 수
	final static int COUNTPERPAGE = 6;
	//그룹당 페이지 수 
	final static int PAGEPERGROUP = 3;
	
	private static final Logger logger = LoggerFactory.getLogger(ChungwonController.class);
	
	//selectAll
	//pasing,search
	@RequestMapping("/chunglist.do")
	public String selectList(Model model,@RequestParam(value="page",defaultValue="1") int page, String searchWord, @RequestParam(value="category",required=false) Integer category) {
		
		logger.info("SELECTLIST CHUNG");
		System.out.println(category);
		if(category == null||category == 1 ) {
			PageNavigator navi = new PageNavigator(COUNTPERPAGE,PAGEPERGROUP,page,biz.selectCount(searchWord));
			
			model.addAttribute("list",biz.selectList(navi,searchWord));
			//,descStatus
			model.addAttribute("navi",navi);
			model.addAttribute("searchWord",searchWord);
			model.addAttribute("category",1);
		}else if(category == 2) {
			PageNavigator navi = new PageNavigator(COUNTPERPAGE,PAGEPERGROUP,page,biz.selectCount(searchWord));
		
			model.addAttribute("list",biz.newestList(navi,searchWord));
			
			model.addAttribute("navi",navi);
			model.addAttribute("searchWord",searchWord);
			model.addAttribute("category",2);
		}
		
		return "/chungwon/petition_list";
	}
	
	
	//selectOne
	@RequestMapping(value="/chungdetail.do",method=RequestMethod.GET)
	public String selectOne(Model model,int pet_no) {
		
		logger.info("SELECTONE CHUNG");
		model.addAttribute("dto",biz.selectOne(pet_no));
		
		//댓글 리스트
		model.addAttribute("list",biz2.sigList(pet_no));
		
		
		return "/chungwon/petition_detailform";
	}
	
	//writeform으로 이동
	@RequestMapping("/chungwriteform.do")
	public void chungwonWrite(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		logger.info("WRITEFORM CHUNG");
		
		
		/////////////////////////////(시작)login/회원가입 합칠 때 만든 코드 /새로 하려면지워두 됨 
		//세션을 가져와서 login했으면 작성 가능 , login안했으면 login 페이지로
		UserDto user = (UserDto)session.getAttribute("user");
				
		if(user !=  null && user.getUser_id() != null) {	
			
			dispatch("/WEB-INF/views/chungwon/petition_write.jsp",request,response);
			
		}else {	
			
			jsResponse("로그인해야 작성 가능합니다.","loginForm.do",response);	
			
		}
		/////////////////////////////(끝)login/회원가입 합칠 때 만든 코드 /새로 하려면지워두 됨  
	}
	//insert
	//fileupload
	@RequestMapping("/chungwriteres.do")
	public String chungwonInsert(HttpServletRequest request , Model model,ChungwonDto dto,
									MultipartFile file) {
		
		logger.info("INSERT CHUNHG");
		
		
		String filename = file.getOriginalFilename();
		dto.setPet_photo(filename);
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
			inputStream = file.getInputStream();
			
			String path = WebUtils.getRealPath(request.getSession().getServletContext(),"resources/upload/images/chungwon");
			
			System.out.println("업로드될 실제 경로  : "+path );
			
			File storage = new File(path);
			
			if(!storage.exists()) {
				storage.mkdirs();
			}
			
			File newfile = new File(path+"/"+filename);
			if(!newfile.exists()) {
				newfile.createNewFile();
			}
			
			outputStream = new FileOutputStream(newfile);
			
			int read = 0;
			
			byte[] b = new byte[(int)file.getSize()];
			
			while((read=inputStream.read(b)) != -1) {
				outputStream.write(b,0,read);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			inputStream.close();
			outputStream.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		/////////////////////////////(시작)login/회원가입 합칠 때 만든 코드 /새로 하려면지워두 됨 
		//session의 user_id를 가져오는 코드
		String user_id = ((UserDto)request.getSession().getAttribute("user")).getUser_id();
		dto.setUser_id(user_id);
		/////////////////////////////(끝)login/회원가입 합칠 때 만든 코드 /새로 하려면지워두 됨 

		int res = biz.insert(dto);

		if(res>0) {
			return "redirect:chunglist.do";
		}else {
			return "redirect:chungwriteform.do";
		}
	}
	
	
	//delete
	@RequestMapping("/chungdelete")
	public String chungDelete(int pet_no) {
		
		logger.info("DELETE CHUNG");
		
		int res = biz.chungDelete(pet_no);
		if(res>0) {
			return "redirect:chunglist.do";
		}else {
			return "redirect:/chungdetail.do?pet_no"+pet_no;
		}
	}
	
	
	//updateform으로 이동
	@RequestMapping("/chungupdateform.do")
	public String chungUpdateform(Model model , int pet_no) {
		logger.info("UPDATE FORM");
		model.addAttribute("dto",biz.selectOne(pet_no));
		return "/chungwon/petition_updateform";
	}
	
	//update
	@RequestMapping("/chungupdateres.do")
	public String chungUpdateres(ChungwonDto dto,MultipartFile file, HttpServletRequest request) {
		
		logger.info("UPDATE RES");
		String filename = file.getOriginalFilename();
		dto.setPet_photo(filename);
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
			inputStream = file.getInputStream();
			
			String path = WebUtils.getRealPath(request.getSession().getServletContext(),"resources/upload/images/chungwon");
			
			System.out.println("업로드될 실제 경로  : "+path );
			
			File storage = new File(path);
			
			if(!storage.exists()) {
				storage.mkdirs();
			}
			
			File newfile = new File(path+"/"+filename);
			if(!newfile.exists()) {
				newfile.createNewFile();
			}
			
			outputStream = new FileOutputStream(newfile);
			
			int read = 0;
			
			byte[] b = new byte[(int)file.getSize()];
			
			while((read=inputStream.read(b)) != -1) {
				outputStream.write(b,0,read);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			inputStream.close();
			outputStream.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		int res = biz.chungUpdate(dto);
		
		if(res>0) {
			return "redirect:chungdetail.do?pet_no="+dto.getPet_no();
		}else {
			return "redirect:chungupdateform.do?pet_no="+dto.getPet_no();
		}
		
	}

	private void dispatch( String url, HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	      RequestDispatcher dispatch = request.getRequestDispatcher( url );
	      dispatch.forward( request, response );
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
