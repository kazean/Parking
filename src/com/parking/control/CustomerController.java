package com.parking.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parking.dao.CustomerDAOMysql;
import com.parking.dao.ReservationDAOMysql;
import com.parking.vo.Customer;
import com.parking.vo.Reservation;

@Controller
public class CustomerController {
	
	// --건들지 마시오--
	@Autowired
	CustomerDAOMysql cDao;
	
	@Autowired
	ReservationDAOMysql rDao;
	
	/**
	 * @author yunmeheo
	 * @comment 존재하는 이이디인지 체크 [구현중~!]
	 * @param c_id 고객의 아이디 
	 * @param model
	 * @return
	 */ 
	@RequestMapping(value="checkId.do")
	public String checkId(String c_id, Model model){
		System.out.println("c_id : "+c_id);
		String msg = "";
		Customer c = cDao.selectById(c_id);
		String dbId = null;
		if("".equals(c_id)) {
			msg="2";      // 아이디를 확인해주세요
		}else if(c!=null) {
			msg = "-1";   // 다른아이디 사용해주세요.
		}else{
			msg="1";      // 사용가능한 아이디 입니다.
		}
		
		model.addAttribute("msg", msg);
		return "/result.jsp";
	}  // end checkId

	/**
	 * @author yunmeheo
	 * @comment 회원 가입 
	 * @param c_id 고객 아이디
	 * @param c_password 고객 비밀번호
	 * @param c_name 고객 이름
	 * @param c_phone_number 고객 전화번호
	 * @param c_car_number 고객 자동차 번호
	 * @param c_card_number 고객 카드 번호
	 * @param model 보낼 정보
	 * @param session 
	 * @return
	 */
	@RequestMapping(value = "/signup.do")
	public String signup(String c_id,
						String c_password,
						String c_name,
						String c_phone_number,
						String c_car_number,
						String c_card_number,
						Model model,
						HttpSession session
						) {
		String msg ="";
		System.out.println("signup");
		Customer c = new Customer(c_id, c_password, c_name, c_phone_number, c_car_number,
				c_card_number, 'N');
		if(c!=null) {
			cDao.signup(c);
			session.setAttribute("customer", c);
			msg = "1";
		}
		model.addAttribute("msg",msg);
		return "/result.jsp";
	}
	
	/**
	 * @author yunmeheo
	 * @comment 로그인
	 * @param c_id 고객 아이디
	 * @param c_password 고객 비밀번호
	 * @param model 보낼 정보
	 * @param session 로그인 정보
	 * @return String url 
	 */
	@RequestMapping(value="/login.do")
	public String login(String c_id,String c_password,Model model,HttpSession session) {
		
		String msg="";
		Customer c =null;
		List<Reservation> list = null;

		//로그인할 때, 주문내역세션에 저장 & 마이페이지에서 보여주기 위해서 모델에 예약내역 저장
		session.removeAttribute("customer");
		if(cDao.selectById(c_id)!=null) {
			c = cDao.selectById(c_id);
			// list= rDao.selectById(c_id); null 수정중
			
			if(list !=null){
			model.addAttribute("list", list);
			}
			
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
	
	/**
	 * @author yunmeheo 
	 * @comment 로그아웃
	 * @param session 
	 * @return String url
	 */
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session) {
		
		session.removeAttribute("customer");
		return "/result.jsp";
	}
	
	/**
	 * @author yunmeheo
	 * @comment 패스워드 확인
	 * @param c_password 고객 비밀번호
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/checkpassword.do")
	public String checkPassword(String c_password,Model model,HttpSession session) {
		Customer c = (Customer) session.getAttribute("customer");
		
		System.out.println(c_password);
		if(c_password !=null){
			if(c_password.equals(c.getC_password())){
				return "/myinformation.jsp";
			}
		}
		return null;
	}
	
}