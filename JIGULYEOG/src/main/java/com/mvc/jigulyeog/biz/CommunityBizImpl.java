package com.mvc.jigulyeog.biz;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.jigulyeog.model.dao.CommunityDao;
import com.mvc.jigulyeog.model.dto.CommunityGuestbookDto;
import com.mvc.jigulyeog.model.dto.CommunityNoticeDto;
import com.mvc.jigulyeog.model.dto.FundingListDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.ProjectDto;
import com.mvc.jigulyeog.model.dto.UserDto;

@Service
public class CommunityBizImpl implements CommunityBiz{
	Logger logger = LoggerFactory.getLogger(CommunityBizImpl.class);
	
	@Autowired
	CommunityDao dao;
	
	@Override
	public Boolean createCommunity(Integer pro_num) {
		logger.info("[ CommunityBiz : createCommunity ]");
		
		return dao.createCommunity(pro_num);
	}

	@Override
	public Integer checkCommnunity(Integer pro_num) {
		logger.info("[ CommunityBiz : checkCommnunity ]");
		return dao.checkCommunity(pro_num);
	}

	@Override
	public List<UserDto> fundingUserList(Integer pro_num) {
		logger.info("[ CommunityBiz : fundingUserList ]");
		return dao.fundingUserList(pro_num);
	}

	@Override
	public int getCommunityNo(int pro_num) {
		logger.info("[ CommunityBiz : getCommunityNo ]");
		return dao.getCommunityNo(pro_num);
	}

	@Override
	public Boolean commNoticeWrite(CommunityNoticeDto notice) {
		logger.info("[ CommunityBiz : commNoticeWrite ]");
		
		return dao.commNoticeWrite(notice);
	}

	@Override
	public Paging noticePaging(Integer nPage,int com_num) {
		logger.info("[ CommunityBiz : noticePaging ]");
		
		// [1] Create a paging object
		Paging paging = new Paging();
		
		// [2] Setting a page.
		paging.setPage(nPage);
		
		// [3] Get total Article count.
		int totalArticle = dao.totalNotice(com_num);
		
		// [4] Set total Article count.
		paging.setTotalArticle(totalArticle);
		
		// [5] Set a Page count.
		paging.setTotalPage(totalArticle);
		
		// [6] Set start row, end row
		paging.setStartRow();
		paging.setEndRow();
		
		logger.info("[ startRow : "+paging.getStartRow()+", endRow : "+paging.getEndRow()+" ]");

		return paging;
	}

	@Override
	public List<CommunityNoticeDto> noticeList(Paging paging,int com_num) {
		logger.info("[ CommunityBiz : noticeList ]");
		List<CommunityNoticeDto> NList = dao.getNoticeList(paging.getStartRow(), paging.getEndRow(),com_num);
		return NList;
	}

	@Override
	public PageMaker getPageMaker(Paging paging) {
		logger.info("[ CommunityBiz : getPageMaker ]");
		PageMaker maker = new PageMaker();
		maker.setPaging(paging);
		return maker;
	}

	@Override
	public CommunityNoticeDto getNoticeOne(Integer notice_num) {
		logger.info("[ CommunityBiz : getNoticeOne ]");
		
		return dao.getNoticeOne(notice_num);
	}

	@Override
	public Boolean commNoticeUpdate(CommunityNoticeDto notice) {
		logger.info("[ CommunityBiz : commNoticeUpdate ]");
		return dao.commNoticeUpdate(notice);
	}
	
	@Override
	public Boolean commNoticeDelete(Integer notice_num) {
		logger.info("[ CommunityBiz : commNoticeDelete ]");
		
		return dao.commNoticeDelete(notice_num);
	}
	
	@Override
	public void countViewNotice(Integer notice_num) {
		logger.info("[ CommunityBiz  : countViewNotice ]");
		dao.countViewNotice(notice_num);
	}


	@Override
	public Paging guestbookPaging(Integer gPage,int com_num) {
		logger.info("[ CommunityBiz : guestbookPaging ]");
		
		// [1] Create a paging object
		Paging paging = new Paging();
		paging.setPageSize(5);
		
		// [2] Setting a page.
		paging.setPage(gPage);
		
		// [3] Get total Article count.
		int totalArticle = dao.totalGuestbook(com_num);
		
		// [4] Set total Article count.
		paging.setTotalArticle(totalArticle);
		
		// [5] Set a Page count.
		paging.setTotalPage(totalArticle);
		
		// [6] Set start row, end row
		paging.setStartRow();
		paging.setEndRow();
		
		logger.info("[ startRow : "+paging.getStartRow()+", endRow : "+paging.getEndRow()+" ]");

		return paging;
	}
	
	@Override
	public List<CommunityGuestbookDto> guestbookList(Paging gPaging,int com_num) {
		logger.info("[ CommunityBiz : guestbookList ]");
		List<CommunityGuestbookDto> GList = dao.getGuestbookList(gPaging.getStartRow(), gPaging.getEndRow(),com_num);
		return GList;
	}

	@Override
	public Boolean commGuestbookWrite(CommunityGuestbookDto guestbook) {
		logger.info("[ CommunityBiz : commGuestbookWrite ]");
		return dao.commGuestbookWrite(guestbook);
	}

	@Override
	public Boolean commGuestbookUpdate(CommunityGuestbookDto guestbook) {
		logger.info("[ CommunityBiz : commGuestbookUpdate ]");
		return dao.commGuestbookUpdate(guestbook);
	}

	@Override
	public Boolean commGuestbookDelete(int cation_num) {
		logger.info("[ CommunityBiz : commGuestbookDelete ]");
		return dao.commGuestbookDelete(cation_num);
	}


}
