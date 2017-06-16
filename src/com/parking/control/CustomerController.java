package com.parking.control;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.parking.dao.CustomerDAOMysql;
import com.parking.vo.Customer;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerDAOMysql dao =null;
	
	//존재하는 이이디인지 체크 - 구현중~!
	@RequestMapping(value="checkId.do")
	public String checkId(String c_id, Model model){
		String msg = "1";
		Customer c = dao.selectById(c_id);
		String dbId = null;
		if(c!=null) {
			dbId = c.getC_id();
			if(dbId.equals(c_id)){
			msg = "-1";
			}
		}
		model.addAttribute("msg", msg);
		return "/result.jsp";
	}  // end checkId

	
	//사인업 
	@RequestMapping(value = "/signup.do")
	public String signup(String c_id,
						String c_password,
						String c_name,
						String c_phone_number,
						String c_car_number,
						String c_card_number,
						Model model
						) {
		String msg ="";
		System.out.println("signup");
		Customer c = new Customer(c_id, c_password, c_name, c_phone_number, c_car_number,
				c_card_number, 'N');
		if(c!=null) {
			dao.signup(c);
			msg = "1";
		}
		model.addAttribute("msg",msg);
		return "/result.jsp";
	}
	
	//로그인 
	@RequestMapping(value="/login.do")
	public String login(String c_id,String c_password,Model model,HttpSession session) {
		
		String msg="";
		Customer c;
		
		session.removeAttribute("customer");
		
		if(dao.selectById(c_id)!=null) {
			c = dao.selectById(c_id);
			System.out.println(c);
			if(c.getC_password().equals(c_password)){
				session.setAttribute("customer", c);
				msg="1";
			}
			
		}else {
		msg="-1";
		}
		model.addAttribute("msg",msg);
		return "/result.jsp";
	}//end login
	
	
	//로그아웃
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("customer");
		return "/result.jsp";
	}
}