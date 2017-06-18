package com.parking.dao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.parking.vo.WishList;

@Repository
public class WishListDAOMysql {
	
	@Autowired
	public SqlSession session;

	
	public WishList selectById(String c_id){
		return session.selectOne("WishListMapper.selectById",c_id);
	}
	
	
	
}
