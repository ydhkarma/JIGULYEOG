package com.mvc.jigulyeog.biz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mvc.jigulyeog.model.dao.MemberDao;
import com.mvc.jigulyeog.model.dto.UserDto;

@Service
public class MemberBizImpl implements MemberBiz {
	private static final Logger logger = LoggerFactory.getLogger(MemberBizImpl.class);

	@Autowired
	private MemberDao dao;
	
	@Override
	public String userfileUpload(MultipartFile file, HttpServletRequest request, UserDto user) {
		logger.info("[ MemberBiz : userfileUpload ]");
		String user_pic = "";
		
		// [0] file 유효성 검사
		if(file.getSize()==0 || file.getOriginalFilename().length()<0) {
			logger.info("[ MemberBiz error: file.getSize()==0 ]");
			return "";
		}
		
		// [1] file에 담겨있는 파일 가져오기
		MultipartFile originalFile = file;
		String originalFileName = originalFile.getOriginalFilename();
		
		
		logger.info("[ originalFileName : "+originalFileName+" ]");
		
		InputStream inputStream = null;
	    OutputStream outputStream = null;
		
		try {
			inputStream = file.getInputStream(); // file을 renameFile에 출력해야 하므로, 출력스트림 open
			
	         String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/images/user");
	         
	         logger.info("[ savePath : "+savePath+" ]");

	         // 파일 이름 정하기
	         String exc = originalFileName.substring(originalFileName.lastIndexOf(".")+1, originalFileName.length());
	         String renameFileName = user.getUser_id()+"."+exc;
	         logger.info("[ renameFileName : "+renameFileName+" ]");
	         
	         File renameFile = new File(savePath); // 해당 savePath를 storage로 해주기 위해
	         
	         
	         // 경로 존재 여부
	         if(!renameFile.exists()) {
	        	 // 만약 존재하지 않는다면,
	        	 renameFile.mkdirs(); // savePath 생성
	         }
	         
	         // 해당 경로에 파일 생성
	         renameFile = new File(savePath + "/" + renameFileName);
	         
	         outputStream = new FileOutputStream(renameFile); // 해당 renameFile에 originalFile을 복사하기 위해 stream open.
	         
	         int read = 0;
	         byte[] b = new byte[(int) originalFile.getSize()];
	         
	         // 파일을 읽기
	         while( (read=inputStream.read(b)) != -1 ) {
	        	 outputStream.write(b,0,read);
	         }
	         
	         user_pic = renameFileName;
	         
	     } catch (IOException e) {
	
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	     
	     return user_pic;
	}

	@Override
	public UserDto login(UserDto user) {
		return dao.login(user);
	}

	@Override
	public int regist_user(UserDto user) {
		return dao.regist_user(user);
	}

	@Override
	public int regist_org(UserDto user) {
		/*솔지 추가 부분 */
		dao.regist_org(user);
		
		return dao.updateOrgImg(user);
	}

	@Override
	public UserDto searchId(UserDto user) {
		return dao.searchId(user);
	}

	@Override
	public UserDto searchPw(UserDto user) {
		return dao.searchPw(user);
	}

	@Override
	public int idCheck(String user_id) {
		return dao.idCheck(user_id);
	}

	@Override
	public int nickCheck(String user_nick) {
		return dao.nickCheck(user_nick);
	}

	@Override
	public int phoneCheck(String user_phone) {
		return dao.phoneCheck(user_phone);
	}
	
	@Override
	public int insertSNS(UserDto user) {
		return dao.insertSNS(user);
	}

	@Override
	public UserDto snslogin(String user_id) {
		return dao.snslogin(user_id);
	}




	
}
