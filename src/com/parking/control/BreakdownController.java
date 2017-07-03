package com.parking.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dao.BreakDownListDAOMysql;
import com.parking.vo.Breakdown;
import com.parking.vo.Customer;
import com.parking.vo.Reservation;

@RestController
@RequestMapping("/")
public class BreakdownController {
	@Autowired
	BreakDownListDAOMysql bDao;

	@PostMapping("abreakdownlist")
	public List<Breakdown> breakdownList(@RequestBody Reservation r){
		List<Breakdown> list =  bDao.selectById(r);
		List<Breakdown> mData = new ArrayList<>();
		for(Breakdown b : list){
			System.out.println(b.toString());
			boolean flag = bDao.selectByReview(b);
			if(flag){
				b.setReview_check(true);
				System.out.println("true");	
			}
			mData.add(b);
		}
		
		return mData;
	}
	
}
