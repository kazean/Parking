package com.parking.control;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dao.ReviewListDAOMysql;
import com.parking.vo.Review;

@RestController
@RequestMapping("/")
public class ReviewController {
	
	@Autowired
	ReviewListDAOMysql rDao;
	
	@PostMapping(value="areviewlistpcode", consumes="application/json; charset=UTF-8")
	public List<Review> androidReviewById(@RequestBody Review review){
		System.out.println("areviewlistpcode");
		List<Review> list = rDao.selecyByParkingCode(review.getReview_parking_code());
		return list;
	}

	@PostMapping("areviewdeclaration")
	public boolean androidReviewDeclaration(@RequestBody Review r ){
		System.out.println("areviewdeclaration()");

		return rDao.updateReviewDeclaration(r);
	}
	
	@PostMapping("areviewinsert")
	public boolean androidReviewInsert(@RequestBody Review r){
		System.out.println(r.toString());
		return rDao.insertByReview(r);
	}
	

}