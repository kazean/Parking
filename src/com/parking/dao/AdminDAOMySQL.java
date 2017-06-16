package com.parking.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.parking.vo.Admin;

@Repository
public class AdminDAOMySQL {

	@Autowired
	SqlSession session;
	
	public Admin selectAll(Admin admin){
		return session.selectOne("AdminMapper.selectAll", admin);
	}
	
}
