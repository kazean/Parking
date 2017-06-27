package com.parking.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("areviewlistpcode")
	public List<Review> androidReviewById(String review_parking_code){
		System.out.println("input");
		System.out.println("pcode : " + review_parking_code);
		return rDao.selecyByParkingCode(Integer.parseInt(review_parking_code));
	}
}
