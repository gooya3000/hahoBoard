package hahoBoard.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import hahoBoard.command.member.MemberCommand;
import hahoBoard.mapper.MemberMapper;

@Component
@Service
public class MemberDelService {
	
	@Autowired
	MemberMapper mapper;

	public void deleteMembers(MemberCommand command) {
		// TODO Auto-generated method stub
		
		System.out.println(command.getMemberDel()[0]);
		
		for (String memberId : command.getMemberDel()) {
			mapper.deleteMember(memberId);
		}
		
		
	}

}
