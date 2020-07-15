package com.mvc.jigulyeog.biz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mvc.jigulyeog.model.dao.ProjectDao;
import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.ProjectDto;

@Service
public class ProjectBizImpl implements ProjectBiz{
	private static final Logger logger = LoggerFactory.getLogger(ProjectBizImpl.class);
	
	@Autowired
	ProjectDao dao;
	
	// ## 이미지 파일 업로드 ## //
	@Override
	public String projectfileUpload(MultipartFile file,HttpServletRequest request) {
		logger.info("[ ProjectBiz : projectfileUpload ]");
		String pro_image = "";
		
		// [0] file 유효성 검사
		if(file.getSize()==0 || file.getOriginalFilename().length()<0) {
			logger.info("[ ProjectBiz error: file.getSize()==0 ]");
			return "";
		}
		
		// [1] file에 담겨있는 파일 가져오기
		MultipartFile originalFile = file;
		String originalFileName = originalFile.getOriginalFilename();
		logger.info("[ originalFileName : "+originalFileName+" ]");
		/*************솔지 수정 부분***************/
		 //파일명 중 확장자만 추출                                
       String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
	     
       InputStream inputStream = null;
	     OutputStream outputStream = null;
	     
	     try {
			inputStream = file.getInputStream(); // file을 renameFile에 출력해야 하므로, 출력스트림 open
			
	         String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/images/project");
	         
	         logger.info("[ savePath : "+savePath+" ]");
	         
	         // 파일 이름 정하기
	         String uuid = UUID.randomUUID().toString();
	         String renameFileName = uuid.substring(0,uuid.indexOf("-"))+originalFileExtension;
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
	         
	         pro_image = renameFileName;
	         
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
	     
	     return pro_image;
		
	}

	// ## 프로젝트 작성 ## //
	@Override
	public boolean projectWrite(ProjectDto project) {
		logger.info("[ ProjectBiz : projectWrite ]");
		return dao.projectWrite(project);
	}
	
	// ## Paging : 초기 설정 ## //
	@Override
	public Paging projectPaging(Integer page) {
		logger.info("[ ProjectBiz : projectPaging ]");
		Paging paging = new Paging();
		
		paging.setPage(page);
		int totalArticle = dao.totalArticle(); // 전체 게시글 개수 가져오기.
		
		// 전체 게시글 갯수
		paging.setTotalArticle(totalArticle); // setting
		
		// 페이지 개수
		paging.setTotalPage(totalArticle); // 전체 페이지 개수 setting
		
		// 게시글 시작 행 및 끝 행 설정
		paging.setStartRow();
		paging.setEndRow();
		
		logger.info("[ startRow : "+paging.getStartRow()+", endRow : "+paging.getEndRow()+" ]");

		return paging;
	}
	

	// ## Paging : 프로젝트 리스트 가져오기 ## //
	@Override
	public List<ProjectDto> projectList(Paging paging) {
		logger.info("[ ProjectBiz : projectList ]");
		
		List<ProjectDto> PList = dao.getArticleList(paging.getStartRow(), paging.getEndRow());

		return PList;
	}
	
	@Override
	public List<ProjectDto> getArticleOldList(Paging paging) {
		logger.info("[ ProjectBiz : getArticleOldList ]");
		
		List<ProjectDto> PList = dao.getArticleOldList(paging.getStartRow(), paging.getEndRow());

		return PList;
	}

	@Override
	public List<ProjectDto> getArticleManyList(Paging paging) {
		logger.info("[ ProjectBiz : getArticleManyList ]");
		
		List<ProjectDto> PList = dao.getArticleManyList(paging.getStartRow(), paging.getEndRow());

		return PList;
	}

	
	// ## Paging : page Maker ## //
	@Override
	public PageMaker getPageMaker(Paging paging) {
		logger.info("[ ProjectBiz : getPageMaker ]");
		PageMaker maker = new PageMaker();
		maker.setPaging(paging);
		return maker;
	}

	// ## get project one ## //
	@Override
	public ProjectDto getProjectOne(int pro_num) {
		logger.info("[ ProjectBiz : getProjectOne ]");
		return dao.getProjectOne(pro_num);
	}

	
	// ## get org one ## //
	@Override
	public OrgDto getProjectOneOrg(String user_id) {
		logger.info("[ ProjectBiz : getProjectOneOrg ]");		
		return dao.getProjectOneOrg(user_id);
	}

	// ## project update ## //
	@Override
	public boolean projectUpdate(ProjectDto project) {
		logger.info("[ ProjectBiz : projectUpdate ]");		
		return dao.projectUpdate(project);
	}

	// ## project delete ##//
	@Override
	public boolean projectDelete(int pro_num) {
		logger.info("[ ProjectBiz : projectDelete ]");	
		return dao.projectDelete(pro_num);
	}

	// ## project search ## //
	@Override
	public List<ProjectDto> projectSearch(Paging paging,String keyword) {
		logger.info("[ ProjectBiz : projectSearch ]");
		
		List<ProjectDto> PList = dao.getArticleListSearch(paging.getStartRow(), paging.getEndRow(),keyword);

		return PList;
	}

	@Override
	public Paging projectPagingSearch(Integer page,String keyword) {
		logger.info("[ ProjectBiz : projectPagingSearch ]");
		Paging paging = new Paging();
		
		paging.setPage(page);
		int totalArticle = dao.totalArticleSearch(keyword); // 전체 게시글 개수 가져오기.
		
		// 전체 게시글 갯수
		paging.setTotalArticle(totalArticle); // setting
		
		// 페이지 개수
		paging.setTotalPage(totalArticle); // 전체 페이지 개수 setting
		
		// 게시글 시작 행 및 끝 행 설정
		paging.setStartRow();
		paging.setEndRow();
		
		logger.info("[ startRow : "+paging.getStartRow()+", endRow : "+paging.getEndRow()+" ]");

		return paging;
	}

}
