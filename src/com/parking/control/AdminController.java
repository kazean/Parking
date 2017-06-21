package com.parking.control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.service.AdminService;
import com.parking.vo.Admin;

@Controller
public class AdminController {
	
	// --건들지 마시오--
	@Autowired
	AdminService service;
	
	/**
	 * 
	 * @param admin
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/admin/admin.do", method=RequestMethod.POST)
	public String check(@RequestBody Admin admin, HttpSession session){
		if(admin != null){
			Admin responseAdmin = service.login(admin);
		
			if(responseAdmin.getA_id() != null){
				System.out.println(responseAdmin);
				session.setAttribute("responseAdmin", responseAdmin);
				return "/admin/main.jsp";
			} 
		
		}
		return "/admin/default.jsp";
	}
	
}
