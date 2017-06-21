package com.parking.dao;
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
	public WishList selectById(String c_id){
		return session.selectOne("WishListMapper.selectById",c_id);
	}
	
}
