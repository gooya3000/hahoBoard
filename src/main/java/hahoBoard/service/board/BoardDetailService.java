package hahoBoard.service.board;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import hahoBoard.command.member.AuthInfo;
import hahoBoard.domain.board.BoardDTO;
import hahoBoard.mapper.board.BoardMapper;

@Component
@Service
public class BoardDetailService {
	
	@Autowired
	BoardMapper mapper;

	public void selectOne(int boardNum, Model model, HttpSession session) {
		// TODO Auto-generated method stub
		
		BoardDTO dto = new BoardDTO();
		dto = mapper.selectOne(boardNum);		
		model.addAttribute("detailPost", dto);
		
		String nowId ="";
		AuthInfo info = (AuthInfo) session.getAttribute("authInfo");
		nowId = info.getId();
		
		System.out.println(nowId);
		System.out.println(dto.getBoardId());
		
		if (dto.getBoardId().equals(nowId)) {
			model.addAttribute("writer", "writer");
		}
		
		
	}

}
