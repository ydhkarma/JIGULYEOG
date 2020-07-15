package com.mvc.jigulyeog.model.dao;

import java.util.List;

import com.mvc.jigulyeog.model.dto.TogetherDto;
import com.mvc.jigulyeog.model.dto.UserDto;

public interface TogetherDao {
	String NAMESPACE = "mapper.Together.";
	
	public List<TogetherDto>selectList(int startRow,int endRow); // 초기 목록보여주기(전체 게시글)
	
	// 봉사 모금 나눔 목록보여주기
	public List<TogetherDto>selectVolunteerList(int startRow, int endRow);
	public List<TogetherDto>selectFundingList(int startRow, int endRow);
	public List<TogetherDto>selectShareList(int startRow, int endRow);
	
	public TogetherDto selectOne(int tog_no); // crud
	public int insert(TogetherDto dto);
	public int update(TogetherDto dto);
	public int delete(int tog_no);
	
	// paging과 검색
	
	// 게시물 갯수 전체 구하기
	int totalArticle();
	
	// 카테고리 별 게시물 갯수 구하기
	int totalFundingArticle();
	int totalShareArticle();
	int totalVolunteerArticle();
	
	
	//입력한 키워드에 해당하는 게시물 갯수 전체 구하기
	int totalArticleSearch(String keyword);
	
	//입력한 키워드에 해당하는 게시물 리스트 불러오기
	List<TogetherDto> getArticleListSearch(int startRow, int endRow, String keyword);

	public UserDto selectWriteUser(String user_id);
	
}
