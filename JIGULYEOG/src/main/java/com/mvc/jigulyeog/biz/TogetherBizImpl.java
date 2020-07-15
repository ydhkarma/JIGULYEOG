package com.mvc.jigulyeog.biz;

import java.util.List;
import java.util.UUID;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mvc.jigulyeog.model.dao.TogetherDao;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.TogetherDto;
import com.mvc.jigulyeog.model.dto.UserDto;

@Service
public class TogetherBizImpl implements TogetherBiz{
	private static final Logger logger = LoggerFactory.getLogger(TogetherBizImpl.class);
	
	@Autowired
	private TogetherDao dao;
	
	// 파일 업로드
	@Override
	public String TogetherfileUpload(MultipartFile file, HttpServletRequest request) {
		String tog_image = "";
		
		// 유효성 검사
		if(file.getSize()==0 || file.getOriginalFilename().length()<0) {
			logger.info("====== Error FileSize 0 ======");
			return "";
		}
		
		// file변수에 담겨있는 버퍼가져오기
		MultipartFile ogFile = file;
		String ogFileName = ogFile.getOriginalFilename();
		/*************솔지 수정 부분***************/
		 //파일명 중 확장자만 추출                                
        String originalFileExtension = ogFileName.substring(ogFileName.lastIndexOf("."));
		
		logger.info("====== ogFileName : "+ogFileName+" ======");
		
		InputStream Istream = null;
		OutputStream Ostream = null;
		
		try {
			Istream = file.getInputStream();
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/images/together");
			
			logger.info("====== savePath : "+savePath+" ======" );
			
			String uuid = UUID.randomUUID().toString();
	        String renameFileName = uuid.substring(0,uuid.indexOf("-"))+originalFileExtension;
	        /*******************************/
	        
	        logger.info("[ renameFileName : "+renameFileName+" ]");
	         
	        File renameFile = new File(savePath);
			
			if(!renameFile.exists()) {
				// 경로존재 X => 경로생성
				logger.info("===== File  mkdirs : "+renameFile.getName()+" =====");
				renameFile.mkdirs();
			}
			
			renameFile = new File(savePath + "/" + renameFileName);
			
			Ostream = new FileOutputStream(renameFile);
			
			int read = 0 ;
			byte[] b = new byte[(int)ogFile.getSize()];
			
			while( (read = Istream.read(b))!=-1) {
				Ostream.write(b,0,read);
			}
			
			tog_image = renameFileName;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Istream.close();
				Ostream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		return tog_image;
	}
	
	@Override
	public List<TogetherDto> selectList(Paging paging) {
		logger.info("TogetherBiz : TogetherList");
		List<TogetherDto> Tlist = dao.selectList(paging.getStartRow(), paging.getEndRow());
		return Tlist;
	}

	@Override
	public TogetherDto selectOne(int tog_no) {
		return dao.selectOne(tog_no);
	}

	@Override
	public int insert(TogetherDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(TogetherDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int tog_no) {
		return dao.delete(tog_no);
	}

	@Override
	public PageMaker getPageMaker(Paging paging) {
		logger.info("[ ProjectBiz : getPageMaker ]");
		PageMaker maker = new PageMaker();
		maker.setPaging(paging);
		return maker;
	}

	@Override
	public Paging togetherPaging(Integer page) {
		logger.info("togetherBiz : TogetherPaging");
		Paging paging = new Paging();
		System.out.println("===============projectPaging page 수 : "+page);
		paging.setPage(page);
		
		int totalArticle = dao.totalArticle();
		
		paging.setTotalArticle(totalArticle);
		
		paging.setTotalPage(totalArticle);
		
		paging.setStartRow();
		paging.setEndRow();
		
		logger.info("[ startRow : "+paging.getStartRow()+", endRow : "+paging.getEndRow()+" ]");

		
		return paging;
	}

	@Override
	public Paging togetherSearchPaging(Integer page, String keyword) {
		Paging paging = new Paging();
		
		paging.setPage(page);
		int totalArticle = dao.totalArticleSearch(keyword);
		
		paging.setTotalArticle(totalArticle);
		
		paging.setTotalPage(totalArticle);
		
		paging.setStartRow();
		paging.setEndRow();
		
		return paging;
	}

	@Override
	public List<TogetherDto> togetherSearch(Paging paging, String keyword) {
		
		List<TogetherDto> list = dao.getArticleListSearch(paging.getStartRow(), paging.getEndRow(), keyword);
		
		return list;
	}

	@Override
	public List<TogetherDto> selectVolunteerList(Paging paging) {
		logger.info("봉사 카테고리 게시글 목록 보여주기 - Biz");
		
		List<TogetherDto> list = dao.selectVolunteerList(paging.getStartRow(),paging.getEndRow());
		
		return list;
	}

	@Override
	public List<TogetherDto> selectFundingList(Paging paging) {
		logger.info("모금 카테고리 게시글 목록 보여주기 - Biz");
		
		List<TogetherDto> list = dao.selectFundingList(paging.getStartRow(),paging.getEndRow());
		
		return list;
	}

	@Override
	public List<TogetherDto> selectShareList(Paging paging) {
		logger.info("나눔 카테고리 게시글 목록 보여주기  - Biz");
		
		List<TogetherDto> list = dao.selectShareList(paging.getStartRow(), paging.getEndRow());		
		return list;
	}

	@Override
	public Paging togetherFundingPaging(Integer page) {
		Paging paging = new Paging();
		paging.setPage(page);
		int totalFundingArticle = dao.totalFundingArticle();
		
		paging.setTotalArticle(totalFundingArticle);
		
		paging.setTotalPage(totalFundingArticle);
		
		paging.setStartRow();
		paging.setEndRow();
		
		return paging;
	}

	@Override
	public Paging togetherVolunteerPaging(Integer page) {
		Paging paging = new Paging();
		paging.setPage(page);
		int totalVolunteerArticle = dao.totalVolunteerArticle();
		
		paging.setTotalArticle(totalVolunteerArticle);
		
		paging.setTotalPage(totalVolunteerArticle);
		
		paging.setStartRow();
		paging.setEndRow();
		
		return paging;
	}

	@Override
	public Paging togetherSharePaging(Integer page) {
		Paging paging = new Paging();
		paging.setPage(page);
		int totalTotalArticle = dao.totalShareArticle();
		
		paging.setTotalArticle(totalTotalArticle);
		
		paging.setTotalPage(totalTotalArticle);
		
		paging.setStartRow();
		paging.setEndRow();
		
		return paging;
	}

	@Override
	public UserDto selectWriteUser(String user_id) {
		
		return dao.selectWriteUser(user_id);
	}

}
