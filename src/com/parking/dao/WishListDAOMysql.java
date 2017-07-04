package com.parking.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.parking.vo.WishList;

@Repository
public class WishListDAOMysql {
	
	// --건들지 마시오--
	@Autowired
	public SqlSession session;

	/**
	 * @author yunmeheo
	 * @comment 해당 고객 아이디에 대한 위시리스트 반환
	 * @param c_id 고객 아이디
	 * @return WishList 고객이 위시한 리스트
	 */
	public List<WishList> selectById(String c_id){
		return session.selectList("WishListMapper.selectById", c_id);
	}
	
	public List<WishList> selectAll() {
		return session.selectList("WishListMapper.selectAll");
	}
	
	public boolean insert(WishList w) {
		int result =  session.insert("WishListMapper.insert", w);
		if(result ==1 ){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean delete(WishList w) {
		int result = session.delete("WishListMapper.delete", w);
		if(result ==1 ){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean validation(WishList w){
		int result = session.selectOne("WishListMapper.selectByCode", w);
		if(result == 1 ){
			return true;
		}else{
			return false;
		}
	}
}
