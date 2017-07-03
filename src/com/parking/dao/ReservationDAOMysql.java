package com.parking.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.parking.vo.Reservation;
import com.parking.vo.Review;

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
	
	public boolean insertByR(Reservation r){
		if(selectBypossible(r)){
			int result = session.insert("ReservationMapper.insertByR", r);
			if(result>0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}
	
	public boolean selectBypossible(Reservation r){
		List<Reservation> result = new ArrayList<>();
		result = session.selectList("ReservationMapper.selectByPossible", r);
		if(result.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean selectByWeekDay(Reservation r){
		int result = session.selectOne("ReservationMapper.selectByWeekDay", r);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean selectBySat(Reservation r){
		int result = session.selectOne("ReservationMapper.selectBySat", r);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean selectByHoliy(Reservation r){
		int result = session.selectOne("ReservationMapper.selectByHoliy", r);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean selectByDuplicate(Reservation r){
		int result = session.selectOne("ReservationMapper.selectByDuplicate", r);
		if(result > 0){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean selectByCur(Reservation r){
		int result = session.selectOne("ReservationMapper.selectByCurseat", r);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
	
}
