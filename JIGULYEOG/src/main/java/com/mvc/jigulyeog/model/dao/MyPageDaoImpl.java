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

import com.mvc.jigulyeog.model.dto.ChungwonDto;
import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.ProjectDto;
import com.mvc.jigulyeog.model.dto.SignUpDto;
import com.mvc.jigulyeog.model.dto.TogetherApplyDto;
import com.mvc.jigulyeog.model.dto.TogetherDto;
import com.mvc.jigulyeog.model.dto.UserDto;

@Repository
public class MyPageDaoImpl implements MyPageDao {
	Logger logger = LoggerFactory.getLogger(MyPageDaoImpl.class);

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<ProjectDto> getMyDonateProjectList(String user_id) {
		List<ProjectDto> projectList = new ArrayList<ProjectDto>();
		projectList = sqlSession.selectList(NAMESPACE + "myDonateProjectList", user_id);
		return projectList;
	}

	@Override
	public List<OrgDto> getMySubscribeOrgList(String user_id) {
		List<OrgDto> orgList = new ArrayList<OrgDto>();
		orgList = sqlSession.selectList(NAMESPACE + "mySubscribeOrgList", user_id);
		return orgList;
	}

	@Override
	public List<ChungwonDto> getMySignUpList(String user_id) {
		List<ChungwonDto> signUpList = new ArrayList<ChungwonDto>();
		signUpList = sqlSession.selectList(NAMESPACE + "mySignUpList", user_id);
		return signUpList;
	}

	/*
	 * @Override public List<ChungwonDto> getMyChungwonList(String user_id) {
	 * List<ChungwonDto> chungwonList = new ArrayList<ChungwonDto>(); chungwonList =
	 * sqlSession.selectList(NAMESPACE+"myChungwonList", user_id); return
	 * chungwonList; }
	 */

	@Override
	public List<TogetherDto> getMyTogetherApplyList(String user_id) {
		List<TogetherDto> togetherApplyList = new ArrayList<TogetherDto>();
		togetherApplyList = sqlSession.selectList(NAMESPACE + "myTogetherApllyList", user_id);
		return togetherApplyList;
	}

	/*
	 * @Override public List<TogetherDto> getMyTogetherList(String user_id) {
	 * List<TogetherDto> togetherList = new ArrayList<TogetherDto>(); togetherList =
	 * sqlSession.selectList(NAMESPACE+"myTogetherList", user_id); return
	 * togetherList; }
	 */

	@Override
	public int getTotalMyChungwon(String user_id) {
		int totalMyChungwon = 0;
		try {
			totalMyChungwon = sqlSession.selectOne(NAMESPACE + "totalMyChungwon", user_id);
		} catch (Exception e) {
			logger.info("[ error : getTotalMyChungwon ]");
			e.printStackTrace();
		}
		logger.info("[ totalMyChungwon : " + totalMyChungwon + " ]");

		return totalMyChungwon;
	}

	@Override
	public List<ChungwonDto> getMyChungwonList(int startRow, int endRow, String user_id) {
		List<ChungwonDto> CWList = new ArrayList<ChungwonDto>();
		Map<Object, Object> param = new HashMap<Object, Object>();
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		param.put("user_id", user_id);
		try {
			CWList = sqlSession.selectList(NAMESPACE + "getMyChungwonList", param);
		} catch (Exception e) {
			logger.info("[ error : getMyChungwonList ]");
			e.getStackTrace();
		}
		logger.info("[ getMyChungwonList : " + CWList.size() + " ]");
		return CWList;
	}

	@Override
	public int getTotalMyTogether(String user_id) {
		int totalMyTogether = 0;
		try {
			totalMyTogether = sqlSession.selectOne(NAMESPACE + "totalMyTogether", user_id);
		} catch (Exception e) {
			logger.info("[ error : getTotalMyTogether ]");
			e.printStackTrace();
		}
		logger.info("[  getTotalMyTogether : " + totalMyTogether + " ]");

		return totalMyTogether;
	}

	@Override
	public List<TogetherDto> getMyTogetherList(int startRow, int endRow, String user_id) {
		List<TogetherDto> TGList = new ArrayList<TogetherDto>();
		Map<Object, Object> param = new HashMap<Object, Object>();
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		param.put("user_id", user_id);
		try {
			TGList = sqlSession.selectList(NAMESPACE + "getMyTogetherList", param);
		} catch (Exception e) {
			logger.info("[ error : getMyTogetherList ]");
			e.getStackTrace();
		}
		logger.info("[ getMyTogetherList : " + TGList.size() + " ]");
		return TGList;
	}

	@Override
	public List<SignUpDto> getSignUpList(Integer pet_no) {
		List<SignUpDto> SUList = new ArrayList<SignUpDto>();
		try {
			SUList = sqlSession.selectList(NAMESPACE + "getSignUpList", pet_no);
		} catch (Exception e) {
		}
		return SUList;
	}

	@Override
	public List<TogetherApplyDto> getTogetherApplyList(Integer tog_no) {
		List<TogetherApplyDto> TGAList = new ArrayList<TogetherApplyDto>();
		try {
			TGAList = sqlSession.selectList(NAMESPACE + "getTogetherApplyList", tog_no);
		} catch (Exception e) {
		}
		return TGAList;
	}

	@Override
	public int secession(String user_id) {
		int res = 0;
		try {
			res = sqlSession.delete(NAMESPACE + "secession", user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public int update_member(UserDto user) {
		int res = 0;
		try {
			res = sqlSession.update(NAMESPACE+"update_member",user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public int secession_org_update(int org_num) {
		int res = 0;
		try {
			res = sqlSession.update(NAMESPACE+"secession_org_update",org_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
