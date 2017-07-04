package com.parking.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarDAOMysql {
	
	// --건들지 마시오--
		@Autowired
		public SqlSession session;
		
		public void selectByCId(){
			
		}
}
