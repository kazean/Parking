package com.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.parking.dao.AdminDAOMysql;
import com.parking.vo.Admin;

@Service
public class AdminService {
	
	// --건들지 마시오--
	@Autowired
	AdminDAOMysql aDao;
	
	/**
	 * @author yeahni
	 * @comment 로그인 정보 확인
	 * @param admin 받아온 로그인 정보
	 * @return Admin 실제 로그인 정보
	 */
	public Admin login(Admin admin){
		return aDao.selectByIdANDPwd(admin);
	}
	
}
