package com.parking.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.parking.vo.Reservation;

@Repository
public class ReservationDAOMysql {
	
	// --건들지 마시오--
	@Autowired
	SqlSession session;
	
	/**
	 * @author yunmeheo
	 * @comment 고객 아이디에 해당하는 예약 리스트 반환
	 * @param c_id 고객 아이디
	 * @return List<Reservation> 예약 리스트
	 */
	public List<Reservation> selectById(String c_id){
		return session.selectList("ReservationMapper.selectById",c_id);
	}
	
}
