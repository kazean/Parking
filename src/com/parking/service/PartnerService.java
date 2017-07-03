package com.parking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.dao.PartnerDAOMysql;
import com.parking.vo.Partner;

@Service
public class PartnerService {

	
	@Autowired
	PartnerDAOMysql ptnDao;
	
	//어드민 페이지에서 파트너 추가하기
	public void addPartner(Partner p){
		
		ptnDao.insert(p);
		
	}
	
	//파트너 가져오기
	public List<Partner> selectAll(){
		
		return ptnDao.selectAll();
	}
	
}
