package hahoBoard.service.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import hahoBoard.command.member.AuthInfo;
import hahoBoard.domain.board.CommentDTO;
import hahoBoard.mapper.comment.CommentMapper;

@Component
@Service
public class CommentListService {
	
	@Autowired
	CommentMapper mapper;

	public void selectList(int boardNum, Model model, HttpSession session) {
		// TODO Auto-generated method stub
		List<CommentDTO> list = new ArrayList();
		
		list = mapper.selectComments(boardNum);
		
		model.addAttribute("comments", list);
		
//		String nowId ="";
//		AuthInfo info = (AuthInfo) session.getAttribute("authInfo");
//		nowId = info.getId();
//		
//		int nowLv;
//		
//		for (CommentDTO dto : list) {
//			
//			nowLv = dto.getMemberLv();
//			
//			if (dto.getCommentId().equals(nowId)) {
//				model.addAttribute("commentNum", dto.getCommentNum());
//				
//				
//			}
//			
//		}
		
		
		
		
	}

}
