package com.mvc.jigulyeog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.mvc.jigulyeog.biz.KakaoAPI;
import com.mvc.jigulyeog.biz.MemberBiz;
import com.mvc.jigulyeog.biz.NaverLoginBO;
import com.mvc.jigulyeog.biz.OrgBiz;
import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.UserDto;


@Controller
public class MemberController {
   private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
   
   @Autowired
   private MemberBiz mb;
   
   @Autowired
   OrgBiz ob;
   
   @Autowired
    private KakaoAPI kakao;
   
   /* NaverBO */
   private String apiResult = null;
   
   @Autowired
   private NaverLoginBO naverLoginBO;
//   private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
//      this.naverLoginBO = naverLoginBO;
//   }
   
   // Date type error : typeMisMatch
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        DateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
    }
   
   //로그인 페이지로 이동
   @RequestMapping("/loginForm.do")
   public String loginForm(Model model,HttpSession session) {
      logger.info("LOGIN FORM");
      // 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 
      String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
      //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
      //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
      //System.out.println("네이버:" + naverAuthUrl);
      //네이버
      model.addAttribute("url", naverAuthUrl);
      
      return "/member/login";
   }
   
   //로그인 페이지로 이동
      @RequestMapping("/loginForm2.do")
      public String loginForm2(Model model) {
         logger.info("LOGIN FORM");
         return "/member/naverLogin";
      }
   
   //로그인
   @RequestMapping(value="/login.do", method=RequestMethod.POST)
   @ResponseBody
   public Map<String,Boolean> ajaxLogin(HttpSession session, @RequestBody UserDto user){
      logger.info("LOGIN");
      
      UserDto res = mb.login(user);
      boolean check = false;
      if(res != null) {
         session.setAttribute("user", res);
         check = true;
      }
      Map<String,Boolean> map = new HashMap<String, Boolean>();
      map.put("check", check);
      
      return map;
   }
   
   //카카오톡 로그인
   @RequestMapping(value="/kakaoLogin.do")
    public void kakaoLogin(@RequestParam("code") String code, HttpSession session, UserDto user, HttpServletResponse response, HttpServletRequest request) throws ServletException {
      response.setContentType("text/html; charset=utf-8");
      logger.info("KAKAOLOGIN");
      
      String access_Token = kakao.getAccessToken(code);
       String userInfo = kakao.getUserInfo(access_Token);
       System.out.println("login Controller : " + userInfo);
       
       UserDto sns_id = new UserDto();
       sns_id.setUser_id(userInfo);
      
      System.out.println( "sns_id : " + sns_id );

      UserDto check = mb.snslogin( sns_id.getUser_id() );
            
      System.out.println( "check : " + check );
      
       if(check == null) {
          user.setUser_id(userInfo);
          user.setUser_pw("1234");
          int res = mb.insertSNS(user);
          if(res>0) {
             try {
                session.setAttribute("user", user);
                //수정1
                session.setAttribute("snsChk", true);
                dispatch("index.do", request, response);
             } catch (IOException e) {
                e.printStackTrace();
             }
          } else {
             try {
                jsResponse("로그인 실패", "loginForm.do", response);
             } catch (IOException e) {
                e.printStackTrace();
             }
          }          
       }else {
          try {
             session.setAttribute("user", check);
             session.setAttribute("snsChk", true);
             dispatch("index.do", request, response);
          } catch (IOException e) {
             e.printStackTrace();
          }
       }
       
       
    }
   
   //네이버 로그인
   @RequestMapping(value="callback.do", method=RequestMethod.GET)
   public void callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, UserDto user, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      System.out.println("여기는 callback");
        OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //로그인 사용자 정보를 읽어온다.
        apiResult = naverLoginBO.getUserProfile(oauthToken);
        System.out.println(naverLoginBO.getUserProfile(oauthToken).toString());
        model.addAttribute("result", apiResult);
        String str = apiResult;
        String[] array = str.split("\"");
