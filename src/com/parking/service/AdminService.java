package com.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.dao.AdminDAOMySQL;
import com.parking.vo.Admin;

@Service
public class AdminService {
	
	@Autowired
	AdminDAOMySQL dao;
	
	public Admin login(Admin admin){
		return dao.selectAll(admin);
	}
}
