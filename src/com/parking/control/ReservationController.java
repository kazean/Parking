package com.parking.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dao.ReservationDAOMysql;
import com.parking.vo.Reservation;

@RestController
public class ReservationController {

	// --건들지 마시오--
	@Autowired
	ReservationDAOMysql rDao;
	
	@PostMapping("arinsert")
	public boolean androidReviewDeclaration(@RequestBody Reservation r ){
		System.out.println(r.toString());
		return rDao.insertByR(r);
	}
}
