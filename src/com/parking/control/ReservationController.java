package com.parking.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dao.ReservationDAOMysql;
import com.parking.vo.Reservation;

@RestController
@RequestMapping("/")
public class ReservationController {

	@Autowired
	ReservationDAOMysql rDao;
	
	@PostMapping("areservationInsert")
	public boolean androidReviewDeclaration(@RequestBody Reservation r ){
		
		return true;
	}
	
		
}
