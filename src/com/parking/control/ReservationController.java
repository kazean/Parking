package com.parking.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.parking.dao.ReservationDAOMysql;

@Controller
public class ReservationController {

	// --건들지 마시오--
	@Autowired
	ReservationDAOMysql rDao;
		
}
