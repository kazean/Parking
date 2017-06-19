package com.parking.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.parking.dao.ReservationDAOMysql;

@Controller
public class ReservationController {

	@Autowired
	ReservationDAOMysql dao;
	
	
	
}
