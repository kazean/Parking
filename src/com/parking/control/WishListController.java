package com.parking.control;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dao.WishListDAOMysql;
import com.parking.vo.Parking;
import com.parking.vo.WishList;

@RestController
@RequestMapping("/")
public class WishListController {
	
	@Autowired
	WishListDAOMysql wDao;
	
	@PostMapping("awishlistbyid")
	public List<WishList> androidWishById(String c_id) {
		System.out.println("androidWishListById()");
		if(c_id != null){
			System.out.println("c_id : " + c_id);
			List<WishList> list = wDao.selectById(c_id);
			for(WishList wl : list){
				System.out.println(wl);
			}
			return wDao.selectById(c_id);
		}
		return null;
	}
	
	@PostMapping("awishlistinsert")
	public void androidWishListInsert(WishList w) {
		// 리턴 형식을 void가 아닌 int나 string으로 해서 값이
		// 정상적으로 들어갔다는 것을 표시 해야할듯
		System.out.println("androidWishListAdd()");
		System.out.println("w.c_id : " + w.getWish_c_id() + " | w.parking_code : " + w.getWish_parking_code());
		//System.out.println("w.c_id : " + w.getWish_c_id() + " | w.parking_code : " + w.getWish_parking_code() + " | w.date : " + w.getWish_date());
		wDao.insert(w);
	}
	
	@PostMapping("awishlistdelete")
	public void androidWishListDelete(WishList w) {
		System.out.println("androidWishListDelete()");
		System.out.println("w.c_id : " + w.getWish_c_id() + " | w.parking_code : " + w.getWish_parking_code());
		//System.out.println("w.c_id : " + w.getWish_c_id() + " | w.parking_code : " + w.getWish_parking_code() + " | w.date : " + w.getWish_date());
		wDao.delete(w);
	}
}
