package com.parking.dao;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.parking.vo.Customer;

@Repository
public class CustomerDAOMysql {
	
	// --건들지 마시오--
	@Autowired
	public SqlSession session;
	
	/**
	 * @author yunmeheo
	 * @comment 회원가입
	 * @param c
	 */
	public void signup(Customer c){
		System.out.println(c);
		session.insert("CustomerMapper.insert",c);
	}
	
	/**
	 * @author yeahni
	 * @comment 해당 고객의 아이디를 가지고 정보 반환
	 * @param c_id 해당 고객의 아이디
	 * @return Customer 해당 고객정보
	 */
	public Customer selectById(String c_id){
		return session.selectOne("CustomerMapper.selectById", c_id);
	}
	
	public List<Customer> selectAll(String sort) {
		System.out.println("CustomerDAOMysql selectAll()");
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if(sort.charAt(sort.length()-1) == 'n') {
			return session.selectList("CustomerMapper.selectByStatus", "n");
		}
		else if(sort.charAt(sort.length()-1) == 'd') {
			return session.selectList("CustomerMapper.selectByStatus", "d");
		}
		else {
			map.put("sort", sort);
			return session.selectList("CustomerMapper.selectAll", sort);
		}
	}
	
	public List<Customer> selectItem(String searchItem, String searchValue) {
		System.out.println("CustomerDAOMysql selectItem()");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchItem", searchItem);
		map.put("searchValue", searchValue);
		
		return session.selectList("CustomerMapper.selectItem", map);
	}
	
	public void updateStatus(String c_id){
		System.out.println("CustomerDAOMysql updateStatus()");
		
		session.update("CustomerMapper.updateStatus", c_id);
	}
	
}
