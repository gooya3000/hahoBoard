package hahoBoard.service.member;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import hahoBoard.command.member.MemberCommand;
import hahoBoard.controller.MailAction;
import hahoBoard.domain.member.MemberDTO;
import hahoBoard.mapper.MemberMapper;


@Service
@Component
public class MemberJoinService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MailAction mailAction;	
	@Autowired
	MemberMapper memberMapper;
	

	public Integer insertMember(MemberCommand memberCommand, Model model) {
		// TODO Auto-generated method stub
		
		//비밀번호 일치여부 체크
		if(!memberCommand.isUserPwEqualToUserPwCon()) {
			model.addAttribute("valid_userPwCon", "*비밀번호가 일치하지 않습니다.");
			System.out.println("비밀번호 불일치");
			return null;
		}
		
		
		
		Integer result=null;
		
		//아이디 중복 체크
		MemberDTO dto = new MemberDTO();
		dto.setMemberId(memberCommand.getMemberId());
		
		MemberDTO dto2 = memberMapper.selectMember(dto);
		System.out.println(dto2);
		
		
		if (dto2 == null) {
			
			//회원정보 입력
			
			
			MemberDTO memberDTO = new MemberDTO();
			
			memberDTO.setMemberPass(passwordEncoder.encode(
					memberCommand.getMemberPass()));
			memberDTO.setMemberId(memberCommand.getMemberId());
			memberDTO.setMemberNick(memberCommand.getMemberNick());
			
			//회원정보 삽입
			try {
				result = memberMapper.insertMember(memberDTO);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//회원가입 인증 이메일 발송
			if(result != null) {
				
				try {
					mailAction.sendMail(memberDTO.getMemberId(), 
							memberDTO.getMemberNick());
					
				} catch (MessagingException e) {
					
					e.printStackTrace();
				}
			}
			
			return result;
			
		}else{
			model.addAttribute("duplicate_userId","*이미 사용중인 아이디입니다.");
			System.out.println("이미 사용중인 아이디");
			return null;
		}
		
	}


	public int joinOkUpdate(String joinOk, String reciver, String memberNick) {
		// TODO Auto-generated method stub
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberJoin(joinOk);
		memberDTO.setMemberId(reciver);
		memberDTO.setMemberNick(memberNick);
		
		return memberMapper.updateJoin(memberDTO);
	}

}
