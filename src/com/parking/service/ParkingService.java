package com.parking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.parking.dao.AdminDAOMysql;
import com.parking.dao.ParkingDAOMysql;
import com.parking.vo.Parking;

@Service
public class ParkingService {
	
	// --건들지 마시오--
	@Autowired
	ParkingDAOMysql pDao;

	/**
	 * @author yeahni
	 * @comment 전체 주차장 정보 반환
	 * @return Collection<Parking> 주차장 리스트
	 */
	public List<Parking> parkingList(String sort){
		return pDao.selectAll(sort);
	}
	
	public List<Parking> parkingSearch(String searchItem, String searchValue) {
		return pDao.selectItem(searchItem, searchValue);
	}
	
	/**
	 * @author yeahni
	 * @comment 전체 주차장 정보 반환
	 * @return Collection<Parking> 해당 주차장 정보
	 */
	public Parking parkingDetail(int parking_code){
		return pDao.selectByCode(parking_code);
	}
	
	public int parkingAdd(Parking p) {
		return pDao.parkingAdd(p);
	}
	
	public int parkingDelete(String s) {
		return pDao.parkingDelete(s);
	}
	
}
