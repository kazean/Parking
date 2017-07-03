<<<<<<< HEAD
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
=======
package com.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD

=======
>>>>>>> 94b24142d6c86f968cf1d83a87cc1fc3acd2fb4f
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
<<<<<<< HEAD
>>>>>>> fbcfe91e4d507f876cbc0de7b92f0943829fec82
=======
>>>>>>> 52c8c8254eed9c3b2027870f354d66ab8a41c19f
>>>>>>> 94b24142d6c86f968cf1d83a87cc1fc3acd2fb4f
