package com.parking.control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parking.service.PartnerService;
import com.parking.vo.Partner;

@Controller
@RequestMapping(value ="/partner/")
public class PartnerController {

	@Autowired
	PartnerService ptnService;
	
	//start for partnerLogin
	@PostMapping("/partnerLogin.do")
  	public String partnerLogin(String p_id , String p_password, Model model,
  			HttpSession session) {
		session.removeAttribute("partner");
		System.out.println("partnerLogin()");
		String msg = "-1";
		Partner partner = null;
		if(!"".equals(p_id) && !"".equals(p_password)) {
			
			try {
				partner = ptnService.selectByP_id(p_id);
				if(p_password.equals(partner.getP_password())){
					msg="1";
				}
				session.setAttribute("partner", partner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		model.addAttribute("msg",msg);
		return "/result.jsp";
	}
	//end for partnerLogin
	
	
}
