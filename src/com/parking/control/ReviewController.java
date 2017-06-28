package com.parking.control;

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
		System.out.println("input");
		System.out.println("pcode : " + review.getReview_parking_code());
		List<Review> list = rDao.selecyByParkingCode(review.getReview_parking_code());
	
		for(Review r: list){
			System.out.println(r.toString());
		}
		return list;
	}
}
