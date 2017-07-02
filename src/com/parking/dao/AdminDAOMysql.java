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
