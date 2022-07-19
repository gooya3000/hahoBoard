package hahoBoard.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import hahoBoard.command.board.CommentCommand;
import hahoBoard.command.member.AuthInfo;
import hahoBoard.domain.board.CommentDTO;
import hahoBoard.mapper.comment.CommentMapper;

@Component
@Service
public class CommentInsertService {
	
	@Autowired
	CommentMapper mapper;

	public void insertComment(CommentCommand command, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		CommentDTO dto = new CommentDTO();
		
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		
		String id = authInfo.getId();
		String ip = request.getRemoteAddr();
		
		dto.setBoardNum(command.getBoardNum());
		dto.setCommentCont(command.getCommentCont());
		dto.setCommentId(id);
		dto.setCommentIpAddr(ip);
		
		mapper.insertComment(dto);
		

		
	}

}
