<<<<<<< HEAD:src/com/parking/dao/AdminDAOMysql.java
package com.parking.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.parking.vo.Admin;

@Repository
public class AdminDAOMysql {

	// --건들지 마시오--
	@Autowired
	SqlSession session;
	
	/**
	 * @author yeahni
	 * @comment 관리자 정보 반환
	 * @param admin 받아온 로그인 정보
	 * @return Admin 관리자 정보
	 */
	public Admin selectByIdANDPwd(Admin admin){
		return session.selectOne("AdminMapper.selectByIdANDPwd", admin);
	}
	
}
=======
package com.parking.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.parking.vo.Admin;

@Repository
public class AdminDAOMysql {

	// --건들지 마시오--
	@Autowired
	SqlSession session;
	
	/**
	 * @author yeahni
	 * @comment 관리자 정보 반환
	 * @param admin 받아온 로그인 정보
	 * @return Admin 관리자 정보
	 */
	public Admin selectByIdANDPwd(Admin admin){
		return session.selectOne("AdminMapper.selectByIdANDPwd", admin);
	}
	
}
>>>>>>> 52c8c8254eed9c3b2027870f354d66ab8a41c19f:src/com/parking/dao/AdminDAOMysql.java
