package com.mvc.jigulyeog.biz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.jigulyeog.model.dao.MyPageDao;
import com.mvc.jigulyeog.model.dao.MyPageDaoImpl;
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

@Service
public class MyPageBizImpl implements MyPageBiz {
	Logger logger = LoggerFactory.getLogger(MyPageBizImpl.class);

	@Autowired
	private MyPageDao dao;

	@Override
	public List<ProjectDto> getMyDonateProjectList(String user_id) {
		List<ProjectDto> projectList = dao.getMyDonateProjectList(user_id);
		return projectList;
	}

	@Override
	public List<OrgDto> getMySubscribeOrgList(String user_id) {
		List<OrgDto> orgList = dao.getMySubscribeOrgList(user_id);
		return orgList;
	}

	@Override
	public List<ChungwonDto> getMySignUpList(String user_id) {
		List<ChungwonDto> signUpList = dao.getMySignUpList(user_id);
		return signUpList;
	}

	/*
	 * @Override public List<ChungwonDto> getMyChungwonList(String user_id) {
	 * List<ChungwonDto> chungwonList = dao.getMyChungwonList(user_id); return
	 * chungwonList; }
	 */

	@Override
	public List<TogetherDto> getMyTogetherApplyList(String user_id) {
		List<TogetherDto> togetherApplyList = dao.getMyTogetherApplyList(user_id);

		return togetherApplyList;
	}

	/*
	 * @Override public List<TogetherDto> getMyTogetherList(String user_id) {
	 * List<TogetherDto> togetherList = dao.getMyTogetherList(user_id); return
	 * togetherList; }
	 */

	@Override
	public MyPagePaging myChungwonPaging(Integer CWPage, String user_id) {
		MyPagePaging paging = new MyPagePaging();

		paging.setPage(CWPage);
		int totalMyCW = dao.getTotalMyChungwon(user_id); // 내가 작성한 청원글 개수 가져오기.

		// 내 청원 게시글 갯수 세팅
		paging.setTotalArticle(totalMyCW);

		// 전체 페이지 개수 setting
		paging.setTotalPage(totalMyCW);

		// 게시글 시작행 및 끝행 서정
		paging.setStartRow();
		paging.setEndRow();

		logger.info("[ startRow : " + paging.getStartRow() + ", endRow : " + paging.getEndRow() + " ]");
		return paging;
	}

	@Override
	public List<ChungwonDto> getMyChungwonList(MyPagePaging CWPaging, String user_id) {
		List<ChungwonDto> CWList = dao.getMyChungwonList(CWPaging.getStartRow(), CWPaging.getEndRow(), user_id);
		return CWList;
	}

	@Override
	public MyPagePageMaker getPageMaker(MyPagePaging paging) {
		MyPagePageMaker pageMaker = new MyPagePageMaker();
		pageMaker.setPaging(paging);
		return pageMaker;
	}

	@Override
	public MyPagePaging myTogetherPaging(Integer TGPage, String user_id) {
		MyPagePaging paging = new MyPagePaging();

		paging.setPage(TGPage);
		int totalMyTG = dao.getTotalMyTogether(user_id); // 내가 작성한 청원글 개수 가져오기.

		// 내 청원 게시글 갯수 세팅
		paging.setTotalArticle(totalMyTG);

		// 전체 페이지 개수 setting
		paging.setTotalPage(totalMyTG);

		// 게시글 시작행 및 끝행 서정
		paging.setStartRow();
		paging.setEndRow();

		logger.info("[ startRow : " + paging.getStartRow() + ", endRow : " + paging.getEndRow() + " ]");
		return paging;
	}

	@Override
	public List<TogetherDto> getMyTogetherList(MyPagePaging TGPaging, String user_id) {
		List<TogetherDto> TGList = dao.getMyTogetherList(TGPaging.getStartRow(), TGPaging.getEndRow(), user_id);
		return TGList;
	}

	@Override
	public List<SignUpDto> getSignUpList(Integer pet_no) {
		List<SignUpDto> SUList = dao.getSignUpList(pet_no);
		return SUList;
	}

