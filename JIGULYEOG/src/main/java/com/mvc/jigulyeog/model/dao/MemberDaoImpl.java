package com.mvc.jigulyeog.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.jigulyeog.model.dto.UserDto;

@Repository
public class MemberDaoImpl implements MemberDao {
	Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public UserDto login(UserDto user) {
		UserDto res = null;
		try {
			res = sqlSession.selectOne(NAMESPACE+"login",user);
		} catch (Exception e) {
			System.out.println("[error] : login");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int regist_user(UserDto user) {
		logger.info("[ MemberDao : REGISTUSER ]");
		int res = 0;
		try {
			res = sqlSession.insert(NAMESPACE+"regist_user",user);
		} catch (Exception e) {
			System.out.println("[error] : regist_user");
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public int regist_org(UserDto user) {
		logger.info("[ MemberDao : REGISTORG ]");
		int res = 0;
		try {
			res = sqlSession.insert(NAMESPACE+"regist_org",user);
		} catch (Exception e) {
			System.out.println("[error] : regist_org");
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public int insertSNS(UserDto user) {
		logger.info("[ MemberDao : INSERTSNS ]");
		int res = 0;
		
		try {
			res = sqlSession.insert( NAMESPACE + "insertSNS", user );
		} catch ( Exception e ) {
			System.out.println("[error] : insertSNS");
			e.printStackTrace();
		} 
		
		return res;
	}

	@Override
	public UserDto searchId(UserDto user) {
		logger.info("[ MemberDao : SEARCHID ]");
		UserDto res = null;
		try {
			res = sqlSession.selectOne(NAMESPACE+"searchId",user);
		} catch (Exception e) {
			System.out.println("[error] : searchId");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public UserDto searchPw(UserDto user) {
		logger.info("[ MemberDao : SEARCHPW ]");
		UserDto res = null;
		try {
			res = sqlSession.selectOne(NAMESPACE+"searchPw",user);
		} catch (Exception e) {
			System.out.println("[error] : searchPw");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int idCheck(String user_id) {
		logger.info("[ MemberDao : IDCHECK ]");
		int res = 0;
		try {
			res = sqlSession.selectOne(NAMESPACE+"idCheck",user_id);
		} catch (Exception e) {
			System.out.println("[error] : idCheck");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int nickCheck(String user_nick) {
		logger.info("[MemberDao : NICKCHECK ]");
		int res = 0;
		try {
			res = sqlSession.selectOne(NAMESPACE+"nickCheck",user_nick);
		} catch (Exception e) {
			System.out.println("[error] : nickCheck");
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public int phoneCheck(String user_phone) {
		logger.info("[MemberDao : PHONECHECK ]");
		int res = 0;
		try {
			res = sqlSession.selectOne(NAMESPACE+"phoneCheck",user_phone);
		} catch (Exception e) {
			System.out.println("[error] : phoneCheck");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public UserDto snslogin(String user_id) {
		UserDto res = null;
		try {
			res = sqlSession.selectOne(NAMESPACE+"snslogin",user_id);
		} catch (Exception e) {
			System.out.println("[error] : login");
			e.printStackTrace();
		}
		return res;
	}

	/*************솔지 추가 부분**************/
	@Override
	public int updateOrgImg(UserDto user) {
		int res = 0;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("user_status", user.getUser_status());
		param.put("user_pic", user.getUser_pic());
		
		try {
			res = sqlSession.update(NAMESPACE+"regist_org_img_update",param);
		} catch (Exception e) {
			System.out.println("[error] : regist_org_img_update");
			e.printStackTrace();
		}
		return res;
	}

	

}
