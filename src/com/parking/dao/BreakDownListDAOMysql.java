package com.parking.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.parking.vo.Breakdown;
import com.parking.vo.Reservation;

@Repository
public class BreakDownListDAOMysql {
	@Autowired
	SqlSession session;
	
	public List<Breakdown> selectById(Reservation r){
		return session.selectList("BreakdownMapper.selectById", r);
	}
	
	public boolean selectByReview(Breakdown b){
		int result = session.selectOne("BreakdownMapper.selectByReview", b);
		if(result>0){
			return true;
		}else{
			return false;
		}
	}
	
}
