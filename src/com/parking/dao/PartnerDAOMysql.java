package com.parking.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.parking.vo.Partner;

@Repository
public class PartnerDAOMysql {

	
	@Autowired
	SqlSession session;
	
	public List<Partner> selectAll(int startPage, int endPage, String searchItem, String searchValue){
		
		Map<String,Object> map = new HashMap<>();
		map.put("startPage", startPage);
		map.put("endPage",endPage);
		map.put("searchItem", searchItem);
		map.put("searchValue",searchValue);
		return session.selectList("PartnerMapper.selectAll",map);
		
	}
	
	public void insert(Partner p){
		
		session.insert("PartnerMapper.insert",p);
		
	}

	public List<Partner> selectForListSize(String searchItem, String searchValue) {
		
		Map<String,Object> map = new HashMap<>();
		map.put("searchItem", searchItem);
		map.put("searchValue",searchValue);
		return session.selectList("PartnerMapper.selectForListSize",map);
		
	}

	public Partner selectByP_id(String p_id) {
		return session.selectOne("PartnerMapper.selectByP_id",p_id);
	}

	public void delete(String p_id) {
		session.delete("PartnerMapper.delete",p_id);
	}
	
	
	
}
