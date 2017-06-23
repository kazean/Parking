package com.parking.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dao.WishListDAOMysql;
import com.parking.vo.WishList;

@RestController
@RequestMapping("/")
public class WishListController {
	
	@Autowired
	WishListDAOMysql wDao;
	
	@PostMapping("awishlistbyid")
	public List<WishList> androidWishById(String c_id) {
		System.out.println("androidWishListById()");
		return wDao.selectById(c_id);
	}
	
	@PostMapping("awishlistinsert")
	public void androidWishListInsert(WishList w) {
		// 리턴 형식을 void가 아닌 int나 string으로 해서 값이
		// 정상적으로 들어갔다는 것을 표시 해야할듯
		System.out.println("androidWishListAdd()");
		wDao.insert(w);
	}
	
	@PostMapping("awishlistdelete")
	public void androidWishListDelete(WishList w) {
		System.out.println("androidWishListDelete()");
		wDao.delete(w);
	}
}
