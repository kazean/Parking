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

	public List<Customer> selectAll() {
		return cDao.selectAll();
	}
}
