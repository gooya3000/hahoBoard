package hahoBoard.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import hahoBoard.domain.member.MemberDTO;
import hahoBoard.mapper.MemberMapper;

@Component
@Service
public class MemberListService {
	
	@Autowired
	MemberMapper mapper;

	public void selectMembers(Model model) {
		// TODO Auto-generated method stub
		
		List<MemberDTO> list = new ArrayList();
		list = mapper.selectMembers();
		
		model.addAttribute("memberList", list);
		
	}

}
