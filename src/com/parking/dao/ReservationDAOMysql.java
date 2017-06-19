package com.parking.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.parking.vo.Reservation;

@Repository
public class ReservationDAOMysql {
	
	@Autowired
	SqlSession session;
	
	public List<Reservation> selectById(String c_id){
		
		return session.selectList("ReservationMapper.selectById",c_id);
		
	}
	

}
