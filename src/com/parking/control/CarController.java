package com.parking.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.parking.dao.CarDAOMysql;
import com.parking.dao.CustomerDAOMysql;

@Controller
public class CarController {
	@Autowired
	CarDAOMysql cardDao;
	
	@Autowired
	CustomerDAOMysql cDao;
}
