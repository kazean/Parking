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

	@Autowired
	AdminService service;

	@RequestMapping(value="/admin/admin.do", method=RequestMethod.POST)
	public String check(@RequestParam(required = false, defaultValue="")String id, @RequestParam(required = false, defaultValue="")String password, HttpSession session){
		System.out.println("AAA");
		
		if(id != "" && password != ""){
			Admin admin = new Admin(id, password);
			Admin responseAdmin = service.login(admin);
		
			if(responseAdmin.getA_name() != null){
				System.out.println(responseAdmin);
				session.setAttribute("responseAdmin", responseAdmin);
				return "/admin/main.jsp";
			} 
		
		}
		return "/admin/index.jsp";
	}
	
}
