package com.parking.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.parking.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService service;
	
}
