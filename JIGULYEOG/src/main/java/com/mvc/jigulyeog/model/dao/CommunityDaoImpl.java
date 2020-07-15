package com.mvc.jigulyeog.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.jigulyeog.model.dto.CommunityGuestbookDto;
import com.mvc.jigulyeog.model.dto.CommunityNoticeDto;
import com.mvc.jigulyeog.model.dto.ProjectDto;
import com.mvc.jigulyeog.model.dto.UserDto;

@Repository
public class CommunityDaoImpl implements CommunityDao{
	Logger logger = LoggerFactory.getLogger(CommunityDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public Boolean createCommunity(Integer pro_num) {
		logger.info("[ CommunityDao : createCommunity ]");
		int res = 0;
		
		try {
			res = sqlSession.insert(NAMESPACE+"createCommunity",pro_num);
		} catch (Exception e) {
			logger.info("[ CommunityDao error : createCommunity ]");
			e.getStackTrace();
		}
		
		return (res>0)?true:false;
	}

	@Override
	public Integer checkCommunity(Integer pro_num) {
		logger.info("[ CommunityDao : checkCommunity ]");
		Integer com_num = 0;
		
		try {
			com_num = sqlSession.selectOne(NAMESPACE+"checkCommunity",pro_num);
		} catch (Exception e) {
			logger.info("[ CommunityDao error : checkCommunity ]");
			e.getStackTrace();
		}
		
		return com_num;
	}

	@Override
	public List<UserDto> fundingUserList(int pro_num) {
		logger.info("[ CommunityDao : fundingUserList ]");
		List<UserDto> fundingUserList = new ArrayList<UserDto>();
		
		try {
			fundingUserList = sqlSession.selectList(NAMESPACE+"fundingUser",pro_num);
			
		} catch (Exception e) {
			logger.info("[ CommunityDao error : fundingUserList ]");
			e.printStackTrace();
		}
		return fundingUserList;
	}

	@Override
	public int getCommunityNo(int pro_num) {
		logger.info("[ CommunityDao : checkCommunity ]");
		Integer commNo = 0;
		
		try {
			commNo = sqlSession.selectOne(NAMESPACE+"checkCommunity",pro_num);
		} catch (Exception e) {
			logger.info("[ CommunityDao error : checkCommunity ]");
			e.getStackTrace();
		}
		
		return commNo;
	}

	@Override
	public Boolean commNoticeWrite(CommunityNoticeDto notice) {
		logger.info("[ CommunityDao : commNoticeWrite ]");
		int res = 0;
		try {
			res = sqlSession.insert(NAMESPACE+"noticeWrite",notice);
		} catch (Exception e) {
			logger.info("[ CommunityDao error : commNoticeWrite ]");
			e.getStackTrace();
		}
		
		return (res>0)?true:false;
	}

	@Override
	public int totalNotice(int com_num) {
		logger.info("[ CommunityDao : totalNotice ]");
		int totalNotice = 0;
		
		try {
			totalNotice = sqlSession.selectOne(NAMESPACE+"totalNotice",com_num);
		}catch (Exception e) {
			logger.info("[ error :totalNotice ]");
			e.getStackTrace();
		}
		logger.info("[ totalNotice : "+totalNotice+"]");
		return totalNotice;
	}

	@Override
	public List<CommunityNoticeDto> getNoticeList(int startRow, int endRow,int com_num) {
		logger.info("[ CommunityDao : getNoticeList ]");
		List<CommunityNoticeDto> getNoticeList = new ArrayList<CommunityNoticeDto>();
		
		Map<String,Integer> param = new HashMap<String,Integer>();
		
		param.put("com_num", com_num);
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		
		try {
			getNoticeList = sqlSession.selectList(NAMESPACE+"getNoticeList",param);
		}catch (Exception e) {
			logger.info("[ error : getNoticeList ]");
			e.getStackTrace();
		}
		
		logger.info("[ getNoticeList : "+getNoticeList.size()+" ]");
		
		
		return getNoticeList;
	}

	@Override
	public CommunityNoticeDto getNoticeOne(Integer notice_num) {
		logger.info("[ CommunityDao : getNoticeOne ]");
		CommunityNoticeDto notice = new CommunityNoticeDto();
		try {
			notice = sqlSession.selectOne(NAMESPACE+"getNoticeOne",notice_num);
		} catch (Exception e) {
			logger.info("[ error : getNoticeOne ]");
			e.getStackTrace();
		}
		
		return notice;
	}

	@Override
	public Boolean commNoticeUpdate(CommunityNoticeDto notice) {
		logger.info("[ CommunityDao : commNoticeUpdate ]");
		int res = 0;
		try {
			res = sqlSession.update(NAMESPACE+"noticeUpdate",notice);
		} catch (Exception e) {
			logger.info("[ error : commNoticeUpdate ]");
			e.getStackTrace();
		}
		return (res>0)?true:false;
	}
	

	@Override
	public Boolean commNoticeDelete(Integer notice_num) {
		logger.info("[ CommunityDao : commNoticeDelete ]");
		int res = 0;
		try {
			res = sqlSession.update(NAMESPACE+"noticeDelete",notice_num);
		} catch (Exception e) {
			logger.info("[ error : commNoticeDelete ]");
			e.getStackTrace();
		}
		return (res>0)?true:false;
	}

	
	@Override
	public void countViewNotice(Integer notice_num) {
		logger.info("[ CommunityDao : countViewNotice ]");
		
		try {
			int res = sqlSession.update(NAMESPACE+"countViewNotice",notice_num);
			if(res>0) {logger.info("[ Success counting view notice ]");}
			
		} catch (Exception e) {
			logger.info("[ CommunityDao error : countViewNotice ]");
		}
		
	}

	@Override
	public int totalGuestbook(int com_num) {
		logger.info("[ CommunityDao : totalGuestbook ]");
		int totalGuestbook = 0;
		
		try {
			totalGuestbook = sqlSession.selectOne(NAMESPACE+"totalGuestbook",com_num);
		}catch (Exception e) {
			logger.info("[ error :totalGuestbook ]");
			e.getStackTrace();
		}
		logger.info("[ totalNotice : "+totalGuestbook+"]");
		return totalGuestbook;
	}
	
	
	@Override
	public List<CommunityGuestbookDto> getGuestbookList(int startRow, int endRow,int com_num) {
		logger.info("[ CommunityDao : getGuestbookList ]");
		List<CommunityGuestbookDto> getGuestbookList = new ArrayList<CommunityGuestbookDto>();
		
		Map<String,Integer> param = new HashMap<String,Integer>();
		
		param.put("com_num", com_num);
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		
		try {
			getGuestbookList = sqlSession.selectList(NAMESPACE+"getGuestbookList",param);
		}catch (Exception e) {
			logger.info("[ error : getGuestbookList ]");
			e.getStackTrace();
		}
		
		logger.info("[ getGuestbookList : "+getGuestbookList.size()+" ]");
		
		
		return getGuestbookList;
	}

	@Override
	public Boolean commGuestbookWrite(CommunityGuestbookDto guestbook) {
		logger.info("[ CommunityDao : commGuestbookWrite ]");
		int res = 0;
		try {
			res = sqlSession.insert(NAMESPACE+"guestbookWrite",guestbook);
		} catch (Exception e) {
			logger.info("[ error : commGuestbookWrite ]");
			e.getStackTrace();
		}
		return (res>0)?true:false;
	}

	@Override
	public Boolean commGuestbookUpdate(CommunityGuestbookDto guestbook) {
		logger.info("[ CommunityDao : commGuestbookUpdate ]");
		int res = 0;
		try {
			res = sqlSession.update(NAMESPACE+"guestbookUpdate",guestbook);
		} catch (Exception e) {
			logger.info("[ error : commGuestbookUpdate ]");
			e.getStackTrace();
		}
		return (res>0)?true:false;
	}

	@Override
	public Boolean commGuestbookDelete(int cation_num) {
		logger.info("[ CommunityDao : commGuestbookDelete ]");
		int res = 0;
		try {
			res = sqlSession.delete(NAMESPACE+"guestbookDelete",cation_num);
		} catch (Exception e) {
			logger.info("[ error : commGuestbookDelete ]");
			e.getStackTrace();
		}
		return (res>0)?true:false;
	}

}
