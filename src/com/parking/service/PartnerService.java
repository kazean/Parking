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
	
	//실제 파트너 가져오기
	public List<Partner> selectAll(String sort) {
		return ptnDao.selectAll(sort);
	}

	//검색시 사이즈만 가져오기위한 메서드
	public List<Partner> selectForListSize(String searchItem, String searchValue) {
		return ptnDao.selectForListSize( searchItem,  searchValue);
	}

	public Partner selectByP_id(String p_id) throws Exception {
		Partner partner = ptnDao.selectByP_id(p_id);
		if(partner==null){
			throw new Exception("없는 아이디");
		}else{
			return ptnDao.selectByP_id(p_id);
		}
	}

	public void delete(String p_id) {
		ptnDao.delete(p_id);
	}
	
}
