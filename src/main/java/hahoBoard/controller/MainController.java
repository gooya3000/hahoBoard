package hahoBoard.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hahoBoard.command.login.LoginCommand;
import hahoBoard.command.member.MemberCommand;
import hahoBoard.service.login.LoginService;
import hahoBoard.service.member.MemberJoinService;

@Controller
public class MainController {
	
	@Autowired
	MemberJoinService memberJoinService;
	@Autowired
	LoginService loginService;
	
	
	@RequestMapping(value = "/main")
	public String home(Model model) {	
		LoginCommand loginCommand = new LoginCommand();
		model.addAttribute("loginCommand", loginCommand);
		return "thymeleaf/hahoBoard/login";
	}
	
	
	@RequestMapping(value = "/join")
	public String join(Model model) {	
		MemberCommand memberCommand = new MemberCommand();
		model.addAttribute("memberCommand", memberCommand);
		return "thymeleaf/hahoBoard/join";
	}
	
	
	@RequestMapping(value = "/joinAction", method = RequestMethod.POST )
	public String joinAction( @Validated MemberCommand memberCommand, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "thymeleaf/hahoBoard/join";
		}
		
		Integer i=null;
		
		try {
			i = memberJoinService.insertMember(memberCommand,model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if( i == null) return "thymeleaf/hahoBoard/join";
		return "redirect:/main";
	
	}
	
	@RequestMapping(value = "/memberMail")
	public String memberMail(@RequestParam(value = "num") String joinOk,
			@RequestParam(value = "reciver") String reciver,
			@RequestParam(value = "memberNick") String memberNick) {
		
		int i = memberJoinService.joinOkUpdate(joinOk, reciver, memberNick);
		
		if(i > 0) {
			return "thymeleaf/hahoBoard/success";
		}else {
			return "thymeleaf/hahoBoard/fail";
		}
	}
	
	
	@RequestMapping(value = "/loginAction"  ,method = RequestMethod.POST )
	public String loginAction(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session,
			HttpServletResponse response, Model model) {		
		if (result.hasErrors()) {
			return "thymeleaf/hahoBoard/login";
		}
		String location = loginService.execute(loginCommand, session, response, model);
		return location;
	}
	
	
	@RequestMapping(value = "/logoutqq")
	public String logout(HttpSession session) {		
		loginService.logout(session);
		System.out.println(">>>>>>>>>>>>>>logout ");
		//session.removeAttribute("authInfo");
		return "redirect:/main";
	}
	@RequestMapping(value = "/logout_ad")
	public String logout_ad(HttpSession session) {		
		loginService.logout(session);
		return "redirect:/admin/main";
	}
	

}
