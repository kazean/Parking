package com.parking.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.parking.vo.Parking;
import com.parking.vo.Reservation;

@Repository
public class ParkingDAOMysql {
	
	// --건들지 마시오--
	@Autowired
	SqlSession session;
	
	/**
	 * @author yeahni
	 * @comment 주차장 API의 정보를 입력
	 * @param list 받아온 주차장 API 리스트
	 */
	public void insert(List<Parking> list){
		System.out.println("ParkingDAOMysql insert()");
		for(Parking p : list){
			session.insert("ParkingMapper.insert", p);
		}
	}
	
	/**
	 * @author yeahni
	 * @comment 전체 주차장 리스트 반환
	 * @return List<Parking> 전체 주차장 리스트
	 */
	public List<Parking> selectAll(String sort){
		System.out.println("ParkingDAOMysql selectAll()");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sort", sort);
		return session.selectList("ParkingMapper.selectAll", map);
	}
	
	public List<Parking> selectItem(String searchItem, String searchValue) {
		System.out.println("ParkingDAOMysql selectItem()");
		
		HashMap<String, String> map = new HashMap<String, String>();
		System.out.println("searchItem : " + searchItem + " | searchValue : " + searchValue);
		
		map.put("searchItem", searchItem);
		map.put("searchValue", searchValue);
		
		return session.selectList("ParkingMapper.selectItem", map);
	}
	
	public int parkingAdd(Parking p) {
		System.out.println("ParkingDAOMysql parkingInsert()");
		return session.selectOne("ParkingMapper.parkingAdd", p);
	}
	
	public int parkingDelete(String s) {
		System.out.println("ParkingDAOMysql parkingDelete()");
		return session.delete("ParkingMapper.parkingDelete", s);
	}

	/**
	 * @author yeahni
	 * @comment 해당 주차장 코드에대한 정보 반환
	 * @param parking_code 해당 주차장 코드
	 * @return Parking 해당 주차장 정보
	 */
	public Parking selectByCode(int parking_code){
		System.out.println("ParkingDAOMysql selectByCode()");
		return session.selectOne("ParkingMapper.selectByCode", parking_code);
	}
	
	/**
	 * @author hawstrike
	 * @comment 해당 위치에 대한 주차장 리스트 반환
	 * @param location 위치정보
	 * @return 해당 위치에 대한 주차장 리스트
	 */
	public List<Parking> selectByLocation(String location) {
		return session.selectList("ParkingMapper.selectByLocation", 
															"%"+location+"%");
	}
	
}
