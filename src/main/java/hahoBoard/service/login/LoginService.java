package hahoBoard.service.login;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import hahoBoard.command.login.LoginCommand;
import hahoBoard.command.member.AuthInfo;
import hahoBoard.domain.member.MemberDTO;
import hahoBoard.mapper.MemberMapper;

@Service
@Component
public class LoginService {
	
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	AuthInfo authInfo;

	public String execute(LoginCommand loginCommand, HttpSession session, HttpServletResponse response, Model model) {
		// TODO Auto-generated method stub
		String location = "";
		MemberDTO member = new MemberDTO();
		member.setMemberId(loginCommand.getMemberId());
		List<MemberDTO> lists = memberMapper.selectLoginMember(member);

		
		if(lists.size() == 0) {
			model.addAttribute("valid_user", "아이디가 존재하지 않습니다.");
			location =  "thymeleaf/hahoBoard/login";
		}else {
			member = lists.get(0);
			if(passwordEncoder.matches(loginCommand.getMemberPass(), 
					member.getMemberPass())) {
				authInfo = new AuthInfo(member.getMemberId(), 
						member.getMemberNick(), member.getMemberLv());
				session.setAttribute("authInfo",authInfo);
				Boolean idStore = loginCommand.getIdStore();
				Boolean autologin = loginCommand.getAutoLogin();

				if(autologin != null && autologin == true) {

					Cookie cookie = new Cookie("autoLogin", authInfo.getId());
					cookie.setMaxAge(60*60*24*30);
					response.addCookie(cookie);
				}
				if(idStore != null && idStore == true) {
					Cookie cookie = new Cookie("idStore",authInfo.getId() );
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					response.addCookie(cookie);
				}else {
					Cookie cookie = new Cookie("idStore",authInfo.getId() );
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				
				location ="redirect:/main"; 
				
				
				
				
			}else {
				model.addAttribute("valid_pw", "비밀번호가 틀립니다.");
				location =  "thymeleaf/hahoBoard/login"; //
			}
		}
		return location;
	}

	public String executeAdmin(LoginCommand loginCommand, HttpSession session, HttpServletResponse response,
			Model model) {
		// TODO Auto-generated method stub
		String location = "";
		MemberDTO member = new MemberDTO();
		member.setMemberId(loginCommand.getMemberId());
		List<MemberDTO> lists = memberMapper.selectLoginMember(member);

		
		if(lists.size() == 0) {
			model.addAttribute("valid_user", "아이디가 존재하지 않습니다.");
			location =  "thymeleaf/hahoBoard_admin/login_admin";
		}else {
			member = lists.get(0);
			if(passwordEncoder.matches(loginCommand.getMemberPass(), 
					member.getMemberPass())) {
				authInfo = new AuthInfo(member.getMemberId(), 
						member.getMemberNick(), member.getMemberLv());
				session.setAttribute("authInfo",authInfo);
				if (member.getMemberLv() == 0) {
					model.addAttribute("normal_user", "관리자 권한이 없습니다.");
					location = "thymeleaf/hahoBoard_admin/login_admin";
				}else {
					location ="redirect:/admin/main";
				}
			
				
			}else {
				model.addAttribute("valid_pw", "비밀번호가 틀립니다.");
				location =  "thymeleaf/hahoBoard_admin/login_admin"; //
			}
		}
		return location;
	}

	public void logout(HttpSession session) {
		// TODO Auto-generated method stub
		//session.removeAttribute("authInfo");
		session.invalidate();
	}

	

}
