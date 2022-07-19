package hahoBoard.controller.admin;

import java.lang.reflect.Member;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hahoBoard.command.board.BoardCommand;
import hahoBoard.command.login.LoginCommand;
import hahoBoard.command.member.MemberCommand;
import hahoBoard.service.admin.MemberDelService;
import hahoBoard.service.admin.MemberListService;
import hahoBoard.service.board.BoardDelService;
import hahoBoard.service.board.BoardListService;
import hahoBoard.service.login.LoginService;

@Controller
@RequestMapping(value="/admin")
public class AdminMainController {
	
	@Autowired
	LoginService loginService;
	@Autowired
	MemberListService memberListService;
	@Autowired
	MemberDelService memberDelService;
	@Autowired
	BoardListService boardListService;
	@Autowired
	BoardDelService boardDelService;
	
	@RequestMapping(value = "/main")
	public String home(Model model) {	
		LoginCommand loginCommand = new LoginCommand();
		model.addAttribute("loginCommand", loginCommand);
		return "thymeleaf/hahoBoard_admin/login_admin";
	}
	
	@RequestMapping(value = "/loginAction"  ,method = RequestMethod.POST )
	public String loginAction(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session,
			HttpServletResponse response, Model model) {		
		if (result.hasErrors()) {
			return "thymeleaf/hahoBoard_admin/login_admin";
		}
		String location = loginService.executeAdmin(loginCommand, session, response, model);
		
		return location;
	}
	
	@RequestMapping(value = "/memberList")
	public String memberList(Model model) {	
		memberListService.selectMembers(model);
		return "thymeleaf/hahoBoard_admin/member_list_admin";
	}
	
	@RequestMapping(value = "/memberDelAction")
	public String memberDelAction(MemberCommand command) {	
		memberDelService.deleteMembers(command);
		return "redirect:/admin/memberList";
	}
	
	@RequestMapping(value = "/boardList")
	public String boardList(Model model) {	
		boardListService.selectList(model);
		return "thymeleaf/hahoBoard_admin/board_list_admin";
	}
	
	@RequestMapping(value = "/boardDelAction")
	public String boardDelAction(BoardCommand command) {	
		boardDelService.delPost_admin(command);
		return "redirect:/admin/boardList";
	}
	
	

}
