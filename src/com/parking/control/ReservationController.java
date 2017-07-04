package com.parking.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.parking.dao.ReservationDAOMysql;
import com.parking.vo.Reservation;
import com.parking.vo.Review;

@RestController
@RequestMapping("/")
public class ReservationController {

	@Autowired
	ReservationDAOMysql rDao;
		
	@PostMapping("arinsert")
	public boolean androidReviewDeclaration(@RequestBody Reservation r ){
		System.out.println(r.toString());
		return rDao.insertByR(r);
	}
	
	@PostMapping("archeck")
	public boolean androidReviewCheck(@RequestBody Reservation r){
		System.out.println("archeck");
		System.out.println(r.toString());
		Calendar cal = Calendar.getInstance();
		
		boolean flag = false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date enDate = sdf.parse(r.getReserve_entrance_time());
			Date exDate = sdf.parse(r.getReserve_exit_time());
			cal.setTime(enDate);
			int enDayNum = cal.get(Calendar.DAY_OF_WEEK);
			int exDayNum = cal.get(Calendar.DAY_OF_WEEK);
			
			if(enDayNum == exDayNum){
				if(1<enDayNum && enDayNum<7){
					flag = rDao.selectByWeekDay(r);
					System.out.println(flag);
				}else if (enDayNum==7){
					flag = rDao.selectBySat(r);
				}else if (enDayNum == 1){
					flag = rDao.selectByHoliy(r);
				}
			}else{
				boolean enflag =false;
				boolean exflag = false;
				if(1<enDayNum && enDayNum<7){
					enflag = rDao.selectByWeekDay(r);
				}else if (enDayNum==7){
					enflag = rDao.selectBySat(r);
				}else if (enDayNum == 1){
					enflag = rDao.selectByHoliy(r);
				}
				
				if(1<exDayNum && exDayNum<7){
					exflag = rDao.selectByWeekDay(r);
				}else if (exDayNum==7){
					exflag = rDao.selectBySat(r);
				}else if (exDayNum == 1){
					exflag = rDao.selectByHoliy(r);
				}
				
				if(enflag==true && exflag==true){
					flag = true;
				}
				
			}
			
			if(flag==true && rDao.selectByDuplicate(r)){
				flag = true;
			}else{
				flag = false;
			}
			System.out.println("dupli : " + flag);
			
			if(flag== true && rDao.selectByCur(r)){
				flag = true;
			}else{
				flag = false;
			}
			
			System.out.println("cur : " + flag);
			
			return flag;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
