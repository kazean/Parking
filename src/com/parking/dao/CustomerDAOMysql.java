package com.parking.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.parking.vo.Customer;

@Repository
public class CustomerDAOMysql {
	
	@Autowired
	public SqlSession session;
	
	public void signup(Customer c){
		System.out.println(c);
		session.insert("CustomerMapper.insert",c);
	}
	
	public Customer selectById(String c_id){
		return session.selectOne("CustomerMapper.selectById",c_id);
	}
	
	
}
