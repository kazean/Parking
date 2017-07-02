package com.parking.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.parking.vo.Partner;

@Repository
public class PartnerDAOMySQL {

	
	@Autowired
	SqlSession session;
	
	public List<Partner> selectAll(){
		
		return session.selectList("PartnerMapper.selectAll");
		
	}
	
	public void insert(Partner p){
		
		session.insert("PartnerMapper.insert",p);
		
	}
	
	
	
}
