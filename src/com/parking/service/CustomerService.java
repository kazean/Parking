package com.parking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.dao.CustomerDAOMysql;
import com.parking.vo.Customer;

@Service
public class CustomerService {
	@Autowired
	CustomerDAOMysql cDao;

	public List<Customer> selectAll(String sort) {
		return cDao.selectAll(sort);
	}
	
	public Customer selectById(String c_id) {
		return cDao.selectById(c_id);
	}
	
	public List<Customer> cSearch(String searchItem, String searchValue) {
		return cDao.selectItem(searchItem, searchValue);
	}
}
