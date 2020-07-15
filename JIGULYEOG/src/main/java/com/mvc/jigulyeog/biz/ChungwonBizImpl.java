package com.mvc.jigulyeog.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.jigulyeog.model.dao.ChungwonDao;
import com.mvc.jigulyeog.model.dto.ChungwonDto;
import com.mvc.jigulyeog.model.dto.PageNavigator;

@Service
public class ChungwonBizImpl implements ChungwonBiz{
	
	@Autowired
	private ChungwonDao dao;
	
	@Override
	public List<ChungwonDto> selectList(PageNavigator navi,String searchWord) {
		return dao.selectList(navi,searchWord);
	}

	@Override
	public ChungwonDto selectOne(int pet_no) {
		return dao.selectOne(pet_no);
	}

	@Override
	public int insert(ChungwonDto dto) {
		return dao.insert(dto);
	}

	@Override
	public List<ChungwonDto> newestList(PageNavigator navi,String searchWord) {
		return dao.newestList(navi,searchWord);
	}

	@Override
	public int selectCount(String searchWord) {
		return dao.selectCount(searchWord);
	}

	@Override
	public int chungDelete(int pet_no) {
		return dao.chungDelete(pet_no);
	}

	@Override
	public int chungUpdate(ChungwonDto dto) {
		return dao.chungUpdate(dto);
	}


	

//	@Override
//	public List<ChungwonDto> searchList(String search) {
//		System.out.println("bizImpl:"+search);
//		return dao.searchList(search);
//	}

}