	@Override
	public void SUExcelCreate(HttpServletRequest request, List<SignUpDto> SUList, String filename) {
		//확장자명
		String version = "xls";
		//String version = "xlsx";
		
		//Workbook 생성
		Workbook workbook = new HSSFWorkbook();
		
		//workbook에 sheet 생성
		Sheet sheet = workbook.createSheet("서명리스트");
		
		//sheet의 셀 취득
		Row row0 = sheet.getRow(0);
		if(row0 == null) {
			row0 = sheet.createRow(0);
		}
		Cell cell0 = row0.getCell(0);
		if(cell0 == null) {
			cell0 = row0.createCell(0);
		}
		Cell cell1 = row0.getCell(1);
		if(cell1 == null) {
			cell1 = row0.createCell(1);
		}
		//cell에 데이터 작성
		cell0.setCellValue("번호");
		cell1.setCellValue("청원자");
		
		//서명리스트의 데이터를 workbook에 담아주기
		int i = 1;
		for(SignUpDto dto:SUList) {
			Row rowi = sheet.getRow(i);
			if(rowi==null) {
				rowi = sheet.createRow(i);
			}
			
			Cell cell_0 = rowi.getCell(0);
			if(cell_0 == null) {
				cell_0 = rowi.createCell(0);
			}
			Cell cell_1 = rowi.getCell(1);
			if(cell_1 == null) {
				cell_1 = rowi.createCell(1);
			}
			cell_0.setCellValue(i);
			cell_1.setCellValue(dto.getUser_id());
			i++;
		}
		
		//파일생성
		String filePath = request.getSession().getServletContext().getRealPath("resources/excel");
		File createPath = new File(filePath);
		if(!createPath.exists()){
			createPath.mkdirs();
		}

		try {
			FileOutputStream stream = new FileOutputStream(filePath+filename);
			workbook.write(stream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TogetherApplyDto> getTogetherApplyList(Integer tog_no) {
		List<TogetherApplyDto>TGAList = dao.getTogetherApplyList(tog_no);
		return TGAList;
	}

	@Override
	public void TGAExcelCreate(HttpServletRequest request, List<TogetherApplyDto> TGAList, String filename) {
		//확장자명
				String version = "xls";
				//String version = "xlsx";
				
				//Workbook 생성
				Workbook workbook = new HSSFWorkbook();
				
				//workbook에 sheet 생성
				Sheet sheet = workbook.createSheet("함께해요 신청 리스트");
				
				//sheet의 셀 취득
				Row row0 = sheet.getRow(0);
				if(row0 == null) {
					row0 = sheet.createRow(0);
				}
				Cell cell0 = row0.getCell(0);
				if(cell0 == null) {
					cell0 = row0.createCell(0);
				}
				Cell cell1 = row0.getCell(1);
				if(cell1 == null) {
					cell1 = row0.createCell(1);
				}
				//cell에 데이터 작성
				cell0.setCellValue("번호");
				cell1.setCellValue("신청자");
				
				//서명리스트의 데이터를 workbook에 담아주기
				int i = 1;
				for(TogetherApplyDto dto : TGAList) {
					Row rowi = sheet.getRow(i);
					if(rowi==null) {
						rowi = sheet.createRow(i);
					}
					
					Cell cell_0 = rowi.getCell(0);
					if(cell_0 == null) {
						cell_0 = rowi.createCell(0);
					}
					Cell cell_1 = rowi.getCell(1);
					if(cell_1 == null) {
						cell_1 = rowi.createCell(1);
					}
					cell_0.setCellValue(i);
					cell_1.setCellValue(dto.getUser_id());
					i++;
				}
				
				//파일생성
				String filePath = request.getSession().getServletContext().getRealPath("resources/excel");
				File createPath = new File(filePath);
				if(!createPath.exists()){
					createPath.mkdirs();
				}

				try {
					FileOutputStream stream = new FileOutputStream(filePath+filename);
					workbook.write(stream);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}

	@Override
	public int secession(String user_id) {
		int res=dao.secession(user_id);
		return res;
	}

	@Override
	public int update_member(UserDto user) {
		int res=dao.update_member(user);
		return res;
	}

	@Override
	public int secession_org_update(int org_num) {
		int res=dao.secession_org_update(org_num);
		return res;
	}
}