//        for(int i=0;i<array.length;i++) {
//        	System.out.println(array[i]);
//        }
        System.out.println("id = "+array[13]);
        String userInfo = array[13];

        UserDto sns_id = new UserDto();
        sns_id.setUser_id(userInfo);
       
       System.out.println( "sns_id : " + sns_id );

       UserDto check = mb.snslogin( sns_id.getUser_id() );
             
       System.out.println( "check : " + check );
       
        if(check == null) {
           user.setUser_id(userInfo);
           user.setUser_pw("1234");
           int res = mb.insertSNS(user);
           if(res>0) {
              try {
                 session.setAttribute("user", user);
                 session.setAttribute("snsChk", true);
                 dispatch("index.do", request, response);
              } catch (IOException e) {
                 e.printStackTrace();
              }
           } else {
              try {
                 jsResponse("로그인 실패", "loginForm.do", response);
              } catch (IOException e) {
                 e.printStackTrace();
              }
           }          
        }else {
           try {
              session.setAttribute("user", check);
              session.setAttribute("snsChk", true);
              dispatch("index.do", request, response);
           } catch (IOException e) {
              e.printStackTrace();
           }
        }
   }
   
   
   //로그아웃
   @RequestMapping("/logout.do")
   public String logout(HttpSession session) {
      session.invalidate();
      return "index";
   }
   
   //회원가입 선택 페이지로 이동
   @RequestMapping("/registForm.do")
   public String registForm(Model model) {
      return "/member/join_select";
   }
   
   //유저 회원가입 페이지로 이동
   @RequestMapping("/registUserForm.do")
   public String registUserForm(Model model) {
      return "/member/join_user";
   }
   
   //유저 회원가입
   @RequestMapping("/registUser.do")
   public void registUser(UserDto user, HttpServletRequest request,HttpServletResponse response,@RequestParam("file") MultipartFile file) {
      response.setContentType("text/html; charset=utf-8");
      logger.info("REGISTUSER");
      
      String user_pic = mb.userfileUpload(file, request,user);
      
      if(user_pic != "") {
         user.setUser_pic(user_pic);
         int res = mb.regist_user(user);
         
         if(res>0) {
            try {
               jsResponse("회원가입 성공", "loginForm.do", response);
            } catch (IOException e) {
               e.printStackTrace();
            }
         } else {
            try {
               jsResponse("회원가입 실패", "registUserForm.do", response);
            } catch (IOException e) {
               e.printStackTrace();
            }
         }         
      }else {
         try {jsResponse("이미지를 등록해주세요","registUserForm.do",response);}
         catch (IOException e) {e.printStackTrace();}
      }
   }
   
   //환경단체 회원가입 페이지로 이동
   @RequestMapping("/registOrgForm.do")
   public String registOrgForm(Model model) {
      return "/member/join_org";
   }
   
   //환경단체 회원가입
   @RequestMapping("/registOrg.do")
   public void registOrg(UserDto user, HttpServletRequest request,HttpServletResponse response,@RequestParam("file") MultipartFile file) {
      response.setContentType("text/html; charset=utf-8");
      logger.info("REGISTORG");
      
      String user_pic = mb.userfileUpload(file, request,user);
      
      if(user_pic != "") {
         user.setUser_pic(user_pic);
         int res = mb.regist_org(user);
         
         if(res>0) {
            try {
               jsResponse("회원가입 성공", "loginForm.do", response);
            } catch (IOException e) {
               e.printStackTrace();
            }
         } else {
            try {
               jsResponse("회원가입 실패", "registOrgForm.do", response);
            } catch (IOException e) {
               e.printStackTrace();
            }
         }         
      }else {
         try {jsResponse("이미지를 등록해주세요","registOrgForm.do",response);}
         catch (IOException e) {e.printStackTrace();}
      }
   }
   
   //환경단체 회원가입 (환경단체 검색)
   @RequestMapping("/orgSearch.do")
   public String orgSearchForm(Model model, 
         @RequestParam(value="orgPage", required=false) Integer orgPage,
         @RequestParam(value="keyword", required=false) String keyword) {
      logger.info("[org_search Page]");
      
      Boolean searchIs = (keyword==null)?true:false;
      
      if(searchIs) {
         if(orgPage == null) {
            orgPage = 1;
         }
         Paging paging = ob.orgPaging(orgPage);
         List<OrgDto> orgList = ob.orgList(paging);
         PageMaker maker = ob.getPageMaker(paging);
         
         model.addAttribute("orgList",orgList);
         model.addAttribute("orgPage",orgPage);
         model.addAttribute("PageMaker",maker);
      } else {
         
         if(orgPage == null) {
            orgPage = 1;
         }
         
         keyword=keyword.trim();
         
         Paging paging = ob.orgPagingSearch(orgPage,keyword);
         List<OrgDto> orgList = ob.orgListSearch(paging,keyword);
         PageMaker maker = ob.getPageMaker(paging);
         
         model.addAttribute("orgList",orgList);
         model.addAttribute("orgPage",orgPage);
         model.addAttribute("PageMaker",maker);
         model.addAttribute("keyword",keyword);
      }
      
      return "/member/join_org_search";
   }
   
   //id/pw 찾기 선택 페이지로 이동
   @RequestMapping("/searchForm.do")
   public String searchForm(Model model) {
      return "/member/find_select";
   }
   
   //id 찾기 페이지로 이동
   @RequestMapping("/searchIdForm.do")
   public String searchIdForm(Model model) {
      return "/member/find_id";
   }
   
   //id 찾기
   @RequestMapping("/searchId.do")
   public void searchId(UserDto user, Model model, HttpServletResponse response) {
      response.setContentType("text/html; charset=utf-8");
      logger.info("SEARCH_ID");
      UserDto res = mb.searchId(user);
      if(res != null) {
         try {
            jsResponse("회원님의 아이디는 "+res.getUser_id()+" 입니다", "loginForm.do", response);
         } catch (IOException e) {
            e.printStackTrace();
         }
      } else {
         try {
            jsResponse("찾으시는 계정이 존재하지 않습니다.", "searchIdForm.do", response);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
   
   //pw 찾기 페이지로 이동
   @RequestMapping("/searchPwForm.do")
   public String searchPwForm(Model model) {
      return "/member/find_pw";
   }
   
   //pw 찾기
   @RequestMapping("/searchPw.do")
   public void searchPw(UserDto user, Model model, HttpServletResponse response) {
      response.setContentType("text/html; charset=utf-8");
      logger.info("SEARCH_PW");
      UserDto res = mb.searchId(user);
      if(res != null) {
         try {
            jsResponse("회원님의 비밀번호는 "+res.getUser_pw()+" 입니다", "loginForm.do", response);
         } catch (IOException e) {
            e.printStackTrace();
         }
      } else {
         try {
            jsResponse("찾으시는 계정이 존재하지 않습니다.", "searchIdForm.do", response);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   //idCheck
   @RequestMapping("/idCheck.do")
   @ResponseBody
   public Map idCheck(@RequestBody Map param) {
      String user_id = (String) param.get("user_id");
      logger.info("user_id:"+user_id);
      int res = 0;
      if (mb.idCheck(user_id) != 0) {
         res = 1;
      }
      // id 중복되면 res : 1, 없으면 0
      
      Boolean is = (res==0)?true:false;
      
      Map<String,Boolean> map = new HashMap<String, Boolean>();
      
      map.put("check", is);
      return map;
   }
   
   //nickCheck
   @RequestMapping("/nickCheck.do")
   @ResponseBody
   public Map nickCheck(@RequestBody Map param) {
      String user_nick = (String) param.get("user_nick");
      logger.info("user_nick:"+user_nick);
      int res = 0;
      if (mb.nickCheck(user_nick) != 0) {
         res = 1;
      }
      // id 중복되면 res : 1, 없으면 0
      
      Boolean is = (res==0)?true:false;
      
      Map<String,Boolean> map = new HashMap<String, Boolean>();
      
      map.put("check", is);
      return map;
   }

   //phoneCheck
   @RequestMapping("/phoneCheck.do")
   @ResponseBody
   public Map phoneCheck(@RequestBody Map param) {
	   String user_phone = (String) param.get("user_phone");
	   logger.info("user_phone:"+user_phone);
	   int res = 0;
	   if (mb.phoneCheck(user_phone) != 0) {
		   res = 1;
	   }
	   // id 중복되면 res : 1, 없으면 0
	   
	   Boolean is = (res==0)?true:false;
	   
	   Map<String,Boolean> map = new HashMap<String, Boolean>();
	   
	   map.put("check", is);
	   return map;
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